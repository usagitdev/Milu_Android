package charles.com.milu.CalendarView.calendarpageview.support;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import charles.com.milu.CalendarView.calendarpageview.utils.CalendarUtil;
import charles.com.milu.CustomViews.ExtraLightTextView;
import charles.com.milu.R;

/**
 * 日历的适配器 <功能简述> <Br>
 * <功能详细描述> <Br>
 *
 * @author kysonX
 */
public class CalendarMonthAdapter extends BaseAdapter {

    private OnDaySelectedChangedListener mOnDaySelectListener;

    private List<CalendarItem> mCalendarItems = new ArrayList<CalendarItem>();
    // 当前选中的日期
    private Calendar mSelectedCal;

    private LayoutInflater mInflater;

    private Context mContext;

    public CalendarMonthAdapter(Context context, Calendar selectedCal, List<CalendarItem> calendarItems) {
        this.mContext = context;
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mSelectedCal = selectedCal;
        this.mCalendarItems = calendarItems;
    }


    @Override
    public int getCount() {
        if (mCalendarItems == null || mCalendarItems.isEmpty()) {
            return 0;
        }
        return mCalendarItems.size();
    }

    @Override
    public CalendarItem getItem(int position) {
        if (mCalendarItems == null || mCalendarItems.isEmpty()) {
            return null;
        }
        return mCalendarItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GridViewHolder holder;
        if (convertView == null) {
            holder = new GridViewHolder();
            convertView = mInflater.inflate(
                    R.layout.widget_item_calendar_cardview, parent,
                    false);
            holder.tvDay = (ExtraLightTextView) convertView
                    .findViewById(R.id.widget_item_calendar_cardview_date);
            holder.cellView = (RelativeLayout) convertView
                    .findViewById(R.id.cell_day);
            holder.imvDay = (ImageView) convertView
                    .findViewById(R.id.today_marker);
            convertView.setTag(holder);
        } else {
            holder = (GridViewHolder) convertView.getTag();
        }
        final CalendarItem calendarItem = getItem(position);
        ExtraLightTextView tvDay = holder.tvDay;
        ImageView imvDay = holder.imvDay;
        tvDay.setText(String.valueOf(calendarItem.calendar
                .get(Calendar.DAY_OF_MONTH)));
        if (calendarItem.monthPos == CalendarItem.MONTH_PRE){
            tvDay.setTextColor(ContextCompat.getColor(mContext, R.color.color_Onyx));
        }else if (calendarItem.monthPos == CalendarItem.MONTH_CURRENT) {
            if (calendarItem.isToday) {
                tvDay.setTextColor(ContextCompat.getColor(mContext, R.color.Deepsky));
            }else{
                if (calendarItem.isPreDay){
                    tvDay.setTextColor(ContextCompat.getColor(mContext, R.color.color_Onyx));
                }else{
                    tvDay.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                }
            }
        }else if (calendarItem.monthPos == CalendarItem.MONTH_NEXT) {
            tvDay.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        }
        imvDay.setSelected(CalendarUtil.isSameDay(mSelectedCal,
                calendarItem.calendar));
//        tvDay.setEnabled(calendarItem.monthPos == CalendarItem.MONTH_CURRENT);
        if (calendarItem.isSpecDay) {
            holder.cellView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.day_white));
        }else{
            holder.cellView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.transparent));
        }
        holder.cellView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                checkItem(calendarItem.calendar);
            }
        });
        return convertView;
    }

    // check one item
    private void checkItem(Calendar cal) {
        // 点击的和当前选中的是同一天
        if (CalendarUtil.isSameDay(cal, mSelectedCal)) {
            return;
        }
        mSelectedCal = (Calendar) cal.clone();
        notifyDataSetChanged();
        if (mOnDaySelectListener != null) {
            mOnDaySelectListener.onDaySelectedChanged(mSelectedCal);
        }
    }


    public static class GridViewHolder {
        public ExtraLightTextView tvDay;
        public ImageView imvDay;
        public RelativeLayout cellView;
    }

    public void setOnDaySelectListener(
            OnDaySelectedChangedListener onDaySelectListener) {
        this.mOnDaySelectListener = onDaySelectListener;
    }

    public List<CalendarItem> getDatas() {
        return mCalendarItems;
    }

    public void setDatas(List<CalendarItem> calendarItems) {
        this.mCalendarItems = calendarItems;
    }

    public void setSelectedDate(Calendar cal) {
        checkItem(cal);
    }

    public Calendar getSelectedDate() {
        return mSelectedCal;
    }

}
