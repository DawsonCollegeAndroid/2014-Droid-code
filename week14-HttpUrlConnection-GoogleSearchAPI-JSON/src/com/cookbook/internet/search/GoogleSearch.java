package com.cookbook.internet.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
/*
 * GoogleSearch.class
 * 
 * Code from 
 * "The Android Developer's Cookbook: Building Applications With The Android Sdk"
 * By Steele, Dutson, Schwartz, To
 *  
 * http://www.chapters.indigo.ca/books/product/9780321897534-item.html
 * 
 * A lot of mods by P. Campbell October 2013, November 2014 most unmarked
 * 
 * This code uses the google feed api https://developers.google.com/feed/v1/jsondevguide
 * 
 * NOTE the api used here has been deprecated, but it still works and illustrates 
 * using JSONObject and JSONArray with api data.
 * 
 *  I haven't had time to update the example using Google Custom search api.  I will do so soon.  
 */
public class GoogleSearch extends Activity {
	private static final String TAG = "GSEARCH";
	TextView displaytv, counttv;
	EditText ed1;
	Button bt1;
	static String url= "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        displaytv = (TextView) this.findViewById(R.id.display);
        counttv = (TextView) this.findViewById(R.id.count);
        ed1 = (EditText) this.findViewById(R.id.editText);
        bt1 = (Button) this.findViewById(R.id.submit);
        
        bt1.setOnClickListener(new OnClickListener(){
    		public void onClick(View view){
    			    Boolean netup = netIsUp();
    				if(ed1.getText().toString()!=null && netup){				
    					try{
    						new mygoogleSearch().execute(ed1.getText().toString());
    						//processResponse(searchRequest(ed1.getText().toString()));
    					}catch(Exception e){
    						Log.v(TAG,"Exception:"+e.getMessage());
    					}
    				}
    				if (netup)
    					ed1.setText("");
    				else
    					ed1.setText("Network access problems, check your net.");
    		}
    	});        
    }
   
	public String SearchRequest(String searchString) throws MalformedURLException, IOException  {
		String newFeed=url+"\""+searchString+"\"";
		StringBuilder response = new StringBuilder();
		Log.v(TAG,"url:"+newFeed);
		URL url = new URL(newFeed);
		
		HttpURLConnection httpconn =(HttpURLConnection) url.openConnection();
		
		if(httpconn.getResponseCode()==HttpURLConnection.HTTP_OK){
			BufferedReader input = new BufferedReader(
					new InputStreamReader(httpconn.getInputStream()), 
					8192);
			String strLine = null;
			while ((strLine = input.readLine()) != null) {
				response.append(strLine);
			}
			input.close();
			httpconn.disconnect();  //PMC
		}
	    return response.toString();
	}
	/*
	 * In order to read this consider the JSON format the api has chosen
	 * https://developers.google.com/feed/v1/jsondevguide
	 * match the code to the returned data,
	 * Try https://developers.google.com/feed/v1/jsondevguide
	 * Try http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=dogs  
	 * in jsoneditoronline.org and compare it to the code:
	 */
	public void ProcessResponse(String resp)throws IllegalStateException, 
	IOException, JSONException, NoSuchAlgorithmException{
		StringBuilder sb = new StringBuilder();
		Log.v(TAG," result:"+resp);
	
		// {} wrapped in object
		JSONObject mResponseObject = new JSONObject(resp);
	
		// { "responseData": { -> object
		JSONObject responseObject = mResponseObject.getJSONObject("responseData");
		
		// { "responseData": { "results": [ -> array
		JSONArray array = responseObject.getJSONArray("results");
		Log.v(TAG,"number of results returned:"+array.length());
		
		// { "responseData": { "cursor": { "resultCount" 
		JSONObject cursor  = responseObject.getJSONObject("cursor");
		String respcount = cursor.getString("resultCount");
		counttv.setText("Count of Google results: "+respcount + " (the api returns 4)");
		Log.v(TAG,"real number of results:"+respcount);
		
		// walk through the array
		for(int i=0;i<array.length();i++){
			Log.v(TAG, "result ["+i+"] "+array.get(i).toString());
		
			// "results" s an array of objects:
			// { "responseData": { "results": [ {} , {}, ... 
			String title = array.getJSONObject(i).getString("title");
			String urllink = array.getJSONObject(i).getString("visibleUrl");
			// add the data we need to the StringBuilder with html tags
			sb.append(title);
			sb.append("<br>");
			sb.append(urllink);
			sb.append("<br><br> ");
		}
		displaytv.setText(Html.fromHtml(sb.toString()));
	}
	
	private class mygoogleSearch extends AsyncTask<String, Integer, String> {
		
		protected String doInBackground(String... searchKey) {
			
			String key = searchKey[0];

			try{
				return SearchRequest(key);
			}catch(Exception e){
				Log.v(TAG,"Exception:"+e.getMessage());
				return "";	
			}
		}		
		protected void onPostExecute(String result) {
			try{
				ProcessResponse(result);
			}catch(Exception e){
				Log.v(TAG,"Exception:"+e.getMessage());
						
			}		
		}
	}
	private boolean netIsUp() {
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		// getActiveNetworkInfo() each time as the network may swap as the
		// device moves
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		// ALWAYS check isConnected() before initiating network traffic
		if (networkInfo != null) 
			return networkInfo.isConnected();
		else
			return false;
	} //netIsUp()
		
}