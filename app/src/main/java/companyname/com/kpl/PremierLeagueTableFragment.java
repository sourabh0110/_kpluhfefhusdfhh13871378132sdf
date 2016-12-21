package companyname.com.kpl;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import companyname.com.kpl.recycler_listviews_adapters.PremierLeagueTableAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PremierLeagueTableFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PremierLeagueTableFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView mrvTable;
    private TextView mtvBack;
    public PremierLeagueTableFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment PremierLeagueTableFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PremierLeagueTableFragment newInstance() {
        PremierLeagueTableFragment fragment = new PremierLeagueTableFragment();
        /*Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);*/
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_premier_league_table, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mrvTable = (RecyclerView) view.findViewById(R.id.fpl_rvTable);
        mrvTable.setLayoutManager(new LinearLayoutManager(getActivity()));
        mrvTable.setAdapter(new PremierLeagueTableAdapter(getActivity()));
        mtvBack = (TextView) view.findViewById(R.id.fop_tvBack);
        mtvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }
}
