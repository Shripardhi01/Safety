package com.dynast.womensafety.helper;
import android.content.Context;
import android.content.SharedPreferences;



public class SharedPrefManager {
    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private static final String SHARED_PREF_NAME = "womensafetydata";

    private static final String KEY_USER_ID = "keyuserid";
    private static final String KEY_USER_NAME = "keyusername";
    private static final String KEY_USER_MOBILE = "keyusermobile";

    private static final String KEY_USER_ADDRESS = "keyuseraddress";
    private static final String KEY_USER_EME1 = "keyuseremergency1";
    private static final String KEY_USER_EME2 = "keyuseremergency2";
    private static final String KEY_USER_EME3 = "keyuseremergency3";

    private static final String KEY_USER_POLICE = "keyuserpolice";

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public boolean userLogin(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_USER_ID, user.getId());
        editor.putString(KEY_USER_NAME, user.getName());
        editor.putString(KEY_USER_MOBILE, user.getMobile());
        editor.apply();
        return true;
    }


    public boolean userData(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_USER_ID, user.getId());
        editor.putString(KEY_USER_NAME, user.getName());
        editor.putString(KEY_USER_MOBILE, user.getMobile());
        editor.putString(KEY_USER_ADDRESS, user.getAddress());
        editor.putString(KEY_USER_EME1, user.getEm1());
        editor.putString(KEY_USER_EME2, user.getEm2());
        editor.putString(KEY_USER_EME3, user.getEm3());
        editor.apply();
        return true;
    }




    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.getString(KEY_USER_EME1, null) != null)
            return true;
        return false;
    }

    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt(KEY_USER_ID, 0),
                sharedPreferences.getString(KEY_USER_NAME, null),
                sharedPreferences.getString(KEY_USER_MOBILE, null),
                sharedPreferences.getString(KEY_USER_ADDRESS, null),
                sharedPreferences.getString(KEY_USER_EME1, null),
                sharedPreferences.getString(KEY_USER_EME2, null),
                sharedPreferences.getString(KEY_USER_EME3, null)
        );
    }


    public boolean logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }
}
