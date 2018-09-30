//package com.example.clean.feature.message.domain.interactors;
//
//import android.support.annotation.NonNull;
//
//import com.example.clean.core.domain.executor.Executor;
//import com.example.clean.core.domain.interactors.AbstractInteractor;
//import com.example.clean.core.threading.MainThread;
//import com.example.clean.feature.message.data.repositories.MessageRepository;
//
//public final class SaveMessageInteractorImpl extends AbstractInteractor implements SaveMessageInteractor {
//
//	@NonNull private SaveMessageInteractor.Callback callback;
//	@NonNull private MessageRepository repository;
//	private String message;
//
//	public SaveMessageInteractorImpl(@NonNull Executor threadExecutor, @NonNull MainThread mainThread,
//	                                 @NonNull SaveMessageInteractor.Callback callback, @NonNull MessageRepository repository) {
//		super(threadExecutor, mainThread);
//		this.callback = callback;
//		this.repository = repository;
//	}
//
//	public void run() {
//		boolean saveMessage = repository.saveMessage(message);
//
//		if (!saveMessage) {
//			post(() -> callback.onSaveFailed("I/O Error while saving!"));
//		} else {
//			post(() -> callback.onMessageSaved());
//		}
//	}
//
//	@Override
//	public void executeSaveMessage(@NonNull String message) {
//		this.message = message;
//		execute();
//	}
//}