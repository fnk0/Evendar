package com.gabilheri.choresapp.detail_event;

import android.os.Bundle;
import android.view.View;

import com.gabilheri.choresapp.BaseListFragment;
import com.gabilheri.choresapp.MockUtils;
import com.gabilheri.choresapp.adapters.CommentsAdapter;
import com.gabilheri.choresapp.adapters.ItemCallback;
import com.gabilheri.choresapp.models.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/21/15.
 */
public class CommentsFragment extends BaseListFragment implements ItemCallback {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Comment> commentList = new ArrayList<>();
        int listSize = new Random().nextInt(50) + 20;
        for(int i = 0; i < listSize; i++) {
            commentList.add(MockUtils.getRandomComment());
        }

        CommentsAdapter adapter = new CommentsAdapter(commentList, this, getActivity());
        initCardsList(adapter);
    }

    @Override
    public void onItemClick(View view) {

    }
}
