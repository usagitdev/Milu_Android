package charles.com.milu.ChatTab;

import java.util.ArrayList;
import java.util.Random;

import charles.com.milu.R;
import charles.com.milu.utils.logger.Images;

/**
 * Created by dev on 9/21/17.
 */

public class ChatConversationItem {
    private String[] userFullNames = {
            "Alvaro Alcocer","Alberto Ates", "Keven Kujawa", "Weldon Wickman",    "Dion Dockstader",    "Domingo Demming",    "Vito Vizcarrondo",    "Ellsworth Edelson",
            "Trey Torgeson",    "Morton Mendoza",    "Tyler Twyman",    "Eric Enriguez",    "Juan Judon",    "Alfred Arellano",    "Garret Ganley",    "Ryan Rotolo",
            "Thanh Thor",    "Clint Cumberland",    "Arlie Abate",    "Emil Eaddy",    "Dwayne Dermody",    "Arron Arrigo",    "Cedric Cavender",
            "Elmo Edelen",    "Devin Dumais",    "Stewart Seaberg",    "Grover Gioia",    "Jamey Junkins",    "Marcellus Marchan",    "Cruz Choudhury",
            "Virgilio Valls",    "Jed Jules",    "Ross Race",    "Salvatore Sarcone",    "Lupe Loeb",    "Ernie Enger",    "Len Laven",    "Paul Potter",
            "Adan Abarca",    "Percy Pando",    "Dusty Dombrowski",    "Gilbert Gershman",    "Jarred Jaqua",    "Carrol Capone",    "Shon Surprenant",    "Ellis Eacret",
            "Elijah Eggert",    "Leonard Lamica",    "Kris Kleiber",    "Arden An",    "Ian Issac",    "Genaro Geter",   "Julianna Jerrell",   "Mahalia Marchi",
            "Le Lerma",            "Marjory Merryman",            "Luigi Laporte",            "Beata Bilski",           "Dominick Duhn",            "Brooks Bettencourt",
            "Onie Oyola",            "Quintin Quinn",            "Jill Jasinski",            "Arlen Alm",            "Lashawnda Lisa",            "Tona Toye",
            "Dorthy Devereux",            "Deb Driscoll",            "Miesha Mundy",            "Kelvin Kinsman",            "Florine Franzone",            "Rosia Rudy",
            "Temeka Tuff",            "Felipe Fein",            "Brigitte Batdorf",            "Lilly Londono",            "Deloras Dempster",            "Iluminada Ikeda",
            "Nidia Nicks",            "Matt Mccoy",            "Susannah Strebel",            "Tanya Tardiff",            "Raul Robeson",            "Claretha Canale",
            "Mireya Minnich",            "Shameka Smidt",            "Lakeesha Lenzi",            "Vito Vance",            "Catharine Ciampa",            "Enoch Estevez",
            "Eden Embry",            "Shawnta Scoles",            "Cristine Chavis",            "Ines Immel",            "Bell Bass",            "Evangelina Epps",
            "Fletcher Fuentez",            "Senaida Swager",            "Buck Byrns",            "Sharleen Smead",            "Yong Yarbro",            "Monnie Mcgrane",
            "Donita Dubray",            "Deandre Delaughter",            "Lorilee Lydick",            "Bob Bergen",            "Kittie Kao",            "Margie Marley",
            "Lu Lammers",            "Clara Carlos",            "Rachal Rozzi",            "Warren Willimas",            "Adrien Asbury",            "Lilian Lovely",
            "Ernestine Eisner",            "Minna Milian",            "Lecia Lamica",            "Kristie Kerry",            "Thomasena Tedeschi",            "Carlita Corella",
            "Ivelisse Ishee",            "Kati Kloster",            "Obdulia Onstad",            "Marisela Mcconnel",            "Damon Delzell",            "Selina Seim",
            "Librada Luellen",            "Marylynn Mattie",            "Dora Debolt",            "Louis Lueck",            "Camellia Chun",            "Karen Kissinger",
            "Annamarie Apple",            "Mazie Mccausland",            "Belinda Bodin",            "Shelli Sanluis",            "Rochell Rafael",            "Hedwig Hildebrandt",            "Millicent Marie",            "Winnie Womack",            "Almeta Abundis",
            "Susan Searls",            "Gus Grisby",            "Paul Penix",            "Emogene Elwell",            "Misti Mccolley",            "Hugo Harpster",
            "Grady Gunnell",            "Sana Shumate",            "Loraine Leff"};
    private Integer[] userimages = {R.drawable.user01, R.drawable.user02, R.drawable.user03, R.drawable.user04, R.drawable.user05,
            R.drawable.user06, R.drawable.user07, R.drawable.user08, R.drawable.user09};
    private Integer[] notitypes = {R.drawable.chat_notification_mingle_match_icon, R.drawable.chat_notification_mingle_location_match_icon,
            R.drawable.chat_notification_like_icon, R.drawable.chat_notification_message_icon};

    private int chatNotiType;
    private String timeStamp;
    private String userBadge;
    private String backgroundImage;
    private int userImage;
    private String userName;
    private boolean isOnline;
    private String userMessage;

    private static int lastContactId = 0;
    public ChatConversationItem() {
    }

    public ChatConversationItem(int chatNotiType, String timeStamp, String userName, String userMessage,
                                String userBadge, int userImage, boolean isOnline, String backgroundImage){

        this.chatNotiType = chatNotiType;
        this.timeStamp = timeStamp;
        this.userBadge = userBadge;
        this.userImage = userImage;
        this.backgroundImage = backgroundImage;
        this.userName = userName;
        this.userMessage = userMessage;
        this.isOnline = isOnline;

    }


    public ArrayList<ChatConversationItem> createContactsList(int numContacts) {
        ArrayList<ChatConversationItem> eventItem = new ArrayList<ChatConversationItem>();

        for (int i = 1; i <= numContacts; i++) {
            Random r = new Random();
            int index = r.nextInt(9);

            eventItem.add(new ChatConversationItem(notitypes[r.nextInt(4)], "10:30P", userFullNames[i].toLowerCase(), "\"this is the test message\"",
                    String.valueOf(r.nextInt(10)), userimages[r.nextInt(9)], r.nextBoolean(), Images.imageUrls[i]));
        }

        return eventItem;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public int getUserImage() {
        return userImage;
    }

    public void setUserImage(int userImage) {
        this.userImage = userImage;
    }

    public String getUserBadge() {
        return userBadge;
    }

    public void setUserBadge(String userBadge) {
        this.userBadge = userBadge;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getChatNotiType() {
        return chatNotiType;
    }

    public void setChatNotiType(int chatNotiType) {
        this.chatNotiType = chatNotiType;
    }
}
