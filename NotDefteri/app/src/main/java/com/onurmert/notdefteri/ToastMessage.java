package com.onurmert.notdefteri;

import android.content.Context;
import android.widget.Toast;

public class ToastMessage {

    public static void myToastMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
