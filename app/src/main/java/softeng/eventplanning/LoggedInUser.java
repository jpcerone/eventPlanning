package softeng.eventplanning;

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
    public static String[] friendsList;
    public static String bio;
    public static String userid;
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
    public static void setlName(String s){
        lName = s;

    }
    public static void setCurrentEventid(String s){
        currentEventid = s;

    }
    public static void setFriendsList(String s){
        friendsList = s.split("\\s+");

    }
    public static void setBio(String s){
        bio = s;

    }
    public static void setUserid(String s){
        userid = s;

    }
}
