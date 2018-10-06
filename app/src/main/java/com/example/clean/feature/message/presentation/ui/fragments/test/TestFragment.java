package com.example.clean.feature.message.presentation.ui.fragments.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.arch.clean.core.domain.executor.ThreadExecutor;
import com.arch.clean.core.presentation.presenters.BasePresenter;
import com.arch.clean.core.presentation.ui.AbstractFragment;
import com.arch.clean.core.threading.MainThreadImpl;

public final class TestFragment extends AbstractFragment implements TestView {

	private final TestPresenter presenter = getPresenter();

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		presenter.action();
	}

	@NonNull
	@Override
	public BasePresenter createPresenter() {
		return new TestPresenter(getContext(), ThreadExecutor.getInstance(), MainThreadImpl.getInstance());
	}

	@Override
	public void showProgress() {
	}

	@Override
	public void hideProgress() {
	}

	@Override
	public void manageProgress(boolean show) {
	}
}