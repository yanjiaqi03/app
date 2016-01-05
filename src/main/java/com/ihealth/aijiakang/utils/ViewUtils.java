package com.ihealth.aijiakang.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;

/**
 * Created by YanJiaqi on 15/12/10
 */
public class ViewUtils {
    private static ViewUtils mInstance = null;
    public static ViewUtils getInstance(){
        if(mInstance == null){
            mInstance = new ViewUtils();
        }

        return mInstance;
    }

    /**
     * 设置AbsListView高度自适应
     * Author YanJiaqi
     * Created at 15/12/10 下午4:05
     */

    public void autoHeightAbsListView(BaseAdapter listAdapter,AbsListView listView,int col,int maxHeight){
        if(listAdapter == null || listView == null || col <= 0 || maxHeight <= 0)
            return;
        int totalHeight = getAbsListViewHeight(listAdapter,listView,col);
        //这里listview Item 的 root不能是 RelativeLayout
        if(totalHeight > maxHeight){
            totalHeight = maxHeight;
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        if(params!=null){
            params.height = totalHeight;
        }
        listView.setLayoutParams(params);
    }

    /**
     * 获取AbsListView高度
     * Author YanJiaqi
     * Created at 15/12/10 下午4:24
     */

    public int getAbsListViewHeight(BaseAdapter listAdapter,AbsListView listView,int col){
        if(listAdapter == null || listView == null || col <= 0)
            return 0;
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i += col) {
            // 获取listview的每一个item
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            // 获取item的高度和
            totalHeight += listItem.getMeasuredHeight();
        }

        return totalHeight;
    }
}
