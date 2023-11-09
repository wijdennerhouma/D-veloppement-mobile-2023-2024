package com.example.app1_mesure_glecymie;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;


public class MainActivity extends AppCompatActivity
{
    private TextView tvage , tvresultat;
    private SeekBar sbage ;
    private RadioButton rboui ;
    private RadioButton rbnon ;

    private EditText etvaleur;

    private Button btnConsulter ;

    private void init()
    {
        etvaleur = (EditText) findViewById(R.id.etvaleur);
        sbage = (SeekBar) findViewById(R.id.sbage);
        tvage = (TextView)findViewById(R.id.tvage);
        rboui = (RadioButton) findViewById(R.id.rboui);
        rbnon = (RadioButton) findViewById(R.id.rbnon);
        btnConsulter=(Button) findViewById(R.id.btConsulter);
        tvresultat = (TextView) findViewById(R.id.tvresultat);
    }

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        sbage.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("Information", "onProgressChanged " + progress);
                tvage.setText("Votre âge : " + progress);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        btnConsulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                calculer(v);
            }

            private void calculer(View v)
            {
                int age ;
                float valeur;
                boolean verifAge = false , verifValeur = false ;
                if(sbage.getProgress()!=0)
                    verifAge= true ;
                else
                    Toast.makeText(MainActivity.this,"Veuillez verifier votre age",Toast.LENGTH_SHORT).show();
                if(!etvaleur.getText().toString().isEmpty())
                    verifValeur=true;
                else
                    Toast.makeText(MainActivity.this, "Veuillez verifier votre valeur mesure", Toast.LENGTH_LONG).show();
                if(verifAge && verifValeur)
                {
                    String contenuTexte = etvaleur.getText().toString();
                    double niveauGlycemie = Double.parseDouble(contenuTexte);

                    int contenuAge = sbage.getProgress();

                    boolean estAJean = rboui.isChecked();

                    String message;

                    if (estAJean) {
                        if (contenuAge >= 13) {
                            if (niveauGlycemie < 5.0)
                                message = "Le niveau de glycémie est bas avant le repas ";
                            else if (niveauGlycemie >= 5.0 && niveauGlycemie <= 7.2)
                                message = "Le niveau de glycémie est nomal avant le repas ";
                            else
                                message = "Le niveau de glycémie est élevé avant le repas";
                        } else if (contenuAge >= 6 && contenuAge <= 12)
                            if (niveauGlycemie < 5.0)
                                message = "Le niveau de glycémie est bas avant le repas";
                            else if (niveauGlycemie >= 5.0 && niveauGlycemie <= 10.0)
                                message = "Le niveau de glycémie est normal avant le repas";
                            else
                                message = "Le niveau de glycémie est élevé avant le repas";
                        else if (niveauGlycemie < 5.5)
                            message = "Le niveau de glycémie est bas avant le repas";
                        else if (niveauGlycemie >= 5.0 && niveauGlycemie <= 10.0)
                            message = "Le niveau de glycémie est normal avant le repas";
                        else
                            message = "Le niveau de glycémie est élevé avant le repas";
                    }
                    else
                    if(niveauGlycemie<10.5)
                        message="Le niveau de glycémie est normal apres le repas";
                    else
                        message="Le niveau de glycémie est élevé apres le repas";
                    tvresultat.setText(message);
                }
            }
        });


    }
}

