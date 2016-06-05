package pipe.numericalanalisys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NumericalIntegration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numerical_integration);

        findViewById(R.id.b_with_function).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String parametro = "Con funcion";

                Intent i = new Intent(NumericalIntegration.this, NumericalIntegrationParameters.class);
                i.putExtra("parametro", parametro);
                startActivityForResult(i, 50);

            }
        });

        findViewById(R.id.b_without_function).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NumericalIntegration.this, NumericalIntegrationParameters.class));
            }
        });

    }
}
