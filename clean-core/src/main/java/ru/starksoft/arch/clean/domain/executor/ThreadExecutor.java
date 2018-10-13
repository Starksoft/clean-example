package ru.starksoft.arch.clean.domain.executor;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import ru.starksoft.arch.clean.domain.interactors.AbstractInteractor;
import ru.starksoft.arch.clean.domain.interactors.InteractorCommand;

public class ThreadExecutor implements Executor {

	private static final int CORE_POOL_SIZE = 3;
	private static final int MAX_POOL_SIZE = 5;
	private static final int KEEP_ALIVE_TIME = 120;
	private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
	private static volatile ThreadExecutor threadExecutor;
	private final Handler handler;
	private ThreadPoolExecutor threadPoolExecutor;

	private ThreadExecutor() {
		handler = new Handler(Looper.getMainLooper());
		threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TIME_UNIT, new LinkedBlockingQueue<>());
	}

	@NonNull
	public static Executor getInstance() {
		if (threadExecutor == null) {
			threadExecutor = new ThreadExecutor();
		}

		return threadExecutor;
	}

	@Override
	public void execute(@NonNull AbstractInteractor interactor, @NonNull InteractorCommand command) {
		threadPoolExecutor.submit(() -> {
			command.executeCommand();

			interactor.onFinished();
		});
	}

	@Override
	public void executeOnMainThread(@NonNull Runnable runnable) {
		handler.post(runnable);
	}
}