package pipe.numericalanalisys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class FixedPointParameters extends AppCompatActivity {

    private EditText etFX;
    private EditText etGx;
    private EditText etInitialX;
    private EditText etTol;
    private EditText etDel;
    private EditText etIter;
    private String funcFX;

    public String getFuncFX() {
        return funcFX;
    }

    public void setFuncFX(String funcFX) {
        this.funcFX = funcFX;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_point_parameters);

        etFX   =  (EditText)findViewById(R.id.editTextFx);
        etGx  =  (EditText)findViewById(R.id.editTextGx);
        etInitialX  =  (EditText)findViewById(R.id.editTextInitialX);
        etTol =  (EditText)findViewById(R.id.editTextTol);
        etDel = (EditText)findViewById(R.id.editTextDel);
        etIter = (EditText)findViewById(R.id.editTextIter);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            String cont_fun = extras.getString("funcion");
            setFuncFX(cont_fun);
            etFX.setText(String.valueOf(getFuncFX()));
        }

        findViewById(R.id.buttonCont).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //capturar datos
                String funFX   = etFX.getText().toString();
                String funGX   = etGx.getText().toString();
                String initialX  = etInitialX.getText().toString();
                String tol = etTol.getText().toString();
                String delta = etDel.getText().toString();
                String iter = etIter.getText().toString();

                Intent i = new Intent(FixedPointParameters.this, FixedPointResult.class);
                i.putExtra("vfunFX", funFX);
                i.putExtra("vfunGX", funGX);
                i.putExtra("vinitialx", initialX);
                i.putExtra("vdelta", delta);
                i.putExtra("vtol", tol);
                i.putExtra("viter", iter);
                startActivityForResult(i, 7);
            }
        });
    }
}
