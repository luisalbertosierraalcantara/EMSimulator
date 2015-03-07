package domain;

import java.util.ArrayList;

import com.juangon.emsimulator.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CircuitItemsAdapter extends BaseAdapter{

	private ArrayList<CircuitItem> items;
	private LayoutInflater inflater;
	
	public CircuitItemsAdapter(Context context, ArrayList<CircuitItem> items) {
		this.inflater = LayoutInflater.from(context);
		this.items = items;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ItemContainer container = null;
		if(convertView == null) {
			convertView = inflater.inflate(R.layout.item_layout, parent, false);
			
			container = new ItemContainer();
			container.itemName = (TextView) convertView.findViewById(R.id.item_name);
			container.imageItem = (ImageView) convertView.findViewById(R.id.item_image);
			
			convertView.setTag(container);
		} else
			container = (ItemContainer) convertView.getTag();
		
		Context context = parent.getContext();
		CircuitItem item = (CircuitItem) getItem(position);
		
		container.itemName.setText(item.getString(context));
		container.imageItem.setImageResource(item.getImageResource(context));
		
		return convertView;
	}
	
	class ItemContainer{
		TextView itemName;
		ImageView imageItem;
	}
}
