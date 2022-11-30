package com.example.chat_application.messages;

public class MessagesList {
    private  String name,mobile,lastMessage, profilePic;
    private int unseenMessage;

    public MessagesList(String name, String mobile, String lastMessage,String profilePic, int unseenMessage) {
        this.name = name;
        this.mobile = mobile;
        this.lastMessage = lastMessage;
        this.unseenMessage = unseenMessage;
        this.profilePic = profilePic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public int getUnseenMessage() {
        return unseenMessage;
    }

    public void setUnseenMessage(int unseenMessage) {
        this.unseenMessage = unseenMessage;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}
