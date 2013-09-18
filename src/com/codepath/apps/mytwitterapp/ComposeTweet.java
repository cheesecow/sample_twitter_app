package com.codepath.apps.mytwitterapp;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class ComposeTweet extends Activity {
	EditText etNewTweet;
	Button btnTweet;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose_tweet);
		setupViews();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compose_tweet, menu);
		return true;
	}
	
	public void composeTweet(View v){
		String message = etNewTweet.getText().toString();
		RequestParams params = new RequestParams("status", message);
		MyTwitterApp.getRestClient().postHomeTimeline(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject jsonTweets) {
				setResult(RESULT_OK);
				finish();
			}
			public void onFailure(Throwable e, JSONObject error) {
				Log.d("error", error.toString());
			} 

		}, params);
		
	}
	
	public void setupViews(){
		etNewTweet = (EditText) findViewById(R.id.etNewTweet);
		btnTweet = (Button) findViewById(R.id.btnTweet);
	}

}
