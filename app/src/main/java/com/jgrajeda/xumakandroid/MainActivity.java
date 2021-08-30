package com.jgrajeda.xumakandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jgrajeda.xumakandroid.classes.Character;
import com.jgrajeda.xumakandroid.classes.CharacterAdapter;
import com.jgrajeda.xumakandroid.data.DataProvider;
import com.jgrajeda.xumakandroid.interfaces.CharacterService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView list;
    ArrayList<Character> characters = new ArrayList<>();
    CharacterAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Breaking Bad Characters");
        setContentView(R.layout.activity_main);
        getCharactersAPI(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getCharacters(null);
    }

    private void getCharacters(Context context){
        DataProvider dp = new DataProvider(getBaseContext());
        characters = dp.getCharacterById(0);
        adapter = new CharacterAdapter(this, characters);
        list = findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,   long arg3) {
                // TODO Auto-generated method stub
                Intent myIntent = new Intent(MainActivity.this, CharacterDetail.class);
                myIntent.putExtra("char_id", characters.get(arg2).getChar_id()); //Optional parameters
                myIntent.putExtra("img", characters.get(arg2).getImg()); //Optional parameters
                myIntent.putExtra("name", characters.get(arg2).getName()); //Optional parameters
                myIntent.putExtra("imgHeart", characters.get(arg2).getFavorite()); //Optional parameters
                myIntent.putExtra("occupation", characters.get(arg2).getOcupation()); //Optional parameters
                myIntent.putExtra("portrayed", characters.get(arg2).getPortrayed()); //Optional parameters
                myIntent.putExtra("status", characters.get(arg2).getStatus()); //Optional parameters
                myIntent.putExtra("nickname", characters.get(arg2).getNickname()); //Optional parameters
                MainActivity.this.startActivity(myIntent);
            }

        });
    }

    private void getCharactersAPI(Context context) {
        Log.i("TAG", "LOAD");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.breakingbadapi.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CharacterService characterService = retrofit.create(CharacterService.class);
        Call<List<Character>> call = characterService.getCharacter("30", "0");
        DataProvider dp = new DataProvider(getBaseContext());

        call.enqueue(new Callback<List<Character>>() {
            @Override
            public void onResponse(Call<List<Character>> call, Response<List<Character>> response) {
                for(Character character : response.body()) {
                    dp.saveCharacter(character);
                }
                getCharacters(context);
            }

            @Override
            public void onFailure(Call<List<Character>> call, Throwable t) {
            }
        });
    }

}