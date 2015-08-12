package com.gabilheri.choresapp;
import android.content.ContentValues;
import android.content.Context;

import com.gabilheri.choresapp.data.ChoresContract;
import com.gabilheri.choresapp.data.models.User;

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

    public static String[] maleUserPics = {
            "https://lh6.googleusercontent.com/-PLz-1wFMskM/U1VdxVwcXgI/AAAAAAAAMvw/JUU7tw4CWms/w1674-h1676-no/1397661337286.jpg",
            "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSIAbiG7YuVqH5RJVgabMrUlIyB0mJyryz4lYjnwq71LgBc-7Rx",
            "https://lh6.googleusercontent.com/--Vbi85jnmwM/VBS56X-uA2I/AAAAAAAAOzw/czqKKZYqS8o/s1350-no/3e093ebb-fba4-4f46-9a23-8597083097b3",
            "https://lh4.googleusercontent.com/-d8fZAy-H32c/UewsNyNUdVI/AAAAAAAAAC4/-4jEoDjKQAI/w962-h960-no/2012-07-01_00.11.19.png",
            "https://lh3.googleusercontent.com/-yknrEtu-eVo/UiUpqd_jZgI/AAAAAAAAAms/tRsd4-bxVhQ/s512-no/e810c46f-9eb0-4c7c-bcab-06ac0c441e37",
            "https://lh4.googleusercontent.com/-ozt4HO8tTBA/U0B-GlkPqkI/AAAAAAAAJZQ/EEfuKWMxUdU/w1678-h1676-no/IMG_20140222_200540.jpg",
            "https://lh4.googleusercontent.com/-scZZhDY21bg/VIXFq5XnI3I/AAAAAAAAAEA/bFJqtZRikIE/w610-h616-no/download.png",
            "https://lh5.googleusercontent.com/-EN25hmNd-YE/VTe_XfWM3vI/AAAAAAAAFwY/KDHC1PBlyWU/s800-no/babbq15_400x400_round.png"
    };

    public static String[] femaleUserPics = {
            "https://33.media.tumblr.com/avatar_709fd8f3d9a7_128.png",
            "https://38.media.tumblr.com/avatar_ea958e421800_128.png",
            "https://lh5.googleusercontent.com/-uYfmMfHEgc0/U8tmAmxQBfI/AAAAAAAAHFU/Lu1sr2s2B_o/s1676-no/6c1565d7-996f-448f-b360-d196f9eaa2d4",
            "https://lh6.googleusercontent.com/--Vbi85jnmwM/VBS56X-uA2I/AAAAAAAAOzw/czqKKZYqS8o/s1350-no/3e093ebb-fba4-4f46-9a23-8597083097b3",
            "https://lh3.googleusercontent.com/-yknrEtu-eVo/UiUpqd_jZgI/AAAAAAAAAms/tRsd4-bxVhQ/s512-no/e810c46f-9eb0-4c7c-bcab-06ac0c441e37",
            "https://lh4.googleusercontent.com/-B6ITzWABbTc/Us5PiY921eI/AAAAAAAAABE/BjVd4_fy1qI/s812-no/2011-04-26%2B11.56.47.jpg",
            "https://lh4.googleusercontent.com/-r_Jj4RJim1c/VHg07lzQDsI/AAAAAAACZCU/AzhBIO0s618/s346-no/profilepic.png",
    };

    public static String[] phrases =  {
            " is going to Whole Foods @3:00 PM",
            " is going to Chipotle @2:20 PM",
            " is going to watch The Avengers @8:30 PM",
            " is going to Huts @6:30 PM",
            " is going to Starbucks @5:00 PM",
            " is going to Austin Java @11:00 AM",
            " is going to Burger King @1:00 PM,",
            " is going to Trader Joe's @4:30 PM",
            " wants to play basketball @4:00 PM",
            " wants to watch The Titanic @9:00 PM",
            " wants to get groceries @11:00 AM",

    };

    public static String[] maleNames = {
            "Marcus Gabilheri",
            "Brad Pitt",
            "John Doe",
            "Jerry Chan",
            "Kieran Stolorz",
            "Bruce Willis",
            "Some Really Long Name",
            "Bon Jovi",
            "Kyle Riedemann",
            "Ryan Dawkins",
            "Aaron Weaver"
    };

    public static String[] femaleNames = {
            "Carissa Gabilheri",
            "Angelina Jolie",
            "Sandra Bullock",
            "Alice Connors",
            "Amanda Carter",
            "Hali Deubler"
    };

    public static String[] smallIpsumStrings = {
            "Duis vitae scelerisque lacus. Ut facilisis massa et tortor aliquam suscipit. Nunc et metus turpis. Etiam laoreet, ipsum vel ultricies mollis, odio sem pretium leo, ac tincidunt metus nunc porttitor diam.",
            "Praesent imperdiet placerat ex, non sollicitudin tortor sagittis et. Nullam porta lacus dui, quis egestas quam lacinia quis. Donec nunc risus, sagittis eu tellus vel, pharetra vehicula sem.",
            "Nullam a vestibulum diam. Proin tincidunt rutrum venenatis.",
            "Nam mollis tempor viverra. Nulla velit tortor, rhoncus ut nibh ac, congue aliquet odio. Aliquam porta, urna at sodales sodales, orci enim consectetur ipsum, a eleifend nunc ipsum vel eros.",
            "Praesent imperdiet placerat ex, non sollicitudin tortor sagittis et.",
            "Proin eleifend ac libero at placerat. Aliquam lobortis porttitor metus.",
            "Cras condimentum mi sit amet erat tincidunt, vitae elementum tellus pulvinar. Suspendisse et risus ut orci tincidunt ultricies. Nullam consequat felis ac lorem luctus, vitae vehicula risus laoreet. Integer vitae ligula in lorem posuere euismod.",
            "Nunc mollis metus vitae erat feugiat, eu condimentum urna luctus. In dictum, leo in luctus consectetur, diam purus aliquam leo.",
            "In enim arcu, congue vel nulla a, rhoncus porttitor nisi. Vestibulum ac nulla velit. Phasellus facilisis nunc est, at iaculis odio ultrices porttitor.",
            "Nullam quis elementum nulla, sit amet malesuada justo. Donec lacinia suscipit tempor. Pellentesque sagittis quis justo at semper.",
            "Aliquam lobortis porttitor metus. Ut eu purus vel felis hendrerit iaculis sed vel erat. Nunc mollis metus vitae erat feugiat, eu condimentum urna luctus. In dictum, leo in luctus consectetur, diam purus aliquam leo, at sollicitudin enim nibh in risus.",
            "Etiam quis libero aliquet, dictum nibh et, tempor diam. In feugiat vehicula posuere. Vivamus nisl ante, convallis sit amet risus ultricies, porta tincidunt sem.",
            "Morbi et ex varius, feugiat felis non, suscipit diam. Nunc volutpat velit enim, vel viverra eros interdum in.",
            "Ut facilisis massa et tortor aliquam suscipit. Nunc et metus turpis. Etiam laoreet, ipsum vel ultricies mollis, odio sem pretium leo, ac tincidunt metus nunc porttitor diam.",
            "Pellentesque sagittis quis justo at semper.",
            "nteger vestibulum id metus sed dapibus. Cras molestie mauris lorem, sit amet volutpat eros efficitur sit amet. Vestibulum tempor sem at tortor facilisis posuere.",
            "Pellentesque et diam aliquam, consequat neque at, molestie nibh. Nullam eget odio sit amet enim pretium fermentum at a lacus.",
            "Cras condimentum mi sit amet erat tincidunt, vitae elementum tellus pulvinar. Suspendisse et risus ut orci tincidunt ultricies. Nullam consequat felis ac lorem luctus, vitae vehicula risus laoreet. Integer vitae ligula in lorem posuere euismod.",
            "Morbi et ex varius, feugiat felis non, suscipit diam. Nunc volutpat velit enim, vel viverra eros interdum in.",
            "Etiam quis libero aliquet, dictum nibh et, tempor diam. In feugiat vehicula posuere. Vivamus nisl ante, convallis sit amet risus ultricies, porta tincidunt sem.",
            "Aliquam lobortis porttitor metus. Ut eu purus vel felis hendrerit iaculis sed vel erat. Nunc mollis metus vitae erat feugiat, eu condimentum urna luctus.",
            "Praesent nisi nisl, aliquet id tellus maximus, sagittis hendrerit erat. Curabitur placerat auctor dui, ac tincidunt orci vestibulum at. Morbi et ante vitae nisi mattis dignissim.",
            "In dolor libero, rutrum quis ligula a, malesuada fringilla ligula. Donec commodo massa nisi. Nunc accumsan dictum libero, at tristique mauris tincidunt nec.",
            "Suspendisse erat leo, luctus et vestibulum viverra, congue eget quam."
    };

    public static String[] largeIpsumStrings = {
            "Suspendisse dapibus enim facilisis lacus lacinia auctor. Quisque nulla ipsum, maximus nec ante eu, viverra euismod velit. Phasellus gravida nisi in molestie ultricies. Sed at ultrices est, vehicula lobortis purus. Quisque rutrum ex consectetur lobortis sagittis. Proin venenatis magna eget nibh eleifend rutrum. Vestibulum vitae tellus mi. Morbi semper iaculis lacus commodo viverra. Aliquam nec lacus volutpat tortor ultricies maximus id a est. Aliquam sem erat, faucibus vitae tortor eu, pulvinar consectetur ligula. Pellentesque mattis tempus tortor. Maecenas cursus scelerisque nisl, sit amet convallis sapien bibendum vitae. Cras maximus pellentesque nisi vel laoreet.",
            "Cras vestibulum ligula a magna lacinia porta. Cras cursus quam risus, id posuere lorem hendrerit ullamcorper. Aliquam at massa nisl. Integer mattis, sem ut ornare volutpat, felis nunc finibus est, a commodo ex lorem elementum sapien. Aliquam sed lobortis nisi. Nam luctus ipsum odio, at aliquam sem molestie ut. Curabitur ut commodo mi, sed accumsan nibh. Suspendisse venenatis felis quam, sit amet mattis turpis gravida a. Vestibulum consequat tortor libero, in hendrerit dui placerat vel. Nunc rhoncus justo a ante fringilla, in vehicula quam tempor. Nunc vehicula sapien quis ullamcorper efficitur. Suspendisse in orci gravida, ornare elit vel, suscipit mauris. Ut a varius mauris. Aenean et lectus vel justo posuere congue quis a quam. Interdum et malesuada fames ac ante ipsum primis in faucibus. Duis condimentum dui non magna fermentum, ut eleifend orci tempus.",
            "Cras aliquam ac est eu eleifend. Vivamus facilisis eu diam non pharetra. Pellentesque laoreet metus in ante bibendum, et cursus lacus faucibus. Duis vel pulvinar elit. Phasellus ultrices felis id mauris lobortis, a sollicitudin enim imperdiet. Duis ac laoreet mauris. Donec eleifend ipsum arcu, sit amet imperdiet metus rhoncus vel. Integer luctus diam quis vehicula sollicitudin. Aliquam nisi nisl, euismod ut nisl id, sodales rhoncus nisi. Nulla facilisi. Suspendisse sed nisi libero. Donec ut velit luctus, sollicitudin nisl convallis, sagittis eros. Fusce libero velit, accumsan sed imperdiet a, consequat sed diam. Aenean consequat felis et ligula sagittis, in blandit erat cursus. Aliquam quis consequat diam. Pellentesque commodo tortor vel neque iaculis, eu congue est sollicitudin.",
            "Proin eget diam odio. Aenean sed ex posuere, faucibus diam eget, ultricies tortor. Donec consectetur, sem vel convallis faucibus, nisi massa consequat velit, id interdum ipsum quam quis elit. In vel cursus purus. Donec mattis tincidunt nisi, sed ultricies metus mattis in. Praesent at tempus nulla. Etiam fringilla hendrerit est ut ultrices. Proin efficitur nibh quis quam dapibus aliquet.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis orci neque, rhoncus sit amet nibh vitae, pretium eleifend est. Fusce cursus velit et ante gravida, sed auctor orci sollicitudin. Proin lacus nulla, suscipit eget venenatis et, faucibus sit amet nibh. Donec eu ligula eu quam ornare viverra eget vitae diam. Mauris aliquam felis sed metus ullamcorper mattis. Sed venenatis et mauris ut malesuada. Nullam blandit odio vel purus euismod, in congue ante sagittis.",
            "Nam rhoncus odio eros, vel condimentum metus feugiat sed. Sed ullamcorper odio eget magna finibus blandit. Sed id urna eget nunc eleifend dictum. Morbi id scelerisque quam. Vivamus rhoncus arcu vitae ante gravida sollicitudin. Proin varius ut dui in congue. Ut ac mi bibendum, laoreet risus eu, malesuada nulla.",
            "Donec sit amet justo ac eros posuere eleifend. Vivamus ex ex, bibendum ac est pulvinar, tristique dictum ligula. Nulla cursus pellentesque porta. Nullam consectetur enim enim, id elementum lacus tempus quis. Nulla sed nisi id mauris dictum tempor sed sed erat. Etiam aliquet velit eu odio tristique, eu interdum leo semper. Phasellus non nisl ut orci dictum auctor ut ac lectus. Cras dolor mauris, tempus eget placerat in, pulvinar nec lorem. Donec eget condimentum eros. Praesent nisi nisl, aliquet id tellus maximus, sagittis hendrerit erat. Curabitur placerat auctor dui, ac tincidunt orci vestibulum at. Morbi et ante vitae nisi mattis dignissim.",
            "Nunc venenatis non erat nec posuere. Cras ligula sapien, hendrerit id vulputate vel, placerat vel ex. Mauris vel nibh consequat elit porta dapibus. Curabitur imperdiet vitae neque commodo blandit. Vivamus ex ante, tempor quis molestie vel, vestibulum in metus. Sed tempor dui enim, ut convallis libero dignissim ac. Maecenas at posuere lacus. Suspendisse interdum ut mauris molestie vestibulum. Donec nunc nisl, imperdiet a blandit sit amet, hendrerit ac orci. Sed eget augue augue.",
            "In dolor libero, rutrum quis ligula a, malesuada fringilla ligula. Donec commodo massa nisi. Nunc accumsan dictum libero, at tristique mauris tincidunt nec. Nunc quis nibh a erat pharetra porta. Cras non egestas urna, ac fermentum ex. Quisque eleifend, nisi sit amet hendrerit pulvinar, libero erat finibus nisi, et finibus est nulla eu sapien. Sed viverra sed ligula nec lobortis.",
            "Suspendisse erat leo, luctus et vestibulum viverra, congue eget quam. Nullam facilisis erat nibh, a rutrum nunc sollicitudin id. Nulla eu erat diam. Fusce et nunc neque. Nam ac molestie est. Morbi condimentum interdum augue. Nunc ac viverra orci. Sed dolor erat, semper nec eros vitae, varius scelerisque ligula. Sed porttitor blandit purus. Fusce hendrerit id turpis in dignissim. Quisque eu blandit neque."
    };

    public static void insertMockUsers(Context context, int num) {
        ContentValues[] values = new ContentValues[num];
        for(int i = 0; i < num; i++) {
            User user = generateMockUser();
            values[i] = User.toContentValues(user);
        }

        context.getContentResolver().bulkInsert(ChoresContract.UserEntry.buildUserUri(), values);
    }

    public static User generateMockUser() {
        int random = new Random().nextInt();
        User user = new User();
        String name = getUserName(random);
        user.setFullName(name);
        user.setUsername(name);
        user.setPicUrl(getUserPicture(random));
        return user;
    }

    public static String getUserPicture(int r) {
        if(r % 2 == 0) {
            return maleUserPics[random.nextInt(maleUserPics.length)];
        } else {
            return femaleUserPics[random.nextInt(femaleUserPics.length)];
        }
    }

    public static String getUserName(int r) {
        if(r % 2 == 0) {
            return maleNames[random.nextInt(maleNames.length)];
        } else {
            return femaleNames[random.nextInt(femaleNames.length)];
        }
    }

    public static int getRandomSocialResource() {
        return socialMediaResources[random.nextInt(socialMediaResources.length)];
    }

}
