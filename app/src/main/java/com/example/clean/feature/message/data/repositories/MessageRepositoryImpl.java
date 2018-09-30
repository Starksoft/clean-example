package com.example.clean.feature.message.data.repositories;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Random;

public class MessageRepositoryImpl implements MessageRepository {

	private static final String MESSAGE = "message";
	@NonNull private final Context context;
	private final Random random = new Random();

	public MessageRepositoryImpl(@NonNull Context context) {
		this.context = context;
	}

	@Nullable
	@Override
	public String getMessage() {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (getRandom() == 1) {
			return PreferenceManager.getDefaultSharedPreferences(context).getString(MESSAGE, null);
		} else {
			return null;
		}
	}

	@Override
	public boolean saveMessage(@Nullable String message) {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (getRandom() == 1) {
			PreferenceManager.getDefaultSharedPreferences(context).edit().putString(MESSAGE, message).apply();
			return true;
		} else {
			return false;
		}
	}

	private int getRandom() {
		return random.nextInt(100) % 2;
	}
}