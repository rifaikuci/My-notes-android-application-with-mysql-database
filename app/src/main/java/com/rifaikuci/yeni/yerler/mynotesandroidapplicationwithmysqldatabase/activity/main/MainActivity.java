package com.rifaikuci.yeni.yerler.mynotesandroidapplicationwithmysqldatabase.activity.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rifaikuci.yeni.yerler.mynotesandroidapplicationwithmysqldatabase.R;
import com.rifaikuci.yeni.yerler.mynotesandroidapplicationwithmysqldatabase.activity.editor.EditorActivity;
import com.rifaikuci.yeni.yerler.mynotesandroidapplicationwithmysqldatabase.model.Note;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    FloatingActionButton fab;
    SwipeRefreshLayout swipeRefresh;
    RecyclerView recyclerView;

    MainPresenter presenter;
    MainAdapter adapter;
    MainAdapter.ItemClickListener itemClickListener;

    List<Note> note;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        recyclerView  = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fab = (FloatingActionButton) findViewById(R.id.add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditorActivity.class);
                startActivity(intent);
            }
        });

        presenter = new MainPresenter(this);
        presenter.getData();

        swipeRefresh.setOnRefreshListener(() -> presenter.getData());

        itemClickListener = ((view, position) -> {

            String title = note.get(position).getTitle();
            Toast.makeText(this,title,Toast.LENGTH_SHORT).show();

        });
    }

    @Override
    public void showLoading() {
        swipeRefresh.setRefreshing(true);

    }

    @Override
    public void hideLoading() {
        swipeRefresh.setRefreshing(false);

    }

    @Override
    public void onGetResult(List<Note> notes) {

        adapter = new MainAdapter(this,notes,itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        note = notes;


    }

    @Override
    public void onErrorLoading(String message) {

        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
