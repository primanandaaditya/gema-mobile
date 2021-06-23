package com.example.gema.helper;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.VibrationEffect;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.androidnetworking.error.ANError;
import com.example.gema.R;
import com.example.gema.model.BaseRespon;
import com.example.gema.model.HomeModel;
import com.example.gema.ui.home.HomeController;
import com.example.gema.ui.home.IHomeRespon;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Stream;


public class AlarmReceiver extends BroadcastReceiver implements IHomeRespon {

    Calendar calendar = Calendar.getInstance();

    int random = Konstanta.RANDOM_NOTIF_ID;
    String CHANNEL_ID = Konstanta.CHANNEL_ID;

    //untuk tembak endpoint
    NotifController notifController;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(calendar.getTime().toString(),"Ulang");

        //init tembak endpoint
        notifController=new NotifController(context, this);
        notifController.get();
    }


    @Override
    public void onSukses(BaseRespon<List<HomeModel>> respon) {


        //cari yang nilai aksesnya =3
        Stream<HomeModel> k = respon.getPayload().stream().filter(x -> x.getAkses().equals(Konstanta.AKSES_MERAH));

        //jika jumlahnya lebih besar/sama dengan 1
        //nyalakan alarm

        if (k.count() >= 1){

            //jika ada orang yang melanggar, maka munculkan notifikasi di HP
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence name = CHANNEL_ID;
                String description = "Basic";
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
                channel.setDescription(description);
                // Register the channel with the system; you can't change the importance
                // or other notification behaviors after this
                NotificationManager notificationManager = notifController.getContext().getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(channel);
            }


            //untuk memunculkan notif di HP
            NotificationCompat.Builder builder = new NotificationCompat.Builder(notifController.getContext(), CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_smartwatch)
                    .setContentTitle("Notifikasi")
                    .setContentText("Ada orang terdeteksi melanggar zona lokasi")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(notifController.getContext());
            notificationManager.notify(random, builder.build());

        }

    }

    @Override
    public void onGagal(ANError error) {
        Log.d("Error", error.getMessage());
    }
}

