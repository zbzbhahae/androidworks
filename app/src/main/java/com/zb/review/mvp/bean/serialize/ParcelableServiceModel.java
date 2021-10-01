package com.zb.review.mvp.bean.serialize;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableServiceModel implements Parcelable {
    private String msg;
    private int state;

    public ParcelableServiceModel(String str, int state) {
        this.msg = str;
        this.state = state;
    }

    protected ParcelableServiceModel(Parcel in) {
        msg = in.readString();
        state = in.readInt();
    }

    public static final Creator<ParcelableServiceModel> CREATOR = new Creator<ParcelableServiceModel>() {
        @Override
        public ParcelableServiceModel createFromParcel(Parcel in) {
            return new ParcelableServiceModel(in);
        }

        @Override
        public ParcelableServiceModel[] newArray(int size) {
            return new ParcelableServiceModel[size];
        }
    };

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(msg);
        dest.writeInt(state);
    }
}
