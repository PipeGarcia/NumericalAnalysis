package pipe.numericalanalisys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CroutParameters extends AppCompatActivity {

    EditText editTextMatrixA;
    EditText editTextVectorB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crout_parameters);
        setTitle("Crout");

        editTextMatrixA = (EditText) findViewById(R.id.editText5);
        editTextVectorB = (EditText) findViewById(R.id.editText7);

        findViewById(R.id.b_gen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String A = editTextMatrixA.getText().toString();
                String B = editTextVectorB.getText().toString();

                Intent i = new Intent(CroutParameters.this, CroutResult.class);
                i.putExtra("matrixA", A);
                i.putExtra("vectorB", B);
                startActivityForResult(i, 23);

            }
        });


    }
}
