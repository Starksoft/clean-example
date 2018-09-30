package com.example.clean.core.domain.executor;

import android.support.annotation.NonNull;

import com.example.clean.core.domain.interactors.AbstractInteractor;
import com.example.clean.core.domain.interactors.InteractorCommand;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * This singleton class will make sure that each interactor operation gets a background thread.
 * <p/>
 */
public class ThreadExecutor implements Executor {

	private static final int CORE_POOL_SIZE = 3;
	private static final int MAX_POOL_SIZE = 5;
	private static final int KEEP_ALIVE_TIME = 120;
	private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
	private static volatile ThreadExecutor threadExecutor;
	private ThreadPoolExecutor threadPoolExecutor;

	private ThreadExecutor() {
		threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TIME_UNIT, new LinkedBlockingQueue<>());
	}

	/**
	 * Returns a singleton instance of this executor. If the executor is not initialized then it initializes it and returns
	 * the instance.
	 */
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
}