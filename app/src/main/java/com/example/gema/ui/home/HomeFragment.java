package com.example.gema.ui.home;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.gema.R;
import com.example.gema.adapter.HomeAdapter;
import com.example.gema.model.BaseRespon;
import com.example.gema.model.HomeModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class HomeFragment extends Fragment implements IHomeRespon {

    ListView listView;
    MediaPlayer mediaPlayer;
    HomeController homeController;
    HomeAdapter homeAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //init bunyi alarm
        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.alarm);

        //init listview
        listView=(ListView)getActivity().findViewById(R.id.lv);

        //init listview+ adapter
        homeAdapter=new HomeAdapter(getActivity(), null);
        listView.setAdapter(homeAdapter);

        //mulai akses endpoint
        homeController=new HomeController(getActivity(), this);
        homeController.get();

    }


    @Override
    public void onDetach() {
        super.onDetach();


        //stop alarm kalau screen tdk fokus
       homeController.stopAlarm();
    }

    @Override
    public void onStop() {
        super.onStop();

        //stop alarm kalau screen tdk fokus
        homeController.stopAlarm();
    }


    @Override
    public void onSukses(BaseRespon<List<HomeModel>> respon) {

        //isi listview
//        homeAdapter=new HomeAdapter(getActivity(), respon.getPayload());

        homeAdapter.setHomeModels(respon.getPayload());
        homeAdapter.notifyDataSetChanged();

        //jika sukses, hit endpoint lagi sesudah 2,5 detik
        //jadi proses ini dilakukan berulang-ulang hanya dari kode dibawah
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                //tunggu 2,5 detik
                //baru hit endpoint lagi
                homeController.get();

            }
        }, 2500);


    }

    @Override
    public void onGagal(ANError error) {

        //jika error tampilkan toast dan matikan alarm
        Log.d("Error", error.getMessage());
//        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
        homeController.stopAlarm();

        //jika gagal, hit endpoint lagi sesudah 3 detik
        //jadi proses ini dilakukan berulang-ulang hanya dari kode dibawah
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                //tunggu 3 detik
                //baru hit endpoint lagi
                homeController.get();

            }
        }, 5000);

    }
}