package charles.com.milu.LiveEvents.LiveFeedMingleFragment;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipeDirectionalView;
import com.mindorks.placeholderview.Utils;
import com.mindorks.placeholderview.listeners.ItemRemovedListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import charles.com.milu.MeetUps.GridSpacingItemDecoration;
import charles.com.milu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveFeedMingleFragment extends Fragment implements TinderDirectionalCard.MyCustomObjectListener, LiveFeedConnectionAdapter.ItemClickListener, LiveFeedInstagramAdapter.ItemClickListener{

    private MapView mapView;
    private GoogleMap googleMap;

    private static final String TAG = "ActivityTinder";

    public SwipeDirectionalView mSwipeView;
    public ScrollView userDetailPage;
    public RelativeLayout userDetialPageButtonLayout;
    LinearLayout sliderDotssapanel;
    private int dotscount;
    private ImageView[] dots;
    public RollPagerView mViewPager;
    Typeface workSans_Light;

    TextView userName, userJob, userWay, userResume, interestsTitle;
    TextView connectoinTitle, campingTxt, starTxt, footballTxt;
    TextView instagramTitle, shareTxt, instagramConnectTxt;
    TextView recommendTxt, friendTxt;

    ArrayList<LiveFeedConnectionItem> mItems;
    ArrayList<LiveFeedInstagramItem> insItems;



    public LiveFeedMingleFragment() {
        // Required empty public constructor
    }

    public void setFonts(View view){


        userName = (TextView) view.findViewById(R.id.liveMingle_userName);
        userJob = (TextView) view.findViewById(R.id.liveMingle_userJob);
        userWay = (TextView) view.findViewById(R.id.liveMingle_userWay);
        userResume = (TextView) view.findViewById(R.id.liveMingle_UserresumeTxt);
        interestsTitle = (TextView) view.findViewById(R.id.liveMingle_InterestsTitle);
        connectoinTitle = (TextView) view.findViewById(R.id.liveMingle_ConnectionTitle);
        campingTxt = (TextView) view.findViewById(R.id.liveMingle_CampingTxt);
        starTxt = (TextView) view.findViewById(R.id.liveMingle_StarTxt);
        footballTxt = (TextView) view.findViewById(R.id.liveMingle_FootTxt);
        instagramTitle = (TextView) view.findViewById(R.id.liveMingle_InstagramTitle);
        shareTxt = (TextView) view.findViewById(R.id.liveMingle_Sharetxt);
        instagramConnectTxt = (TextView) view.findViewById(R.id.liveMingle_InstagramConTxt);
        recommendTxt = (TextView) view.findViewById(R.id.liveMingle_RecommendTxt);
        friendTxt = (TextView) view.findViewById(R.id.liveMingle_FriendTxt);

    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSwipeView = (SwipeDirectionalView) view.findViewById(R.id.swipeView);
        mSwipeView.addItemRemoveListener(new ItemRemovedListener() {
            @Override
            public void onItemRemoved(int count) {
                if (count == 0){
                    for (int i = 0; i < 4; i++){
                        TinderDirectionalCard tinder = new TinderDirectionalCard();
                        tinder.setCustomObjectListener(new TinderDirectionalCard.MyCustomObjectListener() {
                            @Override
                            public void OnClickCardView(Integer data) {
                                userDetailPage.setVisibility(view.VISIBLE);
                                mSwipeView.setVisibility(view.INVISIBLE);
                            }
                        });
                        mSwipeView.addView(tinder);
                    }
                }
            }
        });
        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setIsUndoEnabled(true)
                .setSwipeVerticalThreshold(Utils.dpToPx(50))
                .setSwipeHorizontalThreshold(Utils.dpToPx(50))
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.tinder_swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.tinder_swipe_out_msg_view));
        for (int i = 0; i < 4; i++){
            TinderDirectionalCard tinder = new TinderDirectionalCard();
            tinder.setCustomObjectListener(new TinderDirectionalCard.MyCustomObjectListener() {
                @Override
                public void OnClickCardView(Integer data) {
                    userDetailPage.setVisibility(View.VISIBLE);
                    mSwipeView.setVisibility(View.INVISIBLE);
                    userDetialPageButtonLayout.setVisibility(View.VISIBLE);
                }
            });
            mSwipeView.addView(tinder);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_live_feed_mingle, container, false);

        setMingleButtons(view);

        setMingleUserDetailPage(view);

        setMingleUserDetailImages(view);

        setFonts(view);

        setDetailPageButtons(view);

        setAdapter(view);

        setInstagramAdapter(view);

        return view;
    }
    public void setInstagramAdapter(View view){

        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.liveMingle_InstagramRecyclerView);

        insItems = LiveFeedInstagramItem.createContactsList(0);
        insItems.add(0, new LiveFeedInstagramItem(R.drawable.movements));
        insItems.add(1, new LiveFeedInstagramItem(R.drawable.outdoor_adventure));
        insItems.add(2, new LiveFeedInstagramItem(R.drawable.tech));
        insItems.add(3, new LiveFeedInstagramItem(R.drawable.family));
        insItems.add(4, new LiveFeedInstagramItem(R.drawable.health_wellness));
        insItems.add(5, new LiveFeedInstagramItem(R.drawable.sports_fitness));

        LiveFeedInstagramAdapter adapter = new LiveFeedInstagramAdapter(getContext(), insItems);
        mRecyclerView.setAdapter(adapter);
        adapter.setListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 2, true));

    }

    public void setAdapter(View view){

        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.liveMingle_ConnectionRecyclerView);

        mItems = LiveFeedConnectionItem.createContactsList(0);
        mItems.add(0, new LiveFeedConnectionItem(R.drawable.movements,"movements","1st"));
        mItems.add(1, new LiveFeedConnectionItem(R.drawable.outdoor_adventure,"outdoor & adventure","1st"));
        mItems.add(2, new LiveFeedConnectionItem(R.drawable.tech,"tech","1st"));
        mItems.add(3, new LiveFeedConnectionItem(R.drawable.family,"family","1st"));
        mItems.add(4, new LiveFeedConnectionItem(R.drawable.health_wellness,"health & wellness","1st"));
        mItems.add(5, new LiveFeedConnectionItem(R.drawable.sports_fitness,"sports & fitness","1st"));
        mItems.add(6, new LiveFeedConnectionItem(R.drawable.learning,"learning","1st"));


        LiveFeedConnectionAdapter adapter = new LiveFeedConnectionAdapter(getContext(), mItems);
        adapter.setListener(this);
        mRecyclerView.setAdapter(adapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);


        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 5, true));



    }

    public void setDetailPageButtons(View view){

        ImageView detailRejectButton = (ImageView) view.findViewById(R.id.liveMingle_DetailPageRejectButton);
            detailRejectButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    userDetailPage.setVisibility(View.INVISIBLE);
                    mSwipeView.setVisibility(View.VISIBLE);
                    userDetialPageButtonLayout.setVisibility(View.INVISIBLE);

                }
            });

        ImageView detailSuperLikeButton = (ImageView) view.findViewById(R.id.liveMingle_DetailPageSuperLikeButton);
            detailSuperLikeButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    userDetailPage.setVisibility(View.INVISIBLE);
                    mSwipeView.setVisibility(View.VISIBLE);
                    userDetialPageButtonLayout.setVisibility(View.INVISIBLE);
                }
            });

        ImageView detailLikeButton = (ImageView) view.findViewById(R.id.liveMingle_DetailPageLikeButton);
            detailLikeButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    userDetailPage.setVisibility(View.INVISIBLE);
                    mSwipeView.setVisibility(View.VISIBLE);
                    userDetialPageButtonLayout.setVisibility(View.INVISIBLE);

                }
            });
    }
    public void setMingleUserDetailImages(View view){

        mViewPager = (RollPagerView) view.findViewById(R.id.liveMingle_userDetailimagesViewPager);
        mViewPager.setAdapter(new ImageNormalAdapter());
//        mViewPager.setHintView(new IconHintView(this,R.drawable.point_focus,R.drawable.point_normal));
        mViewPager.setHintView(new ColorPointHintView(getContext(), getResources().getColor(R.color.color_Milumain), getResources().getColor(R.color.color_Silver)));
//        mViewPager.setHintView(new TextHintView(getContext()));
//        mViewPager.setHintView(null);//hide the indicator
    }

    @Override
    public void connectionItemClicked(View v, int adapterPosition) {

        Toast.makeText(v.getContext()," Connection item clicked!!! cell position = " + String.valueOf(adapterPosition), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void instagframItemClicked(View v, int adapterPosition) {

        Toast.makeText(v.getContext()," Instagram item clicked!!! cell position = " + String.valueOf(adapterPosition), Toast.LENGTH_SHORT).show();
        getActivity().startActivity(new Intent(getActivity(), LiveMingleInstagramActivity.class));
    }


    private class ImageNormalAdapter extends StaticPagerAdapter {
        int[] imgs = new int[]{
                R.drawable.user01,
                R.drawable.user02,
                R.drawable.user03,
                R.drawable.user04,
                R.drawable.user05,
        };

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//            view.setImageResource(imgs[position]);
            Picasso.with(getContext()).load(imgs[position]).into(view);
            return view;
        }

        @Override
        public int getCount() {
            return imgs.length;
        }
    }

    public void setMingleUserDetailPage(View view){

        userDetialPageButtonLayout = (RelativeLayout) view.findViewById(R.id.liveMingle_DetailButtonLayout);
            userDetialPageButtonLayout.setVisibility(view.INVISIBLE);

        userDetailPage = (ScrollView) view.findViewById(R.id.liveMingle_userDetailPage);
            userDetailPage.setVisibility(view.INVISIBLE);

        ImageView backButton = (ImageView) view.findViewById(R.id.liveMingle_userDetailPagebackButton);
        backButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                userDetailPage.setVisibility(View.INVISIBLE);
                mSwipeView.setVisibility(View.VISIBLE);
                userDetialPageButtonLayout.setVisibility(View.INVISIBLE);

            }
        });
    }

    public void setMingleButtons(View view){

        ImageButton refreshButton = (ImageButton) view.findViewById(R.id.liveMingle_refreshButton);
                refreshButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(v.getContext()," LiveMingle RefreshButton clicked!!!", Toast.LENGTH_SHORT).show();

                    }
                });

        ImageButton rejectButton = (ImageButton) view.findViewById(R.id.liveMingle_rejectButton);
                rejectButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mSwipeView.doSwipe(false);

                    }
                });

        ImageButton boostButton = (ImageButton) view.findViewById(R.id.liveMingle_boostButton);
            boostButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(v.getContext()," LiveMingle BoostButton clicked!!!", Toast.LENGTH_SHORT).show();

                }
            });

        ImageButton likeButton = (ImageButton) view.findViewById(R.id.liveMingle_likeButton);
            likeButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    mSwipeView.doSwipe(true);

                }
            });

        ImageButton superlikeButton = (ImageButton) view.findViewById(R.id.liveMingle_superlikeButton);
            superlikeButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(v.getContext()," LiveMingle SuperLike Button clicked!!!", Toast.LENGTH_SHORT).show();

                }
            });
    }

    @Override
    public void OnClickCardView(Integer data) {

    }
}
