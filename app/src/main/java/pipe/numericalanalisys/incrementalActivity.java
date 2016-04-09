package pipe.numericalanalisys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class incrementalActivity extends AppCompatActivity {

    private String funcion;

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;
    private ArrayList<String> arrayListFuncResult;
    private ListView lv;

    //Aquí se mostraran los intervalos donde hay raices
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incremental);

        lv = (ListView) findViewById(R.id.listViewDatos);
        arrayList = new ArrayList<String>();
        arrayListFuncResult = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);

        //Se reciben los parametros que se detectaron en los campos
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            int raices = 0;
            String cont_fun   = extras.getString("vfun");
            setFuncion(cont_fun);

            TextView tv = (TextView) findViewById(R.id.textView20);
            tv.setText(String.valueOf(getFuncion()));

            double cont_xini  = Double.parseDouble(extras.getString("vxinf"));
            double cont_delta  = Double.parseDouble(extras.getString("vdelta"));
            double cont_xsup  = Double.parseDouble(extras.getString("vxsup"));
            int cont = -1;


            //while (cont_xinf<=cont_xsup) {
                for(double i= cont_xini;i<=(cont_xsup);i = i + cont_delta) {
                    Expression expr = new ExpressionBuilder(cont_fun)
                            .variables("x", "e", "π")
                            .build()
                            .setVariable("x", i)
                            .setVariable("e", Math.E) //2.71828182846
                            .setVariable("π", Math.PI); //3.14159265359
                    double result = expr.evaluate();
                    arrayListFuncResult.add(String.valueOf(result));
                    cont = cont + 1;

                    if(cont < 1){
                        continue;
                    }else if(Double.parseDouble(arrayListFuncResult.get(cont-1)) < 0 && Double.parseDouble(arrayListFuncResult.get(cont)) >= 0){
                        arrayList.add(String.valueOf(i-1 + "," + i));
                    }else if(Double.parseDouble(arrayListFuncResult.get(cont-1)) >= 0 && Double.parseDouble(arrayListFuncResult.get(cont)) < 0){
                        arrayList.add(String.valueOf(i-1 + "," + i));
                    }

                    if(arrayList.isEmpty()){
                        continue;
                    }else {

                        //arrayList.add(String.valueOf(i + "hola"));

                /*double showres = i;
                TextView tv = (TextView) findViewById(R.id.textView20);
                tv.setText(String.valueOf(showres));*/


                        adapter.notifyDataSetChanged();

                        lv.setAdapter(adapter);
                    }
                }
            }

        findViewById(R.id.go_methods).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(incrementalActivity.this, Main3Activity.class);
                i.putExtra("funcion1", getFuncion());
                //i.putExtra("vdelta", delta);
                startActivityForResult(i,2);

                //startActivity(new Intent(incrementalActivity.this, Main3Activity.class));
            }
        });


    }

}
