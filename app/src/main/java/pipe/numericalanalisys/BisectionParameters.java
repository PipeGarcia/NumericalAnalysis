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
    EditText vxinf;
    EditText vxsup;
    EditText vdelta;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bisection_parameters);

        tv = (TextView)findViewById(R.id.textView);

        //variables
        vfun   =  (EditText)findViewById(R.id.fun_param);
        vxinf  =  (EditText)findViewById(R.id.infx_param);
        vxsup  =  (EditText)findViewById(R.id.supx_param);
        vdelta =  (EditText)findViewById(R.id.delta_param);

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
                String xinf  = vxinf.getText().toString();
                String xsup  = vxsup.getText().toString();
                String delta = vdelta.getText().toString();

                Intent i = new Intent(BisectionParameters.this, Main4Activity.class);
                i.putExtra("vfun", fun);
                i.putExtra("vxinf", xinf);
                i.putExtra("vxsup", xsup);
                i.putExtra("vdelta", delta);
                startActivityForResult(i,4);
                //startActivity(new Intent(BisectionParameters.this, incrementalActivity.class));

            }
        });
    }


}
