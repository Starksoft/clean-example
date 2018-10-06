package com.arch.clean.core.presentation.ui;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.arch.clean.core.PresenterStore;
import com.arch.clean.core.presentation.presenters.BasePresenter;
import com.arch.clean.core.presentation.presenters.PresenterCreator;

public abstract class AbstractFragment extends Fragment implements PresenterCreator, BaseView {

	private final PresenterStore presenterStore = PresenterStore.getInstance();

	@NonNull
	@Override
	public String getPresenterTag() {
		return getActivity().getClass().getName() + "_" + this.getClass().getSimpleName();
	}

	@NonNull
	protected <T extends BasePresenter> T getPresenter() {
		//noinspection unchecked
		return (T) presenterStore.getPresenter(this);
	}

	@Override
	public void showError(@Nullable String message) {
		Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
	}
}