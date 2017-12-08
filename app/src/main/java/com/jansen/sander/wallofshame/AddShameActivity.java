package com.jansen.sander.wallofshame;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.jansen.sander.wallofshame.classes.AppDatabase;
import com.jansen.sander.wallofshame.classes.Shame;

import java.util.ArrayList;
import java.util.List;

public class AddShameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shame);
        Button addButton = findViewById(R.id.btnAddShame);
        addButton.setOnClickListener(btnListener);
    }

    public boolean addToDatabase(){
        String duts = ((EditText)findViewById(R.id.txtDuts)).getText().toString();
        String shame = ((EditText)findViewById(R.id.txtShame)).getText().toString();
        int rating = (int) ((RatingBar)findViewById(R.id.ratingFunny)).getRating();

        try{
            new AddShameTask(new Shame(shame, duts, rating)).execute((Void)null);
            this.finish();
            return true;
        } catch (Exception e){
            Toast.makeText(getApplicationContext(),"Could not add shame", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public Button.OnClickListener btnListener = new Button.OnClickListener(){

        @Override
        public void onClick(View v) {
            addToDatabase();
        }
    };

    public class AddShameTask extends AsyncTask<Void, Void, Boolean> {

        private final Shame newShame;

        AddShameTask(Shame newShame) {
            this.newShame = newShame;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            AppDatabase.getInstance(getApplicationContext())
                    .shameDao().insertAll(newShame);
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (success) {
                finish();
            } else {
                Toast.makeText(getApplicationContext(),
                        "Could not add shame", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
