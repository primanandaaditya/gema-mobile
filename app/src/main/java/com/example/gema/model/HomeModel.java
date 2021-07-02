package com.example.gema.model;

import java.util.Comparator;

public class HomeModel {

    String dmac;
    String nama;
    String registrasi_WBP;
    String akses;
    String kode_Lokasi;
    String gambar;
    String tgl_Aktivitas;
    String tgl_Pasang;
    String baterai;
    String rssi;


    public String getDmac() {
        return dmac;
    }

    public void setDmac(String dmac) {
        this.dmac = dmac;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getRegistrasi_WBP() {
        return registrasi_WBP;
    }

    public void setRegistrasi_WBP(String registrasi_WBP) {
        this.registrasi_WBP = registrasi_WBP;
    }

    public String getAkses() {
        return akses;
    }

    public void setAkses(String akses) {
        this.akses = akses;
    }

    public String getKode_Lokasi() {
        return kode_Lokasi;
    }

    public void setKode_Lokasi(String kode_Lokasi) {
        this.kode_Lokasi = kode_Lokasi;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getTgl_Aktivitas() {
        return tgl_Aktivitas;
    }

    public void setTgl_Aktivitas(String tgl_Aktivitas) {
        this.tgl_Aktivitas = tgl_Aktivitas;
    }

    public String getTgl_Pasang() {
        return tgl_Pasang;
    }

    public void setTgl_Pasang(String tgl_Pasang) {
        this.tgl_Pasang = tgl_Pasang;
    }

    public String getBaterai() {
        return baterai;
    }

    public void setBaterai(String baterai) {
        this.baterai = baterai;
    }

    public String getRssi() {
        return rssi;
    }

    public void setRssi(String rssi) {
        this.rssi = rssi;
    }

    public static Comparator<HomeModel> getModelComparator() {
        return modelComparator;
    }

    public static void setModelComparator(Comparator<HomeModel> modelComparator) {
        HomeModel.modelComparator = modelComparator;
    }

    //fungsi di bawah ini untuk mengurutkan berdasarkan akses
    public static Comparator<HomeModel> modelComparator = new Comparator<HomeModel>() {
        public int compare(HomeModel s1, HomeModel s2) {
            String dt1 = s1.getAkses().toUpperCase();
            String dt2 = s2.getAkses().toUpperCase();

            //desc order
            return dt2.compareTo(dt1);
        }
    };
}
