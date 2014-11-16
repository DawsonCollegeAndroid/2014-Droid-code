package ca.campbell.optionsmenu;
/*
 * Code is duplicated in Activity2 and Activity3 for simplicity sake
 */

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.util.Linkify;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends Activity {
	private MediaPlayer mp;
	private static final String TAG = "OPTMENU2";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView tv = (TextView) findViewById(R.id.instrs);
		tv.setText(" Sounds courtesy of: http://www.acoustica.com/files/aclooplib/Sound%20Effects  No Options Menu in this Activity");
		tv.setAutoLinkMask(Linkify.ALL);
		tv.setLinksClickable(true);
		Button bt = (Button) findViewById(R.id.button1);
		bt.setVisibility(View.INVISIBLE);
		ImageButton ibt = (ImageButton) findViewById(R.id.button3);
		ibt.setVisibility(View.VISIBLE);
		Log.d(TAG, "in Activity2");	
	}

	public void popUpMenu (View view) {
		PopupMenu popup = new PopupMenu(this, view);
		// Activity implements OnMenuClickListener()
		popup.setOnMenuItemClickListener(handleMenu);
		popup.inflate(R.menu.farmanimals);
		popup.show();
	}

	// No Options Menu here
	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	 */

	private PopupMenu.OnMenuItemClickListener handleMenu = new PopupMenu.OnMenuItemClickListener() {
		@Override
		public boolean onMenuItemClick(MenuItem item) {
			// Handle pop up menu item clicks here. 
			int id = item.getItemId();
			switch (id) {
			case R.id.chickens:
				stopMediaPlayer();
				mp = MediaPlayer.create(Activity2.this, R.raw.chicken);
				mp.setLooping(true);
				mp.start();
				Toast.makeText(Activity2.this, "chickens", Toast.LENGTH_SHORT).show();
				Log.d(TAG, "chickens");
				return true;

			case R.id.pigs:
				stopMediaPlayer();
				mp = MediaPlayer.create(Activity2.this, R.raw.pig);
				mp.setLooping(true);
				mp.start();
				Toast.makeText(Activity2.this, "pigs", Toast.LENGTH_SHORT).show();
				Log.d(TAG, "pigs");
				return true;
			case R.id.cows:
				stopMediaPlayer();
				mp = MediaPlayer.create(Activity2.this, R.raw.cowmoo);
				mp.setLooping(true);
				mp.start();
				Toast.makeText(Activity2.this, "cows", Toast.LENGTH_SHORT).show();
				Log.d(TAG, "cows");
				return true;
			case R.id.stop:
				stopMediaPlayer();
				Toast.makeText(Activity2.this, "stop the racket", Toast.LENGTH_SHORT).show();
				Log.d(TAG, "stop the racket");
				return true;
			default:
				Log.d(TAG, "not implemented");
				return false;
			} // switch

		}  
	};

	public void stopMediaPlayer() {
		if (mp != null ) {
			mp.stop();
			mp.release();	
		}
	}
	public void newActivity3(View view) {
		startActivity(new Intent(this, Activity3.class));
	}
}
