package companyname.com.kpl.recycler_listviews_adapters;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import companyname.com.kpl.PremierLeagueTableFragment;
import companyname.com.kpl.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TablesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TablesFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView fsTvPremier;
    private TextView fsTvTheCahmpionships;
    private TextView fsTvDivisionOne;
    private TextView fsTvDivisionTwo;
    private TextView fsTvDivisionThree;
    private TextView fsTvCrusaders;
    private TextView fsTvMagikB;

    private TextView mtvBack;

    public TablesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment StatsFragment.
     */
    public static TablesFragment newInstance() {
        TablesFragment fragment = new TablesFragment();
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
        return inflater.inflate(R.layout.fragment_tables, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fsTvPremier = (TextView) view.findViewById(R.id.fs_tvPremier);
        fsTvTheCahmpionships = (TextView) view.findViewById(R.id.fs_tvTheCahmpionships);
        fsTvDivisionOne = (TextView) view.findViewById(R.id.fs_tvDivisionOne);
        fsTvDivisionTwo = (TextView) view.findViewById(R.id.fs_tvDivisionTwo);
        fsTvDivisionThree = (TextView) view.findViewById(R.id.fs_tvDivisionThree);
        fsTvCrusaders = (TextView) view.findViewById(R.id.fs_tvCrusaders);
        fsTvMagikB = (TextView) view.findViewById(R.id.fs_tvMagikB);

        mtvBack = (TextView) view.findViewById(R.id.fop_tvBack);
        mtvBack.setOnClickListener(this);

        fsTvPremier.setOnClickListener(this);
        fsTvTheCahmpionships.setOnClickListener(this);
        fsTvDivisionOne.setOnClickListener(this);
        fsTvDivisionTwo.setOnClickListener(this);
        fsTvDivisionThree.setOnClickListener(this);
        fsTvCrusaders.setOnClickListener(this);
        fsTvMagikB.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.fs_tvPremier:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fmkpl_flMain, PremierLeagueTableFragment.newInstance()).addToBackStack(null).commit();
                break;
            case R.id.fs_tvTheCahmpionships:
                break;
            case R.id.fs_tvDivisionOne:
                break;
            case R.id.fs_tvDivisionTwo:
                break;
            case R.id.fs_tvDivisionThree:
                break;
            case R.id.fs_tvCrusaders:
                break;
            case R.id.fs_tvMagikB:
                break;
            case R.id.fop_tvBack:
                getActivity().onBackPressed();
                break;
        }
    }
}
