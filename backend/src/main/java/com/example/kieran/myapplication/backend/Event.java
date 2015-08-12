package com.example.kieran.myapplication.backend;


import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * Created by kieran on 8/5/15.
 */
@Entity
public class Event {

    @Id
    Long id;

    @Index
    String username;

    @Index
    String title;

    @Index
    String date;

    @Index
    String time;

    @Index
    boolean isWant;

    int numFavorites;
    int numComments;
    int numGoing;
    int numShares;
    String description;

    String location;

    @Index
    String updatedAt;

    @Index
    String createdAt;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumFavorites() {
        return numFavorites;
    }

    public Event setNumFavorites(int numFavorites) {
        this.numFavorites = numFavorites;
        return this;
    }

    public int getNumComments() {
        return numComments;
    }

    public Event setNumComments(int numComments) {
        this.numComments = numComments;
        return this;
    }

    public int getNumGoing() {
        return numGoing;
    }

    public Event setNumGoing(int numGoing) {
        this.numGoing = numGoing;
        return this;
    }

    public int getNumShares() {
        return numShares;
    }

    public Event setNumShares(int numShares) {
        this.numShares = numShares;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public Event setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Event setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Event setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", isWant=" + isWant +
                ", numFavorites=" + numFavorites +
                ", numComments=" + numComments +
                ", numGoing=" + numGoing +
                ", numShares=" + numShares +
                ", location='" + location + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
