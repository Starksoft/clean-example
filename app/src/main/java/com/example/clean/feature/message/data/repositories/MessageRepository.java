package com.example.clean.feature.message.data.repositories;

import android.support.annotation.Nullable;

public interface MessageRepository {

	@Nullable
	String getMessage();

	boolean saveMessage(@Nullable String message);
}