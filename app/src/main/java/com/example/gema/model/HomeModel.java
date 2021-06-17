package com.example.gema.model;

import java.util.Comparator;

public class HomeModel {

    String DMAC;
    String Nama;
    String RegistrasiWBP;
    String Akses;
    String Gambar;

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

    public String getRegistrasiWBP() {
        return RegistrasiWBP;
    }

    public void setRegistrasiWBP(String registrasiWBP) {
        RegistrasiWBP = registrasiWBP;
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
