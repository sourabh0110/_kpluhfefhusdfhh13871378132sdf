package companyname.com.kpl.recycler_listviews_adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import companyname.com.kpl.OnClickListener;
import companyname.com.kpl.R;

/**
 * Created by admin on 1/3/2017.
 */
public class CalStepOneAdapter_Ref extends RecyclerView.Adapter<CalStepOneAdapter_Ref.HotListViewHolder> {
    private Context context;
    private OnClickListener onClickListener;

    private ArrayList<HotListPojo> hotListPojos;
    private boolean showFull = false;

    public CalStepOneAdapter_Ref(Context applicationContext, ArrayList<HotListPojo> hotListPojos) {
        this.context = applicationContext;
        this.hotListPojos = hotListPojos;
    }

    @Override
    public HotListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_ref_step_one, parent, false);
        return new HotListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HotListViewHolder holder, final int position) {
        if (position%2 == 0)
        {
            holder.mllMain.setBackgroundColor(context.getResources().getColor(R.color.gray));
        }
        else
        {
            holder.mllMain.setBackgroundColor(context.getResources().getColor(android.R.color.white));
        }
        holder.rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i=0;i<hotListPojos.size();i++)
                    hotListPojos.get(i).isSelected = false;
                if (((RadioButton)view).isChecked())
                {
                    hotListPojos.get(position).isSelected = true;
                }
                notifyDataSetChanged();
            }
        });

        holder.rb.setChecked(hotListPojos.get(position).isSelected);

    }

    @Override
    public int getItemCount() {
        return hotListPojos.size();
    }

    public class HotListViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private RadioButton rb;
        private LinearLayout mllMain;
        Boolean isClick=false;
        public HotListViewHolder(View itemView) {
            super(itemView);
            mllMain = (LinearLayout) itemView.findViewById(R.id.cso_llmain);
            tvName = (TextView) itemView.findViewById(R.id.cso_tvName);
            rb= (RadioButton) itemView.findViewById(R.id.cso_rb);
        }
    }
}
