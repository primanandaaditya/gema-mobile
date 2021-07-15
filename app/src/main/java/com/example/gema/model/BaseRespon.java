package com.example.gema.model;

import java.util.List;

public class BaseRespon<T> {

    boolean status;
    List<String> keterangan;
    T payload;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<String> getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(List<String> keterangan) {
        this.keterangan = keterangan;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}
