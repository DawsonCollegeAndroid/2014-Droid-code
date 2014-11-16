package ca.campbell.networkcheckstatus;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/*
 * This app is intended to be an example of checking network connectivity.
 * It does not do any network access, though this is the normal next step.
 * 
 * When using networking it is important to verify that you have an active
 * connected network connection before doing any network activity.
 * 
 * Since it checks connectivity but does no network access it does not need
 * to be done in an AsyncTask.  Any network access (data over the network)
 *  must be done in a background thread.
 * 
 * Permissions required in AndroidMainifest.xml:
 * <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE">
 * </uses-permission>
 */
public class MainActivity extends Activity {
	private final static String TAG = "NETCHK";
	private TextView tv2, tv3;
	private boolean networkIsUp = false;
	NetworkInfo networkInfo;
	ConnectivityManager connMgr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv2 = (TextView) findViewById(R.id.tv2);
		tv3 = (TextView) findViewById(R.id.tv3);
	}

	public void checkStatusButton(View view) {
		checkStatus();
	}

	/*
	 * checkStatus() using an instance of ConnectivityManager via the
	 * Connectivity Service which is an Android Service. Checks if network is
	 * live, sets networkIsUp depending on network state.
	 * 
	 * Connectivity Manager Class
	 * 
	 * gives access to the state of network connectivity. Can be used to notify
	 * apps when connectivity changes
	 * 
	 * -monitor network connections (wi-fi, GPRS, UMTs, etc
	 * 
	 * -send broadcast intents when connectivity changes
	 * 
	 * -trys to failover to another net when connectivity lost
	 * 
	 * -api for network state connectivity
	 * 
	 * ConnectivityManager.getActiveNetworkInfo()
	 * 
	 * returns details about the current active default network (NetworkInfo)
	 * null if no default network ALWAYS check isConnected() before initiating
	 * network traffic
	 * 
	 * requires perm: android.permission.ACCESS_NETWORK_STATE
	 * 
	 * NetworkInfo class
	 * 
	 * gives access information about the status of a network interface
	 * connection
	 */
	public void checkStatus() {
		connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		// getActiveNetworkInfo() each time as the network may swap as the
		// device moves
		if (connMgr != null ) {
			networkInfo = connMgr.getActiveNetworkInfo();
			// ALWAYS check isConnected() before initiating network traffic
			if (networkInfo != null && networkInfo.isConnected()) {
				tv2.setText("Network is connected");
				networkIsUp = true;
			} else {
				tv2.setText("No network connectivity");
				networkIsUp = false;
			}
		} else {
			tv2.setText("No network manager service");
			networkIsUp = false;
		}
	} // checkStatus()

	public void checkEverything(View view) {
		String message = null;
		State state = null;
		checkStatus();
		if (networkIsUp) {
			state = networkInfo.getState();
		}
		if (state == null) {
			tv2.setText("Unable to get state info");
		} else {
			switch (networkInfo.getState()) {
			case CONNECTED:
				message = "State Connected";
				break;
			case CONNECTING:
				message = "State Connecting";
				break;
			case DISCONNECTED:
				message = "State Disconnected";
				break;
			case DISCONNECTING:
				message = "State Disconnecting";
				break;
			case SUSPENDED:
				message = "State Suspened";
				break;
			case UNKNOWN:
				message = "State Unknown";
				break;
			default:
				message = "No valid State found";
				break;
			}

			tv2.setText(message);
		}

	} // checkEverything()

	public void checkEverythingDetailed(View view) {
		String message = null;
		State state = null;
		checkStatus();
		if (networkIsUp) {
			state = networkInfo.getState();
		}
		if (state == null) {
			tv2.setText("Unable to get state info");
		} else {
			switch (networkInfo.getDetailedState()) {
			case IDLE:
				message = "State Idle";
				break;
			case CONNECTED:
				message = "State Connected";
				break;
			case CONNECTING:
				message = "State Connecting";
				break;
			case SCANNING:
				message = "State Scanning: searching for an access point";
				break;
			case DISCONNECTED:
				message = "State Disconnected";
				break;
			case DISCONNECTING:
				message = "State Connected";
				break;
			case AUTHENTICATING:
				message = "State Authenticating";
				break;
			case BLOCKED:
				message = "State Block";
				break;
			case OBTAINING_IPADDR:
				message = "State awaiting DHCP response";
				break;
			case FAILED:
				message = "State Failed";
				break;
			case VERIFYING_POOR_LINK:
				message = "State Link has poor connectivity";
				break;
			default:
				message = "No valid State found";
				break;
			}
			// Ex types WIFI, Bluetooth, Mobile...
			String typeName = networkInfo.getTypeName();
			message = message + "\n Network Type " + typeName;
			if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
				// subtype applies only to type mobile
				// see TelephonyManager class for enum of types
				String subtypeName = networkInfo.getSubtypeName();
				message = message + "\n Network Subtype: " + subtypeName;
			}
			String extraInfo = networkInfo.getExtraInfo();
			message = message + "\n Extra Info " + extraInfo;
			tv2.setText("Detailed State: \n " + message);
		}
		tv3.setText("Available \n " + getAvailablilty());
	} // checkEverythingDetailed()

	private String getAvailablilty() {
		boolean wifiConn, mobileConn;
		NetworkInfo netInfo;
		// get an instance of ConnectivityManager
		connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

		// check if wifi is available
		netInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (netInfo != null) {
			wifiConn = netInfo.isAvailable();
		} else {
			wifiConn = false;
		}
		// check if mobile is available
		netInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (netInfo != null) {
			mobileConn = netInfo.isAvailable();
		} else {
			mobileConn = false;
		}
		return "Wifi available: " + wifiConn + "\n Mobile available: "
		+ mobileConn;
	}

}
