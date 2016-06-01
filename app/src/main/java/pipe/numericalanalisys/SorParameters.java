package pipe.numericalanalisys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SorParameters extends AppCompatActivity {

    EditText editTextLambda;

    String A, B, X, T, D, I;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sor_parameters);

        editTextLambda = (EditText) findViewById(R.id.editText26);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {

            String matA = extras.getString("matrixA");
            String vecB = extras.getString("vectorB");
            String matX = extras.getString("matrixX");
            String tol = extras.getString("tolerance");
            String del = extras.getString("delta");
            String iter = extras.getString("iterations");

            A = matA;
            B = vecB;
            X = matX;
            T = tol;
            D = del;
            I = iter;

        }

        findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String L = editTextLambda.getText().toString();

                Intent i = new Intent(SorParameters.this, SorResult.class);
                i.putExtra("matrixA", A);
                i.putExtra("vectorB", B);
                i.putExtra("matrixX", X);
                i.putExtra("tolerance", T);
                i.putExtra("delta", D);
                i.putExtra("iterations", I);
                i.putExtra("lambda", L);
                startActivityForResult(i, 43);

            }
        });
    }
}
