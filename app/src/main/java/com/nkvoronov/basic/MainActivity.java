package com.nkvoronov.basic;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Snackbar mSnackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mSnackbar = Snackbar.make(view, "Пора кормить кота", Snackbar.LENGTH_LONG);
                mSnackbar.setAction("Да", snackbarOnClickListener).setActionTextColor(Color.GREEN);
//                View snackbarView = mSnackbar.getView();
//                snackbarView.setBackgroundColor(Color.BLUE);
//                TextView snackTextView = snackbarView.findViewById(com.google.android.material.R.id.snackbar_text);
//                snackTextView.setTextColor(Color.RED);
                SnackbarHelper.configSnackbar(MainActivity.this, mSnackbar);
                mSnackbar.show();
                mSnackbar.addCallback(
                        new Snackbar.Callback(){

                            @Override
                            public void onDismissed(Snackbar snackbar, int event){
                                if (event == Snackbar.Callback.DISMISS_EVENT_TIMEOUT) {
                                    Log.i("SnackBar","Закрыт по истечении таймаута");
                                }
                                if (event == Snackbar.Callback.DISMISS_EVENT_SWIPE) {
                                    Log.i("SnackBar","Swipe");
                                }
                            }

                            protected void onShow(Snackbar snackbar) {
                                Log.i("SnackBar","onShow");
                            }
                        }
                );
            }

            View.OnClickListener snackbarOnClickListener = new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "Молодец!", Toast.LENGTH_LONG).show();
                }
            };
        });

        Button dismissButton = findViewById(R.id.dbutton);
        dismissButton.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        mSnackbar.dismiss();
                    }
                }

        );
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
}
