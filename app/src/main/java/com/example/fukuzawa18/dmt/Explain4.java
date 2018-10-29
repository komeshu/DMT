package com.example.fukuzawa18.dmt;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Explain4 extends AppCompatActivity{

    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.explain4);

        Button btn = (Button) findViewById(R.id.button6);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // インテントのインスタンス生成
                Intent intent = new Intent(Explain4.this, MainActivity2.class);
                // 次画面のアクティビティ起動
                startActivity(intent);
            }
        });


    }

}
