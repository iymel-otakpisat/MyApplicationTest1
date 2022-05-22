package com.example.myapplication;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private Bitmap bitmap;
    private Bitmap ts;
    private int x;
    private int y;
    private int speedX = 0;
    private int speedY = 0;
    private final int GRAVITY = -10;
    private int maxY;
    private int minY;
    private int maxX;
    private int minX;

    private boolean boostingY;
    private boolean boostingX;
    private boolean antyboostingX;


    private int per = 0;

    private final int MIN_SPEEDX = 1;
    private final int MAX_SPEEDX = 20;
    private final int MIN_SPEEDY = 1;
    private final int MAX_SPEEDY = 20;

    private Rect detectCollision;

    private List t = new ArrayList();


    public Player(Context context, int screenX, int screenY) {
        x = 75;
        y = 50;
        speedX = 0;
        speedY = 0;
        bitmap = t.get(per);
        maxY = screenY - bitmap.getHeight();
        minY = 0;
        minX = -100000000;
        maxX = 1000000000;


        ts = BitmapFactory.decodeResource(context.getResources(), R.drawable.player);
        t.add(ts);
        ts = BitmapFactory.decodeResource(context.getResources(), R.drawable.player2);
        t.add(ts);
        ts = BitmapFactory.decodeResource(context.getResources(), R.drawable.player3);
        t.add(ts);


        boostingY = false;
        boostingX = false;
        antyboostingX = false;

        detectCollision =  new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());
    }


    public void setPer(){per += 1;}

    public void setBoostingY() {
        boostingY = true;
    }

    public void stopBoostingY() {
        boostingY = false;
    }
    public void setBoostingX() {
        boostingX = true;
    }

    public void stopBoostingX() {
        boostingX = false;
    }
    public void setAntyboostingX() {
        antyboostingX = true;
    }

    public void stopAntyboostingX() {
        antyboostingX = false;
    }

    public void touchFloor(){speedY = 0;}



    public void update() {

        if (boostingY) {
            speedY += 2;
        }

        if (boostingX) {
            speedX += 2;
        } else {
            if (antyboostingX) {
                speedX -= 2;
            } else {
                speedX = 0;
            }
        }


        y = y - GRAVITY + speedY;

        if (y < minY) {
            y = minY;
            speedY = 0;
        }
        if (y > maxY) {
            y = maxY;
            speedY = 0;
        }

        x -= speedX;
        if (x < minX) {
            x = minX;
        }
        if (x > maxX) {
            x = maxX;
        }


        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + bitmap.getWidth();
        detectCollision.bottom = y + bitmap.getHeight();

    }


    public Rect getDetectCollision() {
        return detectCollision;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
