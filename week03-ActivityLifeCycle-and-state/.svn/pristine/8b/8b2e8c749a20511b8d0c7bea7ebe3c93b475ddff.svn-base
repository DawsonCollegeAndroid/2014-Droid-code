package cs518.sample.activityLifecycle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
/*
 * In Activity2 we use the instanceStateBundle to maintain the counter information
 * In MyActivityLifeCycleActivity.java we do not save any state. 
 * note the run time differences, when/is the counter reset 
 * 
 */

public class MyActivityLifeCycleActivity extends Activity {
	protected int counter = 0;

	public static final String TAG = "LIFECYC";

	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate() ");
		setContentView(R.layout.main);

		Button killButton = (Button) findViewById(R.id.killButton);
		killButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log.d(TAG, "finish()");
				finish();
			}
		});

		Button countButton = (Button) findViewById(R.id.countButton);
		countButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log.d(TAG, "counting");
				counter++;
				updateCounter();
			}
		});

		Button activityButton = (Button) findViewById(R.id.activityButton);
		activityButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log.d(TAG, "fire intent");
				Intent launchOtherScreen = new Intent(getApplicationContext(),
						Activity2.class);
				startActivity(launchOtherScreen);
			}
		});

	}   //onCreate()

	@Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG, "onStart()");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume()");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d(TAG, "onPause()");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d(TAG, "onStop()");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy()");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d(TAG, "onRestart()");
	}

	private void updateCounter() {
		TextView textView = (TextView) findViewById(R.id.counterValue);
		textView.setText("Actual counter: " + counter);
	}

}  //MyActivityLifecycle