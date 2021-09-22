package com.xhetriva.basicchattingapp.logic;

import android.util.Log;

/**
 * Checks for the validity of a phone number<br/>
 * {@link ValidPhoneNumber#ValidPhoneNumber(String)}<br/>
 * {@link ValidPhoneNumber#isValid()}<br/>
 * {@link ValidPhoneNumber#EMPTY}<br/>
 * {@link ValidPhoneNumber#VALID}<br/>
 * {@link ValidPhoneNumber#INVALID}<br/>
 */
public class ValidPhoneNumber {

    //Declared Variables
    private final String pno;

    /**
     * The constant EMPTY.
     * @see ValidPhoneNumber#isValid()
     */
//States of validation
    public static long EMPTY = 0;
    /**
     * The constant INVALID.
     * @see ValidPhoneNumber#isValid()
     */
    public static long INVALID = -1;
    /**
     * The constant VALID.
     * @see ValidPhoneNumber#isValid()
     */
    public static long VALID = 1;
    /**
     * The constant NOT_A_PHONE_NUMBER.
     */
    public static long NOT_A_PHONE_NUMBER = 2;

    //Constructor
    /**
     * Instantiates a new Phone number to check if it is valid.
     *
     * @param phoneNumber the phone number
     */
    public ValidPhoneNumber(String phoneNumber){
        pno = phoneNumber;
    }

    /**
     * Checks if the phone number provided in the form of a String, is a valid phone number
     *
     * @return a long value, (0) - Provided number is empty, (-1) -  Provided number is invalid, (1) - Provided number is valid
     * @see ValidPhoneNumber#EMPTY
     * @see ValidPhoneNumber#VALID
     * @see ValidPhoneNumber#INVALID
     */
    public  long isValid(){
        if ((pno == null) || (pno.length() == 0)){
            return EMPTY;
        }else if(isNumeric()){
            if(is10Digit()){
                return VALID;
            }
        }
        return INVALID;
    }

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
