package fr.epsi.mysudoku;

/**
 * Created by ASUS on 14/03/2018.
 */

public class lvl {

        private int lvls;
        private int num;
        private int done;

        public lvl(int lvls, int num, int done) {
            this.lvls = lvls;
            this.num = num;
            this.done = done;
        }

    public int getDone() {
        return done;
    }

    public int getLvls() {
        return lvls;
    }

    public int getNum() {
        return num;
    }
}

