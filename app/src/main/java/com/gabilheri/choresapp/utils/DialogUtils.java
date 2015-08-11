package com.gabilheri.choresapp.utils;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by Jerry on 2015-08-11.
 */
public final class DialogUtils {

    private DialogUtils() {

    }

    public static void showErrorDialog(Context context, String title, String content) {
        new MaterialDialog.Builder(context)
                .title(title == null ? "Oops! Something went wrong!" : title)
                .content(content != null ? content : "Wait a couple of miliseconds and retry.")
                .positiveText("Dismiss")
                .show();
    }
}
