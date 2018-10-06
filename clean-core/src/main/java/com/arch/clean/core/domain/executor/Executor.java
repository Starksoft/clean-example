package com.arch.clean.core.domain.executor;

import android.support.annotation.NonNull;

import com.arch.clean.core.domain.interactors.AbstractInteractor;
import com.arch.clean.core.domain.interactors.InteractorCommand;

public interface Executor {

	void execute(@NonNull AbstractInteractor interactor, @NonNull InteractorCommand command);
}