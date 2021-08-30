package com.jgrajeda.xumakandroid.classes;

public class Character {
    private int char_id;
    private String name;
    private String birthday;
     private String[] occupation;
    private String img;
    private String status;
    private String nickname;
    private String[] appearance;
    private String portrayed;
    private String category;
    private int favorite;
    private int[] better_call_saul_appearance;

    public int getChar_id(){
        return char_id;
    }
    public void setChar_id(int char_id){
        this.char_id = char_id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday(){
        return birthday;
    }

    public void setBirthday(String birthday){
        this.birthday = birthday;
    }

    public String getImg(){
        return img;
    }

    public void setImg(String img){
        this.img = img;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getNickname(){
        return nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    public String getPortrayed(){
        return portrayed;
    }

    public void setPortrayed(String portrayed){
        this.portrayed = portrayed;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public int getFavorite(){
        return favorite;
    }

    public void setFavorite(int favorite){
        this.favorite = favorite;
    }

    public void setOccupation(String[] occupation){
        this.occupation = occupation;
    }

    public String getOcupation() {
        String resp = "";
        for (String oc: occupation
             ) {
            resp += oc + ", ";
        }
        resp = resp.substring(0,resp.length() -2 );
        return resp;
    }

    public void setAppearance(String[] appearance){
        this.appearance = appearance;
    }

    public String[] getAppearance() {
        return appearance;
    }

    public void setBetter_call_saul_appearance(int[] better_call_saul_appearance){
        this.better_call_saul_appearance = better_call_saul_appearance;
    }

    public int[] getBetter_call_saul_appearance() {
        return better_call_saul_appearance;
    }
}
