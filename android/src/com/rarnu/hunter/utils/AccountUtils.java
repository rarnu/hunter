package com.rarnu.hunter.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class AccountUtils {

    public static List<String> getBindedAccounts(Context context) {
        List<String> ret = new ArrayList<String>();
        if (context != null) {
            try {
                AccountManager am = AccountManager.get(context);
                Account[] accs = am.getAccounts();

                for (Account a : accs) {
                    ret.add(String.format("[%s|%s]", a.type, a.name));
                }
            } catch (Exception e) {

            }
        }
        return ret;
    }

    public static String getBindedAccountsAsString(Context context) {
        List<String> accs = getBindedAccounts(context);
        String ret = "";
        for (String s : accs) {
            ret += s + "\n";
        }
        return ret;
    }
}
