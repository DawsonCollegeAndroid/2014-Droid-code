package ca.campbell.optionsmenu;
/*
 * This illustrates option menus but it illustrates the activity lifecycle.
 *  
 * The way this code / UIs are set up I can keep launching activity 2 & 3 
 * none will be gone unless I hit the back button or they are destroyed when
 * resources are not needed.  See what happens, and how could I change this ?
 * 
 * Also I am playing fast and loose with the mediaplayer so it may crash
 * Check here for proper use
 * http://developer.android.com/reference/android/media/MediaPlayer.html
 * 
 */
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	private MediaPlayer mp;
	private static final String TAG = "OPTMENU";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// set default
		mp = null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{

		switch (item.getItemId())
		{
		case R.id.crickets:
			stopMediaPlayer();  	
			mp = MediaPlayer.create(this, R.raw.crickets);
			mp.setLooping(true);
			mp.start();
			Toast.makeText(this, "crickets", Toast.LENGTH_SHORT).show();
			Log.d(TAG, "crickets");
			return true;
		case R.id.bigdogs:
			stopMediaPlayer();  	
			mp = MediaPlayer.create(this, R.raw.bigdog);
			mp.setLooping(true);
			mp.start();
			Toast.makeText(this, "big dogs", Toast.LENGTH_SHORT).show();
			Log.d(TAG, "big dogs");
			return true;

		case R.id.littledogs:
			stopMediaPlayer();
			mp = MediaPlayer.create(this, R.raw.littledog);
			mp.setLooping(true);
			mp.start();
			Toast.makeText(this, "little dogs", Toast.LENGTH_SHORT).show();
			Log.d(TAG, "little dogs");
			return true;

		case R.id.cats:
			stopMediaPlayer();
			mp = MediaPlayer.create(this, R.raw.meow);
			mp.setLooping(true);
			mp.start();
			Toast.makeText(this, "cats", Toast.LENGTH_SHORT).show();
			Log.d(TAG, "cats");
			return true;

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
		case R.id.crows:
			stopMediaPlayer();
			mp = MediaPlayer.create(this, R.raw.crows);
			mp.setLooping(true);
			mp.start();
			Toast.makeText(this, "crows", Toast.LENGTH_SHORT).show();
			Log.d(TAG, "crows");
			return true;
		case R.id.stop:
			stopMediaPlayer();
			Toast.makeText(this, "stop the racket", Toast.LENGTH_SHORT).show();
			Log.d(TAG, "stop the racket");
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}    
	public void stopMediaPlayer() {
		if (mp != null ) {
			mp.stop();
			mp.release();	
		}

	}
	public void newActivity2(View view) {
		startActivity(new Intent(this, Activity2.class));
		
	}
	public void newActivity3(View view) {
		startActivity(new Intent(this, Activity3.class));
	}
}
