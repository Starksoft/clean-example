package com.example.clean;

import android.os.Looper;

public final class Utils {

	private Utils() {
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