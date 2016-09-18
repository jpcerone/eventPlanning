package softeng.eventplanning;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.content.Context;
import android.widget.TextView;
import android.widget.VideoView;
import android.graphics.Typeface;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by brandonboyle on 9/18/16.
 */
public class friendadapter extends BaseExpandableListAdapter {
    private Context ctx;
    private HashMap<String, List<String>> Friends;
    private List<String> Friends_list;

    public friendadapter(Context ctx, HashMap<String, List<String>> Friends, List<String> Friends_list){
        this.ctx = ctx;
        this.Friends = Friends;
        this.Friends_list = Friends_list;
    }

    @Override
    public Object getChild(int parent, int child){

        return Friends.get(Friends_list.get(parent)).get(child);
    }

    @Override
    public long getChildId(int parent, int child){
        return child;
    }

    @Override
    public View getChildView (int parent, int child, boolean lastChild, View convertview, ViewGroup parentview){
        String child_title = (String) getChild(parent, child);
        if (convertview == null){
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertview = inflater.inflate(R.layout.child_view, parentview, false);
        }
        TextView child_textview = (TextView) convertview.findViewById(R.id.child_id);
        child_textview.setText(child_title);

        return convertview;
    }

    @Override
    public int getChildrenCount(int arg0){

        return Friends.get(Friends_list.get(arg0)).size();
    }

    @Override
    public Object getGroup(int arg0){
        return Friends_list.get(arg0);

    }

    @Override
    public int getGroupCount(){
        return Friends_list.size();

    }

    @Override
    public long getGroupId(int arg0){
        return arg0;
    }

    @Override
    public View getGroupView(int parent, boolean isExpanded, View converview, ViewGroup parentview) {

        String group_title = (String) getGroup(parent);
        if(converview == null){
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            converview  = inflater.inflate(R.layout.expandlelist, parentview, false);
        }
        TextView parent_textview = (TextView) converview.findViewById(R.id.parent_txt);
        parent_textview.setTypeface(null, Typeface.BOLD);
        parent_textview.setText(group_title);
        return converview;
    }

    @Override
    public boolean isChildSelectable(int arg0,int arg1){
        return false;
    }

    public boolean hasStableIds(){
        return false;
    }
}
