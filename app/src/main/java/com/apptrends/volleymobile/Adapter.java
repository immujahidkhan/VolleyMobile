package com.apptrends.volleymobile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;



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
        View v = LayoutInflater.from(context).inflate(R.layout.card_view,parent,false);
         return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ModelClass data = mList.get(position);

        holder.title.setText(data.getName());
        /*holder.tags.setText("Email:"+data.getEmail());
        holder.type.setText("Password:"+data.getPass());
        //Glide.with(holder.image.getContext()).load(data.getWebformatURL()).into(holder.image);
        Picasso.with(context).load(data.getImage()).fit().centerInside().into(holder.image);*/

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        View v;
        TextView title,tags,type;
        ImageView image;
        public MyViewHolder(View itemView) {
            super(itemView);
            v=itemView;
            title = v.findViewById(R.id.card_title);
            image = v.findViewById(R.id.card_image);
            type = v.findViewById(R.id.card_type);
            tags = v.findViewById(R.id.card_tag);
        }
    }
}
