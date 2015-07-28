package com.gabilheri.choresapp;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gabilheri.choresapp.ui.DividerItemDecorator;

import butterknife.Bind;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/21/15.
 */
public abstract class BaseListFragment extends BaseFragment {

    @Bind(R.id.recyclerview)
    protected RecyclerView recyclerView;

    @Override
    public int getLayoutResource() {
        return R.layout.list_fragment;
    }

    protected void initBaseList(RecyclerView.Adapter adapter) {
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 1));
        recyclerView.addItemDecoration(new DividerItemDecorator(recyclerView.getContext(), null));
        recyclerView.setAdapter(adapter);
    }

    protected void initCardsList(RecyclerView.Adapter adapter) {
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 1));
        recyclerView.setAdapter(adapter);
    }
}
