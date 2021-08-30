package com.jgrajeda.xumakandroid.classes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class ImageDownload extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;
    int width;
    int heigth;
    public ImageDownload(ImageView bmImage, int width, int height) {
        this.bmImage = bmImage;
        this.width = width;
        this.heigth = height;
    }

    public static Bitmap convertToMutable(Bitmap imgIn) {
        try {
            File file = new File(Environment.getExternalStorageDirectory() + File.separator + "temp.tmp");

            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");

            int width = imgIn.getWidth();
            int height = imgIn.getHeight();
            Bitmap.Config type = imgIn.getConfig();

            FileChannel channel = randomAccessFile.getChannel();
            MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, imgIn.getRowBytes()*height);
            imgIn.copyPixelsToBuffer(map);
            imgIn.recycle();
            System.gc();

            imgIn = Bitmap.createBitmap(width, height, type);
            map.position(0);
            imgIn.copyPixelsFromBuffer(map);
            channel.close();
            randomAccessFile.close();

            file.delete();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imgIn;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
            mIcon11 = Bitmap.createScaledBitmap(mIcon11,width,heigth,false);

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        Bitmap aux = result;
        Log.i("onPostExecute", "");
        bmImage.setImageBitmap(aux);
    }
}
