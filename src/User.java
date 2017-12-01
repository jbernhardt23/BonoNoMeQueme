
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

    private char[] userData;
    private String spreadingMessage;
    private int spreadingCode;

    public void setUserData(char[] userData){
        this.userData = userData;
    }

    public void setSpreadingMessage(String spreadingMessage){
        this.spreadingMessage = spreadingMessage;
    }

    public void setSpreadingCode(int spreadingCode){
        this.spreadingCode = spreadingCode;
    }
    public char[] getUserData(){
        return userData;
    }

    public String getSpreadingMessage(){
        return spreadingMessage;
    }

    public int getSpreadingCode(){
        return spreadingCode;
    }

}