package iband.flip;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dell on 26-11-2017.
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {
    private List<TransactionProvider> list;
    private Context context;

    public TransactionAdapter(Context context , List<TransactionProvider> list){
        this.list = list;
        this.context = context;
    }


    public class TransactionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

      TextView name,amount,mobile,date,transaction_id;

        RelativeLayout front;
              LinearLayout back;

        int click = 1;

        public TransactionViewHolder(View itemView) {
            super(itemView);
            front = (RelativeLayout)itemView.findViewById(R.id.front_layout);
            back = (LinearLayout)itemView.findViewById(R.id.back_layout);
            name= (TextView)itemView.findViewById(R.id.name);
            date= (TextView)itemView.findViewById(R.id.date);
            mobile= (TextView)itemView.findViewById(R.id.mobile);
            amount= (TextView)itemView.findViewById(R.id.amount);
            transaction_id= (TextView)itemView.findViewById(R.id.transaction_id);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            click++;
            if(click % 2 == 0){
                FlipAnimator.flipView(context,front,back,true);
            }else {
                FlipAnimator.flipView(context,front,back,false);
            }
        }
    }


    @Override
    public TransactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_transaction, parent, false);
        return new TransactionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TransactionViewHolder holder, int position) {
        TransactionProvider equipo = list.get(position);
        holder.name.setText(equipo.getName());
        holder.amount.setText(equipo.getAmount());
        holder.mobile.setText(equipo.getMobile());
        holder.date.setText(equipo.getDate());
        holder.transaction_id.setText(equipo.getTransaction());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
