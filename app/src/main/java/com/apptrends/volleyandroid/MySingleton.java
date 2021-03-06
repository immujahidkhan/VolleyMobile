package com.apptrends.volleyandroid;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by mujahidkhan on 12/15/17.
 */

public class MySingleton {
    private static MySingleton mInstance;
    private RequestQueue requestQueue;
    private static Context mCtx;

    private MySingleton(Context context)
    {
        mCtx = context;
        requestQueue = getRequestQueue();
    }
    public RequestQueue getRequestQueue()
    {
        if(requestQueue==null)
        {
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return requestQueue;
    }
    public  static synchronized MySingleton getInstance(Context context)
    {
        if (mInstance==null)
        {
            mInstance = new MySingleton(context);
        }
        return mInstance;
    }
    public<T> void addToRequestQue(Request<T> request)
    {
        requestQueue.add(request);
    }
}
