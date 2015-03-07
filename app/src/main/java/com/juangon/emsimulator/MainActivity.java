package com.juangon.emsimulator;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
 
        TextView txtCCC = (TextView) findViewById(R.id.ccc_view);
        TextView txtCCA = (TextView) findViewById(R.id.cca_view);
        txtCCC.setOnClickListener(this);
        txtCCA.setOnClickListener(this);
        // Change the textview font
        String fontPath = "fonts/Quad Ultra.otf";
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
        txtCCC.setTypeface(tf);
        txtCCA.setTypeface(tf);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		if(v.getId() == findViewById(R.id.ccc_view).getId()) {
			intent = new Intent(this, CCCActivity.class);
		}
		if(v.getId() == findViewById(R.id.cca_view).getId()) {
			intent = new Intent(this, CCAActivity.class);
		}
		startActivity(intent);
	}

}
