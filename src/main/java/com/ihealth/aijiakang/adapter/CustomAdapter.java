package com.ihealth.aijiakang.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class CustomAdapter<E, T> extends BaseAdapter {
	private Context context;
	private ArrayList<E> data = new ArrayList<E>();
	private int layoutResid;
	private LayoutInflater mInflater;

	public CustomAdapter(Context context, ArrayList<E> data, int layoutResid) {
		this.context = context;
		this.data = data;
		this.layoutResid = layoutResid;
		this.mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		T holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(layoutResid, null);
			if (holder == null) {
				holder = initViewHolder();
			}
			findView(convertView, holder);

			convertView.setTag(holder);
		} else {
			holder = (T) convertView.getTag();
		}

		initView(context,position, convertView, parent, holder,data);

		return convertView;
	}

	protected abstract void findView(View convertView, T holder);

	protected abstract T initViewHolder();

	protected abstract void initView(Context context,int position, View convertView, ViewGroup parent, T holder ,ArrayList<E> data);
}
