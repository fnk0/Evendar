package com.example.kieran.myapplication.backend;


import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by kieran on 8/5/15.
 */
@Entity
public class Event {
    @Id
    Long id;
    String username;
    String title;
    String date;
    String time;
    boolean isWant;
    int num_fav;
    int num_comments;
    int num_going;
    int num_shares;
    String location;
    String updated_at;
    String date_created;

    public Event() {}



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isWant() {
        return isWant;
    }

    public void setIsWant(boolean isWant) {
        this.isWant = isWant;
    }

    public int getNum_fav() {
        return num_fav;
    }

    public void setNum_fav(int num_fav) {
        this.num_fav = num_fav;
    }

    public int getNum_comments() {
        return num_comments;
    }

    public void setNum_comments(int num_comments) {
        this.num_comments = num_comments;
    }

    public int getNum_going() {
        return num_going;
    }

    public void setNum_going(int num_going) {
        this.num_going = num_going;
    }

    public int getNum_shares() {
        return num_shares;
    }

    public void setNum_shares(int num_shares) {
        this.num_shares = num_shares;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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


}
