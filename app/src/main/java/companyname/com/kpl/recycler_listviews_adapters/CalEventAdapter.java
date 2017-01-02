package companyname.com.kpl.recycler_listviews_adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import companyname.com.kpl.OnClickListener;
import companyname.com.kpl.R;
import companyname.com.kpl.admin_files.StepZero;
import companyname.com.kpl.admin_files.Stepone;

/**
 * Created by admin on 1/2/2017.
 */
public class CalEventAdapter extends RecyclerView.Adapter<CalEventAdapter.HotListViewHolder>{
    private Context context;
    private OnClickListener onClickListener;

    private ArrayList<HotListPojo> hotListPojos;
    private boolean showFull = false;
    public CalEventAdapter(Context context, OnClickListener onClickListener)
    {
        this.context = context;
        this.onClickListener = onClickListener;
    }


    @Override
    public HotListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_match_step_zero, parent, false);
        return new CalEventAdapter.HotListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HotListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;


    }

    public class HotListViewHolder extends RecyclerView.ViewHolder{
        private TextView mtvTime;
        private TextView mtvTitle1;
        private TextView mtvTitle2;
        private TextView mtvType;
        ImageView mtvStar;
        private LinearLayout mllMain;
        Boolean isClick=false;
        public HotListViewHolder(View itemView) {
            super(itemView);
            mllMain = (LinearLayout) itemView.findViewById(R.id.hil_llmain);
            mtvStar = (ImageView) itemView.findViewById(R.id.hil_ivStar);
            mtvTime = (TextView) itemView.findViewById(R.id.hil_tvTime);
            mtvTitle1 = (TextView) itemView.findViewById(R.id.hil_tvTitle1);
            mtvTitle2 = (TextView) itemView.findViewById(R.id.hil_tvTitle2);
            mtvType = (TextView) itemView.findViewById(R.id.hil_tvType);
            final String a,b,c,d;

            a=mtvTime.getText().toString();
            b=mtvTitle1.getText().toString();
            c=mtvTitle2.getText().toString();
            d=mtvType.getText().toString();
            mtvStar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(isClick==true){
                        mtvStar.setImageResource(R.drawable.gray_star);
                        isClick=false;
                    }
                    else {
                        mtvStar.setImageResource(R.drawable.yellow_star);
                        isClick=true;
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //Toast.makeText(context,a,Toast.LENGTH_LONG).show();
                    //final int adapterPosition = RecyclerView.ViewHolder.this.getAdapterPosition();
                    onClickListener.onClick(view,getAdapterPosition());
                    //Setting Intent
                    Intent intent = new Intent(context, Stepone.class);
                    intent.putExtra("time", a);
                    intent.putExtra("team1", b);
                    intent.putExtra("team2", c);
                    intent.putExtra("division", d);
                    //intent.putExtra("date",);
                    context.startActivity(intent);

                }
            });
        }
    }
}
