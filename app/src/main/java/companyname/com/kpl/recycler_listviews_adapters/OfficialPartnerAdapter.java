package companyname.com.kpl.recycler_listviews_adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import companyname.com.kpl.R;


/**
 * Created by ZetrixWeb on 19/12/16.
 */

public class OfficialPartnerAdapter extends RecyclerView.Adapter<OfficialPartnerAdapter.HotListViewHolder> {

    private Context context;


    public OfficialPartnerAdapter(Context context)
    {
        this.context = context;

    }

    @Override
    public HotListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.official_item_layout, parent, false);
        return new HotListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HotListViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 10;
    }


    public class HotListViewHolder extends RecyclerView.ViewHolder
    {

        public HotListViewHolder(View itemView) {
            super(itemView);
        }
    }
}
