package com.example.catanbankcompanion;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.SplittableRandom;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@SuppressWarnings("ALL")
public class DrawPreview extends SurfaceView implements SurfaceHolder.Callback{
    private SurfaceHolder surfaceHolder;
    Paint paint ;
    SplittableRandom sr =new SplittableRandom();
    int height;
    int width;
    int centerx;
    int centery;
    static int[] colors=new int[19];
    boolean size_init=false;
    ScanCircle[] circles;
    public DrawPreview(Context context) {
        super(context);
        paint= new Paint();
        paint.setColor(Color.CYAN);
        paint.setStyle(Paint.Style.FILL);
        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        // deprecated setting, but required on Android versions prior to 3.0
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);


    }

    public void surfaceCreated(SurfaceHolder holder) {
        setWillNotDraw(false);
        tryDrawing(holder);
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // empty. Take care of releasing the Camera preview in your activity.
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        // If your preview can change or rotate, take care of those events here.
        // Make sure to stop the preview before resizing or reformatting it.
        if (surfaceHolder.getSurface() == null){
            // preview surface does not exist
            return;
        }
        tryDrawing(holder);
    }
    private void tryDrawing(SurfaceHolder holder) {
        Log.i("TAG", "Trying to draw...");
        Canvas canvas = holder.lockCanvas();
        if (canvas == null) {
            Log.e("TAG", "Cannot draw onto the canvas as it's null");
        } else {
            if(!size_init){
                circles=initCircle(canvas.getWidth()/2,canvas.getHeight()/2);
                size_init=true;
            }
            drawMyStuff(canvas);
            holder.unlockCanvasAndPost(canvas);
        }
    }

    private void drawMyStuff(final Canvas canvas) {
        Log.i("TAG", "Drawing...");
        drawCircles(circles,colors,canvas);
    }

    private class ScanCircle{
        private final int x;
        private final int y;
        private Color color;
        ScanCircle(int x,int y){
            this.x=x;
            this.y=y;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return color;
        }
    }
    ScanCircle[] initCircle(int cx,int cy){
        ScanCircle[] circles = new ScanCircle[19];
        circles[0]=new ScanCircle(cx-140,cy-243);
        circles[1]=new ScanCircle(cx,cy-243);
        circles[2]=new ScanCircle(cx+140,cy-243);

        circles[3]=new ScanCircle(cx-208,cy-120);
        circles[4]=new ScanCircle(cx-70,cy-120);
        circles[5]=new ScanCircle(cx+70,cy-120);
        circles[6]=new ScanCircle(cx+208,cy-120);

        circles[7]=new ScanCircle(cx-280,cy);
        circles[8]=new ScanCircle(cx-140,cy);
        circles[9]=new ScanCircle(cx,cy);
        circles[10]=new ScanCircle(cx+140,cy);
        circles[11]=new ScanCircle(cx+280,cy);

        circles[12]=new ScanCircle(cx-208,cy+120);
        circles[13]=new ScanCircle(cx-70,cy+120);
        circles[14]=new ScanCircle(cx+68,cy+120);
        circles[15]=new ScanCircle(cx+208,cy+120);

        circles[16]=new ScanCircle(cx-140,cy+242);
        circles[17]=new ScanCircle(cx,cy+242);
        circles[18]=new ScanCircle(cx+139,cy+242);
        return circles;
    }
    void drawCircles(ScanCircle[] circles,int[] colors,Canvas canvas){
        SplittableRandom sr =new SplittableRandom();
        int i=0;
        for (ScanCircle c:circles ) {
           //paint.setColor(Color.rgb(sr.nextInt(255),sr.nextInt(255),sr.nextInt(255)));
            paint.setColor(colors[i]);
            Log.i("DRAW CIRCLES","The color of "+i+"is:"+colors[i]);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(c.x,c.y,67,paint);
            i++;
        }
    }
    void update(int[] color){
        colors=color;
        tryDrawing(surfaceHolder);
    }



}