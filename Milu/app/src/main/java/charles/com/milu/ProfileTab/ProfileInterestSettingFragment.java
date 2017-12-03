package charles.com.milu.ProfileTab;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import charles.com.milu.Base.BaseFragment;
import charles.com.milu.R;
import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;

/**
 * Created by mac_dev on 10/28/17.
 */

public class ProfileInterestSettingFragment extends BaseFragment {

    @BindView(R.id.scrollView)
    NestedScrollView scrollView;

    @BindView(R.id.tagcontainerLayout)
    TagContainerLayout mTagContainerLayout;

    @BindView(R.id.add_button)
    ImageButton addButton;

    @BindView(R.id.delete_button)
    ImageButton deleteButton;

    ArrayList<String> interests = new ArrayList<>();
    ArrayList<int[]> colors = new ArrayList<>();
    ArrayList<Boolean> selectecInterest = new ArrayList<>();

    public static ProfileInterestSettingFragment getInstance() {
        // Required empty public constructor
        ProfileInterestSettingFragment fragment = new ProfileInterestSettingFragment();
        return fragment;
    }
    protected int addView() {
        return R.layout.fragment_profile_interesting;
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
        rightButton2.setImageResource(R.drawable.nav_bar_search_icon);
        rightButton2.setVisibility(View.VISIBLE);
        rightButton2.setOnClickListener(this);

        addButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.toolbar_btn_left:
                mAct.onBackPressed();
                break;

            case R.id.add_button:
                addTagAlert();
                break;
            case R.id.delete_button:
                deleteTags();
                break;
        }
    }

    public void setTagView() {
        interests.add("internet of things");
        interests.add("graphic design");
        interests.add("new technology");
        interests.add("cryptocurrency");
        interests.add("computer programming");
        interests.add("unity");
        interests.add("preventive wellness");
        interests.add("nutrition");
        interests.add("alternative medicine");
        interests.add("well being");
        interests.add("holistic health");
        interests.add("weight loss");
        colors.add(new int[]{Color.parseColor("#00ffffff"), Color.parseColor("#ffffffff"), Color.parseColor("#ffffffff")});
        colors.add(new int[]{Color.parseColor("#00ffffff"), Color.parseColor("#ffffffff"), Color.parseColor("#ffffffff")});
        colors.add(new int[]{Color.parseColor("#00ffffff"), Color.parseColor("#ffffffff"), Color.parseColor("#ffffffff")});
        colors.add(new int[]{Color.parseColor("#00ffffff"), Color.parseColor("#ffffffff"), Color.parseColor("#ffffffff")});
        colors.add(new int[]{Color.parseColor("#00ffffff"), Color.parseColor("#ffffffff"), Color.parseColor("#ffffffff")});
        colors.add(new int[]{Color.parseColor("#00ffffff"), Color.parseColor("#ffffffff"), Color.parseColor("#ffffffff")});
        colors.add(new int[]{Color.parseColor("#00ffffff"), Color.parseColor("#ffffffff"), Color.parseColor("#ffffffff")});
        colors.add(new int[]{Color.parseColor("#00ffffff"), Color.parseColor("#ffffffff"), Color.parseColor("#ffffffff")});
        colors.add(new int[]{Color.parseColor("#00ffffff"), Color.parseColor("#ffffffff"), Color.parseColor("#ffffffff")});
        colors.add(new int[]{Color.parseColor("#00ffffff"), Color.parseColor("#ffffffff"), Color.parseColor("#ffffffff")});
        colors.add(new int[]{Color.parseColor("#00ffffff"), Color.parseColor("#ffffffff"), Color.parseColor("#ffffffff")});
        colors.add(new int[]{Color.parseColor("#00ffffff"), Color.parseColor("#ffffffff"), Color.parseColor("#ffffffff")});
        selectecInterest.add(false);
        selectecInterest.add(false);
        selectecInterest.add(false);
        selectecInterest.add(false);
        selectecInterest.add(false);
        selectecInterest.add(false);
        selectecInterest.add(false);
        selectecInterest.add(false);
        selectecInterest.add(false);
        selectecInterest.add(false);
        selectecInterest.add(false);
        selectecInterest.add(false);
        Typeface workSans_Light = Typeface.createFromAsset(getContext().getAssets(),"WorkSans-Light.ttf");
        mTagContainerLayout.setTagTypeface(workSans_Light);
        mTagContainerLayout.setDragEnable(false);
        mTagContainerLayout.setTags(interests, colors);
        mTagContainerLayout.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {
                if (!selectecInterest.get(position)){
                    mTagContainerLayout.getTagView(position).setTagBorderColor(ContextCompat.getColor(getContext(), R.color.color_Milumain));
                    mTagContainerLayout.getTagView(position).setTagTextColor(ContextCompat.getColor(getContext(), R.color.color_Milumain));
                    selectecInterest.set(position, true);
                    deleteButton.setVisibility(View.VISIBLE);
                    addButton.setVisibility(View.GONE);
                }else{
                    mTagContainerLayout.getTagView(position).setTagBorderColor(ContextCompat.getColor(getContext(), R.color.colorWhite));
                    mTagContainerLayout.getTagView(position).setTagTextColor(ContextCompat.getColor(getContext(), R.color.colorWhite));
                    selectecInterest.set(position, false);
                    boolean b = false;
                    for (int i = 0; i < selectecInterest.size(); i ++) {
                        if (selectecInterest.get(i)) {
                            b = true;
                        }
                    }
                    if (b) {
                        deleteButton.setVisibility(View.VISIBLE);
                        addButton.setVisibility(View.GONE);
                    }else{
                        deleteButton.setVisibility(View.GONE);
                        addButton.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onTagLongClick(int position, String text) {
                mTagContainerLayout.setDragEnable(true);
                TagView tagView = mTagContainerLayout.getTagView(position);
            }

            @Override
            public void onTagCrossClick(int position) {

            }
        });

        mTagContainerLayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                mTagContainerLayout.setDragEnable(false);
            }
        });
    }

    public void addTagAlert() {
        LayoutInflater layoutInflater = LayoutInflater.from(mAct);
        View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mAct);
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.editText);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String string = editText.getText().toString();
                        interests.add(string);
                        colors.add(new int[]{Color.parseColor("#00ffffff"), Color.parseColor("#ffffffff"), Color.parseColor("#ffffffff")});
                        selectecInterest.add(false);
                        mTagContainerLayout.setTags(interests, colors);
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();

    }

    public void deleteTags() {
        ArrayList<Boolean> arrayList = selectecInterest;
        for (int i = 0; i< selectecInterest.size(); i ++) {
            if (selectecInterest.get(i)) {
                mTagContainerLayout.removeTag(i);
                arrayList.remove(i);
                colors.remove(i);
            }
        }
        selectecInterest = arrayList;
        deleteButton.setVisibility(View.GONE);
        addButton.setVisibility(View.VISIBLE);
    }

}
