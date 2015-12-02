package lesson5.mum.edu.testingresource;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by 984391 on 12/2/2015.
 */
public class GameView extends SurfaceView {

    private SurfaceHolder holder;
    private Bitmap Maharishi;
    private Bitmap flower;
    private GameThread gthread = null;
    // horizontal position (graphic is 205 pixels wide thus initialize right edge of graphic fall to left screen edge)
    private float MaharishiX = -205.0f;
    private float MaharishiY = 100.0f; // vertical position
    private float flowerX = -50.0f;
    private float flowerY = -101.0f;
    private boolean flowerActive = false;
    public GameView(Context context) {
        super(context);

        holder = getHolder();

        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Maharishi = BitmapFactory.decodeResource(getResources(), R.drawable.maharishi_mahesh_yogi);
                flower = BitmapFactory.decodeResource(getResources(),R.drawable.rose50 );

                Bitmap.createScaledBitmap(flower,102,125,false)
                makeThread();

                gthread.setRunning(true);
                gthread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });

    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawColor(Color.WHITE);
        if(flowerActive)
        {
            flowerY = flowerY - 5; // flower travels up the screen 5 pixels per redraw
            if ( flowerY < 325 ) // if the flower goes beyond the bottom of the Maharishi graphic by 25 pixels
            {
                flowerX = -50.0f; // park the flower
                flowerY = -101.0f; // and
                flowerActive = false; // Turn off flower drawing
            }
            else // otherwise draw the flower in its new position
            {
                canvas.drawBitmap(flower, flowerX, flowerY, null);
            }
        }
        MaharishiX = MaharishiX + 2.0f;
        if(MaharishiX > getWidth())
            MaharishiX = -205.0f;

        canvas.drawBitmap(Maharishi, MaharishiX, MaharishiY, null);
    }
    public void giveFlower()
    {
        flowerActive = true;
        flowerX = getWidth() / 2.0f - flower.getWidth() / 2;
        flowerY = getHeight() - flower.getHeight() - 25;
    }
    public void makeThread()
    {
        gthread = new GameThread(this);

    }

    public void killThread()
    {
        boolean retry = true;
        gthread.setRunning(false);
        while(retry)
        {
            try
            {
                gthread.join();
                retry = false;
            }
            catch (InterruptedException e)
            {
            }
        }
    }
    public void onDestroy()
    {
        Maharishi.recycle();
        Maharishi = null;
        flower.recycle();
        flower = null;

        System.gc();
    }
}
