package fr.epsi.mysudoku;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ASUS on 14/03/2018.
 */

public class ListLvl extends ArrayAdapter<lvl> {


        //tweets est la liste des models à afficher
        public ListLvl(Context context, List<lvl> lvl) {
            super(context, 0, lvl);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.rowlvl,parent, false);
            }

            lvlViewHolder viewHolder = (lvlViewHolder) convertView.getTag();
            if(viewHolder == null){
                viewHolder = new lvlViewHolder();
                viewHolder.niveau = (TextView) convertView.findViewById(R.id.lvl);
                viewHolder.num = (TextView) convertView.findViewById(R.id.num);
                viewHolder.done = (TextView) convertView.findViewById(R.id.done);
                convertView.setTag(viewHolder);
            }


            lvl nv = getItem(position);
            viewHolder.niveau.setText(""+position);
            viewHolder.num.setText("Niveau : "+nv.getNum());
            viewHolder.done.setText(" "+nv.getDone());

            return convertView;
        }

        private class lvlViewHolder{
            public TextView niveau;
            public TextView num;
            public TextView done;
        }
    }

