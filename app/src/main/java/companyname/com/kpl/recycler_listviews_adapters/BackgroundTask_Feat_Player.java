package companyname.com.kpl.recycler_listviews_adapters;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import companyname.com.kpl.R;

/**
 * Created by admin on 12/27/2016.
 */
public class BackgroundTask_Feat_Player extends AsyncTask<Void,FeatPlayer,Void> {
    private ProgressDialog loading;
    Context ctx;
    Activity activity;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<FeatPlayer> arrayList = new ArrayList<>();

    public BackgroundTask_Feat_Player(Context ctx) {
        this.ctx = ctx;
        activity = (Activity) ctx;
    }


    String json_string="http://devkpl.com/KPL-Admin/wsGetFeaturedPlayers";

    //String json_string = "http://devkpl.com/getList_news.php";

    @Override
    protected void onPreExecute() {
        recyclerView = (RecyclerView) activity.findViewById(R.id.rv_getallfeatplayers);
        layoutManager = new LinearLayoutManager(ctx);
        //RecyclerView.LayoutManager layoutManager=new GridLayoutManager(ctx,2);
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(2,dpToPx(10),true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerAdapter_featplayer(arrayList,ctx);
        recyclerView.setAdapter(adapter);
        loading=new ProgressDialog(ctx);
        loading.setTitle("Please Wait..");
        loading.setMessage("List is now Loading!");
        loading.setIndeterminate(true);
        loading.setCancelable(false);
        loading.show();
    }


    @Override
    protected void onProgressUpdate(FeatPlayer... values) {
        arrayList.add(values[0]);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onPostExecute(Void aVoid) {

        loading.dismiss();

    }

    @Override
    protected Void doInBackground(Void... voids) {


        try {

            URL url=new URL(json_string);
            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder=new StringBuilder();
            String line;


            while((line=bufferedReader.readLine())!=null)
            {
                stringBuilder.append(line+"\n");
            }

            httpURLConnection.disconnect();
            String json_string=stringBuilder.toString().trim();
            JSONObject jsonObject=new JSONObject(json_string);
            JSONArray jsonArray=jsonObject.getJSONArray("server_response");
            int count=0;
            while(count<jsonArray.length())
            {
                JSONObject JO=jsonArray.getJSONObject(count);
                count++;
                //ForWB
                //Player player=new Player(JO.getString("usr_name"),JO.getInt("usr_team_code"),JO.getString("usr_team_name"),JO.getString("usr_mobile_number"),JO.getString("usr_dob"),JO.getString("usr_profile_pic"),JO.getInt("usr_id"));
                FeatPlayer fplayer=new FeatPlayer(JO.getInt("id"),JO.getInt("matches_played"),JO.getInt("goals_scored"),JO.getInt("yellow_cards"),JO.getInt("red_cards"),JO.getInt("assists"),JO.getString("player_name"),JO.getString("player_pos"),JO.getString("player_team"),JO.getString("image"));
                publishProgress(fplayer);
                Thread.sleep(100);
            }
            Log.e("JSON_STRING",json_string);





        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        //Resources r = getResources();
        Resources r=Resources.getSystem();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));

    }
}

