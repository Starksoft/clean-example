package com.arch.clean.core;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.arch.clean.core.presentation.presenters.BasePresenter;
import com.arch.clean.core.presentation.presenters.PresenterCreator;
import com.arch.clean.core.presentation.ui.BaseView;

import java.util.HashMap;
import java.util.Map;

public final class PresenterStore {

	private static PresenterStore instance;
	private final Map<String, BasePresenter> presentersMap = new HashMap<>();

	private PresenterStore() {
		//no instance
	}

	@NonNull
	public static PresenterStore getInstance() {
		if (instance == null) {
			instance = new PresenterStore();
		}
		return instance;
	}

	public void onCreate(@NonNull PresenterCreator presenterCreator, Bundle savedInstanceState) {
		getPresenter(presenterCreator);
	}

	public void onAttach(@NonNull PresenterCreator presenterCreator, @NonNull BaseView baseView) {
		//noinspection unchecked
		getPresenter(presenterCreator).attachView(baseView);
	}

	public void onSaveInstanceState(@NonNull String presenterTag, Bundle outState) {

	}

	public void onDetach(@NonNull PresenterCreator presenterCreator, @NonNull BaseView baseView) {
		//noinspection unchecked
		getPresenter(presenterCreator).detachView(baseView);
	}

	public void onDestroyView(@NonNull PresenterCreator presenterCreator) {

	}

	public void onDestroy(@NonNull PresenterCreator presenterCreator) {

	}

	@NonNull
	public BasePresenter getPresenter(@NonNull PresenterCreator presenterCreator) {
		String presenterTag = presenterCreator.getPresenterTag();
		BasePresenter basePresenter = presentersMap.get(presenterTag);
		if (basePresenter == null) {
			presentersMap.put(presenterTag, basePresenter = presenterCreator.createPresenter());
		}

		return basePresenter;
	}
}