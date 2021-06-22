package com.example.gema.model;

import java.util.Comparator;

public class HomeModel {

    String DMAC;
    String Nama;
    String Registrasi_WBP;
    String Akses;
    String Kode_Lokasi;
    String Gambar;
    String Tgl_Aktivitas;
    String Tgl_Pasang;
    String Baterai;
    String RSSI;

    public String getTgl_Aktivitas() {
        return Tgl_Aktivitas;
    }

    public void setTgl_Aktivitas(String tgl_Aktivitas) {
        Tgl_Aktivitas = tgl_Aktivitas;
    }

    public String getTgl_Pasang() {
        return Tgl_Pasang;
    }

    public void setTgl_Pasang(String tgl_Pasang) {
        Tgl_Pasang = tgl_Pasang;
    }

    public String getBaterai() {
        return Baterai;
    }

    public void setBaterai(String baterai) {
        Baterai = baterai;
    }

    public String getRSSI() {
        return RSSI;
    }

    public void setRSSI(String RSSI) {
        this.RSSI = RSSI;
    }

    public String getKode_Lokasi() {
        return Kode_Lokasi;
    }

    public void setKode_Lokasi(String kode_Lokasi) {
        Kode_Lokasi = kode_Lokasi;
    }

    public String getDMAC() {
        return DMAC;
    }

    public void setDMAC(String DMAC) {
        this.DMAC = DMAC;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getRegistrasi_WBP() {
        return Registrasi_WBP;
    }

    public void setRegistrasi_WBP(String registrasi_WBP) {
        Registrasi_WBP = registrasi_WBP;
    }

    public String getAkses() {
        return Akses;
    }

    public void setAkses(String akses) {
        Akses = akses;
    }

    public String getGambar() {
        return Gambar;
    }

    public void setGambar(String gambar) {
        Gambar = gambar;
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
