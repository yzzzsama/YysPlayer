package com.example.yzzz.retrofit2use;

public class ShishenInfo {
    private String name;
    private String imageUrl;
    private String type;
    private String si;

    public ShishenInfo(String name, String Url,String type,String skillIntroduction){
        this.name = name;
        this.imageUrl = Url;
        this.type = type;
        si = skillIntroduction;

    }
    //技能简介
    public String getSkillIntroduction() {
        return si;
    }

    public String getName() {
        return name;
    }

    //式神图片url
    public String getImageUrl() {
        return imageUrl;
    }

    public String getType() {
        return type;
    }
}
