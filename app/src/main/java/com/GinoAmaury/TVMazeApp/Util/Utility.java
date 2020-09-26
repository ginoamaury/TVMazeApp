package com.GinoAmaury.TVMazeApp.Util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import com.GinoAmaury.TVMazeApp.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class Utility {


    public static String getPreference(Context c, String preference ,String key){
        SharedPreferences preferences = c.getSharedPreferences(preference, Context.MODE_PRIVATE);
        return preferences.getString(key,c.getString(R.string.noData));
    }

    public static boolean setPreference(Context c, String preference ,String key, String data){
        try{
            SharedPreferences preferences = c.getSharedPreferences(preference, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(key,data);
            editor.apply();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public static void showSnackbarTopMsg (View v, Context context, String msg){
        Snackbar snack = Snackbar.make(v, msg, Snackbar.LENGTH_LONG);
        View view = snack.getView();
        FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)view.getLayoutParams();
        params.gravity = Gravity.TOP;
        view.setLayoutParams(params);
        snack.show();
    }


    public static void GoToNextActivityCleanStack(Activity activity, Class clase, boolean finaliza, ArrayList<Extra> params)
    {
        Intent intent = new Intent(activity, clase ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        if(params!=null){
            for (Extra param: params) {
                intent.putExtra(param.getClave(),param.getValor());
            }
        }
        activity.startActivity(intent);

        if (finaliza){
            activity.finish();
        }
    }

    public static boolean veirifyConnection (Activity activity){
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            View contextView = activity.getCurrentFocus();
            Snackbar.make(contextView, activity.getResources().getString(R.string.errConnect), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return false;
        }

    }

    public static class Extra {
        private String clave;
        private String valor;

        public Extra(String clave, String valor) {
            this.clave = clave;
            this.valor = valor;
        }

        public String getClave() {
            return clave;
        }

        public void setClave(String clave) {
            this.clave = clave;
        }

        public String getValor() {
            return valor;
        }

        public void setValor(String valor) {
            this.valor = valor;
        }
    }
}