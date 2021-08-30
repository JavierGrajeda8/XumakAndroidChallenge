package com.jgrajeda.xumakandroid;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jgrajeda.xumakandroid.classes.Character;
import com.jgrajeda.xumakandroid.classes.ImageDownload;
import com.jgrajeda.xumakandroid.data.DataProvider;

public class CharacterDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);
        Intent intent = getIntent();
        String img = intent.getStringExtra("img"); //if it's a string you stored.
        String name = intent.getStringExtra("name"); //if it's a string you stored.
        String nickname  = intent.getStringExtra("nickname"); //if it's a string you stored.
        int imgHeart = intent.getIntExtra("imgHeart",0); //if it's a string you stored.
        int char_id = intent.getIntExtra("char_id",0); //if it's a string you stored.
        String portrayed = intent.getStringExtra("portrayed"); //if it's a string you stored.
        String occupation = intent.getStringExtra("occupation"); //if it's a string you stored.
        String status = intent.getStringExtra("status"); //if it's a string you stored.
        TextView nameText = (TextView)  findViewById(R.id.name);
        TextView portrayedText = (TextView)  findViewById(R.id.portrayed);
        TextView statusText = (TextView)  findViewById(R.id.status);
        TextView occupationText = (TextView)  findViewById(R.id.occupation);
        ImageView imgView = (ImageView) findViewById(R.id.img);
        ImageView imgHeartView = (ImageView) findViewById(R.id.imgHeart);
        setTitle(name);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nameText.setText(nickname);
        portrayedText.setText(portrayed);
        statusText.setText(status);
        occupationText.setText(occupation);
        new ImageDownload(imgView, 330,440).execute(img);
        Log.i("imgHeart", "" + imgHeart);
        if  (imgHeart == 1){
            imgHeartView.setImageResource(R.drawable.heartfullf);
        } else {
            imgHeartView.setImageResource(R.drawable.heartemptyf);
        }



        imgHeartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataProvider dp = new DataProvider(getApplicationContext());
                if (imgHeart == 1) {
                    dp.setFavorite(char_id, false);
                    imgHeartView.setImageResource(R.drawable.heartemptyf);
                } else {
                    dp.setFavorite(char_id, true);
                    imgHeartView.setImageResource(R.drawable.heartfullf);
                }

            }
        });


    }

    public boolean onOptionsItemSelected(MenuItem item){
        this.onBackPressed();
        return true;
    }
}