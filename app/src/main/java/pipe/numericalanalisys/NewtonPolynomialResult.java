package pipe.numericalanalisys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class NewtonPolynomialResult extends AppCompatActivity {

    TextView textViewPoints;
    TextView textViewSolutions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newton_polynomial_result);
        setTitle("Newton Polynomial");

        textViewPoints = (TextView)findViewById(R.id.textView215);
        textViewSolutions = (TextView)findViewById(R.id.textView217);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {

            String vecX = extras.getString("x");
            String vecFX = extras.getString("fx");
            String pointToInterpolate = extras.getString("point");

            String x[] = vecX.split(";");
            String fx[] = vecFX.split(";");

            int n = x.length;

            for (int i = 0; i < n; i++) {

                textViewPoints.append("\n");
                textViewPoints.append((i+1) + ". " + "(" + x[i] + "," + fx[i] + ")" + "\n");

            }
                double mult;
                double[] vectFx = new double[n];


                for (int i = 0; i < n; i++)
                {
                    vectFx[i] = Double.parseDouble(fx[i]);
                }

            textViewSolutions.append("\n");

                for (int i = 0; i < n; i++)
                {
                    for (int j = n - 1; j > i; j--)
                    {
                        vectFx[j] = (vectFx[j - 1] - vectFx[j]) / (Double.parseDouble(x[j - 1 - i]) - Double.parseDouble(x[j]));
                    }
                    //result.append("b" + i + " = " + vectFx[i] + "\n");

                    textViewSolutions.append("b" + i + " = " + vectFx[i] + "\n");
                    //System.out.println("b" + i + "  = " + vectFx[i]);
                }
                mult = vectFx[n - 1];
                for (int i = n - 2; i >= 0; i--)
                {
                    mult = vectFx[i] + (Double.parseDouble(pointToInterpolate) - Double.parseDouble(x[i])) * mult;

                }

                //result.append("\n");
                textViewSolutions.append("\n");
                //result.append("P(" + puntoInterpolar + ") = " + mult);
                textViewSolutions.append("P(" + pointToInterpolate + ") = " + mult);
                //System.out.println("P("+ puntoInterpolar +")=" + mult);


        }
    }
}
