package pipe.numericalanalisys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayList;

public class Incremental_searches_parameters extends AppCompatActivity {

    private EditText editTextFunction;
    private EditText editTextxInf;
    private EditText editTextDelta;
    private EditText editTextxSup;
    private Button buttonSearch;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incremental_searches_parameters);
        setTitle("Initial Parameters");

        editTextFunction = (EditText) findViewById(R.id.editText);
        editTextxInf = (EditText) findViewById(R.id.editText4);
        editTextDelta = (EditText) findViewById(R.id.editText2);
        editTextxSup = (EditText) findViewById(R.id.editText3);
        buttonSearch = (Button) findViewById(R.id.buttonSearch);
        lv = (ListView) findViewById(R.id.listViewDatos);
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);



        findViewById(R.id.buttonSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String function = editTextFunction.getText().toString();
                String xInf = editTextxInf.getText().toString();
                String delta = editTextDelta.getText().toString();
                String xSup = editTextxSup.getText().toString();

                Intent i = new Intent(Incremental_searches_parameters.this, incrementalActivity.class);
                i.putExtra("vfun", function);
                i.putExtra("vxinf", xInf);
                i.putExtra("vdelta", delta);
                i.putExtra("vxsup", xSup);
                startActivityForResult(i,1);
                //startActivity(new Intent(Incremental_searches_parameters.this, incrementalActivity.class));

                    /*expr = new ExpressionBuilder(function)
                            .variables("x","e","π")
                            .build()
                            .setVariable("x", xIni)
                            .setVariable("e", 2.71828182846 )
                            .setVariable("π", 3.14159265359 );

                    setResult(expr.evaluate());
                String showres = String.valueOf(getResult());

                arrayList.add(showres);
                adapter.notifyDataSetChanged();

                lv.setAdapter(adapter);
                editTextFunction.setText(showres);*/

            }
        });
    }
}
