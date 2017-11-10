package com.huadongmedia.common.support;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import android.support.annotation.NonNull;

public class AsyncExecuter {

	private static Executor executor = Executors.newCachedThreadPool(new ThreadFactory() {
		@Override
		public Thread newThread(@NonNull Runnable runnable) {
			return new Thread(runnable, "AsyncExecuter");
		}
	});

	public static void submit(final Runnable runnable) {
		executor.execute(runnable);
	}
}
