package iut.combes.pierre.tp3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

/**
 * Manage user related storage such as email.
 * <p>
 * Created by Hugo Gresse on 03/12/2017.
 */

public class UserStorage {

    private static final String USER_NAME  = "user_name";
    private static final String USER_EMAIL = "user_email";

    public static void saveUserInfo(Context context, String name, String email) {
        SharedPreferences.Editor editor =
                PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(USER_NAME, name);
        editor.putString(USER_EMAIL, email);
        editor.apply();
    }

    public static boolean isUserLoggedIn(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        return sharedPreferences.getString(USER_NAME, null) != null &&
                sharedPreferences.getString(USER_EMAIL, null) != null;
    }

    @Nullable
    public static User getUserInfo(Context context) {
        if(!isUserLoggedIn(context)){
            return null;
        }
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        return new User(sharedPreferences.getString(USER_NAME, ""), sharedPreferences.getString(USER_EMAIL, ""));
    }

    public static void deleteUserInfo(Context context){
        SharedPreferences.Editor editor =
                PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.remove(USER_NAME);
        editor.remove(USER_EMAIL);
        Intent intent = new Intent(context, NamePickerActivity.class);
        context.startActivity(intent);

    }

}
