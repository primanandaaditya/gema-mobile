package com.example.gema.ui.jumlahdilokasi;

import com.androidnetworking.error.ANError;
import com.example.gema.model.BaseRespon;
import com.example.gema.model.JumlahDilokasiModel;

import java.util.List;

public interface IJumlahDilokasiRespon {

    void onJumlahSukses(BaseRespon<List<JumlahDilokasiModel>> respon);
    void onJumlahGagal(ANError anError);

}
