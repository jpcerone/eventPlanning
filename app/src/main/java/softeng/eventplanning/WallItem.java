package softeng.eventplanning;


/**
 * Created by David on 10/27/2016.
 */
public class WallItem {
    private String event_title;
    private String event_pic_id;
    private String event_desc;

    public WallItem(Object event_title, Object event_pic_id, Object event_desc){
        this.event_title = event_title.toString();
        this.event_pic_id = event_pic_id.toString();
        this.event_desc = event_desc.toString();
    }

    public String getEvent_title(){
        return event_title;
    }

    public String getEvent_pic_id(){

        return event_pic_id;
    }

    public String getEvent_desc(){

        return event_desc;
    }

    public void setEvent_title(String event_title){
        this.event_title = event_title;
    }

    public void setEvent_pic_id(String event_pic_id){
        this.event_pic_id = event_pic_id;
    }

    public void setEvent_desc(String event_desc){
        this.event_desc = event_desc;
    }
}