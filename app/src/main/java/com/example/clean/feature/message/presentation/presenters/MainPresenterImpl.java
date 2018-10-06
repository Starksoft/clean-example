package com.example.clean.feature.message.presentation.presenters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.arch.clean.core.presentation.AndroidInjector;
import com.arch.clean.core.presentation.presenters.AbstractPresenter;
import com.example.clean.Utils;
import com.example.clean.feature.message.data.repositories.MessageRepository;
import com.example.clean.feature.message.data.repositories.MessageRepositoryImpl;
import com.example.clean.feature.message.domain.interactors.MessageInteractor;
import com.example.clean.feature.message.domain.interactors.MessageInteractorImpl;

public class MainPresenterImpl extends AbstractPresenter<MainPresenterView> implements MainPresenter, MessageInteractor.Callback {

	private static final String TAG = "MainPresenterImpl";
	@NonNull private final MessageInteractor messageInteractor;

	public MainPresenterImpl(@NonNull AndroidInjector androidInjector) {
		super(androidInjector);
		MessageRepository messageRepository = new MessageRepositoryImpl(context);
		messageInteractor = new MessageInteractorImpl(executor, mainThread, this, messageRepository);
	}

	@Override
	protected void onAttachView(@NonNull MainPresenterView view) {

	}

	@Override
	protected void onDetachView(@NonNull MainPresenterView view) {

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