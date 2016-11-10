package softeng.eventplanning;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by David on 10/29/2016.
 */

public class WallAdapter extends BaseAdapter {

    //private List<WallItem> wallList;
    Context context;
    List<WallItem> wallItems;

    public WallAdapter(Context context, List<WallItem> wallItems){
        this.context = context;
        this.wallItems = wallItems;
    }

    @Override
    public int getCount() {
        return wallItems.size();
    }

    @Override
    public Object getItem(int position) {
        return wallItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return wallItems.indexOf(getItem(position));
    }

    private class ViewHolder {
        TextView event_title;
        TextView event_pic;
        //ImageView event_pic;
        TextView event_desc;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;

        LayoutInflater li = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){
            convertView = li.inflate(R.layout.feed_list, null);
            vh = new ViewHolder();
            vh.event_title = (TextView)convertView.findViewById(R.id.feedTitle);
            vh.event_pic = (TextView)convertView.findViewById(R.id.feedImage);
            //vh.event_pic = (ImageView)convertView.findViewById(R.id.feedImage);
            vh.event_desc = (TextView)convertView.findViewById(R.id.feedDesc);

            WallItem pos = wallItems.get(position);

            vh.event_title.setText(pos.getEvent_title());
            vh.event_pic.setText(pos.getEvent_pic_id());
            //vh.event_pic.setImageResource(Integer.parseInt(pos.getEvent_pic_id()));
            vh.event_desc.setText(pos.getEvent_desc());
        }
        return convertView;
    }
}