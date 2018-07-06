package com.example.asus.networkcontrolwithprogressdialog;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Window;

public class NetworkReceiver extends BroadcastReceiver {

    public boolean control = false;
    public Dialog dialogNetwork;


    public void onReceive(final Context context, final Intent intent) {
        ProgressDailog(context); //When receiver run this method will be called
    }


    private  boolean ProgressDailog(Context context){//When there is an issue relating to wifi connection the progress dialog
        //that has  wifi_connection.png will appeared.

        if(control == false) {//dialogNetwork object was created once because "oncreate()" is not

            dialogNetwork = new Dialog(context, R.style.TransparentProgressDialog);
            dialogNetwork.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            dialogNetwork.setCancelable(false);//When back button touch the progressdialog don't close
            dialogNetwork.setCanceledOnTouchOutside(false);//When  screen touch the progressdialog don't close

            dialogNetwork.setContentView(R.layout.custom_progress_layout);

            control = true;
        }

        if(isOnline(context) == false){
            dialogNetwork.show();//When "isOnline" function return false the progresdialog will be shown
        }
        else {
            dialogNetwork.dismiss();//When "isOnline" function return true the progresdialog will be shown
        }

        return  true;

    }

    public boolean isOnline(Context context){//Network is checking.When the network connection have
        // a problem "isOnline" function return false.
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}
