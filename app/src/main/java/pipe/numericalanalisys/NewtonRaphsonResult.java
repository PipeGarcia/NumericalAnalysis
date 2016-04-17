package pipe.numericalanalisys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayList;

public class NewtonRaphsonResult extends AppCompatActivity {

    private ArrayAdapter<String> adapterN;
    private ArrayList<String> arrayListN;

    private ArrayAdapter<String> adapterX;
    private ArrayList<String> arrayListX;

    private ArrayAdapter<String> adapterFX;
    private ArrayList<String> arrayListFX;

    private ArrayAdapter<String> adapterF1X;
    private ArrayList<String> arrayListF1X;

    private ArrayAdapter<String> adapterERROR;
    private ArrayList<String> arrayListERROR;

    private ListView listViewN;
    private ListView listViewX;
    private ListView listViewFX;
    private ListView listViewF1X;
    private ListView listViewERROR;

    private double fx;
    private double f1x;
    //private double x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newton_raphson_result);

        arrayListN = new ArrayList<String>();
        adapterN = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayListN);

        arrayListX = new ArrayList<String>();
        adapterX = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayListX);

        arrayListFX = new ArrayList<String>();
        adapterFX = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayListFX);

        arrayListF1X = new ArrayList<String>();
        adapterF1X = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayListF1X);

        arrayListERROR = new ArrayList<String>();
        adapterERROR = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayListERROR);

        listViewN = (ListView)findViewById(R.id.listViewN);
        listViewX = (ListView)findViewById(R.id.listViewX);
        listViewFX = (ListView)findViewById(R.id.listViewFX);
        listViewF1X = (ListView)findViewById(R.id.listViewF1X);
        listViewERROR = (ListView)findViewById(R.id.listViewERROR);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            String func = extras.getString("vfun");
            String derivedFunc = extras.getString("vderfun");
            double x = Double.parseDouble(extras.getString("vx"));
            arrayListX.add(String.valueOf(x));
            double delta = Double.parseDouble(extras.getString("vdelta"));
            double tolerancia = Double.parseDouble(extras.getString("vtol"));
            int iteraciones = Integer.parseInt(extras.getString("viter"));

            //Para encontrar f(x)
            Expression expr = new ExpressionBuilder(func)
                    .variables("x", "e", "π")
                    .build()
                    .setVariable("x", x)
                    .setVariable("e", Math.E) //2.71828182846
                    .setVariable("π", Math.PI); //3.14159265359
            double resultFX = expr.evaluate();
            fx = resultFX;
            arrayListFX.add(String.valueOf(fx));

            //Para encontrar f'(x)
            Expression exprF1X = new ExpressionBuilder(derivedFunc)
                    .variables("x", "e", "π")
                    .build()
                    .setVariable("x", x)
                    .setVariable("e", Math.E) //2.71828182846
                    .setVariable("π", Math.PI); //3.14159265359
            double resultF1X = exprF1X.evaluate();
            f1x = resultF1X;
            arrayListF1X.add(String.valueOf(f1x));

            double error = tolerancia + 1; //revisar machetazo

            int cont = 0;
            int contN = 1;
            arrayListN.add(String.valueOf(contN));

            for(double i= 2;i<=iteraciones && Math.abs(fx) > delta && error > tolerancia;i++) {

                x = Double.parseDouble(arrayListX.get(cont)) - (Double.parseDouble(arrayListFX.get(cont))/
                        Double.parseDouble(arrayListF1X.get(cont)));

                arrayListX.add(String.valueOf(x));

                Expression exprFXnew = new ExpressionBuilder(func)
                        .variables("x", "e", "π")
                        .build()
                        .setVariable("x", x)
                        .setVariable("e", Math.E) //2.71828182846
                        .setVariable("π", Math.PI); //3.14159265359
                double resultFXnew = exprFXnew.evaluate();
                fx = resultFXnew;

                Expression exprF1Xnew = new ExpressionBuilder(derivedFunc)
                        .variables("x", "e", "π")
                        .build()
                        .setVariable("x", x)
                        .setVariable("e", Math.E) //2.71828182846
                        .setVariable("π", Math.PI); //3.14159265359
                double resultF1Xnew = exprF1Xnew.evaluate();
                f1x = resultF1Xnew;

                /*fa = expr3.evaluate();
                fx = expr4.evaluate();
                fb = exprfb.evaluate();*/

                arrayListFX.add(String.valueOf(fx));
                arrayListF1X.add(String.valueOf(f1x));

                cont++;

                error = Math.abs(Double.parseDouble(arrayListX.get(cont)) - Double.parseDouble(arrayListX.get(cont - 1)));
                arrayListERROR.add(String.valueOf(error));

                contN++;
                arrayListN.add(String.valueOf(contN));

                adapterN.notifyDataSetChanged();
                adapterX.notifyDataSetChanged();
                adapterFX.notifyDataSetChanged();
                adapterF1X.notifyDataSetChanged();
                adapterERROR.notifyDataSetChanged();

                listViewN.setAdapter(adapterN);
                listViewX.setAdapter(adapterX);
                listViewFX.setAdapter(adapterFX);
                listViewF1X.setAdapter(adapterF1X);
                listViewERROR.setAdapter(adapterERROR);
            }
            //textView.setText(String.valueOf(fx));

        }
    }
}
