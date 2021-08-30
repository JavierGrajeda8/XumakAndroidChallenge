package com.jgrajeda.xumakandroid.interfaces;
import com.jgrajeda.xumakandroid.classes.Character;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CharacterService {

    String API_ROUTE = "/api/characters";

    @GET(API_ROUTE)
    Call< List<Character> > getCharacter(@Query("limit") String limit, @Query("offset") String offset);

}