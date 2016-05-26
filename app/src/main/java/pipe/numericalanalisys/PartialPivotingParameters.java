package pipe.numericalanalisys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PartialPivotingParameters extends AppCompatActivity {

    EditText editTextMatrixA;
    EditText editTextVectorB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partial_pivoting_parameters);

        editTextMatrixA = (EditText) findViewById(R.id.editTextMatA);
        editTextVectorB = (EditText) findViewById(R.id.editTextVecB);

        findViewById(R.id.buttonSolve).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String A = editTextMatrixA.getText().toString();
                String B = editTextVectorB.getText().toString();

                Intent i = new Intent(PartialPivotingParameters.this, PartialPivotingResult.class);
                i.putExtra("matrixA", A);
                i.putExtra("vectorB", B);
                startActivityForResult(i, 21);

            }
        });
    }
}
