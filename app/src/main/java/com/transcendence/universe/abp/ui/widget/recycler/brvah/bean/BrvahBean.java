package com.transcendence.universe.abp.ui.widget.recycler.brvah.bean;

/**
 * Created by joephone on 2017/6/27.
 */

public class BrvahBean {
    private String userName;
    private String userAvatar;
    private String content;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "BrvahBean{" +
                "userName='" + userName + '\'' +
                ", userAvatar='" + userAvatar + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
