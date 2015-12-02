package lesson6.mum.edu.surfaceviewdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Dane Savot on 12/2/2015.
 */
public class GameView extends SurfaceView {
    private SurfaceHolder holder;
    Bitmap background;
    Bitmap daemon;
    Bitmap angel;
    GameThread gameThread;
    public GameView(Context context) {
        super(context);

        holder = getHolder();

        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
                daemon = BitmapFactory.decodeResource(getResources(),R.drawable.daemon);
                angel = BitmapFactory.decodeResource(getResources(),R.drawable.angel);

                background = Bitmap.createScaledBitmap(background, getWidth(), getHeight(), false);

                daemon = Bitmap.createScaledBitmap(daemon,200,200,false);
                angel = Bitmap.createScaledBitmap(angel,200,250,false);

                makeGameThread();

                gameThread.setRunning(true);
                gameThread.start();

            }



            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
    }
    private void makeGameThread() {
        gameThread = new GameThread(this);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(background, 0, 0, null);

        canvas.drawBitmap(daemon,0,0,null);
        canvas.drawBitmap(angel,200,200,null);

    }
}
