package cs518.sample.activityLifecycle;


import android.app.Activity;
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
public class Activity2 extends Activity {
	protected int counter = 0;

	public static final String TAG = "LIFECYC2";

	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate() ");
		setContentView(R.layout.main);
        // restore savedInstanceState here or in onRestoreInstanceState(Bundle)
		if (savedInstanceState != null )
			counter = savedInstanceState.getInt("counter");

		Button killButton = (Button) findViewById(R.id.killButton);
		killButton.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				Log.d(TAG, "finish()");
				finish();
			}
		});

		Button countButton = (Button) findViewById(R.id.countButton);
		// don't use the button in the 
		countButton.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				Log.d(TAG, "counting");
				counter++;
				updateCounter();
			}
		});
		// don't use button on 2nd activity
		Button activityButton = (Button) findViewById(R.id.activityButton);
		activityButton.setVisibility(View.INVISIBLE);	

	}  //onCreate()

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
    // if called it will be run before onStop(
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.d(TAG, "onSaveInstanceState()");
		outState. putInt("counter", counter);
	}

	// only called if activity killed 
	// restore savedInstanceState here or in onCreate(Bundle)
	protected void onRestoreInstanceState(Bundle inState) { 
		super.onRestoreInstanceState(inState);
		Log.d(TAG, "onRestoreInstanceState(");
		// restore savedInstanceState here or in onCreate(Bundle)
		counter = inState.getInt("counter");
		updateCounter();
	}


	private void updateCounter() {
		TextView textView = (TextView) findViewById(R.id.counterValue);
		textView.setText("Actual counter: " + counter);
	}
	public static void testMe() {
		Log.d(TAG, "testMe()");
	}
}
