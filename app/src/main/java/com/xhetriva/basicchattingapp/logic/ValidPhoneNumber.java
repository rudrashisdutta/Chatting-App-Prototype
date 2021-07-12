package com.xhetriva.basicchattingapp.logic;

import android.util.Log;

public class ValidPhoneNumber {

    //Declared Variables
    private final String pno;

    //States of validation
    public static long EMPTY = 0;
    public static long INVALID = -1;
    public static long VALID = 1;
    public static long NOT_A_PHONE_NUMBER = 2;


    //Constructor
    public ValidPhoneNumber(String phoneNumber){
        pno = phoneNumber;
    }
    //Public Methods
    public  long isValid(){
        if(isNumeric()){
            if(is10Digit()){
                return VALID;
            }
        } else if (pno.length() == 0){
            return EMPTY;
        }
        return INVALID;
    }

    /*public static void main(String[] args) {
        ValidPhoneNumber v = new ValidPhoneNumber("94375q7049");
        Log.i("Is it Valid?", (v.isValid()?"Yes it is valid.":"It is not valid"));
    }*/

    //Private Methods
    private boolean isNumeric(){
        boolean isIt = false;
        try{
            Long.parseLong(pno);
            isIt = true;
        }catch (NumberFormatException ignored){

        }
        return isIt;
    }
    private  boolean is10Digit(){
        boolean isIt;
        isIt = pno.length() == 10;
        return isIt;
    }
}
