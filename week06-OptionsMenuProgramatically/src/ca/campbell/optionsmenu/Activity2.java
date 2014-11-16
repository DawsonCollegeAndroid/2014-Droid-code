package ca.campbell.optionsmenu;

import android.os.Bundle;
import android.app.Activity;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends Activity {
private static final String TAG = "OPTMENU2";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView tv = (TextView) findViewById(R.id.instrs);
		tv.setText(" Sounds courtesy of: http://www.acoustica.com/files/aclooplib/Sound%20Effects");
		tv.setAutoLinkMask(Linkify.ALL);
		tv.setLinksClickable(true);
		Button bt = (Button) findViewById(R.id.button);
		bt.setVisibility(View.INVISIBLE);
		bt = (Button) findViewById(R.id.button2);
		bt.setVisibility(View.INVISIBLE);
	}
/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	*/
}
