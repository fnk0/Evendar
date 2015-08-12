package com.gabilheri.choresapp.data.models;



/**
 * Created by kieran on 8/5/15.
 */


public class Friendship {

    Long id;

    String updatedAt;

    String createdAt;

    Long userId1;

    Long userId2;

    public Friendship(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUserId1() {
        return userId1;
    }

    public void setUserId1(Long userId1) {
        this.userId1 = userId1;
    }

    public Long getUserId2() {
        return userId2;
    }

    public void setUserId2(Long userId2) {
        this.userId2 = userId2;
    }

    @Override
    public String toString() {
        return "Friendship{" +
                "id=" + id +
                ", updatedAt='" + updatedAt + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", userId1=" + userId1 +
                ", userId2=" + userId2 +
                '}';
    }
}
