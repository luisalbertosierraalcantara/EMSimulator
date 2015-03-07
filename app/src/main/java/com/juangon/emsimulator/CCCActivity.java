package com.juangon.emsimulator;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;
import domain.CircuitItem;
import domain.CircuitItemsAdapter;

public class CCCActivity extends Activity implements OnTouchListener {

	RelativeLayout layout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_ccc, new LinearLayout(
                getBaseContext()), false);
        layout = (RelativeLayout) view.findViewById(R.id.ccc_layout);
        setContentView(layout);
        layout.setOnTouchListener(this);

        //Crear un generador inicial.

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y - 38;
        size.set(20, height / 2);

        crearComponente(CircuitItem.DC_GENERATOR, size);

        // Pintar los cables

//		 Bitmap bitmap = Bitmap.createBitmap(getWindowManager()
//		 .getDefaultDisplay().getWidth(), getWindowManager()
//		 .getDefaultDisplay().getHeight(), Bitmap.Config.ARGB_8888);
//		 Paint paint = new Paint();
//		 paint.setColor(Color.BLACK);
//		 Canvas canvas = new Canvas(bitmap);
//		 canvas.drawLine(10, height / 2, 10, 10, paint);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ccc, menu);
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
	public boolean onTouch(View v, MotionEvent event) {
		v.performClick();

		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			float x = event.getX();
			float y = event.getY();

			Point p = new Point();
			p.x = (int) x;
			p.y = (int) y;
			showPopup(p);
		}
		return false;
	}

	private void showPopup(final Point p) {
		int popupWidth = 200;
		int popupHeight = 300;

		// Inflate the popup_layout.xml
		LayoutInflater layoutInflater = getLayoutInflater();
		View layout = layoutInflater.inflate(R.layout.popup_layout,
				new LinearLayout(getBaseContext()), false);

		// Creating the PopupWindow
		final PopupWindow popup = new PopupWindow(this);
		popup.setContentView(layout);
		popup.setOutsideTouchable(true);
		popup.setWidth(popupWidth);
		popup.setHeight(popupHeight);
		popup.setFocusable(true);

		// Allignt the position of the popup
		int OFFSET_X = 0;
		int OFFSET_Y = popupHeight / 3;

		popup.setBackgroundDrawable(new ColorDrawable(
				android.graphics.Color.TRANSPARENT));

		// Displaying the popup at the specified location
		popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y
				+ OFFSET_Y);

		// Configuring the listview
		ListView listview = (ListView) layout
				.findViewById(R.id.ccc_itemListView);
		ArrayList<CircuitItem> items = new ArrayList<CircuitItem>();
		items.add(CircuitItem.DC_GENERATOR);
		items.add(CircuitItem.RESISTANCE);
		items.add(CircuitItem.SWITCH1);
		items.add(CircuitItem.SWITCH2);
		items.add(CircuitItem.AMMETER);
		items.add(CircuitItem.VOLTMETER);
		CircuitItemsAdapter adapter = new CircuitItemsAdapter(this, items);
		listview.setAdapter(adapter);

		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long id) {
				CircuitItem item = (CircuitItem) adapter
						.getItemAtPosition(position);
				crearComponente(item, p);
			}

		});
	}

	private void crearComponente(CircuitItem item, Point p) {
		Context context = getBaseContext();
		Toast.makeText(getApplicationContext(), item.getDescription(context),
				Toast.LENGTH_SHORT).show();
		ImageView imageView = new ImageView(this);
		imageView.setImageResource(item.getImageResource(context));
		imageView.setAdjustViewBounds(true);

		// Allignt the position of the image
		int imageWidth = imageView.getDrawable().getIntrinsicWidth();
		int imageHeight = imageView.getDrawable().getIntrinsicHeight();

		imageView.setX(p.x - imageWidth / 2);
		imageView.setY(p.y - imageHeight);

		// Add the ImageView to the layout and set the layout as the content
		// view
		layout.addView(imageView);
		setContentView(layout);
	}
}
