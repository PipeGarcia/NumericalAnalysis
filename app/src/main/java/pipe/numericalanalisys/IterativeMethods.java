package pipe.numericalanalisys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class IterativeMethods extends AppCompatActivity {

    String A, B, X, T, D, I;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iterative_methods);
        setTitle("Iterative Methods");

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

        findViewById(R.id.buttonJACOBI).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(IterativeMethods.this, JacobiResult.class);
                i.putExtra("matrixA", A);
                i.putExtra("vectorB", B);
                i.putExtra("matrixX", X);
                i.putExtra("tolerance", T);
                i.putExtra("delta", D);
                i.putExtra("iterations", I);
                startActivityForResult(i, 39);

            }
        });

        findViewById(R.id.buttonGAUSSSEIDEL).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(IterativeMethods.this, GaussSeidelResult.class);
                i.putExtra("matrixA", A);
                i.putExtra("vectorB", B);
                i.putExtra("matrixX", X);
                i.putExtra("tolerance", T);
                i.putExtra("delta", D);
                i.putExtra("iterations", I);
                startActivityForResult(i, 40);

            }
        });

        findViewById(R.id.buttonRICHARDSON).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(IterativeMethods.this, RichardsonResult.class);
                i.putExtra("matrixA", A);
                i.putExtra("vectorB", B);
                i.putExtra("matrixX", X);
                i.putExtra("tolerance", T);
                i.putExtra("delta", D);
                i.putExtra("iterations", I);
                startActivityForResult(i, 41);

            }
        });

        findViewById(R.id.buttonSOR).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(IterativeMethods.this, SorParameters.class);
                i.putExtra("matrixA", A);
                i.putExtra("vectorB", B);
                i.putExtra("matrixX", X);
                i.putExtra("tolerance", T);
                i.putExtra("delta", D);
                i.putExtra("iterations", I);
                startActivityForResult(i, 42);

            }
        });
    }
}
