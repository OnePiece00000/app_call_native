package com.hht;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class FileInfo implements Parcelable {

    public String name;
    public String ctime;
    public int fileSize;

    protected FileInfo(Parcel in) {
        name = in.readString();
        ctime = in.readString();
        fileSize = in.readInt();
    }

    public static final Creator<FileInfo> CREATOR = new Creator<FileInfo>() {
        @Override
        public FileInfo createFromParcel(Parcel in) {
            return new FileInfo(in);
        }

        @Override
        public FileInfo[] newArray(int size) {
            return new FileInfo[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(ctime);
        dest.writeInt(fileSize);
    }

    @NonNull
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("name:"+name+"\n");
        buffer.append("size:"+fileSize+"\n");
        buffer.append("ctime:"+ctime+"\n");
        return buffer.toString();
    }
}

