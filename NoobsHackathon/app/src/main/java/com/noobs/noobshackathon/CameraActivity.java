package com.noobs.noobshackathon;

import android.app.Activity;
import android.content.res.Configuration;
import android.media.Image;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.noobs.noobshackathon.R;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import static org.opencv.core.Core.flip;

public class CameraActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2{
    private static String TAG="CameraActivity";
    JavaCameraView javaCameraView;
    // Used in Camera selection from menu (when implemented)


    Mat mRgba,mGray;

    BaseLoaderCallback mBaseLoaderCallback=new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            if(status==BaseLoaderCallback.SUCCESS){
                javaCameraView.enableView();}
            else super.onManagerConnected(status);

        }
    };

    static {
        System.loadLibrary("MyOpencvLibs");
        if(OpenCVLoader.initDebug()){
            Log.d(TAG,"Loaded openCV!!!");
        }
        else{
            Log.d(TAG,"Loaded fail openCV!!!");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        addControl();

    }

    private void addControl() {
        javaCameraView= (JavaCameraView) findViewById(R.id.javaCameraView);
        javaCameraView.setVisibility(View.VISIBLE);
        javaCameraView.setCvCameraViewListener(this);
    }
    @Override
    protected void onDestroy() {
        if(javaCameraView!=null) javaCameraView.disableView();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        if(javaCameraView!=null) javaCameraView.disableView();
        super.onPause();
    }

    @Override
    protected void onResume() {
        if(OpenCVLoader.initDebug()){
            mBaseLoaderCallback.onManagerConnected(BaseLoaderCallback.SUCCESS);
            Log.d(TAG,"Loaded openCV!!!");
        }
        else{
            Log.d(TAG,"Loaded fail openCV!!!");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_2_0,this,mBaseLoaderCallback);
        }
        super.onResume();
    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        mRgba=new Mat(height,width, CvType.CV_8UC4);
        mGray=new Mat(height,width, CvType.CV_8UC1);
    }

    @Override
    public void onCameraViewStopped() {
        mRgba.release();
    }
    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
       mRgba = inputFrame.rgba();
        return mRgba;
    }
}
