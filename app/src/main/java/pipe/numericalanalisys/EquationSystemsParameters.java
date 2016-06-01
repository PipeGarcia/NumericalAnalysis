package pipe.numericalanalisys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EquationSystemsParameters extends AppCompatActivity {

    EditText editTextMatrixA;
    EditText editTextVectorB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equation_systems_parameters);

        editTextMatrixA = (EditText) findViewById(R.id.editText23);
        editTextVectorB = (EditText) findViewById(R.id.editText24);

        findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String A = editTextMatrixA.getText().toString();
                String B = editTextVectorB.getText().toString();

                Intent i = new Intent(EquationSystemsParameters.this, eqsystemsActivity.class);
                i.putExtra("matrixA", A);
                i.putExtra("vectorB", B);
                startActivityForResult(i, 28);

            }
        });
    }
}
