package com.arch.clean.core.presentation.presenters;

import android.content.Context;
import android.support.annotation.NonNull;

import com.arch.clean.core.domain.executor.Executor;
import com.arch.clean.core.presentation.ui.BaseView;
import com.arch.clean.core.threading.MainThread;

@SuppressWarnings("WeakerAccess")
public abstract class AbstractPresenter <V extends BaseView> implements BasePresenter<V> {

	@NonNull protected final Executor executor;
	@NonNull protected final MainThread mainThread;
	@NonNull protected final Context context;
	private V view;

	protected AbstractPresenter(@NonNull Context context, @NonNull Executor executor, @NonNull MainThread mainThread) {
		this.context = context;
		this.executor = executor;
		this.mainThread = mainThread;
	}

	protected V getView() {
		return view;
	}

	@Override
	public final void attachView(@NonNull V view) {
		this.view = view;
		onAttachView(view);
	}

	@Override
	public final void detachView(@NonNull V view) {
		onDetachView(view);
	}

	protected abstract void onAttachView(@NonNull V view);

	protected abstract void onDetachView(@NonNull V view);
}