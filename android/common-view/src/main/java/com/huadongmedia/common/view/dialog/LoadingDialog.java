package com.huadongmedia.common.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.huadongmedia.common.view.R;

public class LoadingDialog extends Dialog {

	public LoadingDialog(@NonNull Context context) {
		this(context, R.style.Common_LoadingDialog);
	}

	public LoadingDialog(@NonNull Context context, @StyleRes int themeResId) {
		super(context, themeResId);
		this.setContentView(R.layout.loading_dialog);

		TypedArray typedArray = context.getTheme().obtainStyledAttributes(themeResId, R.styleable.Common_LoadingDialog);
		try {
			Drawable progressDrawable = typedArray.getDrawable(R.styleable.Common_LoadingDialog_loadingDialogProgressDrawable);
			if (progressDrawable != null) {
				ProgressBar progressBar = (ProgressBar) findViewById(R.id.loading_bar);
				progressBar.setIndeterminateDrawable(progressDrawable);
			}
		} finally {
			typedArray.recycle();
		}
		this.setCanceledOnTouchOutside(false);
		this.setCancelable(true);
	}

	public void setLoadingMessage(String message) {
		((TextView) findViewById(R.id.loading_message)).setText(message);
	}
}
