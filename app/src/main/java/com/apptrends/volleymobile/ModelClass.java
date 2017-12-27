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

    public ModelClass(String name, String email, String pass, String image) {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public String getImage() {
        return image;
    }
}
