package com.example.fukuzawa18.dmt;


import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;
/*
public class Camera extends Activity {

    private Camera mCam = null;

    // カメラプレビュークラス
    private CameraPreview mCamPreview = null;
    */



    /** Called when the activity is first created. */
/*    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // カメラインスタンスの取得
        try {
            mCam = Camera.open();
        } catch (Exception e) {
            // エラー
            this.finish();
        }

        // FrameLayout に CameraPreview クラスを設定
        FrameLayout preview = (FrameLayout)findViewById(R.id.cameraPreview);
        mCamPreview = new CameraPreview(this, mCam);
        preview.addView(mCamPreview);
    }


    @Override
    protected void onPause() {
        super.onPause();
        // カメラ破棄インスタンスを解放
        if (mCam != null) {
            mCam.release();
            mCam = null;
        }
    }



}
*/
