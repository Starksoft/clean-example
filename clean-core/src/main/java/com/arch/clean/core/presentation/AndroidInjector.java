package com.arch.clean.core.presentation;

import android.content.Context;
import android.support.annotation.NonNull;

import com.arch.clean.core.domain.executor.Executor;
import com.arch.clean.core.threading.MainThread;

public final class AndroidInjector {

	@NonNull private final Context context;
	@NonNull private final Executor executor;
	@NonNull private final MainThread mainThread;

	public AndroidInjector(@NonNull Context context, @NonNull Executor executor, @NonNull MainThread mainThread) {
		this.context = context;
		this.executor = executor;
		this.mainThread = mainThread;
	}

	@NonNull
	public Context getContext() {
		return context;
	}

	@NonNull
	public Executor getExecutor() {
		return executor;
	}

	@NonNull
	public MainThread getMainThread() {
		return mainThread;
	}
}
