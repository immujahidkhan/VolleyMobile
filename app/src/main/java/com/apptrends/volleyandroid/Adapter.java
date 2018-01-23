package com.apptrends.volleyandroid;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by mujahidkhan on 12/14/17.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    Context context;
    ArrayList<ModelClass> mList;

    public Adapter(Context context, ArrayList<ModelClass> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_view, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final ModelClass data = mList.get(position);
        final String id = mList.get(position).toString();
        holder.name.setText(data.getName());
        holder.email.setText(data.getEmail());
        holder.gender.setText(data.getGender());
        holder.city.setText(data.getCity());
        holder.country.setText(data.getCountry());
        Typeface ralewayRegular = Typeface.createFromAsset(context.getAssets(), "fonts/Raleway-Regular.ttf");
        Typeface fontAwsome = Typeface.createFromAsset(context.getAssets(), "fonts/fontawesome-webfont.ttf");
        holder.more.setTypeface(fontAwsome);
        holder.more.setText("\uF142");
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence options[] = new CharSequence[]{"Delete", "Update"};
                android.support.v7.app.AlertDialog.Builder mBuilder = new android.support.v7.app.AlertDialog.Builder(context);
                mBuilder.setTitle("Select Options");
                mBuilder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) {
                            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                        }
                        if (i == 1) {
                            Toast.makeText(context, "Update", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                android.support.v7.app.AlertDialog dialog = mBuilder.create();
                dialog.getWindow().setLayout(200,100);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();

                wmlp.gravity = Gravity.TOP | Gravity.LEFT;
                wmlp.x = 100;   //x position
                wmlp.y = 100;   //y position

                dialog.show();

            }
        });

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You Click Me:" + data.getId().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        View v;
        TextView name, email, gender, city, country,more;

        public MyViewHolder(View itemView) {
            super(itemView);
            v = itemView;
            name = v.findViewById(R.id.card_name);
            email = v.findViewById(R.id.card_email);
            gender = v.findViewById(R.id.card_gender);
            city = v.findViewById(R.id.card_city);
            country = v.findViewById(R.id.card_country);
            more = v.findViewById(R.id.more);
        }
    }
}
