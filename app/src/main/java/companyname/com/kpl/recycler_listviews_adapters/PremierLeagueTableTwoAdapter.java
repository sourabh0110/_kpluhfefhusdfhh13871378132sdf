package companyname.com.kpl.recycler_listviews_adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import companyname.com.kpl.R;


/**
 * Created by ZetrixWeb on 19/12/16.
 */

public class PremierLeagueTableTwoAdapter extends RecyclerView.Adapter<PremierLeagueTableTwoAdapter.PremierLeagueViewHolder> {

    private Context context;


    public PremierLeagueTableTwoAdapter(Context context)
    {
        this.context = context;
    }

    @Override
    public PremierLeagueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.premier_league_table_two_item_layout, parent, false);
        return new PremierLeagueViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PremierLeagueViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 15;
    }


    public class PremierLeagueViewHolder extends RecyclerView.ViewHolder
    {
        private TextView mtvDate,mtvTime,mtvTitle1,mtvTitle2,mtvType;
        private LinearLayout mllMain;
        public PremierLeagueViewHolder(View itemView) {
            super(itemView);

        }
    }
}
