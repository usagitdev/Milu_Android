package charles.com.milu.ProfileTab;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.appcompat.BuildConfig;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.ehamutcu.sectionpicker.SectionPicker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import charles.com.milu.MiluApplication;
import charles.com.milu.R;
import charles.com.milu.utils.util.Utils;

import static android.content.ContentValues.TAG;

/**
 * Created by mac_dev on 10/25/17.
 */

public class ProfileConnectionsFollowersFragment extends Fragment implements ProfileConnectionsFollowingRecyclerViewAdapter.RowClickListener,
        ProfileConnectionsFragment.onClickRightButtonListner, ProfileConnectionsFragment.onClickMenuButtonListner {

    boolean mUserVisibleHint = true;

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

    private Integer[] userimages = {R.drawable.user01, R.drawable.user02, R.drawable.user03, R.drawable.user04, R.drawable.user05, R.drawable.user06, R.drawable.user07, R.drawable.user08, R.drawable.user09};
    private List<ProfileConnectionFollowingModel> profileConnectionFollowingModels;
    private RecyclerView recyclerView;
    private SectionPicker sectionPicker;
    private ProfileConnectionsFollowingRecyclerViewAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;

    ProfileConnectionsFragment mFragment;
    public ProfileConnectionsFollowersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_connections_following, container, false);

        mFragment = (ProfileConnectionsFragment) getParentFragment();
        mFragment.setOnRightButtonListner(this);
        mFragment.setMenueListner(this);

        sectionPicker = (SectionPicker) view.findViewById(R.id.following_sectionPicker);
        recyclerView = (RecyclerView) view.findViewById(R.id.following_recyclerView);
        ProfileConnectionsFragment fragment = ProfileConnectionsFragment.getInstance();
        fragment.setOnRightButtonListner(this);

        if (MiluApplication.isCollection){
            populateRecyclerCollectionView();
        }else{
            populateRecyclerListView();
        }
        initSectionPicker();
        return view;
    }

    public void setRecyclerViewLayoutManager() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void populateRecyclerListView() {
        profileConnectionFollowingModels = new ArrayList<>();

        List<String> nameList = new ArrayList<>(Arrays.asList(userFullNames));

        Collections.sort(nameList);
        String letter = "";
        for (int i = 0; i < nameList.size(); i++){
            String character = nameList.get(i).substring(0, 1);
            if (TextUtils.isEmpty(letter) || !letter.equals(character)) {
                profileConnectionFollowingModels.add(new ProfileConnectionFollowingModel("", "", 0, 1, character));
                letter = character;
            }
            String uname = nameList.get(i);
            uname.replace(" ", "");
            uname = "@" + uname;

            Random r = new Random();
            int index = r.nextInt(9);
            profileConnectionFollowingModels.add(new ProfileConnectionFollowingModel(nameList.get(i), uname, userimages[index], 0, null));
        }
        setRecyclerViewLayoutManager();
        adapter = new ProfileConnectionsFollowingRecyclerViewAdapter(profileConnectionFollowingModels, getContext(), false);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setListener(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    sectionPicker.animate().setDuration(1000).alpha(0).setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            sectionPicker.setVisibility(View.INVISIBLE);
                        }
                    });
                }else {
                    sectionPicker.setAlpha(1);
                    sectionPicker.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                sectionPicker.animate().setDuration(1000).alpha(0).setListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        super.onAnimationEnd(animation);
//                        sectionPicker.setVisibility(View.INVISIBLE);
//                    }
//                });
            }
        });
    }

    private void populateRecyclerCollectionView() {
        profileConnectionFollowingModels = new ArrayList<>();

        List<String> nameList = new ArrayList<>(Arrays.asList(userFullNames));

        Collections.sort(nameList);
        String letter = "";
        for (int i = 0; i < nameList.size(); i++){
            String character = nameList.get(i).substring(0, 1);
            String uname = nameList.get(i);
            uname.replace(" ", "");
            uname = "@" + uname;

            Random r = new Random();
            int index = r.nextInt(9);
            if (TextUtils.isEmpty(letter) || !letter.equals(character)) {
                profileConnectionFollowingModels.add(new ProfileConnectionFollowingModel(nameList.get(i), uname, userimages[index], 0, character));
                letter = character;
            }
            profileConnectionFollowingModels.add(new ProfileConnectionFollowingModel(nameList.get(i), uname, userimages[index], 0, null));
        }
        gridLayoutManager = new GridLayoutManager(getContext(), 3);
        adapter = new ProfileConnectionsFollowingRecyclerViewAdapter(profileConnectionFollowingModels, getContext(), true);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onGlobalLayout() {
                        if (adapter.getItemCount() == 0) {
                            final int numColumns = 3;
                            final int columnWidth =
                                    (recyclerView.getMeasuredWidth() / numColumns);
                            adapter.setItemHeight(columnWidth);
                            if (BuildConfig.DEBUG) {
                                Log.d(TAG, "onCreateView - numColumns set to " + numColumns);
                            }
                            if (Utils.hasJellyBean()) {
                                recyclerView.getViewTreeObserver()
                                        .removeOnGlobalLayoutListener(this);
                            } else {
                                recyclerView.getViewTreeObserver()
                                        .removeGlobalOnLayoutListener(this);
                            }

                        }
                    }
                });

        recyclerView.setAdapter(adapter);

        adapter.setListener(this);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        setUserVisibleHint(false);

    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onAttach(Context context) {

        super.onAttach(context);

    }

    @SuppressLint("ClickableViewAccessibility")
    private void initSectionPicker() {
        Object[] sectionsAsObject = adapter.getSections();
        String[] sections = Arrays.copyOf(sectionsAsObject, sectionsAsObject.length, String[].class);

//        sectionPicker.setTextViewIndicator(textViewSection);
        sectionPicker.setSections(sections);
        sectionPicker.setOnTouchingLetterChangedListener(new SectionPicker.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                int position = adapter.getPositionForSection(s.charAt(0));
                sectionPicker.setAlpha(1);
//                sectionPicker.setVisibility(View.VISIBLE);
                if (position != -1) {
                    if (MiluApplication.isCollection){
                        gridLayoutManager.scrollToPositionWithOffset(position, 0);
                    }else{
                        linearLayoutManager.scrollToPositionWithOffset(position, 0);
                    }
                }
            }
        });
        sectionPicker.setVisibility(View.GONE);
    }

    @Override
    public void onRowClick(View view, int position) {
        ProfileConnectionFollowingModel profileConnectionFollowingModel = profileConnectionFollowingModels.get(position);
        String string = profileConnectionFollowingModel.getUserFullName();
        if (string != null) {
            Toast.makeText(getContext(), profileConnectionFollowingModel.getUserName(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClickRightButton(boolean b) {
        if (b) {
            populateRecyclerCollectionView();
        }else{
            populateRecyclerListView();
        }
    }

    @Override
    public void onClickMenuButton(int index) {

    }
}
