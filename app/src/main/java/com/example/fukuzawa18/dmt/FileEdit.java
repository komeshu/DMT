package com.example.fukuzawa18.dmt;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import static com.example.fukuzawa18.dmt.MainActivity2.REQUEST_CAPTURE_IMAGE;
import static com.example.fukuzawa18.dmt.MainActivity2.getPath;

public class FileEdit extends Activity {

    private static final int REQUEST_GALLERY = 0;
    NumberPicker numPicker;
    NumberPicker numPicker_1;
    Button button_ok;

    //ナンバーピッカーの動作設定
    protected void findViews() {
        numPicker = (NumberPicker) findViewById(R.id.numPicker);
        numPicker_1 = (NumberPicker) findViewById(R.id.numPicker_1);
        button_ok = (Button) findViewById(R.id.button_ok);

        numPicker.setMaxValue(9);
        numPicker.setMinValue(0);
        numPicker_1.setMaxValue(9);
        numPicker_1.setMinValue(0);
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Auto-generated method stub
                //インテントのインスタンス生成,値を保存
                Intent intent = new Intent();
                intent.setType("*/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_GALLERY);
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_name);
        findViews();
    }


/*

//    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent,Intent data) {
        String num_1 = numPicker.getValue() + "";
        String num_2 = numPicker_1.getValue() + "";

        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {
                Uri videoUri = intent.getData();
//                Log.i("aa", getPath(this,videoUri));


            //インテントからfilenameのデータ及びその文字列データを取得
//            String fileName = data.getData().toString();
            int End_fileName = videoUri.length();
            String message = "ファイル名を video" + num_1 + num_2 + ".mp4" + "に変更しました。";
            //ESファイルから得られる場合、http://などがパスに含まれるため前から28文字を無視した文字列を取り出す.
            //その文字列（full_path）を edited と設定
            String edited = fileName.substring(28,End_fileName);
            //確認のためトーストを利用
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            ReName rename = new ReName(edited);
            rename.main("/storage/emulated/0/DCIM/Camera/video"+num_1+num_2+".mp4");
        }
    }

*/


}
