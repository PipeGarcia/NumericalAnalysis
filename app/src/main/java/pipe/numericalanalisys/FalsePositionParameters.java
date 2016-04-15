package pipe.numericalanalisys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class FalsePositionParameters extends AppCompatActivity {

    private EditText etFunction;
    private EditText etA;
    private EditText etB;
    private EditText etTolerance;
    private EditText etIterations;
    private EditText etDelta;
    private String func;

    public String getFunc() {
        return func;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_false_position_parameters);

        etFunction   =  (EditText)findViewById(R.id.editTextFunction);
        etA  =  (EditText)findViewById(R.id.editTextA);
        etB  =  (EditText)findViewById(R.id.editTextB);
        etTolerance =  (EditText)findViewById(R.id.editTextTolerance);
        etDelta = (EditText)findViewById(R.id.editTextDelta);
        etIterations = (EditText)findViewById(R.id.editTextIterations);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            String cont_fun = extras.getString("funcion");
            setFunc(cont_fun);
            //tv.setText(String.valueOf(getFuncion1()));
            etFunction.setText(String.valueOf(getFunc()));
        }

        findViewById(R.id.btnContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //capturar datos
                String fun   = etFunction.getText().toString();
                String a  = etA.getText().toString();
                String b  = etB.getText().toString();
                String delta = etDelta.getText().toString();
                String tol = etTolerance.getText().toString();
                String iter = etIterations.getText().toString();

                Intent i = new Intent(FalsePositionParameters.this, FalsePositionResult.class);
                i.putExtra("vfun", fun);
                i.putExtra("va", a);
                i.putExtra("vb", b);
                i.putExtra("vdelta", delta);
                i.putExtra("vtol", tol);
                i.putExtra("viter", iter);
                startActivityForResult(i, 6);
            }
        });

    }
}
