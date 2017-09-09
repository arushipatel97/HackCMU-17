package com.example.arushi.hackathon;

/**
 * Created by Arushi on 9/9/2017.
 */

/**
 * Created by Pallavi on 9/9/17.
 */

public class Club {

    private String name;
    private String imagePath;

    public Club(String imagePath, String name) {
        this.imagePath = imagePath;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
