package companyname.com.kpl;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import companyname.com.kpl.recycler_listviews_adapters.StatesChildAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatsChildFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatsChildFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView mrvStates;
    private TextView mtvBack;


    public StatsChildFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment StatsChildFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatsChildFragment newInstance() {
        StatsChildFragment fragment = new StatsChildFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats_child, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mrvStates = (RecyclerView) view.findViewById(R.id.fol_rvStats);
        mrvStates.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
        mrvStates.setAdapter(new StatesChildAdapter(getActivity()));
        mtvBack =(TextView) view.findViewById(R.id.fop_tvBack);
        mtvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }
}
