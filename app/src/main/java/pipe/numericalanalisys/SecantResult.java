package pipe.numericalanalisys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayList;

public class SecantResult extends AppCompatActivity {

    private ArrayAdapter<String> adapterN;
    private ArrayList<String> arrayListN;

    private ArrayAdapter<String> adapterX;
    private ArrayList<String> arrayListX;

    private ArrayAdapter<String> adapterFX;
    private ArrayList<String> arrayListFX;

    private ArrayAdapter<String> adapterERROR;
    private ArrayList<String> arrayListERROR;

    private ListView listViewN;
    private ListView listViewX;
    private ListView listViewFX;
    private ListView listViewERROR;

    private double x;
    private double fx;
    private double fa;
    private double fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secant_result);

        arrayListN = new ArrayList<String>();
        adapterN = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayListN);

        arrayListX = new ArrayList<String>();
        adapterX = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayListX);

        arrayListFX = new ArrayList<String>();
        adapterFX = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayListFX);

        arrayListERROR = new ArrayList<String>();
        adapterERROR = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayListERROR);

        listViewN = (ListView)findViewById(R.id.listViewN);
        listViewX = (ListView)findViewById(R.id.listViewX);
        listViewFX = (ListView)findViewById(R.id.listViewFX);
        listViewERROR = (ListView)findViewById(R.id.listViewERROR);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            String func = extras.getString("vfun");
            double a = Double.parseDouble(extras.getString("va"));
            arrayListX.add(String.valueOf(a));
            double b = Double.parseDouble(extras.getString("vb"));
            arrayListX.add(String.valueOf(b));
            double delta = Double.parseDouble(extras.getString("vdelta"));
            double tolerancia = Double.parseDouble(extras.getString("vtol"));
            int iteraciones = Integer.parseInt(extras.getString("viter"));

            //Para encontrar f(a)
            Expression expr = new ExpressionBuilder(func)
                    .variables("x", "e", "π")
                    .build()
                    .setVariable("x", a)
                    .setVariable("e", Math.E) //2.71828182846
                    .setVariable("π", Math.PI); //3.14159265359
            double resultFA = expr.evaluate();
            fx = resultFA;
            arrayListFX.add(String.valueOf(fx));

            //Para encontrar f(b)
            Expression exprFB = new ExpressionBuilder(func)
                    .variables("x", "e", "π")
                    .build()
                    .setVariable("x", b)
                    .setVariable("e", Math.E) //2.71828182846
                    .setVariable("π", Math.PI); //3.14159265359
            double resultFB = exprFB.evaluate();
            fx = resultFB;
            arrayListFX.add(String.valueOf(fx));

            double error = tolerancia + 1; //revisar machetazo

            int cont = 1;
            int contN = 0;
            //arrayListN.add(String.valueOf(contN));

            for(double i= 2;i<=iteraciones && Math.abs(fx) > delta && error > tolerancia;i++) {

                x = Double.parseDouble(arrayListX.get(cont)) - (Double.parseDouble(arrayListFX.get(cont))*
                                (Double.parseDouble(arrayListX.get(cont)) - Double.parseDouble(arrayListX.get(cont-1)))/
                                        (Double.parseDouble(arrayListFX.get(cont))-Double.parseDouble(arrayListFX.get(cont-1))));

                arrayListX.add(String.valueOf(x));

                Expression expr3 = new ExpressionBuilder(func)
                                .variables("x", "e", "π")
                                .build()
                                .setVariable("x", x)
                                .setVariable("e", Math.E) //2.71828182846
                                .setVariable("π", Math.PI); //3.14159265359
                double resultFXnew = expr3.evaluate();
                fx = resultFXnew;

                cont++;

                //fx = expr4.evaluate();

                arrayListFX.add(String.valueOf(fx));

                error = Math.abs(Double.parseDouble(arrayListX.get(cont)) - Double.parseDouble(arrayListX.get(cont - 1)));
                arrayListERROR.add(String.valueOf(error));

                contN++;
                arrayListN.add(String.valueOf(contN));

                adapterN.notifyDataSetChanged();
                adapterX.notifyDataSetChanged();
                adapterFX.notifyDataSetChanged();
                adapterERROR.notifyDataSetChanged();

                listViewN.setAdapter(adapterN);
                listViewX.setAdapter(adapterX);
                listViewFX.setAdapter(adapterFX);
                listViewERROR.setAdapter(adapterERROR);
            }
            //textView.setText(String.valueOf(fx));

        }
    }
}
