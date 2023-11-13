package com.example.warcaby;

import static androidx.gridlayout.widget.GridLayout.spec;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity {

    GridLayout Grid;

    char graczWRuchu = 'G';
    int[] pionWRuchu = {9,9};

    Button UsunPion;

    TextView Gracz;

    void generuj(GridLayout Grid){
        Grid.removeAllViews();
        Drawable koloBiale = getResources().getDrawable(R.drawable.kolobiale);
        Drawable koloCzarne = getResources().getDrawable(R.drawable.koloczarne);


        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                FrameLayout pole = new FrameLayout(this);
                pole.setPadding(10, 10, 10, 10);
                ImageView pionek = new ImageView(this);

                pionek.setMinimumHeight(30);
                pionek.setMinimumWidth(30);

                Gracz = findViewById(R.id.gracz);

                if(graczWRuchu == 'G') {
                    Gracz.setText("Ruch wykonuje gracz zielony");
                } else if (graczWRuchu == 'R') {
                    Gracz.setText("Ruch wykonuje gracz czerwony");
                }

                if(Plansza.szachownica[i][j] == 'B'){
                    pole.setBackgroundColor(Color.parseColor("#FFFFFF"));
                } else if (Plansza.szachownica[i][j] == 'C') {
                    if(pionWRuchu[0] == j && pionWRuchu[1] == i){
                        pole.setBackgroundColor(Color.parseColor("#2d4b80"));
                    } else {
                        pole.setBackgroundColor(Color.parseColor("#000000"));
                    }
                    pole.setOnTouchListener(
                            new View.OnTouchListener() {
                                @Override
                                public boolean onTouch(View v, MotionEvent event) {
                                    int poleDocelowe[] = {0, 0};


                                    if(pionWRuchu[0] != 9 && pionWRuchu[1] != 9){
                                        poleDocelowe[0] = pole.getRight() / 50 - 1;
                                        poleDocelowe[1] = pole.getTop() / 50 ;

                                        Log.d("pion w ruchu x", String.valueOf(pionWRuchu[0]));
                                        Log.d("pion w ruchu y", String.valueOf(pionWRuchu[1]));
                                        Log.d("poleDocelowe x", String.valueOf(poleDocelowe[0]));
                                        Log.d("poleDocelowe y", String.valueOf(poleDocelowe[1]));

                                        if(Pionki.ruch(pionWRuchu, poleDocelowe, graczWRuchu)){
                                            if(graczWRuchu == 'G'){
                                                graczWRuchu = 'R';

                                                pionWRuchu[0] = 9;
                                                pionWRuchu[1] = 9;
                                            } else {
                                                graczWRuchu = 'G';
                                                pionWRuchu[0] = 9;
                                                pionWRuchu[1] = 9;

                                            }
                                            generuj(Grid);
                                        } else {
                                            generuj(Grid);
                                        }
                                    }


                                    return true;
                                }
                            }
                    );
                    if(Pionki.tablicaPionow[i][j] == 'R'){
                        pionek.setImageDrawable(koloCzarne);
                        pionek.setTag("pionekCzerwony" + j + i);
                        pole.addView(pionek);
                    } else if (Pionki.tablicaPionow[i][j] == 'G') {
                        pionek.setImageDrawable(koloBiale);
                        pionek.setTag("pionekZielony" + j + i);
                        pole.addView(pionek);
                    }
                }
                Grid.addView(pole,  i % 8, i / 8);
                pole.getLayoutParams().width=50;
                pole.getLayoutParams().height=50;

                for (int l = 0; l < 64; l++){
                    FrameLayout  pole2 = (FrameLayout) Grid.getChildAt(i);
                    if(pole.getChildCount() > 0){
                        ImageView pionek2 = (ImageView) pole.getChildAt(0);
                        pionek.setOnClickListener(
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if(Pionki.tablicaPionow[pole.getTop() / 50][pole.getRight() / 50 - 1] == graczWRuchu ){
                                            pionWRuchu[0] = pole.getRight() / 50 - 1;
                                            pionWRuchu[1] = pole.getTop() / 50;
                                        }
                                        generuj(Grid);

                                    }
                                }
                        );
                    }
                }




            }

        }};


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout Grid = findViewById(R.id.grid);


        this.generuj(Grid);


        UsunPion = findViewById(R.id.usunPion);
        UsunPion.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(pionWRuchu[0] < 9 && pionWRuchu[1] < 9){
                            Pionki.tablicaPionow[pionWRuchu[1]][pionWRuchu[0]] = 'E';
                            generuj(Grid);
                        }
                    }
                }
        );


    }


}
