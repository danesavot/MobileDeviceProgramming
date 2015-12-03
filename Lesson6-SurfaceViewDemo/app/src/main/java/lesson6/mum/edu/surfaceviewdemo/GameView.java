package lesson6.mum.edu.surfaceviewdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Dane Savot on 12/2/2015.
 */
public class GameView extends SurfaceView {
    private SurfaceHolder holder;
    Bitmap background;
    Bitmap daemon;
    Bitmap angel;
    Bitmap bitmap;

    int hits, missed, wrong, index;
    long seconds, startMillis;
    float bitmapX, bitmapY;
    boolean isNewBitmap = true;
    Random random = new Random();
    Position position;
    private Paint scorePaint;

    List<Position> positions = new ArrayList<Position>() {
        {
            add(new Position(200, 200));
            add(new Position(1400, 50));
            add(new Position(600, 400));
            add(new Position(100, 300));

            add(new Position(1200, 200));
            add(new Position(600, 600));
            add(new Position(500, 400));
            add(new Position(1600, 400));

        }
    };

    public class Position {
        float x;
        float y;

        public Position(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    public void reset() {
        hits = missed = wrong = index = 0;
        seconds = 0;
        bitmapX = bitmapY = 0;
        isNewBitmap = true;
    }

    public GameView(Context context) {
        super(context);

        holder = getHolder();

        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
                daemon = BitmapFactory.decodeResource(getResources(), R.drawable.daemon);
                angel = BitmapFactory.decodeResource(getResources(), R.drawable.angel);

                background = Bitmap.createScaledBitmap(background, getWidth(), getHeight(), false);

                daemon = Bitmap.createScaledBitmap(daemon, 200, 200, false);

                angel = Bitmap.createScaledBitmap(angel, 200, 250, false);


                scorePaint = new Paint();
                scorePaint.setColor(Color.YELLOW);
                scorePaint.setTextSize(76.0f);


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
    public boolean onTouchEvent(MotionEvent event) {

        if (bitmap == null) return false;

        float x = event.getX();
        float y = event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            if( x > bitmapX && x < bitmapX + bitmap.getWidth() && y > bitmapY && y < bitmapY + bitmap.getHeight())
            {
                if (bitmap.sameAs(daemon)) {
                    hits += 1;

                }else {
                    hits += -1;
                }
                return true;
            }

            wrong += 1;

            return true;
        }

        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawBitmap(background, 0, 0, null);



        seconds = (System.currentTimeMillis() - startMillis)/1000;

        canvas.drawText("Time:" + seconds ,getWidth() - 400,100,scorePaint);
        canvas.drawText("Hits:" + hits + " Missed: " + missed + " Wrongs:" + wrong,10,100,scorePaint);



        if (isNewBitmap) {

            index = random.nextInt(positions.size());

            if (random.nextInt(3) == 1) {
                bitmap = angel;
            }else {
                bitmap = daemon;
            }

            position = positions.get(index);

            bitmapX = position.x;
            bitmapY = position.y;
            canvas.drawBitmap(bitmap, bitmapX, bitmapY, null);

            isNewBitmap = false;

        }else {

            bitmapX += 2;

            canvas.drawBitmap(bitmap, bitmapX , bitmapY, null);
            if (bitmapX> position.x + 100) {
                isNewBitmap = true;
            }
        }

    }

    public void onDestroy()
    {
        daemon.recycle();
        daemon = null;
        angel.recycle();
        angel = null;
        background.recycle();
        background =null;

        System.gc();
    }

}
