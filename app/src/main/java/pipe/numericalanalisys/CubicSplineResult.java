package pipe.numericalanalisys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class CubicSplineResult extends AppCompatActivity {

    TextView textViewPoints;
    TextView textViewSolutions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cubic_result);
        setTitle("Cubic Spline");

        textViewPoints = (TextView)findViewById(R.id.textView239);
        textViewSolutions = (TextView)findViewById(R.id.textView241);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {

            String vecX = extras.getString("x");
            String vecFX = extras.getString("fx");
            String pointToInterpolate = extras.getString("point");

            String x[] = vecX.split(";");
            String y[] = vecFX.split(";");

            int tamaño = x.length;

            for (int i = 0; i < tamaño; i++) {
                for (int j = 0; j < tamaño-1; j++) {
                    if(Double.parseDouble(x[j])>Double.parseDouble(x[j+1])){
                        String mayor = x[j];
                        String auxFX = y[j];
                        x[j]=x[j+1];
                        y[j]=y[j+1];
                        x[j+1]=mayor;
                        y[j+1]=auxFX;
                    }
                }
            }

            for (int i = 0; i < tamaño; i++) {

                textViewPoints.append("\n");
                textViewPoints.append((i+1) + ". " + "(" + x[i] + "," + y[i] + ")" + "\n");

            }

            int n = x.length;
            String []fn = new String[n];
            String []fn1 = new String[n];

            for(int i=0;i<n-1;i++){

                fn[i] = Math.pow(Double.parseDouble(x[i]),3) + "a" + (i+1) + "+" + Math.pow(Double.parseDouble(x[i]),2) + "b" + (i+1) + "+" + Double.parseDouble(x[i]) + "c" + (i+1) + "+" +"d" + (i+1);
                fn1[i] = Math.pow(Double.parseDouble(x[i+1]),3) + "a" + (i+1) + "+" + Math.pow(Double.parseDouble(x[i+1]),2) + "b" + (i+1) + "+" + Double.parseDouble(x[i+1]) + "c" + (i+1) + "+" +"d" + (i+1);

                textViewSolutions.append(fn[i] + " = " + y[i] + "\n");
                textViewSolutions.append(fn1[i] + " = " + y[i+1] + "\n");

            }

            textViewSolutions.append("\n");

            for(int i=0;i<n-2;i++){

                fn[i] = 3*Math.pow(Double.parseDouble(x[i+1]),2) + "a" + (i+1) + "+" + 2*Double.parseDouble(x[i+1]) + "b" + (i+1) + "+" + "c" + (i+1);
                fn1[i] = 3*Math.pow(Double.parseDouble(x[i+1]),2) + "a" + (i+2) + "+" + 2*Double.parseDouble(x[i+1]) + "b" + (i+2) + "+" + "c" + (i+2);

                textViewSolutions.append(fn[i] + " = " + fn1[i] + "\n");

            }

            textViewSolutions.append("\n");

            for(int i=0;i<n-2;i++){

                fn[i] = 6*Double.parseDouble(x[i+1]) + "a" + (i+1) + "+" + "2b" + (i+1);
                fn1[i] = 6*Double.parseDouble(x[i+1]) + "a" +(i+2) + "+" + "2b" + (i+2);

                textViewSolutions.append(fn[i] + " = " + fn1[i] + "\n");

            }

            textViewSolutions.append("\n");

            //Ecuaciones Naturales

            fn[0] = 6*Double.parseDouble(x[0]) + "a1 + 2b1";
            textViewSolutions.append(fn[0] + " = 0" + "\n");
            fn[1] = 6*Double.parseDouble(x[n-1]) + "a" + (n-1) + " + 2b" + (n-1);
            textViewSolutions.append(fn[1] + " = 0" + "\n");

            /*Fn =  6a1X[1] + 2b1
            Imprimir fn  + “ = “ + 0  // Fn  se debe imprimir como String
            Fn =  6anX[n] + 2bn
            Imprimir fn  + “ = “ + 0  // Fn  se debe imprimir como String*/







        }

    }
}
