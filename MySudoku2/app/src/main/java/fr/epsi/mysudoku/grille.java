package fr.epsi.mysudoku;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class grille extends AppCompatActivity {

    chrono.BackgroundThread backgroundThread;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grille);
        mControlsView = findViewById(R.id.full3);
       /* Generation Grille = new Generation(this);
        Grille.setNumColumns(9);
        Grille.setNumRows(9);
        setContentView(Grille);*/



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

        mHideHandler.removeCallbacks(mShowPart2Runnable);

    }


    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }


    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        backgroundThread = new chrono.BackgroundThread();
        backgroundThread.setRunning(true);
        backgroundThread.start();
        Toast.makeText(this, "onStart()", Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();

        boolean retry = true;
        backgroundThread.setRunning(false);

        while(retry){
            try {
                backgroundThread.join();
                retry = false;
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        Toast.makeText(this, "onStop()", Toast.LENGTH_LONG).show();

    }
}
