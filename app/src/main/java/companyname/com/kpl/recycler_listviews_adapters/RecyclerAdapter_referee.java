package companyname.com.kpl.recycler_listviews_adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.sql.Ref;
import java.util.ArrayList;

import companyname.com.kpl.R;
import companyname.com.kpl.admin_files.Stepone;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by admin on 1/3/2017.
 */
public class RecyclerAdapter_referee extends RecyclerView.Adapter <RecyclerAdapter_referee.RecyclerViewHolder> {

    private Context ctx;
    //private static final int TYPE_HEAD=0;
    //private static final int TYPE_LIST=1;


    ArrayList<Referee> arrayList=new ArrayList<>();


    public RecyclerAdapter_referee(ArrayList<Referee> arrayList, Context ctx) {
        this.arrayList=arrayList;
        this.ctx=ctx;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_ref_step_one,parent,false);
        RecyclerAdapter_referee.RecyclerViewHolder recyclerViewHolder=new RecyclerAdapter_referee.RecyclerViewHolder(view,viewType,ctx,arrayList);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        Referee referee=arrayList.get(position);
        holder.ref_Id.setText(Integer.toString(referee.getRef_id()));
//For Radio Button
        holder.rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i=0;i<arrayList.size();i++)
                    arrayList.get(i).isSelected = false;
                if (((RadioButton)view).isChecked())
                {
                    arrayList.get(position).isSelected = true;
                }
                notifyDataSetChanged();
            }
        });

        holder.rb.setChecked(arrayList.get(position).isSelected);
        holder.ref_name.setText(referee.getRef_name());

        Glide.with(holder.imageView.getContext()).load(referee.getRef_profile_pic())
                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.imageView);

        String mailID = holder.ref_Id.getText().toString();




    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        int viewType;
        TextView ref_name,ref_Id;
        ImageView imageView,imageView_static;
        RadioButton rb;
        ArrayList<Referee>referee=new ArrayList<Referee>();
        Context ctx;
        public RecyclerViewHolder(View view,int viewType,Context ctx,ArrayList<Referee> referee)
        {
            super(view);
            this.referee=referee;
            this.ctx=ctx;
            view.setOnClickListener(this);
            ref_name=(TextView)view.findViewById(R.id.cso_tvName);
            ref_Id=(TextView)view.findViewById(R.id.cso_tvid);
            imageView= (ImageView) view.findViewById(R.id.cso_iv);
            rb=(RadioButton)view.findViewById(R.id.cso_rb);
            //    this.viewType=TYPE_LIST;
            //Here define all your sharedpreferences code with key and value
            //SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);
            ///SharedPreferences.Editor edit = prefs.edit();
            //edit.putString("MID", mailID );
            //edit.commit();

        }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            Referee referee=this.referee.get(position);
            Intent intent = new Intent("custom-message");
            //Intent intent=new Intent(ctx,Stepone.class);
            if (((rb).isChecked()))
            {
                arrayList.get(position).isSelected = true;
                intent.putExtra("ref_id",Integer.toString(referee.getRef_id()));

                intent.putExtra("ref_name",referee.getRef_name());
                LocalBroadcastManager.getInstance(ctx).sendBroadcast(intent);
                Toast.makeText(ctx,referee.getRef_name()+" Confirmed",Toast.LENGTH_LONG).show();
            }
            //this.ctx.startActivity(intent);

        }

        public Context getCtx() {
            return ctx;

        }
    }

}
