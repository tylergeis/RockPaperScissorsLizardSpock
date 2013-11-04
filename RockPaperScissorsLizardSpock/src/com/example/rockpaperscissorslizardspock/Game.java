package com.example.rockpaperscissorslizardspock;

import com.example.javaclient.MyClient;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;

@SuppressLint("SetJavaScriptEnabled") public class Game extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_game); 
		WebView w=(WebView) findViewById(R.id.webView1); 
		w.getSettings().setJavaScriptEnabled(true);
		// enable alert()
		android.webkit.WebChromeClient alerts=new android.webkit.WebChromeClient();
		w.setWebChromeClient(alerts);
		final MyClient c = new MyClient(6666, "0.0.0.0", "Tyler", w);
		w.addJavascriptInterface(c, "api");
		Thread thread=new Thread(c);
		thread.start();
		w.setWebViewClient(new WebViewClient(){
			public void onPageFinished(WebView view, String url){
					// run game
					Thread t = new 
					    Thread(c);
					t.start();
			}
			});
		
		w.loadUrl("file:///android_asset/game.html"); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

}
