package pipe.numericalanalisys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class eqsystemsActivity extends AppCompatActivity {

    private String matrixA, vectorB;
    private Button buttonPartialPivoting, buttonTotalPivoting, buttonStaggeredPivoting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eqsystems);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buttonPartialPivoting = (Button) findViewById(R.id.b_partial_pivoting);
        buttonPartialPivoting.setVisibility(View.INVISIBLE);

        buttonTotalPivoting = (Button) findViewById(R.id.b_total_pivoting);
        buttonTotalPivoting.setVisibility(View.INVISIBLE);

        buttonStaggeredPivoting = (Button) findViewById(R.id.b_staggered_pivoting);
        buttonStaggeredPivoting.setVisibility(View.INVISIBLE);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {

            String matA = extras.getString("matrixA");
            String vecB = extras.getString("vectorB");

            matrixA = matA;
            vectorB = vecB;

        }

        findViewById(R.id.b_gauss_elim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(eqsystemsActivity.this, GaussElimResult.class);
                i.putExtra("matrixA", matrixA);
                i.putExtra("vectorB", vectorB);
                startActivityForResult(i, 29);

                //startActivity(new Intent(eqsystemsActivity.this, GaussElimParameters.class));
            }
        });
        findViewById(R.id.b_cholesky).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(eqsystemsActivity.this, CholeskyResult.class);
                i.putExtra("matrixA", matrixA);
                i.putExtra("vectorB", vectorB);
                startActivityForResult(i, 30);

                //startActivity(new Intent(eqsystemsActivity.this, CholeskyParameters.class));
            }
        });
        findViewById(R.id.b_doolittle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(eqsystemsActivity.this, DoolittleResult.class);
                i.putExtra("matrixA", matrixA);
                i.putExtra("vectorB", vectorB);
                startActivityForResult(i, 31);

                //startActivity(new Intent(eqsystemsActivity.this, DoolittleParameters.class));
            }
        });
        findViewById(R.id.b_crout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(eqsystemsActivity.this, CroutResult.class);
                i.putExtra("matrixA", matrixA);
                i.putExtra("vectorB", vectorB);
                startActivityForResult(i, 32);

                //startActivity(new Intent(eqsystemsActivity.this, CroutParameters.class));
            }
        });
        findViewById(R.id.b_jacobi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(eqsystemsActivity.this, JacobiParameters.class);
                i.putExtra("matrixA", matrixA);
                i.putExtra("vectorB", vectorB);
                startActivityForResult(i, 33);

                //startActivity(new Intent(eqsystemsActivity.this, JacobiParameters.class));
            }
        });
        findViewById(R.id.b_seidel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(eqsystemsActivity.this, GaussSeidelParameters.class);
                i.putExtra("matrixA", matrixA);
                i.putExtra("vectorB", vectorB);
                startActivityForResult(i, 34);

                //startActivity(new Intent(eqsystemsActivity.this, GaussSeidelParameters.class));
            }
        });
        findViewById(R.id.b_partial_pivoting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(eqsystemsActivity.this, PartialPivotingResult.class);
                i.putExtra("matrixA", matrixA);
                i.putExtra("vectorB", vectorB);
                startActivityForResult(i, 35);

                //startActivity(new Intent(eqsystemsActivity.this, PartialPivotingParameters.class));
            }
        });
        findViewById(R.id.b_total_pivoting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(eqsystemsActivity.this, TotalPivotingResult.class);
                i.putExtra("matrixA", matrixA);
                i.putExtra("vectorB", vectorB);
                startActivityForResult(i, 36);

                //startActivity(new Intent(eqsystemsActivity.this, TotalPivotingParameters.class));
            }
        });

    }

}
