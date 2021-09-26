package com.example.motionlayout.Demo05_Album_Photo_with_RecyclerView;

public class POJOPerson {

    String name;
    String photo;

    public POJOPerson(String name, String photo) {
        this.name = name;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
