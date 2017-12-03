package charles.com.milu.HomeScreen;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

import butterknife.BindView;
import charles.com.milu.Base.BaseFragment;
import charles.com.milu.ChatTab.ChatConversationsFragment;
import charles.com.milu.ChatTab.ChatDiscoverFragment;
import charles.com.milu.ChatTab.ChatNewMessageFragment;
import charles.com.milu.CustomViews.ChatTabTitleTextView;
import charles.com.milu.MiluApplication;
import charles.com.milu.PreventScrollonPageView.NonSwipeableViewPager;
import charles.com.milu.ProfileTab.ProfileConnectionsFollowersFragment;
import charles.com.milu.ProfileTab.ProfileConnectionsFollowingFragment;
import charles.com.milu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabBar_chatting extends BaseFragment {

    ChatDashboardTabPageAdapter adapter;
    @BindView(R.id.chatdashPager)
    NonSwipeableViewPager chatdashboardPager;

    @BindView(R.id.tab_btn_chat)
    ChatTabTitleTextView tabBarChat;

    @BindView(R.id.tab_btn_mingle)
    ChatTabTitleTextView tabBarMingle;

    @BindView(R.id.tab_btn_discover)
    ChatTabTitleTextView tabBarDiscover;

    @BindView(R.id.plus_button)
    ImageButton newMessageButton;


    public static TabBar_chatting getInstance() {
        // Required empty public constructor
        return new TabBar_chatting();
    }
    @Override
    protected int addView() {
        return R.layout.fragment_tab_bar_chatting;
    }

    @Override
    public void initView() {
        super.initView();

        setToolBar();

        tabBarChat.setOnClickListener(this);
        tabBarMingle.setOnClickListener(this);
        tabBarDiscover.setOnClickListener(this);
        newMessageButton.setOnClickListener(this);

        tabBarChat.setSelect(true);

        setchatPageAdapter();

//        onMenuButtonListner = (onClickMenuButtonListner) mAct;
//        onRightButtonListner = (onClickRightButtonListner) mAct;

    }

    @Override
    public void setToolBar(){

        assert txtLeft != null;
        txtLeft.setText("milu");

        assert btnMenu != null;
        btnMenu.setImageResource(R.drawable.logo);
        btnMenu.setVisibility(View.VISIBLE);
        btnMenu.setOnClickListener(this);
        assert  rightButton2 != null;
        rightButton2.setImageResource(R.drawable.nav_bar_setting_icon);
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
                break;

            case R.id.tab_btn_chat:
                setTabBar(0);
                break;

            case R.id.tab_btn_mingle:
                setTabBar(1);
                break;

            case R.id.tab_btn_discover:
                setTabBar(2);
                break;

            case R.id.chat_button:
                break;
            case R.id.group_button:
                break;
            case R.id.remove_button:
                break;
            case R.id.plus_button:
                addChildFragment(ChatNewMessageFragment.getInstance(), true);
                break;
        }
    }


    public void setTabBar(int tabBarIndex) {
        tabBarChat.setSelect(false);
        tabBarMingle.setSelect(false);
        tabBarDiscover.setSelect(false);
        switch (tabBarIndex) {
            case 0:
                tabBarChat.setSelect(true);
                showFragment(tabBarIndex);
                break;
            case 1:
                tabBarMingle.setSelect(true);
                showFragment(tabBarIndex);
                break;
            case 2:
                tabBarDiscover.setSelect(true);
                showFragment(tabBarIndex);
                break;
        }
    }

    public void showFragment(int index) {
        chatdashboardPager.setCurrentItem(index, true);
    }

    public void setchatPageAdapter(){
        adapter = new ChatDashboardTabPageAdapter(getChildFragmentManager());
        chatdashboardPager.setAdapter(adapter);
        chatdashboardPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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



    public class ChatDashboardTabPageAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> fragments = new ArrayList<>();

        public ChatDashboardTabPageAdapter(FragmentManager fm){
            super(fm);

            fragments.add(new ChatConversationsFragment());
            fragments.add(new ChatConversationsFragment());
            fragments.add(new ChatDiscoverFragment());
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
        chatdashboardPager.clearOnPageChangeListeners();
    }

}
