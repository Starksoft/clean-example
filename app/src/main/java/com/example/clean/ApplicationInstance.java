package com.example.clean;

import android.app.Application;
import android.util.Log;

public class ApplicationInstance extends Application {

	public static final String TAG = "ApplicationInstance";

	@Override
	public void onCreate() {
		super.onCreate();

		Log.d(TAG, "onCreate");
	}
}