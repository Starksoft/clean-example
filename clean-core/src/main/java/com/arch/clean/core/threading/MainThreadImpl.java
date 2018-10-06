package com.arch.clean.core.threading;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

public final class MainThreadImpl implements MainThread {

	private static MainThread mainThread;

	private final Handler handler;

	private MainThreadImpl() {
		handler = new Handler(Looper.getMainLooper());
	}

	@NonNull
	public static MainThread getInstance() {
		if (mainThread == null) {
			mainThread = new MainThreadImpl();
		}

		return mainThread;
	}

	@Override
	public void post(@NonNull Runnable runnable) {
		handler.post(runnable);
	}
}