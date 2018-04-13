package com.grepthor.hyderabadmetro;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class MyService extends FirebaseMessagingService{

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String url=remoteMessage.getData().get("image");
        String title=remoteMessage.getData().get("title");
        Bitmap    bitmap = getBitmapfromUrl(url);


        receiveNotification(remoteMessage.getNotification().getBody(),bitmap,title);

    }

    private void receiveNotification(String body, Bitmap bitmap,String title)
    {

        Intent i=new Intent(this,NotificationActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pi=PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_ONE_SHOT);

        Uri ringtone=RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder n=new NotificationCompat.Builder(this);
        n.setSmallIcon(R.drawable.noti_icon);
        n.setContentTitle(title);
        n.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap));
        n.setContentText(body);
        n.setSound(ringtone);
        n.setContentIntent(pi);
        n.setAutoCancel(true);
//      n.setOngoing(true);


        NotificationManager mn= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mn.notify(0,n.build());

    }




    public Bitmap getBitmapfromUrl(String imageUrl)
    {

        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;

        }
    }


}