package com.gabilheri.choresapp.new_event;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;

import com.gabilheri.choresapp.BaseFragment;
import com.gabilheri.choresapp.ChoresApp;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.data.ChoresContract;
import com.gabilheri.choresapp.data.models.Event;
import com.gabilheri.choresapp.data.models.User;
import com.gabilheri.choresapp.feed.FeedActivity;
import com.gabilheri.choresapp.sync.ChoresSyncAdapter;
import com.gabilheri.choresapp.utils.DialogUtils;
import com.gabilheri.choresapp.utils.QueryUtils;

import org.joda.time.LocalDateTime;

import butterknife.Bind;
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
public class NewEventFragment extends BaseFragment {

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
    public int getLayoutResource() {
        return R.layout.new_event;
    }



}
