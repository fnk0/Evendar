package com.gabilheri.choresapp.new_event;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.gabilheri.choresapp.BaseFragment;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.feed.FeedActivity;

import butterknife.Bind;
import butterknife.OnClick;

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

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick(R.id.enter_event_name)
    public void saveEventName( View view ) {

    }

    @OnClick(R.id.new_event_time)
    public void saveEventTime( View view ) {

    }

    @OnClick(R.id.new_event_time)
    public void saveEventDate( View view ) {

    }

    @OnClick(R.id.new_event_location)
    public void saveEventLocation( View view ) {

    }

    @OnClick(R.id.new_event_details)
    public void saveEventDetails( View view ) {

    }

    @OnClick(R.id.createEvent)
    public void goToNewEventActivity( View view ) {
        startActivity(new Intent(getActivity(), FeedActivity.class));
    }

    @Override
    public int getLayoutResource() {
        return R.layout.new_event;
    }



}
