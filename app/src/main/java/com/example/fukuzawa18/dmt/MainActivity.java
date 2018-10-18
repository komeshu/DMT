package com.example.fukuzawa18.dmt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {

    public MainActivity(){}
    static final int REQUEST_CAPTURE_IMAGE = 100;
    Button button;
    Button button2;
    Button button_ok;
    ImageView imageView1;
    NumberPicker numPicker;
    NumberPicker numPicker_1;
    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.button);
        Button btn_explain = (Button)findViewById(R.id.button2);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // インテントのインスタンス生成
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                // 次画面のアクティビティ起動
                startActivity(intent);
            }
        });


        btn_explain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // インテントのインスタンス生成
                Intent intent = new Intent(MainActivity.this, Explain.class);
                // 次画面のアクティビティ起動
                startActivity(intent);
            }
        });





    }




}
