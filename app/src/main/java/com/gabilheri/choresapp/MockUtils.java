package com.gabilheri.choresapp;

import com.gabilheri.choresapp.models.Feed;
import com.gabilheri.choresapp.models.User;

import java.util.Random;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/20/15.
 */
public class MockUtils {

    private final static Random random;

    static {
        random = new Random();
    }

    public static int[] socialMediaResources = {
            R.drawable.ic_google,
            R.drawable.ic_facebook,
            R.drawable.ic_twitter
    };

    public static String[] userPics = {
            "https://lh6.googleusercontent.com/-PLz-1wFMskM/U1VdxVwcXgI/AAAAAAAAMvw/JUU7tw4CWms/w1674-h1676-no/1397661337286.jpg",
            "https://33.media.tumblr.com/avatar_709fd8f3d9a7_128.png",
            "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSIAbiG7YuVqH5RJVgabMrUlIyB0mJyryz4lYjnwq71LgBc-7Rx",
            "https://38.media.tumblr.com/avatar_ea958e421800_128.png",
            "https://lh5.googleusercontent.com/-uYfmMfHEgc0/U8tmAmxQBfI/AAAAAAAAHFU/Lu1sr2s2B_o/s1676-no/6c1565d7-996f-448f-b360-d196f9eaa2d4",
            "https://lh6.googleusercontent.com/--Vbi85jnmwM/VBS56X-uA2I/AAAAAAAAOzw/czqKKZYqS8o/s1350-no/3e093ebb-fba4-4f46-9a23-8597083097b3",
            "https://lh4.googleusercontent.com/-d8fZAy-H32c/UewsNyNUdVI/AAAAAAAAAC4/-4jEoDjKQAI/w962-h960-no/2012-07-01_00.11.19.png",
            "https://lh3.googleusercontent.com/-yknrEtu-eVo/UiUpqd_jZgI/AAAAAAAAAms/tRsd4-bxVhQ/s512-no/e810c46f-9eb0-4c7c-bcab-06ac0c441e37",
            "https://lh4.googleusercontent.com/-B6ITzWABbTc/Us5PiY921eI/AAAAAAAAABE/BjVd4_fy1qI/s812-no/2011-04-26%2B11.56.47.jpg",
            "https://lh5.googleusercontent.com/-2tYvUEKmXuU/ThPhwkDIztI/AAAAAAAAIso/Y3L4JPGCQAw/w1680-h1676-no/IMG_1535.JPG",
            "https://lh5.googleusercontent.com/-bSMw755G6SQ/VXFAvlx2A2I/AAAAAAABCb8/_MXmZuPG2wI/s1676-no/dots_2048.png",
            "https://lh4.googleusercontent.com/-ozt4HO8tTBA/U0B-GlkPqkI/AAAAAAAAJZQ/EEfuKWMxUdU/w1678-h1676-no/IMG_20140222_200540.jpg",
            "https://lh4.googleusercontent.com/-r_Jj4RJim1c/VHg07lzQDsI/AAAAAAACZCU/AzhBIO0s618/s346-no/profilepic.png",
            "https://lh4.googleusercontent.com/-scZZhDY21bg/VIXFq5XnI3I/AAAAAAAAAEA/bFJqtZRikIE/w610-h616-no/download.png",
            "https://lh5.googleusercontent.com/-EN25hmNd-YE/VTe_XfWM3vI/AAAAAAAAFwY/KDHC1PBlyWU/s800-no/babbq15_400x400_round.png"
    };

    public static String[] phrases = new String[] {
            "Marcus is going to Whole Foods @3:00 PM",
            "John is going to Chipotle @2:20 PM",
            "Martha is going to watch The Avengers @8:30 PM",
            "Sarah is going to Huts @6:30 PM",
            "Peter is going to Starbucks @5:00 PM",
            "Carissa is going to Austin Java @11:00 AM",
            "Carter is going to Burger King @1:00 PM,",
            "Mike is going to Trader Joe's @4:30 PM"
    };

    public static String[] names = new String[] {
            "Marcus Gabilheri",
            "Carissa Gabilheri",
            "Angelina Jolie",
            "Sandra Bullock",
            "Brad Pitt",
            "John Doe",
            "Alice Connors",
            "Jerry Chan",
            "Kieran Stolorz",
            "Bruce Willis",
            "Some Really Long Name",
            "Amanda Carter",
            "Bon Jovi",
            "Hali Deubler",
            "Kyle Riedemann",
            "Ryan Dawkins",
            "Aaron Weaver"
    };

    public static Feed getRandomFeed() {
        Feed f = new Feed();
        f.setTitle(phrases[random.nextInt(phrases.length)]);
        f.setUserPicUrl(userPics[random.nextInt(userPics.length)]);
        f.setCommentsCount(random.nextInt(30));
        f.setFavoritesCount(random.nextInt(40));
        f.setSharesCount(random.nextInt(15));
        return f;
    }

    public static User getRandomUser() {
        User user = new User();
        user.setName(names[random.nextInt(names.length)]);
        user.setUserPicture(userPics[random.nextInt(userPics.length)]);
        user.setSocialMedia(getRandomSocialResource());
        return user;
    }

    public static int getRandomSocialResource() {
        return socialMediaResources[random.nextInt(socialMediaResources.length)];
    }

}
