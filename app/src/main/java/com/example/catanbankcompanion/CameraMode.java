package com.example.catanbankcompanion;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CameraMode extends Activity {

    private Camera mCamera;
    static private CameraPreview cameraPreview;
    static private ImageButton CaptureButton;
    static private DrawPreview drawPreview;
    static Bitmap bitmap;
    static int[] colors=new int[19];

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_layout);

        // Create an instance of Camera
        mCamera = getCameraInstance();
        if (mCamera==null){
            Log.w("","NO PUDO CONSEGUIR CAMARA!!!!!!!!!!!!!!!!!");}

        // Create our Preview view and set it as the content of our activity.
        cameraPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(cameraPreview);
        cameraPreview.setZOrderMediaOverlay(true);

        drawPreview=new DrawPreview(this);
        FrameLayout drawFrame = (FrameLayout) findViewById(R.id.draw_preview);
        drawFrame.addView(drawPreview);
        drawPreview.setZOrderMediaOverlay(true);
        drawPreview.setZOrderOnTop(true);
        SurfaceHolder drawPreviewHolder = drawPreview.getHolder();
        drawPreviewHolder.setFormat(PixelFormat.TRANSPARENT);

        CaptureButton=(ImageButton) findViewById(R.id.button_capture);
        CaptureButton.setOnTouchListener((v, event) -> {
            if(event.getAction()==event.ACTION_DOWN) {

            }
            return false;
        });

        ScheduledThreadPoolExecutor sch = (ScheduledThreadPoolExecutor)Executors.newScheduledThreadPool(3);
        Runnable Task = new Runnable(){
            @Override
            public void run() {
                try{
                    bitmap= cameraPreview.generateBM();
                    CaptureButton.setBackgroundColor(bitmap.getPixel(bitmap.getWidth()/2,bitmap.getHeight()/2));
                    color_at_circles();
                    drawPreview.update(colors);
                }catch(Exception e){   }
            }
        };

        ScheduledFuture<?> periodicFuture = sch.scheduleAtFixedRate(Task, 500, 500, TimeUnit.MILLISECONDS);
    }
    /** A safe way to get an instance of the Camera object. */
    public static Camera getCameraInstance(){
        Camera c = null;
        try {// attempt to get a Camera instance
            c = Camera.open();
        }
        catch (Exception e){ // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }
    @Override
    protected void onPause() {
        super.onPause();
        releaseCamera();              // release the camera immediately on pause event
    }

    private void releaseCamera(){
        if (mCamera != null){
            mCamera.release();        // release the camera for other applications
            mCamera = null;
        }
    }
    void color_at_circles(){
        int cx=bitmap.getWidth()/2;
        int cy=bitmap.getHeight()/2;
        colors[0]=bitmap.getPixel(cx - 140, cy - 243);
        colors[1]=bitmap.getPixel(cx, cy - 243);
        colors[2]=bitmap.getPixel(cx + 140, cy - 243);
        colors[3]=bitmap.getPixel(cx - 208, cy - 120);
        colors[4]=bitmap.getPixel(cx - 70, cy - 120);
        colors[5]=bitmap.getPixel(cx + 70, cy - 120);
        colors[6]=bitmap.getPixel(cx + 208, cy - 120);
        colors[7]=bitmap.getPixel(cx - 280, cy);
        colors[8]=bitmap.getPixel(cx - 140, cy);
        colors[9]=bitmap.getPixel(cx, cy);
        colors[10]= bitmap.getPixel(cx+140,cy);
        colors[11]= bitmap.getPixel(cx+280,cy);
        colors[12]= bitmap.getPixel(cx-208,cy+120);
        colors[13]= bitmap.getPixel(cx-70,cy+120);
        colors[14]= bitmap.getPixel(cx+68,cy+120);
        colors[15]= bitmap.getPixel(cx+208,cy+120);
        colors[16]= bitmap.getPixel(cx-140,cy+242);
        colors[17]= bitmap.getPixel(cx,cy+242);
        colors[18]= bitmap.getPixel(cx+139,cy+242);
    }

}
