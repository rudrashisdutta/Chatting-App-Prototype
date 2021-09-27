package com.xhetriva.basicchattingapp.authenticate;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

/**
 * The type Authenticate otp.
 */
public class AuthenticateOTP {

    private final String phoneNumber;

    private FirebaseAuth auth;
    private Activity activity;
    private PhoneAuthOptions authOptions;

    /**
     * Instantiates a new Authenticate otp.
     *
     * @param phoneNumber the phone number to authenticate
     * @param activity    the activity {@link AuthenticateOTP} is being used in
     */
    public AuthenticateOTP(@NonNull String phoneNumber, Activity activity) {
        this.phoneNumber = phoneNumber;
        this.activity = activity;
    }

    /**
     * Setup boolean.
     *
     * @param onVerificationCompleted the on verification completed runnable
     * @param onVerificationFailed    the on verification failed runnable
     * @param onCodeSent              the on code sent runnable
     * @return a boolean value indicating success when true and failure when false
     */
    public boolean setup(@NonNull Runnable onVerificationCompleted,@NonNull  Runnable onVerificationFailed,@NonNull  Runnable onCodeSent){
        boolean success;
        try {
            auth = FirebaseAuth.getInstance();
            authOptions = PhoneAuthOptions.newBuilder(auth)
                    .setPhoneNumber(this.phoneNumber)
                    .setTimeout(60L, TimeUnit.SECONDS)
                    .setActivity(activity)
                    .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                        @Override
                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                            onVerificationCompleted.run();
                        }

                        @Override
                        public void onVerificationFailed(@NonNull FirebaseException e) {
                            onVerificationFailed.run();
                        }

                        @Override
                        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                            super.onCodeSent(s, forceResendingToken);
                            onCodeSent.run();
                        }
                    }).build();
            success = true;
        }catch (Exception e){
            success = false;
            e.printStackTrace();
        }
        return success;
    }

    /**
     * Verify boolean.
     *
     * @return a boolean value indicating success when true and failure when false
     */
    public boolean verify(){
        boolean success;
        try {
            PhoneAuthProvider.verifyPhoneNumber(authOptions);
            success = true;
        }catch (Exception e){
            success = false;
            e.printStackTrace();
        }
        return success;
    }
}
