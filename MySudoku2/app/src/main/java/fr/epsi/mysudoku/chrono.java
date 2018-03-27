package fr.epsi.mysudoku;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by dbgfi on 26/03/2018.
 */

public class chrono extends Activity {

    BackgroundThread backgroundThread;
    TextView myText;
    static long startTime=System.currentTimeMillis();


    public static class BackgroundThread extends Thread {

        boolean running = false;

        void setRunning(boolean b){
            running = b;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            //super.run();
            while(running){
                try {
                    long time= System.currentTimeMillis();
                    sleep(1000);
                    Log.d("time", "run: "+((time-startTime)/1000));
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }

    }





    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        myText = (TextView)findViewById(R.id.mytext);

        Toast.makeText(this, "onCreate()", Toast.LENGTH_LONG).show();

    }



    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        backgroundThread = new BackgroundThread();
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
