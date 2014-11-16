package ca.campbell.httpexample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/*
 * HttpURLConnectionExample
 * 
 * This app sends a URL uses HttpURLConnection class + GET
 * to download a web page and display some of it in a TextView
 * 
 */
public class HttpExample extends Activity {
	private static final String TAG = "HttpURLConn";
	private EditText urlText;
	private TextView textView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_http_example);

		urlText = (EditText) findViewById(R.id.myURL);
		textView = (TextView) findViewById(R.id.tv);
	}

	/*
	 * When user clicks button, executes an AsyncTask to do the download. Before
	 * attempting to fetch the URL, makes sure that there is a network
	 * connection.
	 */
	public void myClickHandler(View view) {
		// Gets the URL from the UI's text field.
		String stringUrl = urlText.getText().toString();
		if (stringUrl.isEmpty()) {
			textView.setText("Gimmey some URLz pleaze.");
		} else {
			if (stringUrl.substring(0, 6) != "http://")
				stringUrl = "http://" + stringUrl;

			// first check to see if we can get on the network
			ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
			if (networkInfo != null && networkInfo.isConnected()) {
				// invoke the AsyncTask to do the dirtywork.
				new DownloadWebpageText().execute(stringUrl);
			} else {
				textView.setText("No network connection available.");
			}
		}
	} // myClickHandler()

	/*
	 * Uses AsyncTask to create a task away from the main UI thread. This task
	 * takes a URL string and uses it to create an HttpUrlConnection. Once the
	 * connection has been established, the AsyncTask downloads the contents of
	 * the webpage via an an InputStream. The InputStream is converted into a
	 * string, which is displayed in the UI by the AsyncTask's onPostExecute
	 * method.
	 */

	private class DownloadWebpageText extends AsyncTask<String, Void, String> {

		// onPostExecute displays the results of the AsyncTask.
		// runs in calling thread (in UI thread)
		protected void onPostExecute(String result) {
			textView.setText(result);
		}

		@Override
		// runs in background (not in UI thread)
		protected String doInBackground(String... urls) {
			// params comes from the execute() call: params[0] is the url.
			try {
				return downloadUrl(urls[0]);
			} catch (IOException e) {
				return "Unable to retrieve web page. URL may be invalid.";
			}
		}
	} // AsyncTask DownloadWebpageText()

	/*
	 * Given a URL, establishes an HttpUrlConnection and retrieves the web page
	 * content as a InputStream, which it returns as a string.
	 */

	private String downloadUrl(String myurl) throws IOException {
		InputStream is = null;
		// Only read the first 500 characters of the retrieved
		// web page content.
		int len = 500;
		HttpURLConnection conn = null;
		URL url = new URL(myurl);
		try {
			// create and open the connection
			conn = (HttpURLConnection) url.openConnection();

			/*
			 * set maximum time to wait for stream read read fails with
			 * SocketTimeoutException if elapses before connection established
			 * 
			 * in milliseconds
			 * 
			 * default: 0 - timeout disabled
			 */
			conn.setReadTimeout(10000);
			/*
			 * set maximum time to wait while connecting connect fails with
			 * SocketTimeoutException if elapses before connection established
			 * 
			 * in milliseconds
			 * 
			 * default: 0 - forces blocking connect timeout still occurs but
			 * VERY LONG wait ~several minutes
			 */
			conn.setConnectTimeout(15000 /* milliseconds */);
			/*
			 * HTTP Request method defined by protocol
			 * GET/POST/HEAD/POST/PUT/DELETE/TRACE/CONNECT
			 * 
			 * default: GET
			 */
			conn.setRequestMethod("GET");
			// specifies whether this connection allows receiving data
			conn.setDoInput(true);
			// Starts the query
			conn.connect();

			int response = conn.getResponseCode();
			Log.d(TAG, "Server returned: " + response + " aborting read.");

			/*
			 *  check the status code HTTP_OK = 200 anything else we didn't get what
			 *  we want in the data.
			 */
			if (response != HttpURLConnection.HTTP_OK)
				return "Server returned: " + response + " aborting read.";

			// get the stream for the data from the website
			is = conn.getInputStream();
			// read the stream (max len bytes)
			String contentAsString = readIt(is, len);
			return contentAsString;

		} finally {
			/*
			 * Make sure that the InputStream is closed after the app is
			 * finished using it.
			 * Make sure the connection is closed after the app is finished using it.
			 */

			if (is != null) {
				try {
					is.close();
				} catch (IOException ignore) {
				}
				if (conn != null)
					try {
						conn.disconnect();
					} catch (IllegalStateException ignore ) {
					}
			}
		}
	} // downloadUrl()

	//
	/*
	 * Reads stream from HTTP connection and converts it to a String. See
	 * stackoverflow or a good explanation of why I did it this way.
	 * http://stackoverflow
	 * .com/questions/3459127/should-i-buffer-the-inputstream
	 * -or-the-inputstreamreader
	 */
	public String readIt(InputStream stream, int len) throws IOException,
	UnsupportedEncodingException {
		char[] buffer = new char[len];
		Reader reader = null;
		reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"), len);
		int count = reader.read(buffer);
		Log.d(TAG, "Bytes read: " + count
				+ "(-1 means end of reader so max of " + len + " )");
		return new String(buffer);
	} // readIt()

} // Activity class