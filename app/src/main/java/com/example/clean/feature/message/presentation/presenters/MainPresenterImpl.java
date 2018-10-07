package com.example.clean.feature.message.presentation.presenters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.arch.clean.core.domain.executor.Executor;
import com.arch.clean.core.presentation.presenters.AbstractPresenter;
import com.arch.clean.core.threading.MainThread;
import com.example.clean.R;
import com.example.clean.Utils;
import com.example.clean.feature.message.data.repositories.MessageRepository;
import com.example.clean.feature.message.data.repositories.MessageRepositoryImpl;
import com.example.clean.feature.message.domain.interactors.MessageInteractor;
import com.example.clean.feature.message.domain.interactors.MessageInteractorImpl;

public class MainPresenterImpl extends AbstractPresenter<MainPresenterView> implements MainPresenter, MessageInteractor.Callback {

	private static final String TAG = "MainPresenterImpl";
	@NonNull private final MessageInteractor messageInteractor;

	public MainPresenterImpl(@NonNull Context context, @NonNull Executor executor, @NonNull MainThread mainThread) {
		super(context, executor, mainThread);
		MessageRepository messageRepository = new MessageRepositoryImpl(context);
		messageInteractor = new MessageInteractorImpl(executor, mainThread, this, messageRepository);
	}

	@Override
	protected void onAttachView(@NonNull MainPresenterView view) {
		Log.d(TAG, "onAttachView() called with: view = [" + view + "]");

		String string = context.getString(R.string.app_name);

		Log.d(TAG, "onAttachView: string=" + string);
	}

	@Override
	protected void onDetachView(@NonNull MainPresenterView view) {
		Log.d(TAG, "onDetachView() called with: view = [" + view + "]");
	}

	@Override
	public void readMessage() {
		messageInteractor.executeLoadMessage();
		getView().showProgress();
	}

	@Override
	public void saveMessage(String message) {
		messageInteractor.executeSaveMessage(message);
		getView().showProgress();
	}

	@Override
	public void onMessageSaved(boolean success, @Nullable String explanation) {
		Utils.checkMainThread();

		getView().hideProgress();

		if (!success) {
			getView().showError(explanation);
		}
	}

	@Override
	public void onMessageRetrieved(boolean success, @Nullable String message) {
		Utils.checkMainThread();

		getView().hideProgress();

		if (success) {
			getView().displayMessage(message);
		} else {
			getView().showError(message);
		}

	}
}