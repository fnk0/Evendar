package com.gabilheri.choresapp.utils;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 8/11/15.
 */
public final class SocialUtils {

    private SocialUtils() {
    }

    public static String getFirstLastName(String fullName) {
        String[] fNameArr = fullName.split(" ");
        return String.format("%s %s",fNameArr[0], fNameArr[fNameArr.length - 1]);
    }
}
