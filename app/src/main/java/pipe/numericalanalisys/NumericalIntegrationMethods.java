package pipe.numericalanalisys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NumericalIntegrationMethods extends AppCompatActivity {

    String xi, xf, fx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numerical_integration_methods);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {

            String initialX = extras.getString("initialX");
            String finalX = extras.getString("finalX");
            String function = extras.getString("fx");

            xi = initialX;
            xf = finalX;
            fx = function;

        }

        findViewById(R.id.b_trapeze).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(NumericalIntegrationMethods.this, TrapezeResult.class);
                i.putExtra("initialX", xi);
                i.putExtra("finalX", xf);
                i.putExtra("fx", fx);
                startActivityForResult(i, 52);

            }
        });

        findViewById(R.id.b_compound_trapeze).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        findViewById(R.id.b_simpson).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        findViewById(R.id.b_compound_simpson).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
