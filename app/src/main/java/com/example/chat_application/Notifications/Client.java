package com.example.chat_application.Notifications;

public class Client {
    private String client_id;
    private String client_name;
    private String client_email;
    private String client_password;
    private String client_phone;
    private String client_address;
    private String client_image;
    private String client_status;
    private String client_token;

    public Client(String client_id, String client_name, String client_email, String client_password, String client_phone, String client_address, String client_image, String client_status, String client_token) {
        this.client_id = client_id;
        this.client_name = client_name;
        this.client_email = client_email;
        this.client_password = client_password;
        this.client_phone = client_phone;
        this.client_address = client_address;
        this.client_image = client_image;
        this.client_status = client_status;
        this.client_token = client_token;
    }

    public Client() {
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_email() {
        return client_email;
    }

    public void setClient_email(String client_email) {
        this.client_email = client_email;
    }

    public String getClient_password() {
        return client_password;
    }

    public void setClient_password(String client_password) {
        this.client_password = client_password;
    }

    public String getClient_phone() {
        return client_phone;
    }

    public void setClient_phone(String client_phone) {
        this.client_phone = client_phone;
    }

    public String getClient_address() {
        return client_address;
    }

    public void setClient_address(String client_address) {
        this.client_address = client_address;
    }

    public String getClient_image() {
        return client_image;
    }

    public void setClient_image(String client_image) {
        this.client_image = client_image;
    }

    public String getClient_status() {
        return client_status;
    }

    public void setClient_status(String client_status) {
        this.client_status = client_status;
    }

    public String getClient_token() {
        return client_token;
    }

    public void setClient_token(String client_token) {
        this.client_token = client_token;
    }
}
