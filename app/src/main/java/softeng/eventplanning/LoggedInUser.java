package softeng.eventplanning;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by jpcerone on 11/1/16.
 */

public class LoggedInUser {
    public static String username;
    public static String birthday;
    public static String phone;
    public static String fName;
    public static String lName;
    public static String currentEventid;
    public static ArrayList<String> friendsList;
    public static String friendListRaw;
    public static String bio;
    public static String userid;
    public static Bitmap image;
    public static String encoded;
    public static void setUsername(String s){
        username = s;
    }
    public static void setBirthday(String s){
        birthday = s;
    }
    public static void setPhone(String s){
        phone = s;
    }
    public static void setfName(String s){
        fName = s;
    }
    public static void setlName(String s){lName = s;}
    public static void setCurrentEventid(String s){
        currentEventid = s;
    }
    public static void setFriendsList(String s){friendsList = new ArrayList<String>(Arrays.asList(s.split("\\s+")));}
    public static void setFriendsListRaw(String s){
        friendListRaw = s;
    }
    public static void setBio(String s){
        bio = s;
    }
    public static void setUserid(String s){userid = s;}
    public static void setImage(Bitmap b){image = b;}
    public static void setEncoded(String s){encoded =s;}
    public static void addFriend(String s) {
        friendsList.add(s);
        if (friendListRaw.equals("")) {
            friendListRaw = s;
        } else {
            friendListRaw = friendListRaw + " " + s;
        }
    }
}
