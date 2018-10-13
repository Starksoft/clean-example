package com.example.clean.feature.message.presentation.ui.fragments.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.starksoft.arch.clean.domain.executor.ThreadExecutor;
import ru.starksoft.arch.clean.presentation.AbstractMvpFragment;
import ru.starksoft.arch.clean.presentation.presenters.BasePresenter;

public final class TestFragment extends AbstractMvpFragment implements TestView {

	private final TestPresenter presenter = getPresenter();

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		presenter.action();
	}

	@NonNull
	@Override
	public BasePresenter createPresenter() {
		return new TestPresenter(getContext(), ThreadExecutor.getInstance());
	}

}