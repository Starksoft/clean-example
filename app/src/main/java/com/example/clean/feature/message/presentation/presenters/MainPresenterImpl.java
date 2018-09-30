package com.example.clean.feature.message.presentation.presenters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.example.clean.Utils;
import com.example.clean.core.domain.executor.Executor;
import com.example.clean.core.presentation.presenters.AbstractPresenter;
import com.example.clean.core.threading.MainThread;
import com.example.clean.feature.message.data.repositories.MessageRepository;
import com.example.clean.feature.message.data.repositories.MessageRepositoryImpl;
import com.example.clean.feature.message.domain.interactors.MessageInteractor;
import com.example.clean.feature.message.domain.interactors.MessageInteractorImpl;

@InjectViewState
public class MainPresenterImpl extends AbstractPresenter<MainPresenterView>
		implements MainPresenter, MessageInteractor.Callback/*, LoadMessageInteractor.Callback, SaveMessageInteractor.Callback */ {

	private static final String TAG = "MainPresenterImpl";

	//	@NonNull private final LoadMessageInteractor loadMessageInteractor;
	//	@NonNull private final SaveMessageInteractor saveMessageInteractor;
	@NonNull private final MessageInteractor messageInteractor;

	public MainPresenterImpl(@NonNull Context context, @NonNull Executor executor, @NonNull MainThread mainThread) {
		super(executor, mainThread);
		MessageRepository messageRepository = new MessageRepositoryImpl(context);
		//		loadMessageInteractor = new LoadMessageInteractorImpl(executor, mainThread, this, messageRepository);
		//		saveMessageInteractor = new SaveMessageInteractorImpl(executor, mainThread, this, messageRepository);
		messageInteractor = new MessageInteractorImpl(executor, mainThread, this, messageRepository);
	}

	@Override
	public void attachView(@NonNull MainPresenterView view) {
		super.attachView(view);
		Log.d(TAG, "attachView() called with: view = [" + view + "]");
	}


	@Override
	public void detachView(@NonNull MainPresenterView view) {
		super.detachView(view);
		Log.d(TAG, "detachView() called with: view = [" + view + "]");
	}

	@Override
	public void onError(@NonNull String message) {
		getViewState().showError(message);
	}

	//	@Override
	//	public void onMessageRetrieved(String message) {
	//		getViewState().displayMessage(message);
	//		getViewState().hideProgress();
	//	}
	//
	//	@Override
	//	public void onRetrievalFailed(String error) {
	//		getViewState().showError(error);
	//		getViewState().hideProgress();
	//	}
	//
	@Override
	public void readMessage() {
		messageInteractor.executeLoadMessage();
		getViewState().showProgress();
	}

	@Override
	public void saveMessage(String message) {
		messageInteractor.executeSaveMessage(message);
		getViewState().showProgress();
	}
	//
	//	@Override
	//	public void onMessageSaved() {
	//		getViewState().hideProgress();
	//	}
	//
	//	@Override
	//	public void onSaveFailed(String error) {
	//		getViewState().showError(error);
	//		getViewState().hideProgress();
	//	}

	@Override
	public void onMessageSaved(boolean success, @Nullable String explanation) {
		Utils.checkMainThread();

		getViewState().hideProgress();

		if (!success) {
			getViewState().showError(explanation);
		}
	}

	@Override
	public void onMessageRetrieved(boolean success, @Nullable String message) {
		Utils.checkMainThread();

		getViewState().hideProgress();

		if (success) {
			getViewState().displayMessage(message);
		} else {
			getViewState().showError(message);
		}

	}
}