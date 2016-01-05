package com.ihealth.aijiakang.fragment;

import com.ihealth.aijiakang.activity.BaseActivity;
import com.ihealth.aijiakang.system.AJKLog;
import com.ihealth.aijiakang.widgets.dialog.LoadingDialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import iHealth.AiJiaKang.MI.MyApplication;

public abstract class BaseFragment extends Fragment {
	private final String TAG = "BaseFragment";
	private View mView = null;
	private int layoutId = 0;
	protected MyApplication myApplication;

	public BaseFragment() {
		this(0);
	}

	public BaseFragment(int layoutId) {
		this.layoutId = layoutId;
	}

	/**
	 * initviews and can use getActivity
	 * @param view 
	 * 		  the view created by fragment
	 * @author xiaohuai
	 **/
	public abstract void mViewCreated(Context context,View view);

	/**
	 * release some view sources
	 * 
	 * @author xiaohuai
	 **/
	public abstract void mOnDestroyView(Context context);

	/**
	 * when show this fragment has just be hidden (when viewCreated,this method is not be ran)
	 * 
	 * @author xiaohuai
	 **/
	public abstract void mOnShow();

	/**
	 * when hide this fragment has be created
	 * 
	 * @author xiaohuai
	 **/
	public abstract void mOnHidden();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.mView = inflater.inflate(layoutId, container, false);
		return this.mView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		if (getActivity() != null) {
			myApplication = (MyApplication) getActivity().getApplicationContext();
			mLoadingBar = new LoadingDialog(getActivity());
			mViewCreated(getActivity(),mView);
			mOnShow();
		}
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
//		AJKLog.d(TAG, "Fragment onDestroyView");
		mOnHidden();
		mView = null;
		if(getActivity()!=null){
			mOnDestroyView(getActivity());
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
//		AJKLog.d(TAG, "Fragment onDestroy");
	}

	@Override
	public void onDetach() {
		super.onDetach();
//		AJKLog.d(TAG, "Fragment onDetach");
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		if(hidden){
			mOnHidden();
		}else{
			mOnShow();
		}
	}
	/**
	 * Jump to fragment
	 * @param from 
	 * @param to
	 * @author xiaohuai
	 **/
	public void ChangeFragment(Fragment from, Fragment to) {
		if (getActivity() != null) {
			((BaseActivity) getActivity()).changeFragment(from, to);
		}
	}

	/**
	 * 加载框部分
	 * Author YanJiaqi
	 * Created at 15/11/24 下午10:53
	 */

	protected LoadingDialog mLoadingBar;
	protected void showLoadingBar(boolean isLoading) {
		if(getActivity() == null){
			return;
		}
		if(mLoadingBar!=null) {
			if (isLoading) {
				mLoadingBar.show();
			} else {
				mLoadingBar.dismiss();
			}
		}
	}
}
