package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>  {
    private CardInterface cardInterface;

    private LayoutInflater inflater;
    private List<Card> cardList;

    public CardAdapter(Context context, List<Card> cardList, CardInterface cardInterface){
        inflater = LayoutInflater.from(context);
        this.cardList = cardList;
        this.cardInterface = cardInterface;
    }



    @NonNull
    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  inflater.inflate(R.layout.card_item, parent, false);
        return new ViewHolder(view, cardInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull CardAdapter.ViewHolder holder, int position) {
        Card card = cardList.get(position);
        holder.text.setText(card.getText());

    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }
    public void addCard(Card card){
        cardList.add(card);
        notifyItemInserted(cardList.size() - 1);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView text;
        ImageView delete_but, add_but;

        public ViewHolder(@NonNull View itemView, CardInterface cardInterface) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
            delete_but = itemView.findViewById(R.id.delete_item);
            delete_but.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cardInterface != null){
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION){
                            cardInterface.onDeleteClick(pos);
                        }
                    }
                }
            });


        }
    }
}
