package pipe.numericalanalisys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;

public class GaussElimParameters extends AppCompatActivity {

    TableLayout matrixinput;
    TableLayout vectorbinput;
    int size;
    EditText editTextsize;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gauss_elim_parameters);
        setTitle("Gauss Elimination");
        /*inicializado la entrada de la matriz(tamaño), además de las tablas donde se
          mostraran la matriz y el vector B
        */
        editTextsize = (EditText) findViewById(R.id.matrix_size);
        matrixinput = (TableLayout) findViewById(R.id.matrixA);
        vectorbinput = (TableLayout) findViewById(R.id.VectorB);
        final Button btnCalculate = (Button) findViewById(R.id.b_calculate);

        findViewById(R.id.b_generate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //generar la matriz


            }
        });
    }
}
