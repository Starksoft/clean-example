//package com.example.clean.feature.message.domain.interactors;
//
//import android.support.annotation.NonNull;
//
//import com.example.clean.core.domain.executor.Executor;
//import com.example.clean.core.domain.interactors.AbstractInteractor;
//import com.example.clean.core.domain.interactors.InteractorCommand;
//import com.example.clean.core.threading.MainThread;
//import com.example.clean.feature.message.data.repositories.MessageRepository;
//
//public final class LoadMessageInteractorImpl extends AbstractInteractor implements LoadMessageInteractor{
//
//	@NonNull private LoadMessageInteractor.Callback callback;
//	@NonNull private MessageRepository repository;
//
//	public LoadMessageInteractorImpl(@NonNull Executor threadExecutor, @NonNull MainThread mainThread, @NonNull Callback callback,
//	                                 @NonNull MessageRepository repository) {
//		super(threadExecutor, mainThread);
//		this.callback = callback;
//		this.repository = repository;
//	}
//
//	private void notifyError() {
//		mainThread.post(() -> callback.onRetrievalFailed("Nothing to show!"));
//	}
//
//	private void postMessage(@NonNull String msg) {
//		mainThread.post(() -> callback.onMessageRetrieved(msg));
//	}
//
//	@Override
//	public void executeLoadMessage() {
//		execute(() -> {
//			String message = repository.getMessage();
//
//			if (message == null || message.length() == 0) {
//				notifyError();
//			} else {
//				postMessage(message);
//			}
//		});
//	}
//}