package pipe.numericalanalisys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class InterpolationParameters extends AppCompatActivity {

    EditText editTextX;
    EditText editTextFX;
    EditText editTextP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolation_parameters);

        editTextX = (EditText) findViewById(R.id.editText27);
        editTextFX = (EditText) findViewById(R.id.editText28);
        editTextP = (EditText) findViewById(R.id.editText29);

        findViewById(R.id.button9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String X = editTextX.getText().toString();
                String FX = editTextFX.getText().toString();
                String P = editTextP.getText().toString();

                Intent i = new Intent(InterpolationParameters.this, interpolationActivity.class);
                i.putExtra("x", X);
                i.putExtra("fx", FX);
                i.putExtra("point", P);
                startActivityForResult(i, 45);

            }
        });
    }
}
