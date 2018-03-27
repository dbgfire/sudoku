package fr.epsi.mysudoku;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class easy_sudoku extends AppCompatActivity {

    private final Handler mHideHandler = new Handler();
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };

    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private List<lvl> genererlistlvl(){
        List<lvl> lvls = new ArrayList<lvl>();
        lvls.add(new lvl(2,8, 0));
        lvls.add(new lvl(2, 2, 0));
        lvls.add(new lvl(2, 3,0));
        lvls.add(new lvl(2, 4, 0));


        return lvls;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_sudoku);
        mControlsView = findViewById(R.id.full2);
        final easy_sudoku context = this;
        final ListView lvl;
        lvl = findViewById(R.id.lvl);
        List<lvl> niv = new ArrayList<>();
        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if (b != null) {
            String j = (String) b.get("lvl");
            for (int i = 0; i < 100; i++) {
                niv.add(new lvl(0, Integer.parseInt(j), 0));
            }
        }


        ListLvl adapter = new ListLvl(easy_sudoku.this, niv);
        /*final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, prenoms);*/
        lvl.setAdapter(adapter);
        lvl.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set title
                alertDialogBuilder.setTitle("Votre niveau");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Prêt ?")
                        .setCancelable(false)
                        .setPositiveButton("Commencer",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                               Intent intent=new Intent(context,grille.class);
                               intent.putExtra("choix","11");
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Annuler",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
                /*Intent intent = new Intent(context,grille.class);
                startActivity(intent);*/
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        delayedHide(100);
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    //mControlsView.setVisibility(View.GONE);
        mHideHandler.removeCallbacks(mShowPart2Runnable);
    }

    @SuppressLint("InlinedApi")


    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
