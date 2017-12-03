package charles.com.milu.LiveEvents.LiveMediaPost_Critique;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import charles.com.milu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveMediaPost_CritiqueFragment extends Fragment {

    EditText critiqueInputText;
    TextView maxText;

    public LiveMediaPost_CritiqueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live_media_post_critique, container, false);

        critiqueInputText = (EditText) view.findViewById(R.id.liveMediaPost_Cri_inputtxt);
        maxText = (TextView) view.findViewById(R.id.liveMediaPost_Max_txt);

        String currentTxt = critiqueInputText.toString();
        int currentLength = currentTxt.length();
        maxText.setText(currentLength + "/140");
        Typeface workSans_Light = Typeface.createFromAsset(getContext().getAssets(),"WorkSans-Light.ttf");
        critiqueInputText.setTypeface(workSans_Light);

        setEditTextView(view);

        return  view;
    }

    public void setEditTextView(View view) {

        critiqueInputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String currentTxt = s.toString();
                int count = currentTxt.length();
                maxText.setText(count + "/140");

            }
        });


    }

}
