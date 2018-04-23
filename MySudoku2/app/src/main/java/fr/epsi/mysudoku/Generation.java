package fr.epsi.mysudoku;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import org.w3c.dom.Text;

import java.util.LinkedList;

/**
 * Created by ASUS on 14/03/2018.
 */

public class Generation extends View implements View.OnTouchListener {

    private LinkedList<Rect> Grille;
    private LinkedList<Rect> Chiffre;
    private LinkedList<Text> affiche;
    private Thread chrono = null;
    private int val=0;
    private Context con;
    private int [][] tab=new int[9][9];
    private String res ="008203500 009670408 346050702 430010059 967005001 000496203 280034067 703500904 004107020";
    private long temp=0;

    public Generation(Context context, AttributeSet attrs) {
        super(context,attrs);
        con=context;
        Grille =new LinkedList< Rect>();
        Chiffre =new LinkedList< Rect>();
        affiche = new LinkedList<Text>();

        String[] parts = res.split(" ");
        for(int y=0;y<9;y++) {
            String un = parts[y];
            String[] tmp = un.split("");
            for (int i = 0; i < 9; i++) {
                tab[y][i] = Integer.parseInt(tmp[i +1]);
                //Log.d("grille", "= "+tmp[i]);
                //Log.d("grille", "= "+tab[0][i]);
            }
        }
        this.setOnTouchListener(this);


    }

    @Override
    public void onDraw(Canvas canvas) {
        //dessine la grille
        for(int j=0;j<9;j++){
            for(int i=0;i<9;i++) {
                Grille.add(new Rect(0, 0, canvas.getWidth() / 9+ canvas.getWidth() / 9*i, canvas.getWidth() / 9+ canvas.getWidth() / 9*j));

            }
        }

        for(int i=1;i<10;i++){
             Chiffre.add(new Rect(0, canvas.getHeight() -3*(canvas.getWidth() / 9), canvas.getWidth() / 9*i, canvas.getHeight() -2*(canvas.getWidth() / 9)));

        }
        //definition de diffrent parametre des textes
        Paint blue = new Paint();
        blue.setColor(Color.BLACK);
        blue.setStyle(Paint.Style.STROKE);
        blue.setTextSize(60);


        //Draw to actual canvas

        for (int i = 0; i < 81; i++) {

            canvas.drawRect(Grille.get(i), blue);

        }

        //affiche les nombres dans la grille
        for(int i=0;i<9;i++){

            for(int y=0;y<9;y++) {
                if (tab[i][y] != 0 && i < 9) {
                    canvas.drawText("" + tab[i][y], canvas.getWidth() /( 9 *2)+ canvas.getWidth() / 9 * i , canvas.getWidth() /( 9*2 )+ canvas.getWidth() / 9 * y, blue);
                }
            }

        }

        //affiche les nombres a placer dans la grille
        for (int i=0;i<9;i++){
            canvas.drawRect(Chiffre.get(i), blue);
            canvas.drawText(""+(i+1),canvas.getWidth() / 9+ canvas.getWidth() / 9*i-(canvas.getWidth() / 9)/2-15,canvas.getHeight() -4*(canvas.getWidth() / 9)+15,blue);
        }

        canvas.drawText("" +temp, (canvas.getWidth() /9) *4 , (canvas.getHeight()/18)*17, blue);



    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x = (int)event.getX(); int y = (int)event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:


               if(x<=getWidth()&& y<= getWidth()){

               }else{
                   val=(x/(getWidth()/9 ))+1;
               }
                /
                break;
            case MotionEvent.ACTION_MOVE: // mouvement vers x,y

                if(x<=getWidth()&& y<= getWidth()){
                   // tab[(int)(x/(getWidth()/9 ))][(int)(y/(getWidth()/9 ))]=val;
                    Log.d("x", "onTouch: "+(int)(x/(getWidth()/9 )+1));
                    Log.d("y", "onTouch: "+(int)(y/(getWidth()/9 )+1));

                }

                break;
            case MotionEvent.ACTION_UP:

                if(x<=getWidth()&& y<= getWidth() && tab[(int)(x/(getWidth()/9 ))][(int)(y/(getWidth()/9 ))]==0){
                    tab[(int)(x/(getWidth()/9 ))][(int)(y/(getWidth()/9 ))]=val;
                    Log.d("x", "final: "+(int)(x/(getWidth()/9 )+1));
                    Log.d("y", "final: "+(int)(y/(getWidth()/9 )+1));
                    Log.d("val", "final: "+val);

                }

                break;

        }
        this.invalidate();
        return true;
    }



}
