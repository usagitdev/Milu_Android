package charles.com.milu.ChatTab;

import java.util.ArrayList;
import java.util.Random;

import charles.com.milu.R;
import charles.com.milu.utils.logger.Images;

/**
 * Created by dev on 9/21/17.
 */

public class ChatDiscoverItem {
    private String[] discoverPlaces = {
            "haunted house", "cafe latte coffee house", "motor speedway grand prox", "hipster cafe", "contemporary", "cheers & beers bar", "haunted house", "cafe latte coffee house", "motor speedway grand prox", "hipster cafe", "contemporary", "cheers & beers bar"};
    private String[] discoverLikeTexts = {
            "haunted house", "cafe latte coffee house", "motor speedway grand prox", "hipster cafe", "contemporary", "cheers & beers bar",  "haunted house", "cafe latte coffee house", "motor speedway grand prox", "hipster cafe", "contemporary", "cheers & beers bar"};
    private Integer[] backgroundImages = {R.drawable.event4, R.drawable.event5, R.drawable.event6, R.drawable.event7, R.drawable.event8,
            R.drawable.event9, R.drawable.event10, R.drawable.event11, R.drawable.event12, R.drawable.event13};
    private String[] messageCounts= {"301", "998", "1.3K", "301", "998", "1.3K", "301", "998", "1.3K"};

    private String discoverPlace;
    private String discoverLikeText;
    private String discoverLikeCount;
    private String backgroundImage;

    private static int lastContactId = 0;
    public ChatDiscoverItem() {
    }

    public ChatDiscoverItem(String discoverPlace, String discoverLikeText,
                            String discoverLikeCount, String backgroundImage){

        this.discoverPlace = discoverPlace;
        this.discoverLikeCount = discoverLikeCount;
        this.discoverLikeText = discoverLikeText;
        this.backgroundImage = backgroundImage;

    }


    public ArrayList<ChatDiscoverItem> createContactsList(int numContacts) {
        ArrayList<ChatDiscoverItem> eventItem = new ArrayList<ChatDiscoverItem>();

        for (int i = 1; i <= numContacts; i++) {
            Random r = new Random();
            int index = r.nextInt(9);

            eventItem.add(new ChatDiscoverItem(discoverPlaces[index], discoverLikeTexts[0],  messageCounts[index], Images.imageUrls[index]));
        }

        return eventItem;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getDiscoverPlace() {
        return discoverPlace;
    }

    public void setDiscoverPlace(String discoverPlace) {
        this.discoverPlace = discoverPlace;
    }

    public String getDiscoverLikeText() {
        return discoverLikeText;
    }

    public void setDiscoverLikeText(String discoverLikeText) {
        this.discoverLikeText = discoverLikeText;
    }

    public String getDiscoverLikeCount() {
        return discoverLikeCount;
    }

    public void setDiscoverLikeCount(String discoverLikeCount) {
        this.discoverLikeCount = discoverLikeCount;
    }
}
