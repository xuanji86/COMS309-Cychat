package com.example.exp2;

import android.content.Context;

public class Greating {
    private static Context hold;
    private static int res;
    private static int dura;
    private static Greating g;
    public Greating(Context context){
        hold = context;
        g = this;
    }
    public static Greating makeText(Context context, int resId, int duration){
        hold = context;
        res = resId;
        dura = duration;
        return g;
    }
    public void show(){
        
    }
}
