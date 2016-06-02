package pipe.numericalanalisys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class SorResult extends AppCompatActivity {

    TextView textViewAugmentedMatrix;
    TextView textViewSor;
    TextView textViewSolutions;

    private ArrayList<String> errorTextView;

    private int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sor_result);
        setTitle("S.O.R");

        textViewAugmentedMatrix = (TextView)findViewById(R.id.textView204);
        textViewSor = (TextView)findViewById(R.id.textView206);
        textViewSolutions = (TextView)findViewById(R.id.textView208);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {

            errorTextView = new ArrayList<String>();

            String matA = extras.getString("matrixA");
            String vecB = extras.getString("vectorB");
            String matX = extras.getString("matrixX");
            String tol = extras.getString("tolerance");
            String del = extras.getString("delta");
            String iter = extras.getString("iterations");
            String w = extras.getString("lambda");

            //textView.append(matA);

            String matrix[] = matA.split(";");
            String matrizB[] = vecB.split(";");
            String matrixX[] = matX.split(";");
            int tamaño = matrix.length;
            n = tamaño;
            String matrizA[][] = new String[tamaño][];

            int q = 0;
            for (String row : matrix) {
                matrizA[q++] = row.split(" ");
            }

            //textView.append(matrizA[0][0]);

            for (int i = 0; i < matrizA.length; i++) {
                for (int j = 0; j < matrizA.length; j++) {
                    System.out.print(matrizA[i][j] + " ");
                    textViewAugmentedMatrix.append(matrizA[i][j] + "   ");
                }
                System.out.print(matrizB[i]);
                System.out.print("\n");

                textViewAugmentedMatrix.append(matrizB[i]);
                textViewAugmentedMatrix.append("\n\n");
            }
            //Hasta aquí lo que hice en eclipse

            double[] x = new double[n];
            double errorInmediato;
            int cont = 0;
            double error = Double.parseDouble(tol)+1;
            double norma = 1;

            while(Math.abs(norma) > Double.parseDouble(del) && error > Double.parseDouble(tol) && cont < Double.parseDouble(iter))
            {

                error = 0;
                textViewSor.append("\n");
                textViewSor.append("Iteration " + cont);
                textViewSor.append("\n");

                for(int i = 0; i < n; i++)
                {

                    if(cont == 0){

                        textViewSor.append("x" + i + " = ");
                        textViewSor.append(matrixX[i] + "\n");

                    }else{
                        System.out.print(x[i] + "\t\t");
                        textViewSor.append("x" + i + " = ");
                        textViewSor.append(x[i] + "\n");
                    }

                    if(i == n-1)
                    {
                        System.out.println();
                        textViewSor.append("\n");
                    }

                    double suma = 0;

                    for (int j = 0; j < n; j++)
                    {
                        if (i != j)
                        {
                            suma += -Double.parseDouble(matrizA[i][j])*Double.parseDouble(matrixX[j]);
                        }
                    }

                    x[i] = Double.parseDouble(w)*((suma + Double.parseDouble(matrizB[i]))/Double.parseDouble(matrizA[i][i])) + (1-Double.parseDouble(w))*Double.parseDouble(matrixX[i]);

                    double [] N = new double[tamaño];

                    for(int k = 0; k < n; k++){

                        for(int l = 0; l < n; l++){
                            N[k] = Double.parseDouble(matrizA[k][l])*x[l]-Double.parseDouble(matrizB[l]);
                        }

                        norma = norma + Math.pow(N[k],2);

                    }

                    norma = Math.pow(norma,2);
                    errorInmediato = x[i]-Double.parseDouble(matrixX[i]);
                    error += Math.pow(errorInmediato,2);
                    matrixX[i] = String.valueOf(x[i]);

                }
                error = Math.sqrt(error);

                //Error para poder empezar a imprimirlo desde la iteracion 2, porque en la iteracion 0 no hay error
                errorTextView.add(String.valueOf(error));

                if(cont > 1){

                    textViewSor.append("||X" + cont + " - X" + (cont-1) + "|| = ");
                    textViewSor.append(errorTextView.get(cont - 1));
                    textViewSor.append("\n");

                }

                cont++;
            }
            System.out.println();
            if(error < Double.parseDouble(tol))
            {
                System.out.println("La solucion aproxima del sistema es:" + "\n");
                textViewSolutions.append("The approximate solution of the system is: \n\n");

                for(int i = 0; i < n; i++)
                {
                    System.out.print(x[i] + "\t");
                    textViewSolutions.append("x" + i + " = " + x[i] + "\n");
                }

            }else{

                System.out.println("Fracaso en esas iteraciones" + "\t" + cont);
                textViewSolutions.append("Exceeded the number of iterations");

            }

        }
    }
}
