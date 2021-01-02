package com.miumiuhaskeer.beautifuldialogdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.miumiuhaskeer.beautifuldialog.BeautifulDialog;
import com.miumiuhaskeer.beautifuldialog.BeautifulDialogColors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BeautifulDialogColors colors = new BeautifulDialogColors(this, R.color.white, R.color.teal_200, R.color.black);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_to_add, null);
        BeautifulDialog dialog = new BeautifulDialog(this, colors, view);

        dialog.setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.dialog_positive_button, null)
                .setNegativeButton(R.string.dialog_negative_button, null);

        dialog.show();
    }
}