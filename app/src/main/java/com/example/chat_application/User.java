package com.example.chat_application;

public class User {
    private String id;
    private String name;
    private String email;
    private String imageURL;
    private String status;

    public User(String name, String email, String id, String imageURL, String status) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.imageURL = imageURL;
        this.status = status;
    }
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
