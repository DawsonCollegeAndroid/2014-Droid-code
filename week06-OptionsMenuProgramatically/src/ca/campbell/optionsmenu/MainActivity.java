package ca.campbell.optionsmenu;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	private MediaPlayer mp = null;
	private static final String TAG = "OPTMENU";

	private static final int MENU_CRICKETS = 0;
	private static final int MENU_DOGS = 1;
	private static final int MENU_BIGDOGS = 2;
	private static final int MENU_LITTLEDOGS = 3;
	private static final int MENU_CATS = 4;
	private static final int MENU_CHICKENS = 5;
	private static final int MENU_PIGS = 6;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// set default
		mp = null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		// public abstract MenuItem add (int groupId, int itemId, int order, CharSequence title)
		SubMenu subMenu = menu.addSubMenu(R.string.dogs);
		subMenu.add(Menu.NONE, MENU_BIGDOGS, Menu.NONE, R.string.bigdogs);
		subMenu.add(Menu.NONE, MENU_LITTLEDOGS, Menu.NONE, R.string.littledogs);
		menu.add(Menu.NONE, MENU_CRICKETS, Menu.NONE, R.string.crickets);
		menu.add(Menu.NONE, MENU_CATS, Menu.NONE, R.string.cats);
		menu.add(Menu.NONE, MENU_CHICKENS, Menu.NONE, R.string.chickens);
		menu.add(Menu.NONE, MENU_PIGS, Menu.NONE, R.string.pigs);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case MENU_CRICKETS:
			stopMediaPlayer();
			mp = MediaPlayer.create(this, R.raw.crickets);
			mp.setLooping(true);
			mp.start();
			Toast.makeText(this, "crickets", Toast.LENGTH_SHORT).show();
			Log.d(TAG, "crickets");
			return true;
		case MENU_BIGDOGS:
			stopMediaPlayer();
			mp = MediaPlayer.create(this, R.raw.bigdog);
			mp.setLooping(true);
			mp.start();
			Toast.makeText(this, "big dogs", Toast.LENGTH_SHORT).show();
			Log.d(TAG, "big dogs");
			return true;

		case MENU_LITTLEDOGS:
			stopMediaPlayer();
			mp = MediaPlayer.create(this, R.raw.littledog);
			mp.setLooping(true);
			mp.start();
			Toast.makeText(this, "little dogs", Toast.LENGTH_SHORT).show();
			Log.d(TAG, "little dogs");
			return true;

		case MENU_CATS:
			stopMediaPlayer();
			mp = MediaPlayer.create(this, R.raw.meow);
			mp.setLooping(true);
			mp.start();
			Toast.makeText(this, "cats", Toast.LENGTH_SHORT).show();
			Log.d(TAG, "cats");
			return true;

		case MENU_CHICKENS:
			stopMediaPlayer();
			mp = MediaPlayer.create(this, R.raw.chicken);
			mp.setLooping(true);
			mp.start();
			Toast.makeText(this, "chickens", Toast.LENGTH_SHORT).show();
			Log.d(TAG, "chickens");
			return true;

		case MENU_PIGS:
			stopMediaPlayer();
			mp = MediaPlayer.create(this, R.raw.pig);
			mp.setLooping(true);
			mp.start();
			Toast.makeText(this, "pigs", Toast.LENGTH_SHORT).show();
			Log.d(TAG, "pigs");
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void stopMediaPlayer() {
		if (mp != null) {
			mp.stop();
			mp.release();
		}

	}

	public void newActivity(View view) {
		Intent i = new Intent(this, Activity2.class);
		startActivity(i);
	}
}
