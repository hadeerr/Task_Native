package com.example.task_native.model;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Repository implements Parcelable {


    @SerializedName("id")
    int id;
    @SerializedName("node_id")
    String node_id;
    @SerializedName("full_name")
    String full_name;
    @SerializedName("private")
    boolean Private ;
    @SerializedName("owner")
    User owner;
    @SerializedName("forks_count")
    String fork_count;
    @SerializedName("language")
    String language;
    @SerializedName("default_branch")
    String default_branch;

    public Repository(int id, String node_id, String full_name, boolean aPrivate, User owner) {
        this.id = id;
        this.node_id = node_id;
        this.full_name = full_name;
        Private = aPrivate;
        this.owner = owner;
    }

    public Repository() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getFull_name() {
        return full_name;
    }



    public User getOwner() {
        return owner;
    }



    public String getFork_count() {
        return fork_count;
    }


    public String getLanguage() {
        return language;
    }

    public String getDefault_branch() {
        return default_branch;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.node_id);
        dest.writeString(this.full_name);
        dest.writeByte(this.Private ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.owner, flags);
        dest.writeString(this.fork_count);
        dest.writeString(this.language);
        dest.writeString(this.default_branch);
    }

    protected Repository(Parcel in) {
        this.id = in.readInt();
        this.node_id = in.readString();
        this.full_name = in.readString();
        this.Private = in.readByte() != 0;
        this.owner = in.readParcelable(User.class.getClassLoader());
        this.fork_count = in.readString();
        this.language = in.readString();
        this.default_branch = in.readString();
    }

    public static final Parcelable.Creator<Repository> CREATOR = new Parcelable.Creator<Repository>() {
        @Override
        public Repository createFromParcel(Parcel source) {
            return new Repository(source);
        }

        @Override
        public Repository[] newArray(int size) {
            return new Repository[size];
        }
    };

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
