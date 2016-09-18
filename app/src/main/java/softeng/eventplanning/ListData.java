package softeng.eventplanning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by brandonboyle on 9/18/16.
 */
public class ListData {
    public static HashMap<String, List<String>> getInfo(){
        HashMap<String, List<String>>friend_details = new HashMap<String, List<String>>();
        List<String> Who_dat = new ArrayList<String>();
        Who_dat.add("Shonquay");
        Who_dat.add("Bob Ross");
        Who_dat.add("Randy Savage");
        Who_dat.add("Your Friend");

        friend_details.put("Who's here", Who_dat);

        return friend_details;

    }




}
