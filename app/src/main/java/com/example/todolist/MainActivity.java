package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.todolist.databinding.ActivityMainBinding;
import com.example.todolist.db.MyDbManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CardInterface, AnimationListener{

    EditText addItem;
    ImageView addBut;
    RecyclerView recView;
    CardAdapter cardAdapter;
    List<Card> cardList = new ArrayList<Card>();

    private MyDbManager myDbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);

        myDbManager = new MyDbManager(this);


        //LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        //AnimationDrawable animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        //animationDrawable.setEnterFadeDuration(2000);
        //animationDrawable.setExitFadeDuration(4000);
        //animationDrawable.start();

        addItem = findViewById(R.id.add_item);
        addBut = findViewById(R.id.add_button);
        recView = findViewById(R.id.recyclerView);
        addBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = addItem.getText().toString();
                Card card = new Card(text);
                cardAdapter.addCard(card);

            }
        });
        cardList = myDbManager.getFromDb();
        cardAdapter = new CardAdapter(this, cardList, this);
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setAdapter(cardAdapter);
        new ItemTouchHelper(new CardTouchHelper(this)).attachToRecyclerView(recView);

    }

    @Override
    protected void onResume() {
        super.onResume();
        myDbManager.openDb();
    }

    @Override
    public void onDeleteClick(int position) {
        cardList.remove(position);
        cardAdapter.notifyItemRemoved(position);

    }

    @Override
    public void onMove(int fromPos, int toPos) {
        cardList.add(toPos, cardList.remove(fromPos));
        cardAdapter.notifyItemMoved(fromPos, toPos);
    }

    @Override
    public void onSwiped(int direction, int pos) {
        cardList.remove(pos);
        cardAdapter.notifyItemRemoved(pos);
    }

    @Override
    protected void onStop() {
        super.onStop();
        myDbManager.deleteAllDb();
        myDbManager.insertToDb(cardList);
    }

    @Override
    protected void onDestroy() {
        myDbManager.closeDb();
        super.onDestroy();

    }
}