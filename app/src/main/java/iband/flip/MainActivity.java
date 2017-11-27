package iband.flip;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView transactionRV, kvisRV;
    TextView transactionText;
    TransactionAdapter adapter;
    List<TransactionProvider> list;

    KvisAdapter kvadapter;
    List<KvisProvider> kvlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        kvlist = new ArrayList<>();
        transactionText = (TextView) findViewById(R.id.transaction);
        adapter = new TransactionAdapter(MainActivity.this, list);
        transactionRV = (RecyclerView) findViewById(R.id.lower_layout);
        transactionRV.setLayoutManager((new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)));
        transactionRV.setAdapter(adapter);

        kvadapter = new KvisAdapter(MainActivity.this, kvlist);
        kvisRV = (RecyclerView) findViewById(R.id.upper_layout);
        kvisRV.setLayoutManager((new GridLayoutManager(this,2)));
        kvisRV.setAdapter(kvadapter);


        configKvis();
        configTransaction();



        getData();

    }

    private void getGridData(JSONArray array) {
        try {
            for (int i = 0; i < array.length(); i++) {
                JSONObject result = (JSONObject) array.get(i);


                kvlist.add(new KvisProvider(result.getString("text1"), result.getString("value")));

            }
            kvadapter.notifyDataSetChanged();
        }
        catch (JSONException e){e.printStackTrace();}
    }

    private void getData() {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "http://35.154.241.44:5555/api/v1/greenspades/data", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                transactionText.setText(response.toString());

                try {
                    JSONArray transaction = response.getJSONArray("transactions");
                    getGridData(response.getJSONArray("kpis"));

                    for (int i = 0; i < transaction.length(); i++) {
                        JSONObject result = (JSONObject)transaction.get(i);

                        String id = result.getString("_id");
                        String name="";
                        String mobile="";
                        if (result.getJSONObject("customer").has("name"))
                        {
                            name = result.getJSONObject("customer").getString("name");
                        }
                            if (result.getJSONObject("customer").has("mobileNumber")) {
                                mobile = result.getJSONObject("customer").getString("mobileNumber");
                            }
                                String amount = result.getString("amountPaid");
                        String date = result.getString("dateTransaction");

    list.add(new TransactionProvider(name, date, mobile, amount, id));
                    }
                    adapter.notifyDataSetChanged();

                }
                catch (JSONException e){e.printStackTrace();}


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                transactionText.setText(error.getMessage());
            }
        });

        MySingleton.getInstance(MainActivity.this).addToRequestQueue(request);
    }

    private void configTransaction() {


    }

    private void configKvis() {
        kvisRV = (RecyclerView) findViewById(R.id.upper_layout);
        kvisRV.setLayoutManager(new GridLayoutManager(this, 2));

    }
}
