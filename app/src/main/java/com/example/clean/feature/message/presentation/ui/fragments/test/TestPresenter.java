package com.example.clean.feature.message.presentation.ui.fragments.test;

import android.content.Context;
import android.support.annotation.NonNull;

import com.arch.clean.core.domain.executor.Executor;
import com.arch.clean.core.presentation.presenters.AbstractPresenter;
import com.arch.clean.core.threading.MainThread;

public class TestPresenter extends AbstractPresenter<TestView> {

	TestPresenter(@NonNull Context context, @NonNull Executor executor, @NonNull MainThread mainThread) {
		super(context, executor, mainThread);
	}

	@Override
	protected void onAttachView(@NonNull TestView view) {

	}

	@Override
	protected void onDetachView(@NonNull TestView view) {

	}

	public void action() {
	}
}