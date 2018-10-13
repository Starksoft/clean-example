package com.example.clean.feature.message.presentation.presenters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.clean.R;
import ru.starksoft.arch.clean.utils.ThreadUtils;
import com.example.clean.feature.message.data.repositories.MessageRepository;
import com.example.clean.feature.message.data.repositories.MessageRepositoryImpl;
import com.example.clean.feature.message.domain.interactors.MessageInteractor;
import com.example.clean.feature.message.domain.interactors.MessageInteractorImpl;

import ru.starksoft.arch.clean.domain.executor.Executor;
import ru.starksoft.arch.clean.presentation.presenters.AbstractPresenter;

public class MainPresenterImpl extends AbstractPresenter<MainPresenterView> implements MainPresenter, MessageInteractor.Callback {

	private static final String TAG = "MainPresenterImpl";
	@NonNull private final MessageInteractor messageInteractor;

	public MainPresenterImpl(@NonNull Context context, @NonNull Executor executor) {
		super(context, executor);
		MessageRepository messageRepository = new MessageRepositoryImpl(context);
		messageInteractor = new MessageInteractorImpl(executor, this, messageRepository);
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
		ThreadUtils.checkMainThread();

		getView().hideProgress();

		if (!success) {
			getView().showError(explanation);
		}
	}

	@Override
	public void onMessageRetrieved(boolean success, @Nullable String message) {
		ThreadUtils.checkMainThread();

		getView().hideProgress();

		if (success) {
			getView().displayMessage(message);
		} else {
			getView().showError(message);
		}

	}
}