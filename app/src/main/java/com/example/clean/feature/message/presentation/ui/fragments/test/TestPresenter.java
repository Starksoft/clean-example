package com.example.clean.feature.message.presentation.ui.fragments.test;

import android.content.Context;
import android.support.annotation.NonNull;

import ru.starksoft.arch.clean.domain.executor.Executor;
import ru.starksoft.arch.clean.presentation.presenters.AbstractPresenter;

public class TestPresenter extends AbstractPresenter<TestView> {

	TestPresenter(@NonNull Context context, @NonNull Executor executor) {
		super(context, executor);
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