package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.ArrayList;
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
    private int k = 0;


    public Floor(Context context, int screenX, int screenY) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.floor);
        maxX = screenX - bitmap.getWidth();
        maxY = screenY - bitmap.getHeight();
        minY = 0;
        minX = 0;

        Random generator = new Random();

        //размешение
        ArrayList<Integer> ksXY = new ArrayList<>();
        int kx = 100;
        int ky = 100;
        ksXY.add(kx);
        ksXY.add(ky);
        kx = 100;
        ky = 150;
        ksXY.add(kx);
        ksXY.add(ky);
        kx = 200;
        ky = 150;
        ksXY.add(kx);
        ksXY.add(ky);
        kx = 130;
        ky = 120;
        ksXY.add(kx);
        ksXY.add(ky);
        kx = 100;
        ky = 90;
        ksXY.add(kx);
        ksXY.add(ky);
        kx = 170;
        ky = 200;
        ksXY.add(kx);
        ksXY.add(ky);
        kx = 120;
        ky = 210;
        ksXY.add(kx);
        ksXY.add(ky);

        for (int i = 0; i < 14; i++) {
            x = ksXY.get(k);
            y = ksXY.get(k + 1);
        }

        detectCollision =  new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());


    }
    public void updates(int playerSpeed) {

        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + bitmap.getWidth();
        detectCollision.bottom = y + bitmap.getHeight();
    }

    public void nextPosition(){ k += 2;}


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