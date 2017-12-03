package charles.com.milu.Login;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import charles.com.milu.R;

public class Registration2_Fragment extends Fragment {


    public static ImageView secondImage;

    public Registration2_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_second, container, false);

        secondImage = (ImageView)rootView.findViewById(R.id.imageView2);

        TextView txt1 = (TextView) rootView.findViewById(R.id.register1Text);

        Typeface workSans_font = Typeface.createFromAsset(getActivity().getAssets(),"WorkSans-Light.ttf");
        txt1.setTypeface(workSans_font);

        return rootView;
    }

}
