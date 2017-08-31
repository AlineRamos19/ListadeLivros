package com.example.android.listadelivros;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.net.URL;


public class ImagemUrlTask extends AsyncTask<URL, Void, Bitmap> {

    ImageView imgURL;

    public ImagemUrlTask(ImageView img) {
        imgURL = img;
    }

    @Override
    protected Bitmap doInBackground(URL... urls) {
        Bitmap mBitmap = null;
        URL urlBitmap = urls[0];

        try {
            mBitmap = BitmapFactory.decodeStream(urlBitmap.openConnection().getInputStream());
            mBitmap = Bitmap.createScaledBitmap(mBitmap, 200, 250, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mBitmap;
    }

    @Override
    protected void onPostExecute(Bitmap resultado) {
        imgURL.setImageBitmap(resultado);
    }
}