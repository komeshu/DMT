package com.example.fukuzawa18.dmt;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.io.InputStream;

import static com.example.fukuzawa18.dmt.MainActivity.REQUEST_CAPTURE_IMAGE;

public class MainActivity2 extends Activity {

    public MainActivity2(){}
    static final int REQUEST_CAPTURE_IMAGE = 100;
    private static final int REQUEST_GALLERY = 0;
    Button button1;
    Button button2;
    Button button7;
    Button button_ok;
    ImageView imageView1;
    NumberPicker numPicker;
    NumberPicker numPicker_1;
    TextView textView1;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button btn = (Button) findViewById(R.id.button7);
        Button btn_saisei = (Button) findViewById(R.id.button8);

        findViews();
        setListeners();
        findViews3();
//        initViews();

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            try {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        0);
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }

    }


    //カメラ起動するところ***************************
    protected void findViews() {
        button1 = (Button) findViewById(R.id.button7);
        imageView1 = (ImageView) findViewById(R.id.imageView1);
    }
    protected void setListeners() {
//        Button btn = (Button) findViewById(R.id.button7);
//        btn.setOnClickListener(new View.OnClickListener() {
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //クリック時の処理
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(intent, REQUEST_CAPTURE_IMAGE);
            }
        });
    }
    // ****************************



    // pathを取得*****************************
    public static String getPath(Context context, Uri uri) {
        ContentResolver contentResolver = context.getContentResolver();
        String[] columns = {MediaStore.Images.Media.DATA};
        Cursor cursor = contentResolver.query(uri, columns, null, null, null);
        cursor.moveToFirst();
        String path = cursor.getString(0);
        cursor.close();
        return path;
//        Log.i("zzzzzz",path);
//        String message = "pathは"+path;
//            String message = edited;
        //確認のためトーストを利用
//        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data, Intent intent_path) {
        // カメラ起動した後にpathを取得＊＊＊
        if (requestCode == REQUEST_CAPTURE_IMAGE && resultCode == RESULT_OK) {
            Uri videoUri = data.getData();
            Log.i("aa", getPath(this,videoUri));
            //インテントに値をセット
            intent_path.putExtra("PATH", videoUri);
            //  mVideoView.setVideoURI(videoUri);
        }

        // ギャラリーから動画を選択した後に動画を再生させる
        if(requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {

            Intent intent = new Intent(MainActivity2.this, Explain.class);
            // 次画面のアクティビティ起動
            startActivity(intent);
        }
    }
    //***************************************






    //動画のリストを表示させるとこ＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
    protected  void findViews3(){
        Button btn_saisei = (Button) findViewById(R.id.button8);
        btn_saisei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // インテントのインスタンス生成
                Intent intent = new Intent();
                intent.setType("*/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,REQUEST_GALLERY);
            }
        });
        // *****************************************

    }

}
