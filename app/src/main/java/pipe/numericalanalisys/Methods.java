package pipe.numericalanalisys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Methods extends AppCompatActivity {

    private String funcion1;
    private TextView textView;

    public String getFuncion1() {
        return funcion1;
    }

    public void setFuncion1(String funcion1) {
        this.funcion1 = funcion1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_methods);

        textView = (TextView)findViewById(R.id.textView7);

        Bundle extras1 = getIntent().getExtras();
        if(extras1 != null) {
            int raices = 0;
            String cont_fun1 = extras1.getString("funcion1");
            setFuncion1(cont_fun1);
            //textView.setText(String.valueOf(getFuncion1()));
        }

        findViewById(R.id.bisectionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Methods.this, BisectionParameters.class);
                i.putExtra("funcion1", getFuncion1());
                //i.putExtra("vdelta", delta);
                startActivityForResult(i,3);

                //startActivity(new Intent(Methods.this, BisectionParameters.class));
            }
        });
        findViewById(R.id.fixedPointButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Methods.this, FixedPointParameters.class);
                i.putExtra("funcion", getFuncion1());
                startActivityForResult(i,6);

                //startActivity(new Intent(Methods.this, BisectionResult.class));
            }
        });
        findViewById(R.id.secantButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Methods.this, SecantParameters.class);
                i.putExtra("funcion", getFuncion1());
                startActivityForResult(i,8);

                //startActivity(new Intent(Methods.this, BisectionResult.class));
            }
        });
        findViewById(R.id.falsePositionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            Intent i = new Intent(Methods.this, FalsePositionParameters.class);
                i.putExtra("funcion", getFuncion1());
                startActivityForResult(i, 4);

                //startActivity(new Intent(Methods.this, BisectionResult.class));
            }
        });
        findViewById(R.id.newtonRaphsonButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Methods.this, BisectionResult.class));
            }
        });
        findViewById(R.id.multipleRootsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Methods.this, BisectionResult.class));
            }
        });
    }
}
