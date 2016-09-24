package com.mega.recyclerviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mega.recyclerviewdemo.com.mega.recyclerviewdemo1.StaggeredAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements StaggeredAdapter.OnItemClickListener{
    private RecyclerView rv;
    private List<String> mDatas;
    private StaggeredAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intDatas();
        initViews();
        adapter = new StaggeredAdapter(mDatas, this);
        rv.setAdapter(adapter);

        rv.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        rv.setItemAnimator(new DefaultItemAnimator());
        adapter.setOnItemClickListener(this);
 //       rv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
    }

    private void initViews() {
        rv = (RecyclerView) findViewById(R.id.id_recyclerView);
    }

    private void intDatas() {
        mDatas = new ArrayList<>();
        for (int i = 'A'; i < 'a' ;i++) {
            mDatas.add("" + (char) i);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_add:
                adapter.addData(1);
                break;
            case R.id.action_delete:
                adapter.deleteData(1);
                break;
            case R.id.action_griView:
                rv.setLayoutManager(new GridLayoutManager(this,4));
            break;
            case R.id.action_hor_griView:
                rv.setLayoutManager(new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.HORIZONTAL));
                break;
            case R.id.action_staggered:
                break;
            case R.id.action_ListView:
                rv.setLayoutManager(new LinearLayoutManager(this));
                break;
            default:
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(View view, int pos) {
        Toast.makeText(this, "Click" + pos, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View view, int pos) {
        Toast.makeText(this, "LOngClick" + pos, Toast.LENGTH_SHORT).show();
    }
}
