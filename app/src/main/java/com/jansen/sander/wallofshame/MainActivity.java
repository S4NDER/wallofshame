package com.jansen.sander.wallofshame;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.jansen.sander.wallofshame.classes.AppDatabase;
import com.jansen.sander.wallofshame.classes.Shame;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewShame();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addNewShame(){
        startActivity(new Intent(this, AddShameActivity.class));
    }

    public void getWithRating(View view){
        getAllShamesWithRating();
    }

    public boolean getAllShames(){
        try{
            new GetAllShamesTask().execute((Void)null);
            return true;
        } catch (Exception e){
            Toast.makeText(getApplicationContext(),"Could not access data", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public boolean getAllShamesWithRating(){

        try{
            int rating = Integer.parseInt(((Spinner)findViewById(R.id.spinnerRating)).getSelectedItem().toString().substring(0,1));

            Log.e("rating: ", rating +"");
            new GetAllShamesWithRatingTask(rating).execute((Void)null);
            return true;
        } catch (Exception e){
            Toast.makeText(getApplicationContext(),"Could not access data", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public class GetAllShamesWithRatingTask extends AsyncTask<Void, Void, List<Shame>> {

        private final int rating;

        GetAllShamesWithRatingTask(int rating){
            this.rating = rating;
        }

        @Override
        protected List<Shame> doInBackground(Void... voids) {
            List<Shame> allWithRating = AppDatabase.getInstance(getApplicationContext()).shameDao().getByRating(rating);

            for (Shame shameX : allWithRating ) {
                Log.v("Object", "Shame: " + shameX.getShameMessage() + "\r\nThe fool: "+ shameX.getShameDuts() + "\r\nRating: " + shameX.getStarRating()+ "\r\nid:"+shameX.getSid());
            }
            return allWithRating;
        }
    }

    public class GetAllShamesTask extends AsyncTask<Void, Void, List<Shame>>{

        private final List<Shame> allShames = new ArrayList<Shame>();

        @Override
        protected List<Shame> doInBackground(Void... voids) {
            List<Shame> all = AppDatabase.getInstance(getApplicationContext()).shameDao().getAll();

            for (Shame shameX : all ) {
                Log.v("Shame", shameX.getShameMessage() + shameX.getShameDuts()  + shameX.getStarRating()+ shameX.getSid());
            }
            return all;
        }
    }

}
