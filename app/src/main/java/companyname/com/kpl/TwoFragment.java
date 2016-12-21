package companyname.com.kpl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import companyname.com.kpl.recycler_listviews_adapters.FixturesFragment;
import companyname.com.kpl.recycler_listviews_adapters.KplExpandableListAdapter;
import companyname.com.kpl.recycler_listviews_adapters.TablesFragment;


public class TwoFragment extends Fragment implements View.OnClickListener {
    private ArrayList<String> arrayListGroup = new ArrayList<>();
    private HashMap<String, List<String>> listDataChild = new HashMap<>();
    private KplExpandableListAdapter listAdapter;


    private ExpandableListView melvFixtures,melvTable,melvResults;
    private TextView mtvAllTable,mtvAllFixture;

    int mheight;

    public TwoFragment() {
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
        return inflater.inflate(R.layout.fragment_two, container, false);


    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.fk_tvAllTable:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fmkpl_flMain, TablesFragment.newInstance()).addToBackStack(null).commit();
                break;

            case R.id.fk_tvAllFixture:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fmkpl_flMain, FixturesFragment.newInstance()).addToBackStack(null).commit();
                break;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        melvFixtures = (ExpandableListView) view.findViewById(R.id.fmk_elFixture);
        melvResults = (ExpandableListView) view.findViewById(R.id.fmk_elResult);
        melvTable = (ExpandableListView) view.findViewById(R.id.fmk_elTable);

        prepareListData();
        listAdapter = new KplExpandableListAdapter(getActivity(), arrayListGroup, listDataChild);
        melvFixtures.setAdapter(listAdapter);
        listAdapter = new KplExpandableListAdapter(getActivity(), arrayListGroup, listDataChild);
        melvTable.setAdapter(listAdapter);
        listAdapter = new KplExpandableListAdapter(getActivity(), arrayListGroup, listDataChild);
        melvResults.setAdapter(listAdapter);

        mtvAllTable = (TextView) view.findViewById(R.id.fk_tvAllTable);
        mtvAllFixture = (TextView) view.findViewById(R.id.fk_tvAllFixture);

        mtvAllTable.setOnClickListener(this);
        mtvAllFixture.setOnClickListener(this);

        melvFixtures.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                int height = 0;
                for (int i = 0; i < melvFixtures.getChildCount(); i++) {
                    height += melvFixtures.getChildAt(i).getMeasuredHeight();
                    height += melvFixtures.getDividerHeight();
                }
                mheight = melvFixtures.getLayoutParams().height;
                melvFixtures.getLayoutParams().height = (height+32)*2;
            }
        });

        // Listview Group collapsed listener
        melvFixtures.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                melvFixtures.getLayoutParams().height = mheight;
            }
        });

        melvResults.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                int height = 0;
                for (int i = 0; i < melvResults.getChildCount(); i++) {
                    height += melvResults.getChildAt(i).getMeasuredHeight();
                    height += melvResults.getDividerHeight();
                }
                mheight = melvResults.getLayoutParams().height;
                melvResults.getLayoutParams().height = (height+32)*2;
            }
        });

        // Listview Group collapsed listener
        melvResults.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                melvResults.getLayoutParams().height = mheight;
            }
        });


        melvTable.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                int height = 0;
                for (int i = 0; i < melvTable.getChildCount(); i++) {
                    height += melvTable.getChildAt(i).getMeasuredHeight();
                    height += melvTable.getDividerHeight();
                }
                mheight = melvTable.getLayoutParams().height;
                melvTable.getLayoutParams().height = (height+32)*2;
            }
        });

        // Listview Group collapsed listener
        melvTable.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                melvTable.getLayoutParams().height = mheight;
            }
        });
    }
    private void prepareListData() {
        arrayListGroup = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        arrayListGroup.add("Fixture");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("Blazers");
        top250.add("Blazers");

        listDataChild.put(arrayListGroup.get(0), top250);
    }
}
