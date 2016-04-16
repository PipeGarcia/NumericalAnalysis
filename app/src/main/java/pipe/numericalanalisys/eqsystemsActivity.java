package pipe.numericalanalisys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class eqsystemsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eqsystems);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.b_gauss_elim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(eqsystemsActivity.this, GaussElimParameters.class));
            }
        });
        findViewById(R.id.b_cholesky).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(eqsystemsActivity.this, CholeskyParameters.class));
            }
        });
        findViewById(R.id.b_doolittle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(eqsystemsActivity.this, DoolittleParameters.class));
            }
        });
        findViewById(R.id.b_crout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(eqsystemsActivity.this, CroutParameters.class));
            }
        });
        findViewById(R.id.b_jacobi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(eqsystemsActivity.this, JacobiParameters.class));
            }
        });
        findViewById(R.id.b_seidel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(eqsystemsActivity.this, GaussSeidelParameters.class));
            }
        });

    }

}
