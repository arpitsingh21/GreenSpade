package iband.flip;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dell on 26-11-2017.
 */

public class KvisAdapter extends RecyclerView.Adapter<KvisAdapter.KvisViewHolder>{
    private List<KvisProvider> list;
    private Context context;

    public KvisAdapter(Context context , List<KvisProvider> list){
        this.list = list;
        this.context = context;
    }


    public class KvisViewHolder extends RecyclerView.ViewHolder {

        TextView name,value;




        public KvisViewHolder(View itemView) {
            super(itemView);

            name= (TextView)itemView.findViewById(R.id.text);
            value= (TextView)itemView.findViewById(R.id.value);
        }


    }


    @Override
    public KvisAdapter.KvisViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_grid, parent, false);
        return new KvisAdapter.KvisViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(KvisAdapter.KvisViewHolder holder, int position) {
        KvisProvider equipo = list.get(position);
        holder.name.setText(equipo.getName());
        holder.value.setText(equipo.getValue());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
