package charles.com.milu.Login;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skydoves.medal.MedalAnimation;

import charles.com.milu.R;


public class Registration1_Fragment extends Fragment {


    public static ImageView firstImage;
    public static TextView firstText;

    public Registration1_Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_first, container, false);

        firstImage = (ImageView) rootView.findViewById(R.id.imageView1);
        firstText = (TextView) rootView.findViewById(R.id.register1Text);

        setFont(rootView);

//        MedalAnimation medalAnimation = new MedalAnimation.Builder()
//                .setDirection(MedalAnimation.LEFT)
//                .setSpeed(4200)
//                .setTurn(4)
//                .build();
//
//        ImageView imageView = (ImageView)rootView.findViewById(R.id.badge);
//        imageView.startAnimation(medalAnimation);

        return rootView;
    }

    public void setFont(View view){

        TextView txt1 = (TextView) view.findViewById(R.id.register1Text);
        Typeface workSans_font = Typeface.createFromAsset(getActivity().getAssets(),"WorkSans-Light.ttf");
        txt1.setTypeface(workSans_font);
    }
}
