package com.example.gema.ui.home;

import com.androidnetworking.error.ANError;
import com.example.gema.model.BaseRespon;
import com.example.gema.model.HomeModel;

import java.util.List;

public interface IHomeRespon {

    void onSukses(BaseRespon<List<HomeModel>> respon);
    void onGagal(ANError error);

}
