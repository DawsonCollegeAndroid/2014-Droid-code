package cs518.samples.imageswap;

/*
 * App uses various resources
 * drawables
 * colour
 * string       
 * raw     (sound)
 *
 * in xml:  
 *  use an alternate layout  landscape / portrait
 *  use dimensions in layout 
 */

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	ImageButton bt01, bt02;
	TextView tv;
	MediaPlayer mp;
	int bt01image, bt01colour;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bt01 = (ImageButton) findViewById(R.id.imageButton1);
		bt02 = (ImageButton) findViewById(R.id.imageButton2);
		tv = (TextView) findViewById(R.id.message);
		
		mp = MediaPlayer.create(this, R.raw.meow);
		bt01image = R.drawable.cleannallboo;
		bt01colour = R.color.indigo;
		swapimage();
	}

    protected void onPause() {
		super.onPause();
    	// releasing the MediaPlayer resources
    	mp.release();
    }
    
	public void button1Clicked(View view) {
		Toast.makeText(this, "Button1 clicked", Toast.LENGTH_SHORT).show();
		swapimage();	
	}
	public void button2Clicked(View view) {
		Toast.makeText(this, "Button2 clicked", Toast.LENGTH_SHORT).show();
		swapimage();
	}
	public void button3Clicked(View view) {
		Toast.makeText(this, "Button3 clicked", Toast.LENGTH_LONG).show();
		mp.start();
		
	}
	public void swapimage() {
		// swap the two images in place and changes the message text colour.
		if (bt01image == R.drawable.cleannallboo) {
			bt01.setImageResource(R.drawable.cleanallyay);
			bt02.setImageResource(R.drawable.cleannallboo);
			bt01image = R.drawable.cleanallyay;
			tv.setTextColor(getResources().getColor(R.color.lightcoral));
			 
		} else {
			bt02.setImageResource(R.drawable.cleanallyay);
			bt01.setImageResource(R.drawable.cleannallboo);
			bt01image = R.drawable.cleannallboo;
			tv.setTextColor(getResources().getColor(R.color.indigo));
		}
	}
}
