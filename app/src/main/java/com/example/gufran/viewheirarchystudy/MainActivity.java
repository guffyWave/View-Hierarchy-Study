package com.example.gufran.viewheirarchystudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.debug.hv.ViewServer;

public class MainActivity extends AppCompatActivity {

    private int mCounter;

    // https://github.com/romainguy/ViewServer
    // don't forget to add  internet permission

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent().getExtras() != null) mCounter = getIntent().getExtras().getInt("counter");
        ((TextView) findViewById(R.id.label)).setText("Activity #" + (mCounter + 1));

        ViewServer.get(this).addWindow(this);
    }

    public void nextActivity(View v) {
        Intent intent = new Intent(this, getClass());
        intent.putExtra("counter", mCounter + 1);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ViewServer.get(this).removeWindow(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewServer.get(this).setFocusedWindow(this);
    }
}
