package charles.com.milu.LiveEvents.LiveFeedPostFragment;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ogaclejapan.arclayout.ArcLayout;
import com.qinwenbo.circlemenulib.CircleMenu;
import com.qinwenbo.circlemenulib.MenuIcon;

import java.util.ArrayList;
import java.util.List;

import charles.com.milu.Base.BaseFragment;
import charles.com.milu.LiveEvents.LiveMediaPost.LiveMediaPostMainActivity;
import charles.com.milu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveFeedDispFragment extends BaseFragment implements LiveFeedDispAdapter.ItemClickListener, View.OnClickListener {


    ArrayList<LiveMediaPostItem> feedItems;
    public TextView titleBar_title;
    CircleMenu circleMenu;
    ImageView addButton;
    List<MenuIcon> menuIcons = new ArrayList<>();
    boolean hiddenFlag = false;
    View menuLayout;
    ArcLayout arcLayout;
    View fab;
    Toast toast = null;

    public LiveFeedDispFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_live_feed_post, container, false);

        fab = view.findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    hideMenu();
                    hiddenFlag = false;
                fab.setVisibility(View.INVISIBLE);
                addButton.setVisibility(View.VISIBLE);
            }
        });

        menuLayout = view.findViewById(R.id.menu_layout);
        arcLayout = (ArcLayout) view.findViewById(R.id.arc_layout);
//        for (int i = 0, size = arcLayout.getChildCount(); i < size; i++) {
//            arcLayout.getChildAt(i).setOnClickListener(this);
//        }


        setAddButtonclick(view);

        RecyclerView rvContacts = (RecyclerView) view.findViewById(R.id.liveMediaPost_RecyclerView);

        feedItems = LiveMediaPostItem.createContactsList(0);
        feedItems.add(0, new LiveMediaPostItem(0,"andrea bocceli",R.drawable.user01,"1.6K",R.drawable.image1,R.string.globalfeed_description1, R.string.globalfeed_property1));
        feedItems.add(1, new LiveMediaPostItem(0,"maxim",R.drawable.user02,"1.5K",R.drawable.image2,R.string.globalfeed_description2, R.string.globalfeed_property2));
        feedItems.add(2, new LiveMediaPostItem(0,"vasily",R.drawable.user03,"1.8K",R.drawable.image3,R.string.globalfeed_description3, R.string.globalfeed_property3));
        feedItems.add(3, new LiveMediaPostItem(0,"krystsina",R.drawable.user04,"1.4K",R.drawable.image4,R.string.globalfeed_description4, R.string.globalfeed_property4));
        feedItems.add(4, new LiveMediaPostItem(0,"mihail",R.drawable.user05,"3.8K",R.drawable.image5,R.string.globalfeed_description5, R.string.globalfeed_property5));
        feedItems.add(5, new LiveMediaPostItem(0,"ognijen",R.drawable.user06,"7.8K",R.drawable.image6,R.string.globalfeed_description6, R.string.globalfeed_property6));

        LiveFeedDispAdapter adapter = new LiveFeedDispAdapter(view.getContext(), feedItems);
        rvContacts.setAdapter(adapter);
        adapter.setListener(this);

        rvContacts.setLayoutManager(new LinearLayoutManager(view.getContext()));


        ImageView rate = (ImageView) view.findViewById(R.id.rateimg);
            rate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    movetoMediaPost();
                }
            });

        ImageView live = (ImageView) view.findViewById(R.id.liveimg);
        live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                movetoMediaPost();

            }
        });

        ImageView photo = (ImageView) view.findViewById(R.id.photoimg);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                movetoMediaPost();

            }
        });

        ImageView video = (ImageView) view.findViewById(R.id.videoimg);
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                movetoMediaPost();

            }
        });

        ImageView quote = (ImageView) view.findViewById(R.id.quoteimg);
        quote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                movetoMediaPost();

            }
        });

        ImageView audio = (ImageView) view.findViewById(R.id.audioimg);
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                movetoMediaPost();

            }
        });

        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);


    }
    public void setAddButtonclick(View view){

        addButton = (ImageView) view.findViewById(R.id.liveMediaPost_addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hiddenFlag = true;
                addButton.setVisibility(View.INVISIBLE);
                fab.setVisibility(View.VISIBLE);
                showMenu();
            }
        });
    }

    public void showMenu(){

        menuLayout.setVisibility(View.VISIBLE);

        List<Animator> animList = new ArrayList<>();

        for (int i = 0, len = arcLayout.getChildCount(); i < len; i++) {
            animList.add(createShowItemAnimator(arcLayout.getChildAt(i)));
        }

        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(400);
        animSet.setInterpolator(new OvershootInterpolator());
        animSet.playTogether(animList);
        animSet.start();
    }

    private Animator createShowItemAnimator(View item) {

        float dx = fab.getX() - item.getX();
        float dy = fab.getY() - item.getY();

        item.setRotation(0f);
        item.setTranslationX(dx);
        item.setTranslationY(dy);

        Animator anim = ObjectAnimator.ofPropertyValuesHolder(
                item,
                AnimatorUtils.rotation(0f, 720f),
                AnimatorUtils.translationX(dx, 0f),
                AnimatorUtils.translationY(dy, 0f)
        );

        return anim;
    }

//        circleMenu.setMenuIcons(menuIcons);
//        circleMenu.setOnMenuSwitchListener(new CircleMenu.OnMenuSwitchListener() {
//            @Override
//            public void onMenuSwitch(int menuStatus, int currentMenuIndex) {
//
//                if (menuStatus == 2 && currentMenuIndex == 0){
//
//                    circleMenuHidden(view);
//
//                }
//                if (menuStatus == 2 && currentMenuIndex == 1){
//
//                    circleMenuHidden(view);
//                    movetoMediaPost();
//
//                }else if (menuStatus == 2 && currentMenuIndex == 2){
//
//                    circleMenuHidden(view);
//                    movetoMediaPost();
//
//                }else if (menuStatus == 2 && currentMenuIndex == 3){
//
//                    circleMenuHidden(view);
//                    movetoMediaPost();
//
//                }else if (menuStatus == 2 && currentMenuIndex == 4){
//
//                    circleMenuHidden(view);
//                    movetoMediaPost();
//
//                }else if (menuStatus == 2 && currentMenuIndex == 5){
//
//                    circleMenuHidden(view);
//                    movetoMediaPost();
//
//                }else if (menuStatus == 2 && currentMenuIndex == 6){
//
//                    circleMenuHidden(view);
//                    movetoMediaPost();
//
//
//                }
//            }
//        });
//    }

    public void movetoMediaPost(){

        Intent i = new Intent(getActivity(), LiveMediaPostMainActivity.class);
        startActivity(i);

    }
    @Override
    public void likeButtonClicked(View view, int position) {
        Log.e("Like Button Clicked", String.valueOf(position));

        Toast.makeText(view.getContext(),"like Button Clicked!! cell position = " + String.valueOf(position), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void shareButtuonClicked(View view, int position) {
        Log.e("Share Button Clicked", String.valueOf(position));

        Toast.makeText(view.getContext(),"share Button Clicked!! cell position = " + String.valueOf(position), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void moreButtonClicked(View view, int position) {
        Log.e("More Button Clicked", String.valueOf(position));
        Toast.makeText(view.getContext(),"more Button Clicked!!   cell position = " + String.valueOf(position), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void chatButtonClicked(View view, int positon) {
        Log.e("chat Button Clicked", String.valueOf(positon));
        Toast.makeText(view.getContext(),"chat Button Clicked!!   cell position = " + String.valueOf(positon), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void upvoteButton1Clicked(View view, int positon) {

        Toast.makeText(view.getContext(),"upvote Button1 Clicked!!   cell position = " + String.valueOf(positon), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void upvoteButton2Clicked(View view, int positon) {

        Toast.makeText(view.getContext(),"upvote Button2 Clicked!!   cell position = " + String.valueOf(positon), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void liveMediaPostCell_clicked(View v, int adapterPosition) {

        Toast.makeText(v.getContext(),"Live Media Post Cell Clicked" + String.valueOf(adapterPosition), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.fab) {
            onFabClick(v);
            return;
        }

//        if (v instanceof Button) {
//            showToast((Button) v);
//        }
    }
    private void showToast(Button btn) {
        if (toast != null) {
            toast.cancel();
        }

        String text = "Clicked: " + btn.getText();
        toast = Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT);
        toast.show();

    }

    private void onFabClick(View v) {
        if (v.isSelected()) {
            hideMenu();
        } else {
            showMenu();
        }
        v.setSelected(!v.isSelected());
    }

    private void hideMenu() {

        List<Animator> animList = new ArrayList<>();

        for (int i = arcLayout.getChildCount() - 1; i >= 0; i--) {
            animList.add(createHideItemAnimator(arcLayout.getChildAt(i)));
        }

        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(400);
        animSet.setInterpolator(new AnticipateInterpolator());
        animSet.playTogether(animList);
        animSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                menuLayout.setVisibility(View.INVISIBLE);
            }
        });
        animSet.start();
    }

    private Animator createHideItemAnimator(final View item) {
        float dx = fab.getX() - item.getX();
        float dy = fab.getY() - item.getY();

        Animator anim = ObjectAnimator.ofPropertyValuesHolder(
                item,
                AnimatorUtils.rotation(720f, 0f),
                AnimatorUtils.translationX(0f, dx),
                AnimatorUtils.translationY(0f, dy)
        );

        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                item.setTranslationX(0f);
                item.setTranslationY(0f);
            }
        });

        return anim;
    }
}
