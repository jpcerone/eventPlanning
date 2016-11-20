package softeng.eventplanning;


import android.graphics.Bitmap;

/**
 * Created by David on 10/27/2016.
 */
public class WallItem {
    private String event_title;
    private Bitmap event_pic_id;
    private String event_desc;
    private String event_id;
    private String event_encoded;

    public WallItem(Object event_title, Object event_pic_id, Object event_desc, Object event_id){
        this.event_title = event_title.toString();
        this.event_pic_id = (Bitmap) event_pic_id;
        this.event_desc = event_desc.toString();
        this.event_id = event_id.toString();
    }

    public String getEvent_title(){
        return event_title;
    }


    public Bitmap getEvent_pic_id(){

        return event_pic_id;
    }

    public String getEvent_desc(){

        return event_desc;
    }

    public String getEvent_id(){

        return event_id;
    }

    public void setEvent_title(String event_title){
        this.event_title = event_title;
    }

    public void setEvent_pic_id(Bitmap event_pic_id){
        this.event_pic_id = event_pic_id;
    }

    public void setEvent_desc(String event_desc){
        this.event_desc = event_desc;
    }

    public void setEvent_id(String event_desc){
        this.event_desc = event_desc;
    }
}