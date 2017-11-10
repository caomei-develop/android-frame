package com.huadongmedia.common.callback;

public abstract class AsyncCallbacks {

	public static abstract class ZeroZero {

		public void onSuccess() {
		}

		public void onError() {
		}
	}

	public static abstract class OneZero<S1> {

		public void onSuccess(S1 result) {
		}

		public void onError() {
		}
	}

	public static abstract class ZeroOne<E1> {

		public void onSuccess() {
		}

		public void onError(E1 result) {
		}
	}

	public static abstract class ZeroTwo<E1, E2> {

		public void onSuccess() {
		}

		public void onError(E1 resultOne, E2 resultTwo) {
		}
	}

	public static abstract class OneOne<S1, E1> {

		public void onSuccess(S1 result) {
		}

		public void onError(E1 result) {
		}
	}

	public static abstract class OneTwo<S1, E1, E2> {

		public void onSuccess(S1 result) {
		}

		public void onError(E1 resultOne, E2 resultTwo) {
		}
	}

	public static abstract class TwoOne<S1, S2, E1> {

		public void onSuccess(S1 resultOne, S2 resultTwo) {
		}

		public void onError(E1 result) {
		}
	}

	public static abstract class TwoTwo<S1, S2, E1, E2> {

		public void onSuccess(S1 resultOne, S2 resultTwo) {
		}

		public void onError(E1 resultOne, E2 resultTwo) {
		}
	}

	public static abstract class ThreeTwo<S1, S2, S3, E1, E2> {

		public void onSuccess(S1 resultOne, S2 resultTwo, S3 resultThree) {
		}

		public void onError(E1 resultOne, E2 resultTwo) {
		}
	}
}
