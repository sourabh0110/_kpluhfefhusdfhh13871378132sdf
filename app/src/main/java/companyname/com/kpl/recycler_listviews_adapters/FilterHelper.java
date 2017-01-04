package companyname.com.kpl.recycler_listviews_adapters;

    import android.support.v7.widget.RecyclerView;
import android.widget.Filter;

import java.util.ArrayList;

/**
 * Created by admin on 12/22/2016.
 */

public class FilterHelper extends Filter {
    RecyclerView.Adapter adapter;
    ArrayList<Player> filterList;


    public FilterHelper(ArrayList<Player> filterList,RecyclerView.Adapter adapter)
    {
        this.adapter=adapter;
        this.filterList=filterList;

    }

    //FILTERING OCURS
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();

        //CHECK CONSTRAINT VALIDITY
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<Player> filteredPlayers=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getPlayer_name().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredPlayers.add(filterList.get(i));
                }
            }

            results.count=filteredPlayers.size();
            results.values=filteredPlayers;
        }else
        {
            results.count=filterList.size();
            results.values=filterList;

        }


        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

         //adapter.= (ArrayList<Player>) results.values;

        //REFRESH
        adapter.notifyDataSetChanged();
    }
}
