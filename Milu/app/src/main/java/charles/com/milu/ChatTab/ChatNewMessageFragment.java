package charles.com.milu.ChatTab;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import charles.com.milu.Base.BaseFragment;
import charles.com.milu.CustomViews.TitleTextView;
import charles.com.milu.R;
import charles.com.milu.utils.CustomImageButton;

/**
 * Created by mac_dev on 11/6/17.
 */

public class ChatNewMessageFragment extends BaseFragment implements NewMessageAdapter.ItemClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.nav_close_button)
    CustomImageButton closeButton;

    @BindView(R.id.edt_conversation_name)
    EditText conversation_name;

    NewMessageAdapter adapter;

    public static ChatNewMessageFragment getInstance(){
        return new ChatNewMessageFragment();
    }

    @Override
    protected int addView() {
        return R.layout.fragment_chat_new_message;
    }

    @Override
    public void initView() {
        super.initView();
        Typeface workSans_Light = Typeface.createFromAsset(mAct.getAssets(),"WorkSans-Light.ttf");
        conversation_name.setTypeface(workSans_Light);

        closeButton.setOnClickListener(this);
        adapter = new NewMessageAdapter(getContext(), (new ChatUser()).setChatUsers(50));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.setListener(this);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.nav_close_button:
                mAct.onBackPressed();
                break;
        }
    }

    @Override
    public void mEventCell_Clicked(View v, int adapterPosition) {
        conversation_name.setText(adapter.getmContacts().get(adapterPosition).getUserName());
        addFragment(new ChatRoomFragment().getInstance(), true);
    }
}

class NewMessageAdapter extends RecyclerView.Adapter<NewMessageAdapter.ViewHolder> {



    public class ViewHolder extends RecyclerView.ViewHolder{

        public TitleTextView userName;
        public ImageView userAvatar;
        public RelativeLayout conversationCell;

        public ViewHolder(View itemView){

            super(itemView);

            userName = (TitleTextView) itemView.findViewById(R.id.user_full_name);
            userAvatar = (ImageView)itemView.findViewById(R.id.user_image);
            conversationCell = (RelativeLayout)itemView.findViewById(R.id.conversationCell);
        }
    }

    public List<ChatUser> getmContacts() {
        return mContacts;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {

        ChatUser eventItem = mContacts.get(position);

        Picasso.with(mContext).load(eventItem.getUserImage()).into(viewHolder.userAvatar);
        viewHolder.userName.setText(eventItem.getUserName().toLowerCase());
        viewHolder.conversationCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.mEventCell_Clicked(v, position);

            }
        });

    }

    private List<ChatUser> mContacts;
    // Store the context for easy access

    private Context mContext;

    // Pass in the contact array into the constructor
    public NewMessageAdapter(Context context, List<ChatUser> contacts) {

        mContacts = contacts;

        mContext = context;
    }

    private Context getContext() {

        return mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);

        View contactView = inflater.inflate(R.layout.select_group_new_message_cell, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public int getItemCount() {

        return mContacts.size();
    }
    public ItemClickListener listener;

    public void setListener(ItemClickListener listener){

        this.listener = listener;

    }
    public interface ItemClickListener {

        void mEventCell_Clicked(View v, int adapterPosition);
    }



}

class ChatUser{
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
    private int userImage;
    private String userName;

    public ChatUser(int userImage, String userName) {
        this.userImage = userImage;
        this.userName = userName;
    }

    public ChatUser() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserImage() {
        return userImage;
    }

    public void setUserImage(int userImage) {
        this.userImage = userImage;
    }

    public List<ChatUser> setChatUsers(int count) {
        List<ChatUser> chatusers = new ArrayList<>();

        for (int i = 0; i< count; i ++) {
            Random r = new Random();
            chatusers.add(new ChatUser(userimages[r.nextInt(9)], userFullNames[i]));
        }
        return chatusers;
    }

}
