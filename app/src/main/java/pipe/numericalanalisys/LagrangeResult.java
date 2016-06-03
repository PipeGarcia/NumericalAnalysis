package pipe.numericalanalisys;

import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LagrangeResult extends AppCompatActivity {

    TextView textViewPoints;
    TextView textViewSolutions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lagrange_result);
        setTitle("Lagrange Polynomial");

        textViewPoints = (TextView)findViewById(R.id.textView220);
        textViewSolutions = (TextView)findViewById(R.id.textView222);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {

            String vecX = extras.getString("x");
            String vecFX = extras.getString("fx");
            String pointToInterpolate = extras.getString("point");

            String x[] = vecX.split(";");
            String fx[] = vecFX.split(";");


            int n = x.length;
            String[] Lx = new String[n];
            for (int i = 0; i < n; i++) {

                textViewPoints.append("\n");
                textViewPoints.append((i+1) + ". " + "(" + x[i] + "," + fx[i] + ")" + "\n");

            }
                double[] L = new double[n];

                double mult = 1;

                for(int i = 0; i < n; i++)
                {
                    for(int j = 0; j < n; j++)
                    {
                        if(i != j)
                        {
                            mult = (Double.parseDouble(pointToInterpolate) - Double.parseDouble(x[j]))/(Double.parseDouble(x[i]) - Double.parseDouble(x[j]));
                        }
                    }
                    L[i] = mult;
                //    mult = 1;
                }

                textViewSolutions.append("\n");

                for(int i = 0; i < n; i++)
                {
                    textViewSolutions.append("L" + i + "(" + pointToInterpolate + ")" + " = " + L[i] + "\n");
                }

                double pol = 0;

                for (int i = 0; i < n; i++)
                {
                    pol += L[i]*Double.parseDouble(fx[i]);
                }
                String ptotal = "";


            //L's
            try {
                for (int a = 0; a < n; a++) {
                    for (int b = 0; b < n; b++) {
                        if (b != a) {
                            if(b==0){Lx[a]="";}
                            Lx[a] += "[(x-" + x[b] + ")/(" + x[a] + "-" + x[b] + ")]";
                        }

                    }

                }

            }catch (Exception e){}

                for (int t = 0; t < n;t++){
                    if (t<(n-1)) {
                        ptotal += fx[t] + "(" + Lx[t] + ")" + "+";
                    }
                    else{
                        ptotal += fx[t] + "(" + Lx[t] + ")";
                    }
                }

                textViewSolutions.append("\n");
                textViewSolutions.append("P(x) = " + ptotal);
                textViewSolutions.append("\n");
                textViewSolutions.append("P(" + pointToInterpolate + ") = " + pol);

        }
    }
}
