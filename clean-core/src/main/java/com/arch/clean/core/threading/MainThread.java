package com.arch.clean.core.threading;

import android.support.annotation.NonNull;

public interface MainThread {

	void post(@NonNull Runnable runnable);
}