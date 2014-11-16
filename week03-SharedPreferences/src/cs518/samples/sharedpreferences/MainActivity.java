package cs518.samples.sharedpreferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.LinearLayout;

/* 
 * Sample using preferences
 * ref: http://developer.android.com/training/basics/data-storage/shared-preferences.html
 */
public class MainActivity extends Activity {
	private static String TAG = "SHPREF";
	private String uidstr, passwdstr;
	private EditText userID, passwd;
	private SharedPreferences prefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// no editor needed we're reading values
		prefs = getPreferences(MODE_PRIVATE);

		uidstr = prefs.getString(getString(R.string.uid), "nothing ");
		passwdstr = prefs.getString(getString(R.string.pass), "nothing ");

		// display on UI
		TextView userIDt = (TextView) findViewById(R.id.uidsaved);
		TextView passwdt = (TextView) findViewById(R.id.passwdsaved);
		LinearLayout llsaved = (LinearLayout) findViewById(R.id.resultlayout);

		Log.w(TAG, uidstr);
		Log.w(TAG, passwdstr);
		userIDt.setText(uidstr);
		passwdt.setText(passwdstr);
		llsaved.setVisibility(View.VISIBLE);

	} // onCreate()

	public void saveUserInfo(View view) {
		userID = (EditText) findViewById(R.id.uid);
		passwd = (EditText) findViewById(R.id.passwd);
		uidstr = userID.getText().toString();
		passwdstr = passwd.getText().toString();
		/*
		 * logically is better to save the preferences when the data are changed
		 */

		// Store values between app instances
		// MODE_PRIVATE: file only accessible by calling app (same UID)

		// done in onCreate()
		// SharedPreferences prefs = getPreferences(MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();

		// set the key/value pairs
		editor.putString(getString(R.string.uid), uidstr);
		editor.putString(getString(R.string.pass), passwdstr);

		// don't forget to commit the changes
		editor.commit();
	}

}
