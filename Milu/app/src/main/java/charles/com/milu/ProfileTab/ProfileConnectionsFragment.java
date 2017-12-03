package charles.com.milu.ProfileTab;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nightonke.wowoviewpager.Animation.ViewAnimation;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import charles.com.milu.Base.BaseFragment;
import charles.com.milu.CustomViews.TabTitleTextView;
import charles.com.milu.EventBusListeners.ReplaceFragmentListener;
import charles.com.milu.HomeScreen.TabBar_userProfile;
import charles.com.milu.HomeScreen.Tabbar_eventdash;
import charles.com.milu.LiveEvents.LiveFeedChatFragment.LiveFeedChatFragment;
import charles.com.milu.LiveEvents.LiveFeedMain.LiveFeedPageAdapter;
import charles.com.milu.LiveEvents.LiveFeedMingleFragment.LiveFeedMingleFragment;
import charles.com.milu.LiveEvents.LiveFeedPostFragment.LiveFeedDispFragment;
import charles.com.milu.MeetUps.GridSpacingItemDecoration;
import charles.com.milu.MeetUps.MeetUpAdapter;
import charles.com.milu.MeetUps.MeetUpItem;
import charles.com.milu.MiluApplication;
import charles.com.milu.PreventScrollonPageView.NonSwipeableViewPager;
import charles.com.milu.R;
import charles.com.milu.utils.expandableselector.ExpandableItem;
import charles.com.milu.utils.expandableselector.ExpandableSelector;
import charles.com.milu.utils.expandableselector.OnExpandableItemClickListener;

public class ProfileConnectionsFragment extends BaseFragment  {

    ProfileConnectionTabPageAdapter adapter;

    @BindView(R.id.tab_btn_following)
    TabTitleTextView tabBarFollowing;

    @BindView(R.id.tab_btn_followers)
    TabTitleTextView tabBarFollowers;

    @BindView(R.id.tab_btn_groups)
    TabTitleTextView tabBarGroups;

    @BindView(R.id.profileConnectionPager)
    NonSwipeableViewPager profileConnectionPager;



//    @BindView(R.id.menu_selector)
//    ExpandableSelector menuSelector;

    @BindView(R.id.close_button)
    ImageButton closeButton;

    @BindView(R.id.menu_button)
    ImageButton menuButton;

    @BindView(R.id.chat_button)
    ImageButton chatButton;

    @BindView(R.id.remove_button)
    ImageButton unfollowButton;

    @BindView(R.id.group_button)
    ImageButton groupButton;



    private onClickMenuButtonListner onMenuButtonListner;
    private onClickRightButtonListner onRightButtonListner;
    public interface onClickRightButtonListner {
        public void onClickRightButton(boolean b);
    }

    public interface onClickMenuButtonListner {
        public void onClickMenuButton(int index);
    }

    public void setMenueListner(onClickMenuButtonListner listner) {
        onMenuButtonListner = listner;
    }

    public void setOnRightButtonListner(onClickRightButtonListner listner) {
        onRightButtonListner = listner;
    }
    public static ProfileConnectionsFragment getInstance() {
        ProfileConnectionsFragment fragment = new ProfileConnectionsFragment();
        return fragment;
    }

    @Override
    protected int addView() {
        return R.layout.fragment_profile_connections;
    }

    @Override
    public void initView() {
        super.initView();

        setToolBar();

        tabBarFollowing.setOnClickListener(this);
        tabBarFollowers.setOnClickListener(this);
        tabBarGroups.setOnClickListener(this);

        tabBarFollowing.setSelect(true);

        setConnectionPageAdapter();

        initializeMenuExpandableSelector();
        initializeCloseAllButton();

    }

    @Override
    public void setToolBar(){

        assert txtLeft != null;
        txtLeft.setText("connections");

        assert btnMenu != null;
        btnMenu.setImageResource(R.drawable.nav_bar_back_icon);
        btnMenu.setVisibility(View.VISIBLE);
        btnMenu.setOnClickListener(this);

        assert  rightButton2 != null;
        rightButton2.setImageResource(R.drawable.nav_bar_collection_icon1);
        rightButton2.setVisibility(View.VISIBLE);
        rightButton2.setSelected(false);
        rightButton2.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.toolbar_btn_left:
                mAct.onBackPressed();
                break;

            case R.id.toolbar_btn_right2:
                onClickRightButton();
                break;

            case R.id.tab_btn_following:
                setTabBar(0);
                break;

            case R.id.tab_btn_followers:
                setTabBar(1);
                break;

            case R.id.tab_btn_groups:
                setTabBar(2);
                break;

            case R.id.close_button:
                closeExpandButton();
                break;
            case R.id.menu_button:
                expandButtons();
                break;
            case R.id.chat_button:
                break;
            case R.id.group_button:
                break;
            case R.id.remove_button:
                break;
        }
    }

    public void onClickRightButton() {
        assert rightButton2 != null;
        rightButton2.setSelected(!rightButton2.isSelected());
        if (rightButton2.isSelected()) {
            MiluApplication.isCollection = true;
            rightButton2.setImageResource(R.drawable.nav_bar_list_icon1);
//            onRightButtonListner.onClickRightButton(true);
        }else{
            MiluApplication.isCollection = false;
            rightButton2.setImageResource(R.drawable.nav_bar_collection_icon1);
//            onRightButtonListner.onClickRightButton(false);
        }
        Fragment fragment = adapter.getItem(profileConnectionPager.getCurrentItem());
        if (fragment instanceof ProfileConnectionsFollowersFragment) {
            ((ProfileConnectionsFollowersFragment)fragment).onClickRightButton(MiluApplication.isCollection);
        }else if (fragment instanceof ProfileConnectionsFollowingFragment) {
            ((ProfileConnectionsFollowingFragment) fragment).onClickRightButton(MiluApplication.isCollection);
        }

    }

    public void setTabBar(int tabBarIndex) {
        tabBarFollowing.setSelect(false);
        tabBarFollowers.setSelect(false);
        tabBarGroups.setSelect(false);
        switch (tabBarIndex) {
            case 0:
                tabBarFollowing.setSelect(true);
                showFragment(tabBarIndex);
                break;
            case 1:
                tabBarFollowers.setSelect(true);
                showFragment(tabBarIndex);
                break;
            case 2:
                tabBarGroups.setSelect(true);
                showFragment(tabBarIndex);
                break;
        }
    }

    public void showFragment(int index) {
        profileConnectionPager.setCurrentItem(index, true);
    }

    public void setConnectionPageAdapter(){
        adapter = new ProfileConnectionTabPageAdapter(getChildFragmentManager());
        profileConnectionPager.setAdapter(adapter);
        profileConnectionPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                Fragment fragment = adapter.getItem(i);
                if (fragment instanceof ProfileConnectionsFollowersFragment) {
                    ((ProfileConnectionsFollowersFragment)fragment).onClickRightButton(MiluApplication.isCollection);
                }else if (fragment instanceof ProfileConnectionsFollowingFragment) {
                    ((ProfileConnectionsFollowingFragment) fragment).onClickRightButton(MiluApplication.isCollection);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


    private void initializeMenuExpandableSelector() {

        menuButton.setOnClickListener(this);
        closeButton.setOnClickListener(this);
        groupButton.setOnClickListener(this);
        chatButton.setOnClickListener(this);
        unfollowButton.setOnClickListener(this);
    }

    public void closeExpandButton() {
        WindowManager wm = (WindowManager) mAct.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int width = size.x;
        getView().post(new Runnable() {
            @Override
            public void run() {
                int itemSize = getResources().getDimensionPixelSize(R.dimen.item_size);
                int margin = (width - itemSize * 4) / 8;

                menuButton.setVisibility(View.VISIBLE);

                unfollowButton.animate().translationX((width - 16)).setDuration(1000).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                    }
                    @Override
                    public void onAnimationEnd(Animator animation){
                        super.onAnimationEnd(animation);
                        unfollowButton.setVisibility(View.GONE);
                    }
                });
                chatButton.animate().translationX((width - 16) * 2 / 3).setDuration(1000).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                    }
                    @Override
                    public void onAnimationEnd(Animator animation){
                        super.onAnimationEnd(animation);
                        chatButton.setVisibility(View.GONE);
                    }
                });
                groupButton.animate().translationX((width - 16) / 3 ).setDuration(1000).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                    }
                    @Override
                    public void onAnimationEnd(Animator animation){
                        super.onAnimationEnd(animation);
                        groupButton.setVisibility(View.GONE);
                    }
                });
            }
        });

    }


    public void expandButtons() {

        //
        WindowManager wm = (WindowManager) mAct.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int width = size.x;
        int height = size.y;

        int itemSize = getResources().getDimensionPixelSize(R.dimen.item_size);
        int margin = (width - itemSize * 4) / 8;
        getView().post(new Runnable() {
            @Override
            public void run() {
                menuButton.setVisibility(View.GONE);
                closeButton.setVisibility(View.VISIBLE);
                chatButton.setVisibility(View.VISIBLE);
                groupButton.setVisibility(View.VISIBLE);
                unfollowButton.setVisibility(View.VISIBLE);
                closeButton.setAlpha(1);
                chatButton.setAlpha(1);
                groupButton.setAlpha(1);
                unfollowButton.setAlpha(1);
                unfollowButton.animate().translationX(-(width - unfollowButton.getWidth() - 16)).setDuration(1000).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                    }
                });
                chatButton.animate().translationX(-(width - chatButton.getWidth() - 16) * 2 / 3).setDuration(1000).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                    }
                });
                groupButton.animate().translationX(-(width - groupButton.getWidth() - 16) / 3 ).setDuration(1000).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                    }
                });
            }
        });
    }

    private void initializeCloseAllButton() {
//        final View closeButton = findViewById(R.id.bt_close);
//        closeButton.setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View v) {
//                colorsExpandableSelector.collapse();
//                sizesExpandableSelector.collapse();
//                iconsExpandableSelector.collapse();
//                updateIconsFirstButtonResource(R.mipmap.ic_keyboard_arrow_up_black);
//            }
//        });
//        colorsExpandableSelector.setExpandableSelectorListener(new ExpandableSelectorListener() {
//            @Override public void onCollapse() {
//
//            }
//
//            @Override public void onExpand() {
//
//            }
//
//            @Override public void onCollapsed() {
//                colorsHeaderButton.setVisibility(View.VISIBLE);
//            }
//
//            @Override public void onExpanded() {
//
//            }
//        });
    }



//    private void updateIconsFirstButtonResource(int resourceId) {
//        ExpandableItem arrowUpExpandableItem = new ExpandableItem();
//        arrowUpExpandableItem.setResourceId(resourceId);
//        menuSelector.updateExpandableItem(0, arrowUpExpandableItem);
//    }
//
//    private void showToast(String message) {
//        Toast.makeText(mAct, message, Toast.LENGTH_SHORT).show();
//    }


    public class ProfileConnectionTabPageAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> fragments = new ArrayList<>();

        public ProfileConnectionTabPageAdapter(FragmentManager fm){
            super(fm);

            fragments.add(new ProfileConnectionsFollowingFragment());
            fragments.add(new ProfileConnectionsFollowersFragment());
            fragments.add(new ProfileConnectionsGroupsFragment());
        }

        @Override
        public Fragment getItem(int position) {

            return fragments.get(position);

        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }


    @Override
    public void onDestroy(){
        super.onDestroy();
        profileConnectionPager.clearOnPageChangeListeners();
    }

}
