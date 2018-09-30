package com.example.clean.core.presentation.presenters;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;
import com.example.clean.core.domain.executor.Executor;
import com.example.clean.core.threading.MainThread;

/**
 * This is a base class for all presenters which are communicating with interactors. This base class will hold a
 * reference to the Executor and MainThread objects that are needed for running interactors in a background thread.
 */
@SuppressWarnings("WeakerAccess")
public abstract class AbstractPresenter <V extends MvpView> extends MvpPresenter<V> {

	@NonNull protected final Executor executor;
	@NonNull protected final MainThread mainThread;

	public AbstractPresenter(@NonNull Executor executor, @NonNull MainThread mainThread) {
		super();
		this.executor = executor;
		this.mainThread = mainThread;
	}

	@Override
	protected void onFirstViewAttach() {
	}

	@Override
	@CallSuper
	public void attachView(V view) {
		super.attachView(view);
	}

	@Override
	@CallSuper
	public void detachView(V view) {
		super.detachView(view);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}