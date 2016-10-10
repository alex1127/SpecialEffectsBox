package com.ly.specialeffectsbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ly.specialeffectsbox.adapter.MyRecyclerAdapter;
import com.ly.specialeffectsbox.intefaces.ItemTouchCallback;
import com.ly.specialeffectsbox.intefaces.MyItemTouchHelperCallback;
import com.ly.specialeffectsbox.model.Item;
import com.ly.specialeffectsbox.view.DividerItemDecoration;
import com.ly.specialeffectsbox.view.TumblrRelativeLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemTouchCallback {

    private RecyclerView recyclerView;
    private View.OnClickListener clickListener;
    private View.OnClickListener menuClickListener;
    private List<Item> mList;
    MyRecyclerAdapter myRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mList.add(new Item(i, "name" + i, "name" + i));
        }

        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, -1));
        myRecyclerAdapter = new MyRecyclerAdapter(mList); // Create Instance of MyRecyclerAdapter
        recyclerView.setAdapter(myRecyclerAdapter);
        ItemTouchHelper.Callback callback = new MyItemTouchHelperCallback(this);// create MyItemTouchHelperCallback
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback); // Create ItemTouchHelper and pass with parameter the MyItemTouchHelperCallback
        touchHelper.attachToRecyclerView(recyclerView);
        TumblrRelativeLayout rootLayout = (TumblrRelativeLayout) findViewById(R.id.tumblr_frame_layout);
        rootLayout.setMenuListener(menuClickListener);
    }

    @Override
    public void itemTouchOnMove(int oldPosition, int newPosition) {
        Collections.swap(mList, oldPosition, newPosition); // change position
        myRecyclerAdapter.notifyItemMoved(oldPosition, newPosition);
    }

    @Override
    public void itemTouchOnSwiped(int position) {
        mList.remove(position);
        myRecyclerAdapter.notifyItemRemoved(position);
    }


}
