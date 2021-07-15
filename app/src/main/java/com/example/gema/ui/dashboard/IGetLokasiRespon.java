package com.example.gema.ui.dashboard;

import com.androidnetworking.error.ANError;
import com.example.gema.model.GetLokasiModel;
import com.example.gema.model.*;

import java.util.List;

public interface IGetLokasiRespon {
    void onGetLokasiSukses(BaseRespon<List<GetLokasiModel>> models);
    void onGetLokasiGagal(ANError anError);
}
