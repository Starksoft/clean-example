package ru.starksoft.arch.clean.presentation.presenters;

import android.content.Context;
import android.support.annotation.NonNull;

import ru.starksoft.arch.clean.domain.executor.Executor;
import ru.starksoft.arch.clean.presentation.BaseMvpView;

@SuppressWarnings("WeakerAccess")
public abstract class AbstractPresenter <V extends BaseMvpView> implements BasePresenter<V> {

	@NonNull protected final Executor executor;
	@NonNull protected final Context context;
	private V view;

	protected AbstractPresenter(@NonNull Context context, @NonNull Executor executor) {
		this.context = context;
		this.executor = executor;
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