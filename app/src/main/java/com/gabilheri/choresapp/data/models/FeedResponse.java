package com.gabilheri.choresapp.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 8/11/15.
 */
public class FeedResponse {

    @SerializedName("items")
    List<Event> items;

    public FeedResponse() {
    }

    public List<Event> getItems() {
        return items;
    }

    public FeedResponse setItems(List<Event> items) {
        this.items = items;
        return this;
    }
}
