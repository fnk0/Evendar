package com.example.kieran.myapplication.backend;


import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;


/**
 * Created by kieran on 8/5/15.
 */

@Entity
public class Favorite {
    @Id
    Long id;

    @Index
    String updatedAt;

    @Index
    String createdAt;

    @Index
    Long eventId;

    @Index
    Long userId;

    public Favorite(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Favorite{" +
                "id=" + id +
                ", updatedAt='" + updatedAt + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", eventId=" + eventId +
                ", userId=" + userId +
                '}';
    }
}
