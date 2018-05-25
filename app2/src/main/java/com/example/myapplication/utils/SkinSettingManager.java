package com.example.myapplication.utils;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.example.myapplication.R;

public class SkinSettingManager {

	private final static String SKIN_PREF = "skinSetting";
	private SharedPreferences skinSettingPreference;
	private String key = "skin_type";
	private Editor editor;
	private int[] skinResources = { R.style.AppTheme, R.style.NightAppTheme };
	private Activity mActivity;

	public SkinSettingManager(Activity activity) {
		this.mActivity = activity;
		skinSettingPreference = mActivity.getSharedPreferences(SKIN_PREF, 3);
	}

	/**
	 * 获取当前程序的皮肤序号
	 * 
	 * @return
	 */
	public int getSkinType() {

		return skinSettingPreference.getInt(key, 0);
	}

	/**
	 * 把皮肤序号写到全局设置里去
	 * 
	 * @param j
	 */
	public void setSkinType(int j) {

		editor = skinSettingPreference.edit();
		editor.putInt(key, j);
		editor.commit();
	}
}