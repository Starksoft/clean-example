package com.arch.clean.core.presentation.presenters;

import android.content.Context;
import android.support.annotation.NonNull;

import com.arch.clean.core.domain.executor.Executor;
import com.arch.clean.core.presentation.AndroidInjector;
import com.arch.clean.core.presentation.ui.BaseView;
import com.arch.clean.core.threading.MainThread;

@SuppressWarnings("WeakerAccess")
public abstract class AbstractPresenter <V extends BaseView> implements BasePresenter<V> {

	@NonNull protected final Executor executor;
	@NonNull protected final MainThread mainThread;
	@NonNull protected final Context context;
	private V view;

	public AbstractPresenter(@NonNull AndroidInjector androidInjector) {
		executor = androidInjector.getExecutor();
		mainThread = androidInjector.getMainThread();
		context = androidInjector.getContext();
	}

	protected V getView() {
		return view;
	}

	public final void attachView(@NonNull V view) {
		this.view = view;
		onAttachView(view);
	}

	public final void detachView(@NonNull V view) {
		onDetachView(view);
	}

	protected abstract void onAttachView(@NonNull V view);

	protected abstract void onDetachView(@NonNull V view);
}