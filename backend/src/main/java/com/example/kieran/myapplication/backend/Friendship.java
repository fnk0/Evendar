package com.example.kieran.myapplication.backend;


import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;


/**
 * Created by kieran on 8/5/15.
 */

@Entity
public class Friendship {
    @Id
    Long id;
    String updated_at;
    String date_created;
    Long user_id1;
    Long user_id2;

    public Friendship(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public Long getUser_id1() {
        return user_id1;
    }

    public void setUser_id1(Long user_id1) {
        this.user_id1 = user_id1;
    }

    public Long getUser_id2() {
        return user_id2;
    }

    public void setUser_id2(Long user_id2) {
        this.user_id2 = user_id2;
    }
}
