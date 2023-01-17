package com.example.recyclerviewpagination;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ModelClass> arrayList = new ArrayList<>();
    String[] data = new String[]{"Jan 1", "Jan 2", "Jan 3", "Jan 4", "Jan 5", "Jan 6", "Jan 7", "Jan 8", "Jan 9","Jan 10"
            ,"Jan 11", "Jan 12", "Jan 13", "Jan 14", "Jan 15", "Jan 16", "Jan 17", "Jan 18", "Jan 19","Jan 20","Jan 21", "Jan 22",
            "Jan 23", "Jan 24", "Jan 25", "Jan 26", "Jan 27", "Jan 28","Jan 29","Jan 30"};
    String[] data2 = new String[]{"Feb 1", "Feb 2", "Feb 3", "Feb 4", "Feb 5", "Feb 6", "Feb 7", "Feb 8", "Feb 9","Feb 10"
            ,"Feb 11", "Feb 12", "Feb 13", "Feb 14", "Feb 15", "Feb 16", "Feb 17", "Feb 18", "Feb 19","Feb 20","Feb 21", "Feb 22",
            "Feb 23", "Feb 24", "Feb 25", "Feb 26", "Feb 27", "Feb 28","Feb 29","Feb 30"};

    private boolean loading = true;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    private LinearLayoutManager mLayoutManager;
    private MyListAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        myAdapter = new MyListAdapter(getData(), this);

        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(myAdapter);

        setupPagination();
    }

    private void setupPagination() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0){
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (loading){
                        if ((visibleItemCount+pastVisiblesItems)>=totalItemCount){
                            loading = false;
                            Toast.makeText(MainActivity.this, "This is the last item", Toast.LENGTH_SHORT).show();

                            getData2();
                            myAdapter.notifyDataSetChanged();
                            loading = true;
                        }
                    }
                }
                if (dy < 0){
                    mLayoutManager.setReverseLayout(true);
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (loading){
                        if ((visibleItemCount+pastVisiblesItems)>=totalItemCount){
                            loading = false;
                            Toast.makeText(MainActivity.this, "This is the last item", Toast.LENGTH_SHORT).show();
                            Collections.reverse(arrayList);
                            getData2();
                            myAdapter.notifyDataSetChanged();
                            loading = true;
                        }
                    }
                }
            }
        });
    }

    private ArrayList<ModelClass> getData(){
        for(int i=0;i<data.length;i++){
            arrayList.add(new ModelClass(data[i]));
        }
        return arrayList;
    }

    private ArrayList<ModelClass> getData2(){
        for(int i=0;i<data2.length;i++){
            arrayList.add(new ModelClass(data2[i]));
        }
        return arrayList;
    }
}