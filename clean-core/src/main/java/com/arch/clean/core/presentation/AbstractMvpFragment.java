package com.arch.clean.core.presentation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.arch.clean.core.PresenterStore;
import com.arch.clean.core.presentation.presenters.BasePresenter;
import com.arch.clean.core.presentation.presenters.PresenterCreator;

@SuppressWarnings("unchecked")
public abstract class AbstractMvpFragment extends Fragment implements PresenterCreator, BaseMvpView {

	private final PresenterStore presenterStore = PresenterStore.getInstance();

	@NonNull
	@Override
	public String getPresenterTag() {
		FragmentActivity activity = getActivity();
		return String.format("%s_%s", activity != null ? activity.getClass().getName() : null, this.getClass().getSimpleName());
	}

	@NonNull
	protected <T extends BasePresenter> T getPresenter() {
		//noinspection unchecked
		return (T) presenterStore.getPresenter(this);
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		getPresenter().attachView(this);
	}

	@CallSuper
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		getPresenter().detachView(this);
	}

	@Override
	public void showError(@Nullable String message) {
		Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
	}
}