package companyname.com.kpl.admin_files;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import companyname.com.kpl.R;
import companyname.com.kpl.recycler_listviews_adapters.Player_Details;

import static android.content.ContentValues.TAG;

/**
 * Created by LENOVO on 11/22/2016.
 */
public class OneFragment_admin extends Fragment {
    Context ctx;
    private boolean shouldShow = false;
    TextView date;
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());
    private SimpleDateFormat dateFormatForDisplaying = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
    private Toolbar toolbar;
    final List<String> mutableBookings = new ArrayList<>();
//    final ArrayAdapter adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, mutableBookings);

    public OneFragment_admin() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one_admin, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*
        * Original Calendarview
        * CalendarView cl=(CalendarView) getView().findViewById(R.id.calendar1);
        cl.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Intent i=new Intent(getActivity().getApplication(), StepZero.class);
                startActivity(i);
            }
        });
        * */

        /*
        * New CalendarView
        * */
        final CompactCalendarView cl = (CompactCalendarView) getView().findViewById(R.id.compactcalendar_view);
        toolbar = (Toolbar)getView().findViewById(R.id.tool_bar);
        toolbar.setTitle(dateFormatForMonth.format(cl.getFirstDayOfCurrentMonth()));
        cl.setListener(new CompactCalendarView.CompactCalendarViewListener() {


            @Override
            public void onDayClick(Date dateClicked) {
                toolbar.setTitle(dateFormatForMonth.format(dateClicked));

                List<Event> bookingsFromMap = cl.getEvents(dateClicked);
                Log.d(TAG, "inside onclick " + dateFormatForDisplaying.format(dateClicked));
                if (bookingsFromMap != null) {
                    Log.d(TAG, bookingsFromMap.toString());

                    /*
                    * Using Array Adapter
                    *if (bookingsFromMap != null) {
                        Log.d(TAG, bookingsFromMap.toString());
                        mutableBookings.clear();
                        for (Event booking : bookingsFromMap) {
                            mutableBookings.add((String) booking.getData());
                        }
                        //  adapter.notifyDataSetChanged();
                    }
                    * */
                    //USING THE INTENT TO LOAD ON THE CLICK OF ANY DATES
                    Intent i=new Intent(getActivity().getApplication(), StepZero.class);
                    i.putExtra("date", dateFormatForDisplaying.format(dateClicked));
                    Log.e("Current",""+dateFormatForDisplaying.format(dateClicked));
                    startActivity(i);
                    //((Activity)ctx).finish();


                }

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                toolbar.setTitle(dateFormatForMonth.format(firstDayOfNewMonth));
            }
            //toolbar.setTitle(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));
        /*
        * For the Animation
        * if (!cl.isAnimating()) {
            if (shouldShow) {
                cl.showCalendarWithAnimation();
            } else {
                cl.hideCalendarWithAnimation();
            }
            shouldShow = !shouldShow;
        }
        * */


        });
    }
}
