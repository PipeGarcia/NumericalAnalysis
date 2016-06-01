package pipe.numericalanalisys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class IterativeMethodsParameters extends AppCompatActivity {

    EditText editTextMatrixA;
    EditText editTextVectorB;
    EditText editTextMatrixX;
    EditText editTextTolerance;
    EditText editTextDelta;
    EditText editTextIterations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iterative_methods_parameters);
        setTitle("Iterative Methods");

        editTextMatrixA = (EditText) findViewById(R.id.editText31);
        editTextVectorB = (EditText) findViewById(R.id.editText32);
        editTextMatrixX = (EditText) findViewById(R.id.editText33);
        editTextTolerance = (EditText) findViewById(R.id.editText34);
        editTextDelta = (EditText) findViewById(R.id.editText35);
        editTextIterations = (EditText) findViewById(R.id.editText25);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            String matA = extras.getString("matrixA");
            String vecB = extras.getString("vectorB");


            editTextMatrixA.setText(matA);
            editTextVectorB.setText(vecB);
        }

        findViewById(R.id.button7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String A = editTextMatrixA.getText().toString();
                String B = editTextVectorB.getText().toString();
                String X = editTextMatrixX.getText().toString();
                String T = editTextTolerance.getText().toString();
                String D = editTextDelta.getText().toString();
                String I = editTextIterations.getText().toString();

                Intent i = new Intent(IterativeMethodsParameters.this, IterativeMethods.class);
                i.putExtra("matrixA", A);
                i.putExtra("vectorB", B);
                i.putExtra("matrixX", X);
                i.putExtra("tolerance", T);
                i.putExtra("delta", D);
                i.putExtra("iterations", I);
                startActivityForResult(i, 38);

            }
        });
    }
}
