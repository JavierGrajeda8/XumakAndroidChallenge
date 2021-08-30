package com.jgrajeda.xumakandroid.classes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jgrajeda.xumakandroid.R;
import com.jgrajeda.xumakandroid.data.DataProvider;

import java.util.ArrayList;

public class CharacterAdapter extends ArrayAdapter<Character> {
    public CharacterAdapter(Context context, ArrayList<Character> characters) {
        super(context, 0, characters);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Character character = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.character_item, parent, false);
        }
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView nickname = (TextView) convertView.findViewById(R.id.nickname);
        ImageView img = (ImageView) convertView.findViewById(R.id.img);
        ImageView imgHeart = (ImageView) convertView.findViewById(R.id.imgHeart);
        Log.i("Characer Adapter", "" + position + "");
        if (character.getFavorite() == 1){
            imgHeart.setImageResource(R.drawable.heartfullf);
        } else {
            imgHeart.setImageResource(R.drawable.heartemptyf);
        }
        name.setText(character.getName());
        nickname.setText(character.getNickname());
        Log.i("img",character.getImg());
        new ImageDownload(img,150,200).execute(character.getImg());
        imgHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataProvider dp = new DataProvider(getContext());
                Log.i("Info", "Click on heart" + position);
                Character character = getItem(position);
                if (character.getFavorite() == 1) {
                    character.setFavorite(0);
                    dp.setFavorite(character.getChar_id(), false);
                    imgHeart.setImageResource(R.drawable.heartemptyf);
                } else {
                    character.setFavorite(1);
                    dp.setFavorite(character.getChar_id(), true);
                    imgHeart.setImageResource(R.drawable.heartfullf);
                }

            }
        });

        return convertView;
    }
}
