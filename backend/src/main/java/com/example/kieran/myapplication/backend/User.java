package com.example.kieran.myapplication.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by kieran on 8/5/15.
 */

@Entity
public class User {
    @Id
    Long id;
    String full_name;
    String username;
    String email;
    String date_registered;
    String facebook_username;
    String twitter_username;
    String google_username;
    int num_events;
    int num_favorites;
    String updated_at;
    String date_created;
    String pic_URL;

    public User(){}

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getFull_name(){
        return full_name;
    }

    public void setFull_name(String full_name){
        this.full_name = full_name;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getDate_registered(){
        return date_registered;
    }

    public void setDate_registered(String date_registered) {
        this.date_registered = date_registered;
    }

    public String getFacebook_username(){
        return facebook_username;
    }

    public void setFacebook_username(String facebook_username) {
        this.facebook_username = facebook_username;
    }

    public String getTwitter_username() {
        return twitter_username;
    }

    public void setTwitter_username(String twitter_username) {
        this.twitter_username = twitter_username;
    }

    public String getGoogle_username() {
        return google_username;
    }

    public void setGoogle_username(String google_username) {
        this.google_username = google_username;
    }

    public int getNum_events() {
        return num_events;
    }

    public void setNum_events(int num_events) {
        this.num_events = num_events;
    }

    public int getNum_favorites() {
        return num_favorites;
    }

    public void setNum_favorites(int num_favorites) {
        this.num_favorites = num_favorites;
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

    public String getPic_URL() {
        return pic_URL;
    }

    public void setPic_URL(String pic_URL) {
        this.pic_URL = pic_URL;
    }
}
