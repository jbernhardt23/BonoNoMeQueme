
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.*;
import java.util.Scanner;


/**
 * @author Jose Bernhardt
 */
public class User {

    private String[] userData;
    private String[] spreadingMessage;
    private int spreadingCode;

    public void setUserData(String[] userData){
        this.userData = userData;
    }

    public void setSpreadingMessage(String[] spreadingMessage){
        this.spreadingMessage = spreadingMessage;
    }

    public void setSpreadingCode(int spreadingCode){
        this.spreadingCode = spreadingCode;
    }
    public String[] getUserData(){
        return userData;
    }

    public String[] getSpreadingMessage(){
        return spreadingMessage;
    }

    public int getSpreadingCode(){
        return spreadingCode;
    }

}