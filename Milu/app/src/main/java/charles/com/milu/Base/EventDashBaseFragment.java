package charles.com.milu.Base;


import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import charles.com.milu.EventBusListeners.ReplaceFragmentListener;
import charles.com.milu.R;
import ir.mirrajabi.viewfilter.core.ViewFilter;
import ir.mirrajabi.viewfilter.renderers.BlurRenderer;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventDashBaseFragment extends Fragment {

    private FrameLayout container;

    public EventDashBaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        EventBus.getDefault().register(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test, container, false);


        initUI(view);
        return view;
    }

    private void initUI(View view) {
        container = (FrameLayout) view.findViewById(R.id.layout_event_container);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReplaceFragmentEvent(ReplaceFragmentListener listener) {

        if (listener.getFragment() == null)
            return;

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        if (listener.isAction()) {

//            transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.push_down_out);

        } else {

//            transaction.setCustomAnimations(R.anim.slide_out_left, R.anim.push_down_in);

        }

        transaction.replace(R.id.layout_event_container, listener.getFragment());
        transaction.commit();
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();

    }
    public void setBlurTitleBar(View view, View rootView) {
        ViewFilter.getInstance(getContext())
                .setRenderer(new BlurRenderer(16))
                .applyFilterOnView(view, rootView);
    }
    public static int convertDpToPixels(float dp, Context context){
        Resources resources = context.getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                resources.getDisplayMetrics()
        );
    }
}
