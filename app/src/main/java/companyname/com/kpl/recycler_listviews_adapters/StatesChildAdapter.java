package companyname.com.kpl.recycler_listviews_adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import companyname.com.kpl.R;

/**
 * Created by ZetrixWeb on 19/12/16.
 */

public class StatesChildAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<String> arrayListGroup = new ArrayList<>();
    private HashMap<String, List<String>> listDataChild = new HashMap<>();
    private StatesExpandableListAdapter listAdapter;
    private boolean isExpand = false;
    int mheight;

    public StatesChildAdapter(Context context)
    {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if ((position+1)%3 == 0)
        {
            return 2;
        }
        else
        {
            return 1;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 2) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.stats_type_two_layout, parent, false);
            return new TypeTwoViewHolder(itemView);
        }
        else
        {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.stats_type_one_layout, parent, false);
            return new TypeOneViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof TypeTwoViewHolder)
        {
            prepareListData();
            listAdapter = new StatesExpandableListAdapter(context, arrayListGroup, listDataChild);
            ((TypeTwoViewHolder) holder).expListView.setAdapter(listAdapter);

            ((TypeTwoViewHolder) holder).expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                @Override
                public void onGroupExpand(int groupPosition) {
                    int height = 0;
                    for (int i = 0; i < ((TypeTwoViewHolder) holder).expListView.getChildCount(); i++) {
                        height += ((TypeTwoViewHolder) holder).expListView.getChildAt(i).getMeasuredHeight();
                        height += ((TypeTwoViewHolder) holder).expListView.getDividerHeight();
                    }
                    mheight = ((TypeTwoViewHolder) holder).expListView.getLayoutParams().height;
                    ((TypeTwoViewHolder) holder).expListView.getLayoutParams().height = height+height*listDataChild.get(arrayListGroup.get(0)).size();
                }
            });

            // Listview Group collapsed listener
            ((TypeTwoViewHolder) holder).expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

                @Override
                public void onGroupCollapse(int groupPosition) {
                    ((TypeTwoViewHolder) holder).expListView.getLayoutParams().height = mheight;
                }
            });
            /*((TypeTwoViewHolder) holder).expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                @Override
                public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                    if (isExpand)
                        isExpand = false;
                    else
                        isExpand= true;
                    notifyItemChanged(position);
                    return false;
                }
            });
            if (isExpand)
            {
                ((TypeTwoViewHolder) holder).expListView.expandGroup(0);
            }*/
        }
        else
        {

        }
    }


    @Override
    public int getItemCount() {
        return 10;
    }


    public class TypeOneViewHolder extends RecyclerView.ViewHolder
    {

        public TypeOneViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class TypeTwoViewHolder extends RecyclerView.ViewHolder
    {

        private ExpandableListView expListView;

        public TypeTwoViewHolder(View itemView) {
            super(itemView);
            expListView = (ExpandableListView) itemView.findViewById(R.id.expStates);
        }
    }

    private void prepareListData() {
        arrayListGroup = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        arrayListGroup.add("View Top 20 Goal Scores");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("Blazers");
        top250.add("Blazers");
        top250.add("Blazers");
        top250.add("Blazers");
        top250.add("Blazers");
        top250.add("Blazers");
        top250.add("Blazers");
        top250.add("Blazers");
        top250.add("Blazers");
        top250.add("Blazers");

        listDataChild.put(arrayListGroup.get(0), top250);
    }
}
