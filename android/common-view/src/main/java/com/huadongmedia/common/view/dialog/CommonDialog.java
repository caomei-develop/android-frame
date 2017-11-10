package com.huadongmedia.common.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;

public class CommonDialog extends Dialog {

	public CommonDialog(@NonNull Context context) {
		super(context);
	}

	public CommonDialog(@NonNull Context context, @StyleRes int themeResId) {
		super(context, themeResId);
	}
}
