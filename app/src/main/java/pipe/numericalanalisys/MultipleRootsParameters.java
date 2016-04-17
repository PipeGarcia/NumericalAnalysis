package pipe.numericalanalisys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MultipleRootsParameters extends AppCompatActivity {

    private EditText function;
    private EditText firstDerivedFunction;
    private EditText secondDerivedFunction;
    private EditText initialX;
    private EditText tolerance;
    private EditText delta;
    private EditText iterations;
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
        setContentView(R.layout.activity_multiple_roots_parameters);

        function = (EditText)findViewById(R.id.editTextFX);
        firstDerivedFunction = (EditText)findViewById(R.id.editTextF1X);
        secondDerivedFunction = (EditText)findViewById(R.id.editTextF2X);
        initialX  =  (EditText)findViewById(R.id.editTextInitialX);
        tolerance =  (EditText)findViewById(R.id.editTextTolerance);
        delta = (EditText)findViewById(R.id.editTextDelta);
        iterations = (EditText)findViewById(R.id.editTextIterations);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            String cont_fun = extras.getString("funcion");
            setFunc(cont_fun);
            function.setText(String.valueOf(getFunc()));
        }

        findViewById(R.id.buttonContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //capturar datos
                String fun  = function.getText().toString();
                String derFun  = firstDerivedFunction.getText().toString();
                String secDerFun = secondDerivedFunction.getText().toString();
                String vx = initialX.getText().toString();
                String vdelta = delta.getText().toString();
                String vtol = tolerance.getText().toString();
                String viter = iterations.getText().toString();

                Intent i = new Intent(MultipleRootsParameters.this, MultipleRootsResult.class);
                i.putExtra("vfun", fun);
                i.putExtra("vfirstderfun", derFun);
                i.putExtra("vsecderfun", secDerFun);
                i.putExtra("vx", vx);
                i.putExtra("vdelta", vdelta);
                i.putExtra("vtol", vtol);
                i.putExtra("viter", viter);
                startActivityForResult(i, 12);
            }
        });
    }
}
