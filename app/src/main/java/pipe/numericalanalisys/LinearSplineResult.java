package pipe.numericalanalisys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LinearSplineResult extends AppCompatActivity {

    TextView textViewPoints;
    TextView textViewSolutions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_spline_result);
        setTitle("Linear Spline");

        textViewPoints = (TextView)findViewById(R.id.textView225);
        textViewSolutions = (TextView)findViewById(R.id.textView227);

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

            int n = x.length-1;

            double []m = new double[n];
            String []f = new String[n];
            double []b = new double[n];

            for(int i=0;i<n;i++){

                m[i] = (Double.parseDouble(y[i+1])-Double.parseDouble(y[i]))/(Double.parseDouble(x[i+1])-Double.parseDouble(x[i]));

                b[i] = Double.parseDouble(y[i]) - (Double.parseDouble(x[i])*m[i]);

                f[i] = String.valueOf(m[i]) + "x + " + b[i];

            }

            textViewSolutions.append("\n");
            textViewSolutions.append("p(x)= ");

            for(int i=0;i<n;i++){

                textViewSolutions.append(f[i] + "\t\t\t" + "when" + "\t\t\t" + x[i] + " <= x <= " + x[i+1] + "\n\t\t\t\t");

            }

            textViewSolutions.append("\n\n");
            double result = 0;

            for(int i=0;i<n;i++){

                if(Double.parseDouble(pointToInterpolate)>=Double.parseDouble(x[i]) && Double.parseDouble(pointToInterpolate)<=Double.parseDouble(x[i+1])){

                    result = m[i]*Double.parseDouble(pointToInterpolate)+b[i];
                    textViewSolutions.append("p(" + pointToInterpolate + ")= " + result);
                    break;
                }

            }

            if(result == 0){
                textViewSolutions.append("The point " + pointToInterpolate + " is not within any of the intervals");
            }

        }
    }
}
