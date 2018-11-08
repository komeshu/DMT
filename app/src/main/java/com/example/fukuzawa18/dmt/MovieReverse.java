package com.example.fukuzawa18.dmt;

import android.app.Activity;
import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MovieReverse extends Activity implements TextureView.SurfaceTextureListener{

    boolean isMirrored = true;
    private MediaPlayer mMediaPlayer;
    private TextureView mPreview;
    Button button1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_reverse);

        mPreview = (TextureView) findViewById(R.id.textureView);
        mPreview.setSurfaceTextureListener(this);
        mPreview.setScaleX(isMirrored ? -1 : 1);

        findViews();
        setListeners();

    }



    protected void findViews() {
        button1 = (Button) findViewById(R.id.button11);
    }
    protected void setListeners() {
       button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //クリック時の処理
                Intent intent = new Intent(MovieReverse.this, MovieReverse.class);
                 startActivity(intent);
            }
        });
    }




    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {

        mMediaPlayer = new MediaPlayer();

//        Intent intent = getIntent();
        //インテントに保存されたデータを取得
//        Uri videoUri = intent.getParcelableExtra("PATH");
    //    String data_1 = intent.getStringExtra("Keyword");
    //    String data_2 = intent.getStringExtra("Keyword2");

        mMediaPlayer.setSurface(new Surface(surface));
        try {

         //   mMediaPlayer.setDataSource("/storage/emulated/0/DCIM/Camera/"+"video"+data_1+data_2+".mp4");
        //    mMediaPlayer.setDataSource(videoUri);
         //   mMediaPlayer.setDataSource("/storage/emulated/0/DCIM/Camera/video77.mp4");
         //   String path = "/storage/emulated/0/DCIM/Camera/video77.mp4";

         //   String path = getIntent().getExtras().getString("PATH");
         //   String path = getIntent().getStringExtra("PATH");

            Intent intent = getIntent();
            String path = intent.getStringExtra("PATH");
            mMediaPlayer.setDataSource(path);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {return false;}
    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int width, int height) {}
    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {}

}
