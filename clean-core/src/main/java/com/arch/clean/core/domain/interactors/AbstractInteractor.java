package com.arch.clean.core.domain.interactors;

import android.support.annotation.AnyThread;
import android.support.annotation.NonNull;

import com.arch.clean.core.domain.executor.Executor;
import com.arch.clean.core.threading.MainThread;

@SuppressWarnings("WeakerAccess")
public abstract class AbstractInteractor {

	@NonNull private final MainThread mainThread;
	@NonNull private final Executor executor;
	private volatile boolean isCanceled;
	private volatile boolean isRunning;

	public AbstractInteractor(@NonNull Executor threadExecutor, @NonNull MainThread mainThread) {
		executor = threadExecutor;
		this.mainThread = mainThread;
	}

	public final void cancel() {
		isCanceled = true;
		isRunning = false;
	}

	public final boolean isRunning() {
		return isRunning;
	}

	public final void onFinished() {
		isRunning = false;
		isCanceled = false;
	}

	public final void execute(@NonNull InteractorCommand command) {
		// mark this interactor as running
		this.isRunning = true;

		// start running this interactor in a background thread
		executor.execute(this, command);
	}

	@AnyThread
	public final void executeOnMainThread(@NonNull InteractorCommand command) {
		// mark this interactor as running
		this.isRunning = true;

		mainThread.post(() -> {
			command.executeCommand();
			onFinished();
		});
	}

	public final void postOnMainThread(@NonNull Runnable runnable) {
		mainThread.post(runnable);
	}
}