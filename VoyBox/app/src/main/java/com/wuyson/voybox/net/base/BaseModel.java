package com.wuyson.voybox.net.base;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.BufferedSource;

public class BaseModel implements Parcelable {
    private boolean error;
    private List<ImageModel> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ImageModel> getResults() {
        return results;
    }

    public void setResults(List<ImageModel> results) {
        this.results = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.error ? (byte) 1 : (byte) 0);
        dest.writeList(this.results);
    }

    protected BaseModel(Parcel in) {
        this.error = in.readByte() != 0;
        this.results = new ArrayList<ImageModel>();
        in.readList(this.results, ImageModel.class.getClassLoader());
    }

    public static final Parcelable.Creator<BaseModel> CREATOR = new Parcelable.Creator<BaseModel>() {
        @Override
        public BaseModel createFromParcel(Parcel source) {
            return new BaseModel(source);
        }

        @Override
        public BaseModel[] newArray(int size) {
            return new BaseModel[size];
        }
    };

}
