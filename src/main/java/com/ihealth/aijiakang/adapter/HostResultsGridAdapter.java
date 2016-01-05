package com.ihealth.aijiakang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ihealth.aijiakang.adapter.datasource.SelectPersonAdapterData;
import com.ihealth.aijiakang.imageloader.ImageLoaderTools;

import java.util.ArrayList;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/12/10
 */
public class HostResultsGridAdapter extends CustomAdapter<SelectPersonAdapterData,GridViewHolder>{
    private ArrayList<SelectPersonAdapterData> data = null;
    public HostResultsGridAdapter(Context context, ArrayList<SelectPersonAdapterData> data) {
        super(context, data, R.layout.adapter_hostresults_select);
        this.data = data;
    }

    @Override
    protected void findView(View convertView, GridViewHolder holder) {
        holder.userIcon = (ImageView) convertView.findViewById(R.id.adapter_hostresults_selectitem_icon);
        holder.userName = (TextView) convertView.findViewById(R.id.adapter_hostresults_selectitem_name);
        holder.selectedBg = (ImageView) convertView.findViewById(R.id.adapter_hostresults_selectitem_selectedbg);
    }

    @Override
    protected GridViewHolder initViewHolder() {
        return new GridViewHolder();
    }

    @Override
    protected void initView(Context context, int position, View convertView, ViewGroup parent, GridViewHolder holder, ArrayList<SelectPersonAdapterData> data) {
        ImageLoaderTools.getInstance().displayNetRoundImg(data.get(position).getLogoUrl(), holder.userIcon);
        holder.userName.setText(data.get(position).getUserName());
        if(data.get(position).isSelected()){
            holder.selectedBg.setVisibility(View.VISIBLE);
        }else{
            holder.selectedBg.setVisibility(View.GONE);
        }
    }
}

class GridViewHolder{
    ImageView userIcon;
    TextView userName;
    ImageView selectedBg;
}
