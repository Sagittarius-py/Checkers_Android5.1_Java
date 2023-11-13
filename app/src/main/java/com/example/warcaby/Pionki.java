package com.example.warcaby;

import android.util.Log;

public class Pionki {

    static char[][] tablicaPionow = {
            {'N', 'R', 'N', 'R','N', 'R','N', 'R'},
            {'R', 'N', 'R','N', 'R','N', 'R', 'N'},
            {'N', 'R', 'N', 'R','N', 'R','N', 'R'},
            {'E', 'N', 'E', 'N', 'E', 'N','E','N'},
            {'N', 'E', 'N', 'E', 'N','E','N', 'E'},
            {'G', 'N', 'G','N', 'G','N', 'G', 'N'},
            {'N', 'G','N', 'G','N', 'G', 'N', 'G'},
            {'G', 'N', 'G','N', 'G','N', 'G', 'N'},
    };

    static boolean ruch(int[] pion, int[] poleDocelowe, char graczWRuchu){

        int x = pion[0];
        int y = pion[1];

        int poleDoceloweX = poleDocelowe[0];
        int poleDoceloweY = poleDocelowe[1];

        Log.d("przekazany X", String.valueOf(poleDoceloweX));
        Log.d("przekazany Y", String.valueOf(poleDoceloweY));

        if(tablicaPionow[y][x] == graczWRuchu &&  tablicaPionow[poleDoceloweY][poleDoceloweX] == 'E'){
            tablicaPionow[y][x] = 'E';
            tablicaPionow[poleDoceloweY][poleDoceloweX] = graczWRuchu;
            Log.d("Penis",    String.valueOf(tablicaPionow[poleDoceloweY][poleDoceloweX]));
            return true;
        } else {
            return false;
        }
    }


}
