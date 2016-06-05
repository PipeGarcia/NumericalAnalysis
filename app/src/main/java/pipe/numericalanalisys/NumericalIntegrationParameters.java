package pipe.numericalanalisys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class NumericalIntegrationParameters extends AppCompatActivity {

    TextView textViewFX;
    EditText editTextFX;
    EditText editTextInitialX;
    EditText editTextFinalX;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numerical_integration_parameters);

        textViewFX = (TextView)findViewById(R.id.textView231);
        editTextInitialX = (EditText)findViewById(R.id.editText30);
        editTextFinalX = (EditText)findViewById(R.id.editText37);
        editTextFX = (EditText)findViewById(R.id.editText36);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {

            String param = extras.getString("parametro");

            if(param.equals("Con funcion")){

                textViewFX.setText("Set the function:");
                editTextFX.setHint("Ej: e^(x^2)");

            }

        }

        findViewById(R.id.button10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String initialX = editTextInitialX.getText().toString();
                String finalX = editTextFinalX.getText().toString();
                String fx = editTextFX.getText().toString();

                Intent i = new Intent(NumericalIntegrationParameters.this, NumericalIntegrationMethods.class);
                i.putExtra("initialX", initialX);
                i.putExtra("finalX", finalX);
                i.putExtra("fx", fx);
                startActivityForResult(i, 51);

            }
        });
    }
}
