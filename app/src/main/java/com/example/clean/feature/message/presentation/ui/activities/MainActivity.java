package com.example.clean.feature.message.presentation.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.arellomobile.mvp.presenter.ProvidePresenterTag;
import com.example.clean.R;
import com.example.clean.core.domain.executor.ThreadExecutor;
import com.example.clean.core.etc.moxy.MvpActivity;
import com.example.clean.core.threading.MainThreadImpl;
import com.example.clean.feature.message.presentation.presenters.MainPresenterImpl;
import com.example.clean.feature.message.presentation.presenters.MainPresenterView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public final class MainActivity extends MvpActivity implements MainPresenterView {

	@InjectPresenter(type = PresenterType.GLOBAL) MainPresenterImpl presenter;

	@BindView(R.id.root) ViewGroup root;
	@BindView(R.id.input) EditText input;
	@BindView(R.id.progress) ProgressBar progress;
	@BindView(R.id.load) Button load;
	@BindView(R.id.save) Button save;

	private Unbinder unbinder;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		unbinder = ButterKnife.bind(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unbinder.unbind();
	}

	@ProvidePresenterTag(presenterClass = MainPresenterImpl.class, type = PresenterType.GLOBAL)
	String provideRepositoryPresenterTag() {
		return MainPresenterImpl.class.getSimpleName();
	}

	@ProvidePresenter(type = PresenterType.GLOBAL)
	MainPresenterImpl provideRepositoryPresenter() {
		return new MainPresenterImpl(getApplicationContext(), ThreadExecutor.getInstance(), MainThreadImpl.getInstance());
	}

	@OnClick({R.id.load, R.id.save})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.load:
				presenter.readMessage();
				break;

			case R.id.save:
				presenter.saveMessage(input.getText().toString());
				break;
		}
	}

	@Override
	public void showProgress() {
		setViewsEnabled(false);
		progress.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideProgress() {
		setViewsEnabled(true);
		progress.setVisibility(View.GONE);
	}

	private void setViewsEnabled(boolean enabled) {
		input.setEnabled(enabled);
		load.setEnabled(enabled);
		save.setEnabled(enabled);
	}

	@Override
	public void showError(@Nullable String message) {
		Snackbar.make(root, "Error: " + message, Snackbar.LENGTH_LONG).show();
	}

	@Override
	public void displayMessage(@Nullable String message) {
		input.setText(message);
		input.setSelection(message == null ? 0 : message.length());
	}
}