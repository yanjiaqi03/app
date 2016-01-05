package com.ihealth.aijiakang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ihealth.aijiakang.adapter.datasource.RelationshipAdapterData;
import com.ihealth.aijiakang.imageloader.ImageLoaderTools;
import com.ihealth.aijiakang.widgets.FontTextView;

import java.util.ArrayList;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/11/27
 */
public class RelationshipAdapter extends CustomAdapter<RelationshipAdapterData,RelationshipAdapterViewHolder>{
    private ClickListener mClickListener = null;
    public RelationshipAdapter(Context context, ArrayList<RelationshipAdapterData> data,ClickListener mClickListener) {
        super(context, data, R.layout.adapter_relationship);
        this.mClickListener = mClickListener;
    }
    public interface ClickListener{
        public void itemClick(int position);
        public void deleteClick(int position);
    }
    @Override
    protected void findView(View convertView, RelationshipAdapterViewHolder holder) {
        holder.layout = (LinearLayout) convertView.findViewById(R.id.relatonship_adapter_layout);
        holder.icon = (ImageView) convertView.findViewById(R.id.relationship_listview_usericon);
        holder.name = (FontTextView) convertView.findViewById(R.id.relationship_listview_username);
        holder.delete = (FrameLayout) convertView.findViewById(R.id.relationship_listview_delete);
    }

    @Override
    protected RelationshipAdapterViewHolder initViewHolder() {
        return new RelationshipAdapterViewHolder();
    }

    @Override
    protected void initView(Context context, final int position, View convertView, ViewGroup parent, RelationshipAdapterViewHolder holder, ArrayList<RelationshipAdapterData> data) {
        ImageLoaderTools.getInstance().displayNetRoundImg(data.get(position).getLogoUrl(),holder.icon);
        holder.name.setText(data.get(position).getUserName());
        if(data.get(position).isEditing()){
            holder.layout.setClickable(false);
            holder.delete.setClickable(true);
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mClickListener!=null){
                        mClickListener.deleteClick(position);
                    }
                }
            });
        }else{
            holder.layout.setClickable(true);
            holder.delete.setClickable(false);
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mClickListener!=null){
                        mClickListener.itemClick(position);
                    }
                }
            });
        }
    }
}
class RelationshipAdapterViewHolder{
    LinearLayout layout;
    ImageView icon;
    FontTextView name;
    FrameLayout delete;
}