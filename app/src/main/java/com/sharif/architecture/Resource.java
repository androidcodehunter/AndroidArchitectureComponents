package com.sharif.architecture;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by User on 6/3/2017.
 */

public class Resource<T> {

    @NonNull
    public final Status status;

    @Nullable
    public final String message;

    public final T data;

    public Resource(Status status, T data, String message){
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(T data){
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String msg, T data){
        return new Resource<>(Status.ERROR, data, msg);
    }

    public static <T> Resource<T> loading(T data){
        return new Resource<>(Status.LOADING, data, null);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }

        if (obj == null || getClass() != obj.getClass()){
            return false;
        }

        Resource<?> resource = (Resource<?>) obj;

        if (status != resource.status){
            return false;
        }

        if (message != null ? !message.equals(resource.message) : resource.message != null){
            return false;
        }
        return data != null ? data.equals(resource.data) : resource.data == null;
    }


    @Override
    public int hashCode() {
        int result = status.hashCode();
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

}
