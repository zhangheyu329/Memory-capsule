package com.zql.lib_calendar.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.alibaba.android.arouter.launcher.ARouter;
import com.zql.comm.bean.Means;
import com.zql.comm.bean.NoteBean;
import com.zql.comm.route.RouteUrl;
import com.zql.lib_calendar.R;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by 尽途 on 2018/5/11.
 */

public class RecyclerViewTimeCardAdapter extends RecyclerView.Adapter<RecyclerViewTimeCardAdapter.Viewholder>{
    private ArrayList<NoteBean> notelist;
    private Context context;

    public RecyclerViewTimeCardAdapter(ArrayList<NoteBean>mnotelist, Context mcontext){
        Collections.reverse(mnotelist);//倒序
        this.notelist=mnotelist;
        this.context=mcontext;
    }


    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View iten_recycler= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclercard,parent,false);
        RecyclerViewTimeCardAdapter.Viewholder viewholder=new RecyclerViewTimeCardAdapter.Viewholder(iten_recycler);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        switch (notelist.get(position).getNotetype().toString()){
            case "旅行":
                holder.recycler_image_notetype.setImageResource(R.drawable.icon_travel);
                holder.recycler_text_note.setText(R.string.note_travel);
                break;
            case "学习":
                holder.recycler_image_notetype.setImageResource(R.drawable.icon_study);
                holder.recycler_text_note.setText(R.string.note_study);
                break;
            case "工作":
                holder.recycler_image_notetype.setImageResource(R.drawable.icon_work);
                holder.recycler_text_note.setText(R.string.note_work);
                break;
            case "日记":
                holder.recycler_image_notetype.setImageResource(R.drawable.icon_diary);
                holder.recycler_text_note.setText(R.string.note_diary);
                break;
            case "生活":
                holder.recycler_image_notetype.setImageResource(R.drawable.icon_live);
                holder.recycler_text_note.setText(R.string.note_live);
                break;
            default:
                break;
        }
        holder.recycler_text_note.setText(Means.getNotetextOnRecyclerCard(notelist.get(position).getNoteinfo()));
        holder.recycler_text_time.setText(notelist.get(position).getCreatetime());
        startNoteinfoActivity(holder.linearLayout,notelist.get(position));
    }


    @Override
    public int getItemCount() {
        return notelist==null ? 0 : notelist.size();
    }

    public  class Viewholder extends RecyclerView.ViewHolder{
        ImageView recycler_image_notetype,recycler_image_menu;
        TextView recycler_text_note,recycler_text_time;
        LinearLayout linearLayout;
        public Viewholder(View itemView){
            super(itemView);
            recycler_image_notetype=(ImageView)itemView.findViewById(R.id.recycler_image_notetype);
            recycler_image_menu=(ImageView)itemView.findViewById(R.id.recycler_image_menu);
            recycler_text_note=(TextView)itemView.findViewById(R.id.recycler_text_note);
            recycler_text_time=(TextView)itemView.findViewById(R.id.recycler_text_time);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.recycler_item);
        }
    }
    private void startNoteinfoActivity(View view,final NoteBean noteBean){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putSerializable("noteinfo", Means.changefromNotebean(noteBean));
                ARouter.getInstance().build(RouteUrl.Url_NoteinfoActivity).withBundle("info",bundle).navigation();
            }
        });
    }
}
