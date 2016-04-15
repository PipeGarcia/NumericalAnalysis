package pipe.numericalanalisys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class BisectionParameters extends AppCompatActivity {

    private String funcion1;

    public String getFuncion1() {
        return funcion1;
    }

    public void setFuncion1(String funcion1) {
        this.funcion1 = funcion1;
    }

    //Declaración de variables
    EditText vfun;
    EditText vA;
    EditText vB;
    EditText vdelta;
    EditText vTol;
    EditText vIter;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bisection_parameters);

        tv = (TextView)findViewById(R.id.textView);

        //variables
        vfun   =  (EditText)findViewById(R.id.fun_param);
        vA  =  (EditText)findViewById(R.id.a_param);
        vB  =  (EditText)findViewById(R.id.b_param);
        vdelta =  (EditText)findViewById(R.id.delta_param);
        vTol = (EditText)findViewById(R.id.tol_param);
        vIter = (EditText)findViewById(R.id.iter_param);

        Bundle extras1 = getIntent().getExtras();
        if(extras1 != null) {
            String cont_fun1 = extras1.getString("funcion1");
            setFuncion1(cont_fun1);
            //tv.setText(String.valueOf(getFuncion1()));
            vfun.setText(String.valueOf(getFuncion1()));
        }

        findViewById(R.id.bcontinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //capturar datos
                String fun   = vfun.getText().toString();
                String a  = vA.getText().toString();
                String b  = vB.getText().toString();
                String delta = vdelta.getText().toString();
                String tol = vTol.getText().toString();
                String iter = vIter.getText().toString();

                Intent i = new Intent(BisectionParameters.this, BisectionResult.class);
                i.putExtra("vfun", fun);
                i.putExtra("va", a);
                i.putExtra("vb", b);
                i.putExtra("vdelta", delta);
                i.putExtra("vtol", tol);
                i.putExtra("viter", iter);
                startActivityForResult(i, 5);
                //startActivity(new Intent(BisectionParameters.this, incrementalActivity.class));

            }
        });
    }


}
