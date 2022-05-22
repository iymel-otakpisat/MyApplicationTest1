package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Random;

public class Floor {

    private Bitmap bitmap;
    private int x;
    private int y;
    private int maxY;
    private int minY;
    private int maxX;
    private int minX;
    private Rect detectCollision;


    public Floor(Context context, int screenX, int screenY) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.floor);
        maxX = screenX - bitmap.getWidth();
        maxY = screenY - bitmap.getHeight();
        minY = 0;
        minX = 0;

        Random generator = new Random();

        x = 100;
        y = 100;
        detectCollision =  new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());


    }
    public void updates(int playerSpeed) {

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