package com.example.nfccardemulation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nfccardemulation.R;
import com.example.nfccardemulation.entities.Card;
import com.example.nfccardemulation.interfaces.ItemClickListener;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private ItemClickListener clickListener;
    private Context context;
    private List<Card> cardList;

    public RecyclerViewAdapter(List<Card> cardList, Context context) {
        this.context = context;
        this.cardList = cardList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Card card = cardList.get(position);
        holder.cardNumber.setText(card.getNumber());
        holder.cardCvv.setText(card.getCvv());
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        TextView cardNumber, cardCvv;

        MyViewHolder(View itemView) {
            super(itemView);
            //imageView = (NetworkImageView) itemView.findViewById(R.id.imageView);
            imageView = itemView.findViewById(R.id.imageView);
            cardNumber = itemView.findViewById(R.id.cardNumber);
            cardCvv = itemView.findViewById(R.id.cardCvv);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null)
                clickListener.onClick(v, getAdapterPosition());
        }
    }
}


