package com.example.kieran.myapplication.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * Created by kieran on 8/5/15.
 */

@Entity
public class User {

    @Id
    Long id;

    @Index
    String username;

    @Index
    String fullName;

    @Index
    String email;

    @Index
    String dateRegistered;

    @Index
    String facebookUsername;

    @Index
    String twitterUsername;

    @Index
    String googleUsername;

    String picUrl;

    int numEvents;

    int numFavorites;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public User setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getDateRegistered() {
        return dateRegistered;
    }

    public User setDateRegistered(String dateRegistered) {
        this.dateRegistered = dateRegistered;
        return this;
    }

    public String getFacebookUsername() {
        return facebookUsername;
    }

    public User setFacebookUsername(String facebookUsername) {
        this.facebookUsername = facebookUsername;
        return this;
    }

    public String getTwitterUsername() {
        return twitterUsername;
    }

    public User setTwitterUsername(String twitterUsername) {
        this.twitterUsername = twitterUsername;
        return this;
    }

    public String getGoogleUsername() {
        return googleUsername;
    }

    public User setGoogleUsername(String googleUsername) {
        this.googleUsername = googleUsername;
        return this;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public User setPicUrl(String picUrl) {
        this.picUrl = picUrl;
        return this;
    }

    public int getNumEvents() {
        return numEvents;
    }

    public User setNumEvents(int numEvents) {
        this.numEvents = numEvents;
        return this;
    }

    public int getNumFavorites() {
        return numFavorites;
    }

    public User setNumFavorites(int numFavorites) {
        this.numFavorites = numFavorites;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", dateRegistered='" + dateRegistered + '\'' +
                ", facebookUsername='" + facebookUsername + '\'' +
                ", twitterUsername='" + twitterUsername + '\'' +
                ", googleUsername='" + googleUsername + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", numEvents=" + numEvents +
                ", numFavorites=" + numFavorites +
                '}';
    }
}
