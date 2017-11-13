package com.example.desarrollo_elevation.babysec;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;

import com.example.desarrollo_elevation.viveelite.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Desarrollo on 03/06/2017.
 */

public class AlarmReceiver extends android.content.BroadcastReceiver {

    @Override
    public void onReceive(android.content.Context context, android.content.Intent intent) {
        sendNotification(context, intent);
    }

    public void sendNotification(Context context, Intent intent)
    {
        Bundle bundle = intent.getExtras();
        long id = bundle.getLong("idevent");
        String date = bundle.getString("date");
        String title = bundle.getString("title");
        //Log.d("DATE>>>>", date);
        /*Date firstdate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
        try {
            firstdate = format.parse(date);
            System.out.println(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Date seconddate = new Date();


        long diff =  seconddate.getTime() - firstdate.getTime();
        System.out.println(diff);
        long seconds = diff / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        long week = days / 7;

        String text = "";
        if(week>1)
        {
            text = week+" semanas";
        }
        else
        {
            text = week+" semana";
        }*/

        /*DBHome db = new DBHome(context);
        model_events e ;
        e=db.getEvent(((int) id));*/

        Date x=new Date();
        System.out.println(x);

        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.logo)
                        .setContentTitle("El evento "+title)
                        .setContentText("Cumple un mes mas, festeja realizando una memoria.");

        //"Hoy se cumple " +" de tu evento: "

        Intent resultIntent = new Intent(context, MainActivity_galeria_imagen.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);

        stackBuilder.addParentStack(MainActivity_recordatorios.class);

        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(((int) id), mBuilder.build());


        sendNextIntent(context, intent);

    }

    public void sendNextIntent(Context context, Intent intent)
    {
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Bundle bundle = intent.getExtras();
        long id = bundle.getLong("idevent");
        int cont = bundle.getInt("cont");
        String title = bundle.getString("title");
        System.out.println(title + "<<<<<<<<<<<<<<<<<<<<<");
        Intent newintent  = new Intent(context, AlarmReceiver.class);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH,1);
//        calendar.add(Calendar.MINUTE,1);

        Date newDate = calendar.getTime();

        SimpleDateFormat simpleDateFormat =
        new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZZZZZ");

        Bundle bundle2 = new Bundle();
        bundle2.putString("date", simpleDateFormat.format(newDate));
        bundle2.putString("title", title);
        bundle2.putInt("cont", cont++);
        newintent.putExtras(bundle);

        PendingIntent pIntent = PendingIntent.getBroadcast(context, ((int) id), newintent,  PendingIntent.FLAG_CANCEL_CURRENT);
        manager.cancel(pIntent);
        //manager.setInexactRepeating(AlarmManager.RTC_WAKEUP , SystemClock.elapsedRealtime()+interval, interval , pIntent);
        manager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() ,pIntent);
    }
}
