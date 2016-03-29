package fr.univ_lille1.iut_info.bernardt.xbattlecompanionapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by mager on 25/03/2016.
 */
public class TutoActivity extends AppCompatActivity {

    /*

    AJOUTER CLIGUETEMENT APRES SWIPE 


     */

    private int onglet_tuto;

    private ImageView hillView;
    private int[] hills={R.drawable.hill1,R.drawable.hill2,R.drawable.hill3};

    private ImageView waterView;
    private int[] waters={R.drawable.water1,R.drawable.water2,R.drawable.water3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial_tab1);

        hillView=(ImageView)findViewById(R.id.hillView);
        waterView=(ImageView)findViewById(R.id.waterView);

        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int i=0;
            public void run() {
                hillView.setImageResource(hills[i]);
                waterView.setImageResource(waters[i]);
                i++;
                if(i>hills.length-1) {
                    i=0;
                }
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(runnable, 0);

        addSwipeListener(R.id.layout_tuto1);

        onglet_tuto = 1;
    }

    public void addSwipeListener(int idLayout){
        findViewById(idLayout).setOnTouchListener(new OnSwipeTouchListener(TutoActivity.this) {
            public void onSwipeRight() {
                if (onglet_tuto > 1) {
                    onglet_tuto--;
                    changeTab(onglet_tuto);
                } else {
                    changeTab(3);
                }
            }

            public void onSwipeLeft() {
                if (onglet_tuto < 3) {
                    onglet_tuto++;
                    changeTab(onglet_tuto);
                } else {
                    changeTab(1);
                }
            }

            public void onSwipeBottom() {
                // changeContentView(R.layout.menu);
                finish();
            }
        });
    }

    public void changeTab(int iTab){
        onglet_tuto = iTab;

        switch (iTab){
            case 1:
                setContentView(R.layout.tutorial_tab1);
                addSwipeListener(R.id.layout_tuto1);
                break;
            case 2:
                setContentView(R.layout.tutorial_tab2);
                addSwipeListener(R.id.layout_tuto2);
                break;
            case 3:

                break;
        }
    }

    public void onClick_Title(View view){
        switch(view.getId()){
            case R.id.pres_title1:
                changeTab(1);
                break;
            case R.id.pres_title2:
                changeTab(2);
                break;
            case R.id.pres_title3:
                changeTab(3);
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("onglet_tuto", onglet_tuto);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        onglet_tuto = savedInstanceState.getInt("onglet_tuto");
        changeTab(onglet_tuto);
    }
}