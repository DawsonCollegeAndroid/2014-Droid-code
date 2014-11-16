package ca.campbell.optionsmenu;
/*
 * Code is duplicated in Activity2 and Activity3 for simplicity sake
 */
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Activity3 extends Activity {
	private MediaPlayer mp;

	private static final String TAG = "OPTMENU3";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView tv = (TextView) findViewById(R.id.instrs);
		tv.setText(" Sounds courtesy of: http://www.acoustica.com/files/aclooplib/Sound%20Effects");
		tv.setAutoLinkMask(Linkify.ALL);
		tv.setLinksClickable(true);
		Button bt = (Button) findViewById(R.id.button2);
		bt.setVisibility(View.GONE);
		Log.d(TAG, "in Activity3");	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.farmanimals, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
		case R.id.chickens:
			stopMediaPlayer();
			mp = MediaPlayer.create(this, R.raw.chicken);
			mp.setLooping(true);
			mp.start();
			Toast.makeText(this, "chickens", Toast.LENGTH_SHORT).show();
			Log.d(TAG, "chickens");
			return true;

		case R.id.pigs:
			stopMediaPlayer();
			mp = MediaPlayer.create(this, R.raw.pig);
			mp.setLooping(true);
			mp.start();
			Toast.makeText(this, "pigs", Toast.LENGTH_SHORT).show();
			Log.d(TAG, "pigs");
			return true;
		case R.id.cows:
			stopMediaPlayer();
			mp = MediaPlayer.create(this, R.raw.cowmoo);
			mp.setLooping(true);
			mp.start();
			Toast.makeText(this, "cows", Toast.LENGTH_SHORT).show();
			Log.d(TAG, "cows");
			return true;
		case R.id.stop:
			stopMediaPlayer();
			Toast.makeText(this, "stop the racket", Toast.LENGTH_SHORT).show();
			Log.d(TAG, "stop the racket");
			return true;
		default:
			return super.onOptionsItemSelected(item);
		} // switch

	}  
	public void newActivity2(View view) {
		stopMediaPlayer();
		startActivity(new Intent(this, Activity2.class));

	}
	public void stopMediaPlayer() {
		if (mp != null ) {
			mp.stop();
			mp.release();	
		}
	}
} // Activity3