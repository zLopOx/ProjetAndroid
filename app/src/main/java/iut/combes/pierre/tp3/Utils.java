package iut.combes.pierre.tp3;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.util.Patterns;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * Utils classes
 *
 * Created by Hugo Gresse on 03/12/2017.
 */

public class Utils {

    public static String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getDefaultUserEmail(Context context){
        Pattern   emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
        Account[] accounts     = AccountManager.get(context).getAccounts();
        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                return account.name;
            }
        }
        return "";
    }

    public static String getRelativeDate(long timestamp) {
        try {
            Date past = new Date(new Timestamp(timestamp).getTime());
            Date now = new Date();

            return TimeUnit.MILLISECONDS.toMinutes(now.getTime() - past.getTime()) + " minutes ago";
        } catch (Exception j) {
            j.printStackTrace();
            return "";
        }
    }

}
