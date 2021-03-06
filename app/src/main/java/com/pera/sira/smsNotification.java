package com.pera.sira;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;

import static android.support.v4.app.NotificationCompat.Builder;
import static com.pera.sira.R.drawable.ic_launcher;

/**
 * Created by Rama on 11/7/2015.
 */
class smsNotification extends Activity{

    private Builder mBuilder;
    // Creates an explicit intent for an Activity in your app
    private Intent resultIntent;
    private int mId;
    // The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
    private TaskStackBuilder stackBuilder;
// Adds the back stack for the Intent (but not the Intent itself)
    private String number;
    private String recievedMsg;
    private String sentMsg;

    smsNotification(String number, String recievedMsg, String sentMsg) {
        this.number = number;
        this.recievedMsg = recievedMsg;
        this.sentMsg = sentMsg;
    }


    public void smsNotify(){
        stackBuilder = TaskStackBuilder.create(this);
        mBuilder = new Builder(this);
        mBuilder.setSmallIcon(ic_launcher);
        mBuilder.setContentTitle("SiRA");
        mBuilder.setContentText("SiRA has taken care of " + number + "\n" + "Received: " + recievedMsg + "\nSent: " + sentMsg);
        resultIntent = new Intent(this, smsNotification.class);
        stackBuilder.addParentStack(smsNotification.class);
// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(mId, mBuilder.build());
    }
// mId allows you to update the notification later on.


}
