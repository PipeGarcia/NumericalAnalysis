package pipe.numericalanalisys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayList;

public class FalsePositionResult extends AppCompatActivity {

    private ArrayAdapter<String> adapterN;
    private ArrayList<String> arrayListN;

    private ArrayAdapter<String> adapterA;
    private ArrayList<String> arrayListA;

    private ArrayAdapter<String> adapterFA;
    private ArrayList<String> arrayListFA;

    private ArrayAdapter<String> adapterB;
    private ArrayList<String> arrayListB;

    private ArrayAdapter<String> adapterFB;
    private ArrayList<String> arrayListFB;

    private ArrayAdapter<String> adapterX;
    private ArrayList<String> arrayListX;

    private ArrayAdapter<String> adapterFX;
    private ArrayList<String> arrayListFX;

    private ArrayAdapter<String> adapterError;
    private ArrayList<String> arrayListError;

    private ListView listViewN;
    private ListView listViewA;
    private ListView listViewFA;
    private ListView listViewB;
    private ListView listViewFB;
    private ListView listViewX;
    private ListView listViewFX;
    private ListView listViewError;

    private double fa;
    private double fb;
    private double x;
    private double fx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_false_position_result);

        arrayListN = new ArrayList<String>();
        adapterN = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayListN);

        arrayListA = new ArrayList<String>();
        adapterA = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayListA);

        arrayListFA = new ArrayList<String>();
        adapterFA = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayListFA);

        arrayListB = new ArrayList<String>();
        adapterB = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayListB);

        arrayListFB = new ArrayList<String>();
        adapterFB = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayListFB);

        arrayListX = new ArrayList<String>();
        adapterX = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayListX);

        arrayListFX = new ArrayList<String>();
        adapterFX = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayListFX);

        arrayListError = new ArrayList<String>();
        adapterError = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayListError);

        listViewN = (ListView)findViewById(R.id.listViewN);
        listViewA = (ListView)findViewById(R.id.listViewA);
        listViewFA = (ListView)findViewById(R.id.listViewFA);
        listViewB = (ListView)findViewById(R.id.listViewB);
        listViewFB = (ListView)findViewById(R.id.listViewFB);
        listViewX = (ListView)findViewById(R.id.listViewX);
        listViewFX = (ListView)findViewById(R.id.listViewFX);
        listViewError = (ListView)findViewById(R.id.listViewError);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            String func = extras.getString("vfun");
            double a = Double.parseDouble(extras.getString("va"));
            arrayListA.add(String.valueOf(a));
            double b = Double.parseDouble(extras.getString("vb"));
            arrayListB.add(String.valueOf(b));
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
            fa = resultFA;
            arrayListFA.add(String.valueOf(fa));

            //Para encontrar f(b)
            Expression exprFB = new ExpressionBuilder(func)
                    .variables("x", "e", "π")
                    .build()
                    .setVariable("x", b)
                    .setVariable("e", Math.E) //2.71828182846
                    .setVariable("π", Math.PI); //3.14159265359
            double resultFB = exprFB.evaluate();
            fb = resultFB;
            arrayListFB.add(String.valueOf(fb));

            x = b - (fb*(b-a)/(fb-fa));
            arrayListX.add(String.valueOf(x));
            double error = tolerancia + 1; //revisar machetazo

            //Para encontrar f(x)
            Expression expr2 = new ExpressionBuilder(func)
                    .variables("x", "e", "π")
                    .build()
                    .setVariable("x", x)
                    .setVariable("e", Math.E) //2.71828182846
                    .setVariable("π", Math.PI); //3.14159265359
            double resultFX = expr2.evaluate();
            fx = resultFX;
            arrayListFX.add(String.valueOf(fx));

            int cont = 0;
            int contN = 1;
            arrayListN.add(String.valueOf(contN));

            if(fa*fx < 0){
                b = x;
            }else{
                a= x;
            }

            for(double i= 2;i<=iteraciones && Math.abs(fx) > delta && error > tolerancia;i++) {

                if(fa*fx < 0){
                    b = x;
                }else{
                    a= x;
                }

                arrayListB.add(String.valueOf(b));
                arrayListA.add(String.valueOf(a));

                Expression expr3 = new ExpressionBuilder(func)
                        .variables("x", "e", "π")
                        .build()
                        .setVariable("x", a)
                        .setVariable("e", Math.E) //2.71828182846
                        .setVariable("π", Math.PI); //3.14159265359
                double resultFA3 = expr3.evaluate();
                fa = resultFA3;

                Expression exprfb = new ExpressionBuilder(func)
                        .variables("x", "e", "π")
                        .build()
                        .setVariable("x", b)
                        .setVariable("e", Math.E) //2.71828182846
                        .setVariable("π", Math.PI); //3.14159265359
                double resultfb = exprfb.evaluate();
                fb = resultfb;

                x= b - (fb*(b-a)/(fb-fa));
                cont++;
                arrayListX.add(String.valueOf(x));

                //Para encontrar f(x)
                Expression expr4 = new ExpressionBuilder(func)
                        .variables("x", "e", "π")
                        .build()
                        .setVariable("x", x)
                        .setVariable("e", Math.E) //2.71828182846
                        .setVariable("π", Math.PI); //3.14159265359
                double resultFX4 = expr4.evaluate();
                fx = resultFX4;

                fa = expr3.evaluate();
                fx = expr4.evaluate();
                fb = exprfb.evaluate();

                arrayListFA.add(String.valueOf(fa));
                arrayListFX.add(String.valueOf(fx));
                arrayListFB.add(String.valueOf(fb));

                error = Math.abs(Double.parseDouble(arrayListX.get(cont)) - Double.parseDouble(arrayListX.get(cont - 1)));
                arrayListError.add(String.valueOf(error));

                contN++;
                arrayListN.add(String.valueOf(contN));

                adapterN.notifyDataSetChanged();
                adapterA.notifyDataSetChanged();
                adapterFA.notifyDataSetChanged();
                adapterB.notifyDataSetChanged();
                adapterFB.notifyDataSetChanged();
                adapterX.notifyDataSetChanged();
                adapterFX.notifyDataSetChanged();
                adapterError.notifyDataSetChanged();

                listViewN.setAdapter(adapterN);
                listViewA.setAdapter(adapterA);
                listViewFA.setAdapter(adapterFA);
                listViewB.setAdapter(adapterB);
                listViewFB.setAdapter(adapterFB);
                listViewX.setAdapter(adapterX);
                listViewFX.setAdapter(adapterFX);
                listViewError.setAdapter(adapterError);
            }
            //textView.setText(String.valueOf(fx));

        }
    }
}
