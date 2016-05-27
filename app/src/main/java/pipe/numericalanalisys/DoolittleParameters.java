package pipe.numericalanalisys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DoolittleParameters extends AppCompatActivity {

    EditText editTextMatrixA;
    EditText editTextVectorB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doolittle_parameters);
        setTitle("Doolittle");

        editTextMatrixA = (EditText) findViewById(R.id.editText6);
        editTextVectorB = (EditText) findViewById(R.id.editText10);

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String A = editTextMatrixA.getText().toString();
                String B = editTextVectorB.getText().toString();

                Intent i = new Intent(DoolittleParameters.this, DoolittleResult.class);
                i.putExtra("matrixA", A);
                i.putExtra("vectorB", B);
                startActivityForResult(i, 25);

            }
        });
    }
}
