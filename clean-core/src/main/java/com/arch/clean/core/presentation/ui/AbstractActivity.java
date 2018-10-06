package com.arch.clean.core.presentation.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.arch.clean.core.PresenterStore;
import com.arch.clean.core.presentation.presenters.BasePresenter;
import com.arch.clean.core.presentation.presenters.PresenterCreator;

public abstract class AbstractActivity extends AppCompatActivity implements PresenterCreator, BaseView {

	private final PresenterStore presenterStore = PresenterStore.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		presenterStore.onCreate(this, savedInstanceState);
	}

	@Override
	protected void onResume() {
		super.onResume();

		presenterStore.onAttach(this, this);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		//presenterStore.onSaveInstanceState(getPresenterTag(), outState);
		presenterStore.onDetach(this, this);
	}

	@Override
	protected void onStop() {
		super.onStop();

		presenterStore.onDetach(this, this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		presenterStore.onDestroyView(this);

		if (isFinishing()) {
			presenterStore.onDestroy(this);
		}
	}

	@NonNull
	@Override
	public String getPresenterTag() {
		return getClass().getName();
	}

	@NonNull
	protected <T extends BasePresenter> T getPresenter() {
		//noinspection unchecked
		return (T) presenterStore.getPresenter(this);
	}

	@Override
	public void showError(@Nullable String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}
}