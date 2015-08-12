package com.gabilheri.choresapp.data.models;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 8/12/15.
 */
public class Favorite {

    Long id;

    String updatedAt;

    String createdAt;

    Long eventId;

    Long userId;

    public Favorite() {
    }

    public Long getId() {
        return id;
    }

    public Favorite setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public Favorite setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Favorite setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Long getEventId() {
        return eventId;
    }

    public Favorite setEventId(Long eventId) {
        this.eventId = eventId;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public Favorite setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
}
