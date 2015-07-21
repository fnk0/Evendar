package com.gabilheri.choresapp.new_event;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gabilheri.choresapp.BaseFragment;
import com.gabilheri.choresapp.R;

import butterknife.Bind;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/20/15.
 */
public class NewEventFragment extends BaseFragment {

    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.new_event;
    }


}
