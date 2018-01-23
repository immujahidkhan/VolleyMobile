package com.apptrends.volleymobile;

import com.android.volley.toolbox.StringRequest;

/**
 * Created by mujahidkhan on 12/14/17.
 */

public class ModelClass {
    String name;
    String email;
    String pass;
    String image;
    public ModelClass()
    {

    }

    public ModelClass(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

}
