package com.gabilheri.choresapp.models;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/20/15.
 */
public class User {

    String name;
    String userPicture;
    int socialMedia;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(String userPicture) {
        this.userPicture = userPicture;
    }

    public int getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(int socialMedia) {
        this.socialMedia = socialMedia;
    }
}
