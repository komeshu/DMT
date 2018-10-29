package com.example.fukuzawa18.dmt;

import android.app.Activity;
import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MovieReverse extends Activity implements TextureView.SurfaceTextureListener{

    boolean isMirrored = true;
    private MediaPlayer mMediaPlayer;
    private TextureView mPreview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_reverse);

        //インテントを取得
        Intent intent = getIntent();
        //インテントに保存されたデータを取得
        String data_1 = intent.getStringExtra("Keyword");
        String data_2 = intent.getStringExtra("Keyword2");
        String message = "この動画はvideo" + data_1 + data_2 + ".mp4 です";
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

/*
        //戻るボタン
        ImageButton btn = (ImageButton) findViewById(R.id.button01_id);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // 次画面のアクティビティ終了
                finish();
            }
        });
*/

        mPreview = (TextureView) findViewById(R.id.textureView);
        mPreview.setSurfaceTextureListener(this);
        mPreview.setScaleX(isMirrored ? -1 : 1);
    }


    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {

        mMediaPlayer = new MediaPlayer();

        Intent intent = getIntent();
        //インテントに保存されたデータを取得
        String data_1 = intent.getStringExtra("Keyword");
        String data_2 = intent.getStringExtra("Keyword2");

        mMediaPlayer.setSurface(new Surface(surface));
        try {

            mMediaPlayer.setDataSource("/storage/emulated/0/DCIM/Camera/"+"video"+data_1+data_2+".mp4");
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
