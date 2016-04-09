package pipe.numericalanalisys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main3);

        textView = (TextView)findViewById(R.id.textView7);

        Bundle extras1 = getIntent().getExtras();
        if(extras1 != null) {
            int raices = 0;
            String cont_fun1 = extras1.getString("funcion1");
            setFuncion1(cont_fun1);
            //textView.setText(String.valueOf(getFuncion1()));
        }

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Main3Activity.this, BisectionParameters.class);
                i.putExtra("funcion1", getFuncion1());
                //i.putExtra("vdelta", delta);
                startActivityForResult(i,3);

                //startActivity(new Intent(Main3Activity.this, BisectionParameters.class));
            }
        });
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main3Activity.this, Main4Activity.class));
            }
        });
        findViewById(R.id.button7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main3Activity.this, Main4Activity.class));
            }
        });
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main3Activity.this, Main4Activity.class));
            }
        });
        findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main3Activity.this, Main4Activity.class));
            }
        });
        findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main3Activity.this, Main4Activity.class));
            }
        });
    }
}
