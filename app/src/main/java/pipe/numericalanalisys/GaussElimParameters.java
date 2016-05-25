package pipe.numericalanalisys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;

public class GaussElimParameters extends AppCompatActivity {

    TableLayout matrixinput;
    TableLayout vectorbinput;
    int vsize;
    EditText editTextMatrixA;
    EditText editTextVectorB;


    //private JTextArea result;

    protected void onCreate(Bundle savedInstanceState) {
    //public View onCreateView(LayoutInflater inflater, ViewGroup container,
      //                       Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gauss_elim_parameters);
        setTitle("Gauss Elimination");
        /*inicializado la entrada de la matriz(tamaño), además de las tablas donde se
          mostraran la matriz y el vector B
        */
        editTextMatrixA = (EditText) findViewById(R.id.matrixA);
        editTextVectorB = (EditText) findViewById(R.id.vectorB);
        //matrixinput = (TableLayout) findViewById(R.id.matrixA);
        //vectorbinput = (TableLayout) findViewById(R.id.VectorB);
        //final Button btnCalculate = (Button) findViewById(R.id.b_calculate);
        findViewById(R.id.b_solve).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           /* matrixinput.removeAllViews();
            vectorbinput.removeAllViews();
            String size = editTextsize.getText().toString();
            vsize = Integer.parseInt(size);*/

                String A = editTextMatrixA.getText().toString();
                String B = editTextVectorB.getText().toString();

                Intent i = new Intent(GaussElimParameters.this, GaussElimResult.class);
                i.putExtra("matrixA", A);
                i.putExtra("vectorB", B);
                startActivityForResult(i, 20);

            }
        });
    }
}