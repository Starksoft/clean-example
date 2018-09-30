package com.example.clean.feature.message.domain.interactors;

import android.support.annotation.NonNull;

import com.example.clean.core.domain.executor.Executor;
import com.example.clean.core.domain.interactors.AbstractInteractor;
import com.example.clean.core.threading.MainThread;
import com.example.clean.feature.message.data.repositories.MessageRepository;

public final class MessageInteractorImpl extends AbstractInteractor implements MessageInteractor {

	@NonNull private final MessageInteractor.Callback callback;
	@NonNull private final MessageRepository repository;

	public MessageInteractorImpl(@NonNull Executor threadExecutor, @NonNull MainThread mainThread,
	                             @NonNull MessageInteractor.Callback callback, @NonNull MessageRepository repository) {
		super(threadExecutor, mainThread);
		this.callback = callback;
		this.repository = repository;
	}

	@Override
	public void executeLoadMessage() {
		execute(() -> {
			String message = repository.getMessage();

			postOnMainThread(() -> {
				boolean success = message != null && message.length() > 0;
				callback.onMessageRetrieved(success, success ? message : "Error loading message");
			});
		});
	}

	@Override
	public void executeSaveMessage(@NonNull String message) {
		execute(() -> {
			boolean success = repository.saveMessage(message);
			postOnMainThread(() -> callback.onMessageSaved(success, success ? null : "Error saving message"));
		});
	}
}