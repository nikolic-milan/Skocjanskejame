package android.parkskocjanskejame;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.parkskocjanskejame.utils.Constants;
import android.parkskocjanskejame.utils.RateView;
import android.parkskocjanskejame.utils.RateViewListener;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import static android.parkskocjanskejame.R.string.gumbZnova;
import static android.parkskocjanskejame.R.string.naprej;


public class Tabla7 extends AppCompatActivity implements RateViewListener {

    //Client side variables
    RateView rateView;
    AlertDialog alert;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabla7);

        Constants.status++;

       /* //Setup the number font
        font = Typeface.createFromAsset(getAssets(), "dinFont.ttf");
        confidence = (TextView)findViewById(R.id.confidence);
        confidence.setTypeface(font);*/

        //Setup event listener for drawing
        rateView = (RateView) findViewById(R.id.rateView);
        rateView.setCustomEventListener(this);
    }

    @Override
    public void onMove(int movedX, int maxHeight) {

    }

    @Override
    public void onSwipe(boolean prav) {
        /*
        AlertDialog.Builder aD = new AlertDialog.Builder(Tabla7.this);
        LayoutInflater inf = getLayoutInflater();
        View v = inf.inflate(R.layout.tabla3apopup, null);
        aD.setView(v);
        alert = aD.create();
        alert.setCancelable(false);
        alert.show();
        */

        final Dialog alertDialog = new Dialog(this, R.style.AppTheme);
        alertDialog.setContentView(R.layout.tabla3apopup2);
        alertDialog.setCancelable(false);
        alertDialog.show();

        TextView text = (TextView) alertDialog.findViewById(R.id.textView23);
        text.setText(R.string.tabla7razlaga);
        final ImageView tabla = (ImageView) alertDialog.findViewById(R.id.imageTabla3a2);
        tabla.setImageResource(R.drawable.tabla7slika1);

        /*
        TextView text = (TextView) v.findViewById(R.id.textView23);
        text.setText(R.string.tabla7razlaga);
        final ImageView tabla = (ImageView) v.findViewById(R.id.imageTabla3a);
        tabla.setImageResource(R.drawable.tabla7slika1);
        */

        if (prav) {
            //Retry button
            Intent serviceIntent= new Intent(Tabla7.this, Tracking.class);
            serviceIntent.putExtra("question_tabla7_false", 1);
            startService(serviceIntent);
            Button continueButton = (Button) alertDialog.findViewById(R.id.button2345);
            continueButton.setText(gumbZnova);
            continueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.cancel();
                }
            });
        } else {
            //Continue button
            Intent serviceIntent= new Intent(Tabla7.this, Tracking.class);
            serviceIntent.putExtra("question_tabla7_true", 1);
            startService(serviceIntent);
            Button continueButton = (Button) alertDialog.findViewById(R.id.button2345);
            continueButton.setText(naprej);
            continueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), Cestitamo.class);
                    startActivity(intent);
                    finish();
                }
            });

        }

        tabla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ImageFS.class);
                intent.putExtra("image", 7);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
    }
}


