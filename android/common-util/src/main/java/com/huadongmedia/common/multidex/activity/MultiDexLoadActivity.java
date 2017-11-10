package com.huadongmedia.common.multidex.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.huadongmedia.common.multidex.MultiDexLoader;
import com.huadongmedia.common.util.R;

public class MultiDexLoadActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		overridePendingTransition(R.anim.null_anim, R.anim.null_anim);
		setContentView(R.layout.activity_load_res);
		new LoadDexTask().execute();
	}

	@Override
	public void onBackPressed() {
	}

	private class LoadDexTask extends AsyncTask {

		@Override
		protected Object doInBackground(Object[] params) {
			try {
				MultiDexLoader.actualInstall(getApplicationContext());
			} catch (Exception e) {
			}
			return null;
		}

		@Override
		protected void onPostExecute(Object o) {
			try {
				Thread.sleep(8000);
			} catch (Exception e) {
			} finally {
				finish();
				System.exit(0);
			}
		}
	}
}
