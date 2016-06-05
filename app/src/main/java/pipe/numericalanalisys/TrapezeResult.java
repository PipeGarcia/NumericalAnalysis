package pipe.numericalanalisys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TrapezeResult extends AppCompatActivity {

    TextView textViewSolutions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trapeze_result);
        setTitle("Trapeze");

        textViewSolutions = (TextView)findViewById(R.id.textView236);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {

            String initialX = extras.getString("initialX");
            String finalX = extras.getString("finalX");
            String function = extras.getString("fx");

            String y[] = function.split(";");
            int n = y.length;

            double integral = ((Double.parseDouble(y[0])+Double.parseDouble(y[1]))/2)*(Double.parseDouble(finalX)-Double.parseDouble(initialX));
            textViewSolutions.append("\n");
            textViewSolutions.append("x" + "\t\t\t\t" + "fx" + "\n");
            textViewSolutions.append(initialX + "\t\t\t\t" + y[0] + "\n");
            textViewSolutions.append(finalX + "\t\t\t\t" + y[1] + "\n\n");
            textViewSolutions.append("Integral = " + integral);


        }


    }
}
