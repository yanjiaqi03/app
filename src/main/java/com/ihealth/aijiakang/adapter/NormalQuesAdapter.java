package com.ihealth.aijiakang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.BaseExpandableListAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.ihealth.aijiakang.adapter.datasource.NormalQuesAdapterData;
import com.ihealth.aijiakang.utils.AnimationUtils;
import com.ihealth.aijiakang.widgets.AnimatedExpandableListView;

import java.util.ArrayList;
import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/12/5
 */
public class NormalQuesAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {
    private Context context;
    private LayoutInflater mLayoutInflater;
    private ArrayList<NormalQuesAdapterData> data = new ArrayList<NormalQuesAdapterData>();
    private ClickGroupListener mListener;

    public NormalQuesAdapter(Context context,ArrayList<NormalQuesAdapterData> data,ClickGroupListener mListener){
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.data = data;
        this.mListener = mListener;
    }

    public interface ClickGroupListener{
        public void click(int groupPosition);
    }
    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return data.get(groupPosition).getGroup();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(groupPosition).getChild();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ParentHolder holder = null;
        if(convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.adapter_normalques_parent,null);
            if(holder == null){
                holder = new ParentHolder();
            }

            holder.groupTv = (TextView) convertView.findViewById(R.id.normalques_parent_tv);
            holder.groupArrow = (ImageView) convertView.findViewById(R.id.normalques_parent_arrow);
            holder.groupLayout = (FrameLayout) convertView.findViewById(R.id.normalques_parent_layout);

            convertView.setTag(holder);
        }else{
            holder = (ParentHolder) convertView.getTag();
        }

        holder.groupTv.setText(data.get(groupPosition).getGroup());
        if(isExpanded){
            AnimationUtils.getInstance().runRotateAnim(holder.groupArrow, holder.groupArrow.getRotation(),
                    180f, 200, new DecelerateInterpolator());
        }else{
            if(holder.groupArrow.getRotation()!=0f) {
                AnimationUtils.getInstance().runRotateAnim(holder.groupArrow, holder.groupArrow.getRotation(),
                        0f, 200, new DecelerateInterpolator());
            }
        }

        holder.groupLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null){
                    mListener.click(groupPosition);
                }
            }
        });

        return convertView;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder holder = null;
        if(convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.adapter_normalques_child,null);
            if(holder == null){
                holder = new ChildHolder();
            }

            holder.childTv = (TextView) convertView.findViewById(R.id.normalques_child_tv);

            convertView.setTag(holder);
        }else{
            holder = (ChildHolder) convertView.getTag();
        }

        holder.childTv.setText(data.get(groupPosition).getChild());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
class ParentHolder{
    FrameLayout groupLayout;
    TextView groupTv;
    ImageView groupArrow;
}
class ChildHolder{
    TextView childTv;
}
