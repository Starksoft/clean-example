package ru.starksoft.arch.clean.utils;

import android.os.Looper;

public final class ThreadUtils {

	private ThreadUtils() {
		throw new UnsupportedOperationException();
	}

	public static void checkMainThread() {
		if (Looper.getMainLooper() != Looper.myLooper()) {
			throw new NotOnMainThreadException("Only main thread allowed");
		}
	}

	public static class NotOnMainThreadException extends RuntimeException {

		public NotOnMainThreadException() {
		}

		public NotOnMainThreadException(String message) {
			super(message);
		}
	}
}