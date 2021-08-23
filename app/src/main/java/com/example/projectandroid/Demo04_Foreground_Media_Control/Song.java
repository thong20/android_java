package com.example.projectandroid.Demo04_Foreground_Media_Control;

import java.io.Serializable;

public class Song implements Serializable {

    private String title;
    private String singer;
    private int image;
    private int resource; // file .mp3

    public Song(String title, String single, int image, int resource) {
        this.title = title;
        this.singer = single;
        this.image = image;
        this.resource = resource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSinger() {
        return singer;
    }

    public void setSingle(String single) {
        this.singer = single;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }
}
