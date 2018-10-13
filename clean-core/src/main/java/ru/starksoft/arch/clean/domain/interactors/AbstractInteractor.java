package ru.starksoft.arch.clean.domain.interactors;

import android.support.annotation.NonNull;

import ru.starksoft.arch.clean.domain.executor.Executor;

@SuppressWarnings("WeakerAccess")
public abstract class AbstractInteractor {

	@NonNull private final Executor executor;
	private volatile boolean isCanceled;
	private volatile boolean isRunning;

	public AbstractInteractor(@NonNull Executor threadExecutor) {
		executor = threadExecutor;
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
		this.isRunning = true;
		executor.execute(this, command);
	}

	public final void executeOnMainThread(@NonNull Runnable runnable) {
		executor.executeOnMainThread(runnable);
	}
}