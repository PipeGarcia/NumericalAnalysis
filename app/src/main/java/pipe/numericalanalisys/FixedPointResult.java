package pipe.numericalanalisys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayList;

public class FixedPointResult extends AppCompatActivity {

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

    private double fx;
    private double gx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_point_result);

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
            String funcFX = extras.getString("vfunFX");
            String funcGX = extras.getString("vfunGX");
            double initialX = Double.parseDouble(extras.getString("vinitialx"));
            arrayListX.add(String.valueOf(initialX));
            double delta = Double.parseDouble(extras.getString("vdelta"));
            double tolerancia = Double.parseDouble(extras.getString("vtol"));
            int iteraciones = Integer.parseInt(extras.getString("viter"));

            //Para encontrar f(x)
            Expression expr = new ExpressionBuilder(funcFX)
                    .variables("x", "e", "π")
                    .build()
                    .setVariable("x", initialX)
                    .setVariable("e", Math.E) //2.71828182846
                    .setVariable("π", Math.PI); //3.14159265359
            double resultFX = expr.evaluate();
            fx = resultFX;
            arrayListFX.add(String.valueOf(fx));

            double error = tolerancia + 1; //revisar machetazo

            int cont = 0;
            int contN = 1;
            arrayListN.add(String.valueOf(contN));

            for(double i= 2;i<=iteraciones && Math.abs(fx) > delta && error > tolerancia;i++) {

                Expression exprGX = new ExpressionBuilder(funcGX)
                        .variables("x", "e", "π")
                        .build()
                        .setVariable("x", initialX)
                        .setVariable("e", Math.E) //2.71828182846
                        .setVariable("π", Math.PI); //3.14159265359
                double resultGX = exprGX.evaluate();
                gx = resultGX;
                initialX = gx;

                Expression exprNewFX = new ExpressionBuilder(funcFX)
                        .variables("x", "e", "π")
                        .build()
                        .setVariable("x", initialX)
                        .setVariable("e", Math.E) //2.71828182846
                        .setVariable("π", Math.PI); //3.14159265359
                double resultNewFX = exprNewFX.evaluate();
                fx = resultNewFX;

                cont++;

                //gx = exprGX.evaluate();
                //fx = exprNewFX.evaluate();

                arrayListFX.add(String.valueOf(fx));
                arrayListX.add(String.valueOf(gx));

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
