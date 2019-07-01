package com.example.task_native.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {


    @SerializedName("id")
    int id ;
    @SerializedName("avatar_url")
    String avatar_url;
    @SerializedName("login")
    String name;
    @SerializedName("repos_url")
    String repo_url;


    public User(int id, String avatar_url, String name) {
        this.id = id;
        this.avatar_url = avatar_url;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.avatar_url);
        dest.writeString(this.name);
    }

    protected User(Parcel in) {
        this.id = in.readInt();
        this.avatar_url = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };



}
