package charles.com.milu.MeetUps;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;

import butterknife.BindView;
import charles.com.milu.Base.BaseFragment;
import charles.com.milu.R;
import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;

/**
 * Created by mac_dev on 10/28/17.
 */

public class MeetupSetup2TagsFragment extends BaseFragment {

    @BindView(R.id.scrollView)
    NestedScrollView scrollView;

    @BindView(R.id.tags_tech_layout)
    TagContainerLayout mTagTechContainerLayout;

    @BindView(R.id.tags_health_layout)
    TagContainerLayout mTagHealthContainerLayout;

    ArrayList<String> techtags = new ArrayList<>();
    ArrayList<int[]> techcolors = new ArrayList<>();
    ArrayList<Boolean> selectedTech = new ArrayList<>();

    ArrayList<String> healthtags = new ArrayList<>();
    ArrayList<int[]> healthcolors = new ArrayList<>();
    ArrayList<Boolean> selectedHealth = new ArrayList<>();

    public static MeetupSetup2TagsFragment getInstance() {
        // Required empty public constructor
        MeetupSetup2TagsFragment fragment = new MeetupSetup2TagsFragment();
        return fragment;
    }
    protected int addView() {
        return R.layout.fragment_meetups_setup2_tags;
    }

    @Override
    public void initView() {
        super.initView();
        setToolBar();
        setTagView();
    }

    @Override
    public void setToolBar(){

        assert txtLeft != null;
        txtLeft.setText("interests");

        assert btnMenu != null;
        btnMenu.setImageResource(R.drawable.nav_bar_back_icon);
        btnMenu.setVisibility(View.VISIBLE);
        btnMenu.setOnClickListener(this);

        assert rightButton2 != null;
        rightButton2.setImageResource(R.drawable.nav_bar_check_mark_disable_icon);
        rightButton2.setVisibility(View.VISIBLE);
        rightButton2.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.toolbar_btn_left:
                mAct.onBackPressed();
                break;
            case R.id.toolbar_btn_right2:
                addChildFragment(MeetUpSetup3InterestsFragment.getInstance(), true);
                break;

        }
    }

    public void setTagView() {
        techtags.add("internet of things");
        techtags.add("graphic design");
        techtags.add("new technology");
        techtags.add("cryptocurrency");
        techtags.add("computer programming");
        techtags.add("unity");
        healthtags.add("preventive wellness");
        healthtags.add("nutrition");
        healthtags.add("alternative medicine");
        healthtags.add("well being");
        healthtags.add("holistic health");
        healthtags.add("weight loss");
        techcolors.add(new int[]{Color.parseColor("#00ffffff"), Color.parseColor("#ffffffff"), Color.parseColor("#ffffffff")});
        techcolors.add(new int[]{Color.parseColor("#00ffffff"), Color.parseColor("#ffffffff"), Color.parseColor("#ffffffff")});
        techcolors.add(new int[]{Color.parseColor("#00ffffff"), Color.parseColor("#ffffffff"), Color.parseColor("#ffffffff")});
        techcolors.add(new int[]{Color.parseColor("#00ffffff"), Color.parseColor("#ffffffff"), Color.parseColor("#ffffffff")});
        techcolors.add(new int[]{Color.parseColor("#00ffffff"), Color.parseColor("#ffffffff"), Color.parseColor("#ffffffff")});
        techcolors.add(new int[]{Color.parseColor("#00ffffff"), Color.parseColor("#ffffffff"), Color.parseColor("#ffffffff")});
        healthcolors.add(new int[]{Color.parseColor("#00ffffff"), Color.parseColor("#ffffffff"), Color.parseColor("#ffffffff")});
        healthcolors.add(new int[]{Color.parseColor("#00ffffff"), Color.parseColor("#ffffffff"), Color.parseColor("#ffffffff")});
        healthcolors.add(new int[]{Color.parseColor("#00ffffff"), Color.parseColor("#ffffffff"), Color.parseColor("#ffffffff")});
        healthcolors.add(new int[]{Color.parseColor("#00ffffff"), Color.parseColor("#ffffffff"), Color.parseColor("#ffffffff")});
        healthcolors.add(new int[]{Color.parseColor("#00ffffff"), Color.parseColor("#ffffffff"), Color.parseColor("#ffffffff")});
        healthcolors.add(new int[]{Color.parseColor("#00ffffff"), Color.parseColor("#ffffffff"), Color.parseColor("#ffffffff")});
        selectedTech.add(false);
        selectedTech.add(false);
        selectedTech.add(false);
        selectedTech.add(false);
        selectedTech.add(false);
        selectedTech.add(false);
        selectedHealth.add(false);
        selectedHealth.add(false);
        selectedHealth.add(false);
        selectedHealth.add(false);
        selectedHealth.add(false);
        selectedHealth.add(false);
        Typeface workSans_Light = Typeface.createFromAsset(getContext().getAssets(),"WorkSans-Light.ttf");
        mTagTechContainerLayout.setTagTypeface(workSans_Light);
        mTagTechContainerLayout.setDragEnable(false);
        mTagTechContainerLayout.setTags(techtags, techcolors);
        mTagTechContainerLayout.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {
                if (!selectedTech.get(position)){
                    mTagTechContainerLayout.getTagView(position).setTagBorderColor(ContextCompat.getColor(getContext(), R.color.color_Milumain));
                    mTagTechContainerLayout.getTagView(position).setTagTextColor(ContextCompat.getColor(getContext(), R.color.color_Milumain));
                    selectedTech.set(position, true);
                }else{
                    mTagTechContainerLayout.getTagView(position).setTagBorderColor(ContextCompat.getColor(getContext(), R.color.colorWhite));
                    mTagTechContainerLayout.getTagView(position).setTagTextColor(ContextCompat.getColor(getContext(), R.color.colorWhite));
                    selectedTech.set(position, false);
                    boolean b = false;
                    for (int i = 0; i < selectedTech.size(); i ++) {
                        if (selectedTech.get(i)) {
                            b = true;
                        }
                    }
                }
                checkSelected();
            }

            @Override
            public void onTagLongClick(int position, String text) {
                mTagTechContainerLayout.setDragEnable(true);
                TagView tagView = mTagTechContainerLayout.getTagView(position);
            }

            @Override
            public void onTagCrossClick(int position) {

            }
        });

        mTagTechContainerLayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                mTagTechContainerLayout.setDragEnable(false);
            }
        });

        mTagHealthContainerLayout.setTagTypeface(workSans_Light);
        mTagHealthContainerLayout.setDragEnable(false);
        mTagHealthContainerLayout.setTags(healthtags, healthcolors);
        mTagHealthContainerLayout.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {
                if (!selectedHealth.get(position)){
                    mTagHealthContainerLayout.getTagView(position).setTagBorderColor(ContextCompat.getColor(getContext(), R.color.color_Milumain));
                    mTagHealthContainerLayout.getTagView(position).setTagTextColor(ContextCompat.getColor(getContext(), R.color.color_Milumain));
                    selectedHealth.set(position, true);
                }else{
                    mTagHealthContainerLayout.getTagView(position).setTagBorderColor(ContextCompat.getColor(getContext(), R.color.colorWhite));
                    mTagHealthContainerLayout.getTagView(position).setTagTextColor(ContextCompat.getColor(getContext(), R.color.colorWhite));
                    selectedHealth.set(position, false);
                    boolean b = false;
                    for (int i = 0; i < selectedHealth.size(); i ++) {
                        if (selectedHealth.get(i)) {
                            b = true;
                        }
                    }
                }
                checkSelected();
            }

            @Override
            public void onTagLongClick(int position, String text) {
                mTagHealthContainerLayout.setDragEnable(true);
                TagView tagView = mTagHealthContainerLayout.getTagView(position);
            }

            @Override
            public void onTagCrossClick(int position) {

            }
        });

        mTagHealthContainerLayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                mTagHealthContainerLayout.setDragEnable(false);
            }
        });

    }

    public void checkSelected() {
        boolean isValid = false;
        for (boolean b : selectedTech) {
            if (b) {
                isValid = true;
                break;
            }
        }
        for (boolean b : selectedHealth) {
            if (b) {
                isValid = true;
                break;
            }
        }
        if (isValid) {
            assert rightButton2 != null;
            rightButton2.setImageResource(R.drawable.nav_bar_check_mark_icon);
            rightButton2.setEnabled(true);
        }else{
            assert rightButton2 != null;
            rightButton2.setImageResource(R.drawable.nav_bar_check_mark_disable_icon);
            rightButton2.setEnabled(false);
        }
    }


}
