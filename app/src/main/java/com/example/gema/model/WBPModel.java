package com.example.gema.model;

import java.util.List;

public class WBPModel {
    boolean status;
    List<String> keterangan;
    List<HomeModel> payload;

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

    public List<HomeModel> getPayload() {
        return payload;
    }

    public void setPayload(List<HomeModel> payload) {
        this.payload = payload;
    }
}
