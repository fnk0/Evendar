package com.gabilheri.choresapp.new_event;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.gabilheri.choresapp.BaseFragment;
import com.gabilheri.choresapp.ChoresApp;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.adapters.PlaceAutocompleteAdapter;
import com.gabilheri.choresapp.data.ChoresContract;
import com.gabilheri.choresapp.data.models.Event;
import com.gabilheri.choresapp.data.models.User;
import com.gabilheri.choresapp.feed.FeedActivity;
import com.gabilheri.choresapp.sync.ChoresSyncAdapter;
import com.gabilheri.choresapp.utils.DialogUtils;
import com.gabilheri.choresapp.utils.QueryUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.GeoDataApi;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.PlaceDetectionApi;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;

import org.joda.time.LocalDateTime;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/20/15.
 */
public class NewEventFragment extends BaseFragment
        implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

    @Bind(R.id.createEvent)
    Button createEventButton;

    @Bind(R.id.enter_event_name)
    EditText enterEventName;

    @Bind(R.id.new_event_time)
    TimePicker newEventTime;

    @Bind(R.id.new_event_date)
    DatePicker newEventDate;

    @Bind(R.id.new_event_location)
    EditText newEventLocation;

    @Bind(R.id.new_event_details)
    EditText newEventDetails;

    @Bind(R.id.radio_button_going)
    RadioButton radioButtonGoing;

    @Bind(R.id.radio_button_wants)
    RadioButton radioButtonWants;

    @Bind(R.id.autoCompleteTextView)
    AutoCompleteTextView mAutocompleteView;

    @Bind(R.id.place_attribution)
    TextView mPlaceDetailsText ;

    @Bind(R.id.place_details)
    TextView mPlaceDetailsAttribution;

    protected GoogleApiClient mGoogleApiClient;

    private PlaceAutocompleteAdapter mAdapter;

    static String TAG = "hello";

    //private AutoCompleteTextView mAutocompleteView;

//    private TextView mPlaceDetailsText;
//
//    private TextView mPlaceDetailsAttribution;

    private static final LatLngBounds BOUNDS_GREATER_SYDNEY = new LatLngBounds(
            new LatLng(-34.041458, 150.790100), new LatLng(-33.682247, 151.383362));

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick(R.id.createEvent)
    public void goToNewEventActivity(View view ) {
        // save details

        String eventTitle = enterEventName.getText().toString();

        if(!eventTitle.isEmpty()) {

            Event newEvent = new Event();
            newEvent.setTitle(eventTitle);
            newEvent.setLocation(newEventLocation.getText().toString());
            newEvent.setIsWant(radioButtonWants.isChecked());
            newEvent.setDescription(newEventDetails.getText().toString());
            newEvent.setTime(newEventTime.getCurrentHour() + ":" + newEventTime.getCurrentMinute());

            String date = newEventDate.getMonth() + "-" + newEventDate.getDayOfMonth() + "-" + newEventDate.getYear();
            String now = String.valueOf(LocalDateTime.now().toDate().getTime());
            newEvent.setDate(date);
            newEvent.setCreatedAt(now);
            newEvent.setUpdatedAt(now);
            User user = QueryUtils.getAuthenticatedUserFromDB();
            newEvent.setUsername(user.getUsername());

            ChoresApp.instance().getApi().insertEvent(newEvent)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Event>() {
                        @Override
                        public void onCompleted() {
                            unsubscribe();
                        }

                        @Override
                        public void onError(Throwable e) {
                            DialogUtils.showErrorDialog(getActivity(), null, null);
                            Log.e("NewEventFragment", "onError", e);
                        }

                        @Override
                        public void onNext(Event event) {
                            ContentValues values = Event.toContentValues(event);
                            getActivity().getContentResolver().insert(ChoresContract.EventEntry.buildEventUri(), values);
                            ChoresSyncAdapter.syncImmediately(getActivity());
                            if(event.getTitle() != null) {
                                startActivity(new Intent(getActivity(), FeedActivity.class));
                            } else {
                                DialogUtils.showErrorDialog(getActivity(), null, null);
                            }
                        }
                    });
        } else {
            DialogUtils.showErrorDialog(getActivity(), "No Event Title", "Please enter an event title when creating a new event");
        }
    }

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);

        mGoogleApiClient = new GoogleApiClient
                .Builder(getActivity())
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View returnView = super.onCreateView(inflater, container, savedInstanceState);

        // Register a listener that receives callbacks when a suggestion has been selected
        mAutocompleteView.setOnItemClickListener(mAutocompleteClickListener);

        // Retrieve the TextViews that will display details and attributions of the selected place.
//        mPlaceDetailsText = (TextView) super.getView().findViewById(R.id.list_item);
//        mPlaceDetailsAttribution = (TextView) super.getView().findViewById(R.id.place_attribution);

        // Set up the adapter that will retrieve suggestions from the Places Geo Data API that cover
        // the entire world.
        mAdapter = new PlaceAutocompleteAdapter(getActivity(), android.R.layout.simple_list_item_1,
                mGoogleApiClient, BOUNDS_GREATER_SYDNEY, null);
        mAutocompleteView.setAdapter(mAdapter);

        // Set up the 'clear text' button that clears the text in the autocomplete view
//        Button clearButton = (Button) getView().findViewById(R.id.button_clear);
//        clearButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mAutocompleteView.setText("");
//            }
//        });

        return returnView;
    }

    private AdapterView.OnItemClickListener mAutocompleteClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            /*
             Retrieve the place ID of the selected item from the Adapter.
             The adapter stores each Place suggestion in a PlaceAutocomplete object from which we
             read the place ID.
              */
            final PlaceAutocompleteAdapter.PlaceAutocomplete item = mAdapter.getItem(position);
            final String placeId = String.valueOf(item.placeId);
            Log.i(TAG, "Autocomplete item selected: " + item.description);

            /*
             Issue a request to the Places Geo Data API to retrieve a Place object with additional
              details about the place.
              */
            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                    .getPlaceById(mGoogleApiClient, placeId);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);

            Toast.makeText(getActivity(), "Clicked: " + item.description,
                    Toast.LENGTH_SHORT).show();
            Log.i(TAG, "Called getPlaceById to get Place details for " + item.placeId);
        }
    };


    @Override
    public void onStart() {
        super.onStart();
        if(mGoogleApiClient != null && !mGoogleApiClient.isConnected() ) {
            mGoogleApiClient.connect();
        }
    }
    @Override
    public void onStop() {
        if(mGoogleApiClient != null && mGoogleApiClient.isConnected() ) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.new_event;
    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }


    /**
     * Callback for results from a Places Geo Data API query that shows the first place result in
     * the details view on screen.
     */
    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback
            = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(PlaceBuffer places) {
            if (!places.getStatus().isSuccess()) {
                // Request did not complete successfully
                Log.e(TAG, "Place query did not complete. Error: " + places.getStatus().toString());
                places.release();
                return;
            }
            // Get the Place object from the buffer.
            final Place place = places.get(0);

            // Format details of the place for display and show it in a TextView.
            mPlaceDetailsText.setText(formatPlaceDetails(getResources(), place.getName(),
                    place.getId(), place.getAddress(), place.getPhoneNumber(),
                    place.getWebsiteUri()));

            // Display the third party attributions if set.
            final CharSequence thirdPartyAttribution = places.getAttributions();
            if (thirdPartyAttribution == null) {
                mPlaceDetailsAttribution.setVisibility(View.GONE);
            } else {
                mPlaceDetailsAttribution.setVisibility(View.VISIBLE);
                mPlaceDetailsAttribution.setText(Html.fromHtml(thirdPartyAttribution.toString()));
            }

            Log.i(TAG, "Place details received: " + place.getName());

            places.release();
        }
    };

    private static Spanned formatPlaceDetails(Resources res, CharSequence name, String id,
                                              CharSequence address, CharSequence phoneNumber, Uri websiteUri) {
       Log.e(TAG, res.getString(R.string.place_details, name, id, address, phoneNumber,
                websiteUri));
        return Html.fromHtml(res.getString(R.string.place_details, name, id, address, phoneNumber,
                websiteUri));

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

       Log.e(TAG, "onConnectionFailed: ConnectionResult.getErrorCode() = "
                + connectionResult.getErrorCode());

        Toast.makeText(getActivity(),
                "Could not connect to Google API Client: Error " + connectionResult.getErrorCode(),
                Toast.LENGTH_SHORT).show();
    }
}
