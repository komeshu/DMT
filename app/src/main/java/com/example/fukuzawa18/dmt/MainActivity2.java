package com.example.fukuzawa18.dmt;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
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

import static android.content.ContentValues.TAG;
import static com.example.fukuzawa18.dmt.MainActivity.REQUEST_CAPTURE_IMAGE;

public class MainActivity2 extends Activity {

    public MainActivity2(){}
    static final int REQUEST_CAPTURE_IMAGE = 100;
    private static final int REQUEST_GALLERY = 0;
    private static final int READ_REQUEST_CODE = 42;
 //   public static final String EXTRA_SCREEN_ORIENTATION = ;
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
                //intent.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                //intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                startActivityForResult(intent, REQUEST_CAPTURE_IMAGE);
            }
        });
    }
    // ****************************



    // pathを取得*****************************
    /*
    public static String getPath(Context context, Uri uri) {
        ContentResolver contentResolver = context.getContentResolver();
        String[] columns = {MediaStore.Images.Media.DATA};
        Cursor cursor = contentResolver.query(uri, columns, null, null, null);
        cursor.moveToFirst();
        String path = cursor.getString(0);
        cursor.close();
        return path;
    }
    */
    //  **************************************


    public String getPathFromUri(final Context context, final Uri uri) {
        boolean isAfterKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        // DocumentProvider
        Log.e(TAG,"uri:" + uri.getAuthority());
        if (isAfterKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            if ("com.android.externalstorage.documents".equals(
                    uri.getAuthority())) {// ExternalStorageProvider
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }else {
                    return "/stroage/" + type +  "/" + split[1];
                }
            }else if ("com.android.providers.downloads.documents".equals(
                    uri.getAuthority())) {// DownloadsProvider
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            }else if ("com.android.providers.media.documents".equals(
                    uri.getAuthority())) {// MediaProvider
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                Uri contentUri = null;
                contentUri = MediaStore.Files.getContentUri("external");
                final String selection = "_id=?";
                final String[] selectionArgs = new String[] {
                        split[1]
                };
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }else if ("content".equalsIgnoreCase(uri.getScheme())) {//MediaStore
            return getDataColumn(context, uri, null, null);
        }else if ("file".equalsIgnoreCase(uri.getScheme())) {// File
            return uri.getPath();
        }
        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {
        Cursor cursor = null;
        final String[] projection = {
                MediaStore.Files.FileColumns.DATA
        };
        try {
            cursor = context.getContentResolver().query(
                    uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int cindex = cursor.getColumnIndexOrThrow(projection[0]);
                return cursor.getString(cindex);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }





    //  onActivityResult ***************************
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // カメラ起動した後にpathを取得＊＊＊
//        if (requestCode == REQUEST_CAPTURE_IMAGE && resultCode == RESULT_OK) {
//            Uri videoUri = data.getData();
//            Log.i("aa", getPath(this, videoUri));

//            String stringUri;
//            stringUri = videoUri.toString();

//            Intent intent = new Intent(MainActivity2.this, MovieReverse.class);
//            intent.putExtra("PATH", stringUri);
            // 次画面のアクティビティ起動
//            startActivity(intent);
//        }
        //インテントに値をセット
        //    intent.putExtra("PATH", videoUri);
        //  mVideoView.setVideoURI(videoUri);
        //       }



        // ギャラリーから動画を選択した後に動画を再生させる
//        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {

//            Uri videoUri = data.getData();
            //   Log.i("aa", getPath(this,videoUri));
            //   data.putExtra("PATH", videoUri);

//            Intent intent = new Intent(MainActivity2.this, Explain.class);
            // 次画面のアクティビティ起動
//            startActivity(intent);
//        }


        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri videoUri = data.getData();
            Log.i("aa", videoUri.toString());
            Log.i("aa", getPathFromUri(this,videoUri));
            //  data.putExtra("PATH", videoUri);

            String stringUri;
         //   stringUri = videoUri.toString();
            stringUri = getPathFromUri(this,videoUri).toString();

            Intent intent = new Intent(MainActivity2.this, MovieReverse.class);
            intent.putExtra("PATH",stringUri);
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
             //   Intent intent = new Intent();
             //   intent.setType("*/*");
             //   intent.setAction(Intent.ACTION_GET_CONTENT);
             //   startActivityForResult(intent,REQUEST_GALLERY);


                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                startActivityForResult(intent, READ_REQUEST_CODE);


            }
        });
        // *****************************************

    }

}
