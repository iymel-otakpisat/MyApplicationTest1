package com.example.myapplication;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;


public class GameView extends SurfaceView implements Runnable {

    private ImageButton button2;
    private ImageButton button3;
    private ImageButton button4;
    private ImageButton button9;

    volatile boolean playing;
    private Thread gameThread = null;
    private Player player;

    private Key key;
    private Door door;
    private Floor floor;
    private Saw saw;
    private Spike spike;

    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    private ArrayList<Saw> saws = new ArrayList<Saw>();
    private ArrayList<Spike> spikes = new ArrayList<Spike>();
    private ArrayList<Floor> floors = new ArrayList<Floor>();

    int screenX;
    int countMisses;

    boolean flag;
    private int keyEst;
    public int way = 0;
    public int per = 0;

    private boolean isGameOver;

    SharedPreferences sharedPreferences;

    Context context;


    public GameView(Context context, int screenX, int screenY) {
        super(context);
        player = new Player(context, screenX, screenY);
        key = new Key(context, screenX, screenY);
        door = new Door(context, screenX, screenY);
        floor = new Floor(context, screenX, screenY);
        saw = new Saw(context, screenX, screenY);
        spike = new Spike(context, screenX, screenY);
        keyEst = 0;



        surfaceHolder = getHolder();
        paint = new Paint();


        this.screenX = screenX;
        countMisses = 0;
        isGameOver = false;


    }


    public void onClick (View v) {
        if (v == button2) {
            player.setBoostingY();
        } else{
            player.stopBoostingY();
        }
        if (v == button4) {
            player.setAntyboostingX();
        } else{
            player.stopAntyboostingX();
        }
        if (v == button3) {
            player.setBoostingX();
        } else{
            player.stopBoostingX();
        }
        if (v == button9){
            player.setPer();
        }
    }

    @Override
    public void run() {
        while (playing) {
            update();
            draw();
            control();
        }
    }

    public void draw() {
        if (surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);


            paint.setColor(Color.WHITE);
            paint.setTextSize(20);


            canvas.drawBitmap(
                    player.getBitmap(),
                    player.getX(),
                    player.getY(),
                    paint);

            canvas.drawBitmap(
                    key.getBitmap(),
                    key.getX(),
                    key.getY(),
                    paint);

            canvas.drawBitmap(
                    door.getBitmap(),
                    door.getX(),
                    door.getY(),
                    paint);

            canvas.drawBitmap(
                    spike.getBitmap(),
                    spike.getX(),
                    spike.getY(),
                    paint);

            canvas.drawBitmap(
                    saw.getBitmap(),
                    saw.getX(),
                    saw.getY(),
                    paint);

            canvas.drawBitmap(
                    floor.getBitmap(),
                    floor.getX(),
                    floor.getY(),
                    paint);

            if(isGameOver){
                paint.setTextSize(150);
                paint.setTextAlign(Paint.Align.CENTER);

                int yPos=(int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2));
                canvas.drawText("Конец игры",canvas.getWidth()/2,yPos,paint);
            }

            surfaceHolder.unlockCanvasAndPost(canvas);

        }
    }


    private void update() {

        player.update();

        if (Rect.intersects(player.getDetectCollision(), spike.getDetectCollision())){
            //gameOversound.start();
            isGameOver = true;
        }

        if (Rect.intersects(player.getDetectCollision(), saw.getDetectCollision())){
            //gameOversound.start();
            isGameOver = true;
        }

        if(Rect.intersects(player.getDetectCollision(), key.getDetectCollision()) == true) {
            keyEst = 1;
        }

        if(Rect.intersects(player.getDetectCollision(), door.getDetectCollision()) == true) {
            if (keyEst == 1){
                way = 1;
            }
        }

        if (Rect.intersects(player.getDetectCollision(), floor.getDetectCollision())){
            player.touchFloor();
        }
    }



    private void control() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
        }
    }

    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }
}
