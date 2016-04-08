package pipe.numericalanalisys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class Main2Activity extends AppCompatActivity {

    //Declaración de variables
    EditText vfun;
    EditText vxinf;
    EditText vxsup;
    EditText vdelta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //variables
        vfun   =  (EditText)findViewById(R.id.fun_param);
        vxinf  =  (EditText)findViewById(R.id.infx_param);
        vxsup  =  (EditText)findViewById(R.id.supx_param);
        vdelta =  (EditText)findViewById(R.id.delta_param);

        findViewById(R.id.bcontinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //capturar datos
                String fun   = vfun.getText().toString();
                String xinf  = vxinf.getText().toString();
                String xsup  = vxsup.getText().toString();
                String delta = vdelta.getText().toString();

                Intent i = new Intent(Main2Activity.this, incrementalActivity.class);
                i.putExtra("vfun", fun);
                i.putExtra("vxinf", xinf);
                i.putExtra("vxsup", xsup);
                i.putExtra("vdelta", delta);
                startActivity(i);
                startActivity(new Intent(Main2Activity.this, incrementalActivity.class));

            }
        });
    }


}
