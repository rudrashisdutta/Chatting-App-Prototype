package com.xhetriva.basicchattingapp.activity;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mukesh.OtpView;
import com.xhetriva.basicchattingapp.R;
import com.xhetriva.basicchattingapp.databinding.ActivityLoginBinding;
import com.xhetriva.basicchattingapp.logic.ValidPhoneNumber;

import java.util.Objects;


/**
 * The type Login activity.
 */
public class LoginActivity extends AppCompatActivity {

    private String phoneNumber;

    private Button getOTP;
    private EditText pno;
    private  Toast toast;
    private OtpView otpView;
    /**
     * The Binding.
     */
    protected ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();
        pno = binding.pno;
        pno.requestFocus();
        getOTP = binding.getOTPForPNO;
        otpView = binding.otpView;
        toast = new Toast(getApplicationContext());
        setGetOTP();
        setPno();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void  setPno(){
        pno.setOnEditorActionListener((v, actionId, event) -> {
            boolean handled = false;
            if(actionId == EditorInfo.IME_ACTION_DONE){
                getOTP.performClick();
                handled = true;
            }
            return handled;
        });
    }

    private void setGetOTP() {
        getOTP.setOnClickListener(v -> {
            phoneNumber = pno.getText().toString();
            ValidPhoneNumber validPhoneNumber = new ValidPhoneNumber(phoneNumber);
            long validity;
            if ((validity = validPhoneNumber.isValid()) == ValidPhoneNumber.VALID) {
                onPhoneNumberValid();
            } else if (validity == ValidPhoneNumber.INVALID){
                onPhoneNumberInvalid(R.string.on_invalid_pno);
            } else if (validity == ValidPhoneNumber.EMPTY){
                onPhoneNumberInvalid(R.string.on_empty_pno);
            }
            pno.setText(R.string.empty_string);

        });
    }
    private void revert(){

    }
    private void onPhoneNumberValid(){
        try {
            softKeyboard(pno,true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        try {
            LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.custom_toast, findViewById(R.id.toast_layout_root));
            TextView textView = view.findViewById(R.id.customToast);
            try {
                toast.cancel();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.BOTTOM, 0, 400);
            toast.setDuration(Toast.LENGTH_SHORT);
            textView.setText(R.string.otp_sent);
            toast.setView(view);
            toast.show();
            otpView.setVisibility(View.VISIBLE);
            pno.setEnabled(false);
            pno.setVisibility(View.GONE);
            otpView.requestFocus();
            getOTP.setText(R.string.verify_otp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void onPhoneNumberInvalid(int messageResourceID){
        try {
            LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.custom_toast, findViewById(R.id.toast_layout_root));
            TextView textView = view.findViewById(R.id.customToast);
            try {
                toast.cancel();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.TOP, 0, 50);
            toast.setDuration(Toast.LENGTH_SHORT);
            textView.setText(messageResourceID);
            toast.setView(view);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            pno.requestFocus();
            softKeyboard(pno,false);
        } catch (Exception ignored){

        }
    }

    private void softKeyboard(View view, boolean hide){
        try{
            if(hide) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            } else{
                if(view.requestFocus()){
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(view,InputMethodManager.SHOW_IMPLICIT);
                }
            }
        }catch (Exception ignored){

        }
    }
}


/*TODO
*   Add a LOG IN/ SIGN UP at the top.
*   Show Loading App Like a Toast. (cancel) => Maybe a complete new activity.\
*   Add default modes of all components.
*   Add an App LOGO by adding XML or mipmap for app logo.
 */