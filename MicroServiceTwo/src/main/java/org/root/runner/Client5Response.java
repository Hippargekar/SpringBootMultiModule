package org.root.runner;

import java.io.Serializable;

public class Client5Response<T> implements Serializable {
    private int code;
    private String raw;
    private T data;

    public Client5Response(int code, String raw, T data) {
        this.code = code;
        this.raw = raw;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
