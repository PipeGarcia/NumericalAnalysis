package pipe.numericalanalisys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class interpolationActivity extends AppCompatActivity {

    private String x, fx, point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {

            String vecX = extras.getString("x");
            String vecFX = extras.getString("fx");
            String pointToInterpolate = extras.getString("point");

            x = vecX;
            fx = vecFX;
            point = pointToInterpolate;

        }

        findViewById(R.id.b_newton_polynomial).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(interpolationActivity.this, NewtonPolynomialResult.class);
                i.putExtra("x", x);
                i.putExtra("fx", fx);
                i.putExtra("point", point);
                startActivityForResult(i, 46);

            }
        });

        findViewById(R.id.b_lagrange_polynomial).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(interpolationActivity.this, LagrangeResult.class);
                i.putExtra("x", x);
                i.putExtra("fx", fx);
                i.putExtra("point", point);
                startActivityForResult(i, 47);

            }
        });

        findViewById(R.id.b_linear_spline).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(interpolationActivity.this, LinearSplineResult.class);
                i.putExtra("x", x);
                i.putExtra("fx", fx);
                i.putExtra("point", point);
                startActivityForResult(i, 48);

            }
        });

        findViewById(R.id.b_cubic_spline).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(interpolationActivity.this, CubicResult.class);
                i.putExtra("x", x);
                i.putExtra("fx", fx);
                i.putExtra("point", point);
                startActivityForResult(i, 49);

            }
        });


    }

}
