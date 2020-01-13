package com.rifaikuci.yeni.yerler.mynotesandroidapplicationwithmysqldatabase.activity.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rifaikuci.yeni.yerler.mynotesandroidapplicationwithmysqldatabase.R;
import com.rifaikuci.yeni.yerler.mynotesandroidapplicationwithmysqldatabase.activity.editor.EditorActivity;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = (FloatingActionButton) findViewById(R.id.add);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditorActivity.class);
                startActivity(intent);
            }
        });
    }
}
