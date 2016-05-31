package pipe.numericalanalisys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class JacobiResult extends AppCompatActivity {

    TextView textViewAugmentedMatrix;
    TextView textViewJacobi;
    TextView textViewSolutions;

    private ArrayList<String> errorTextView;

    private int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jacobi_result);

        textViewAugmentedMatrix = (TextView)findViewById(R.id.textView162);
        textViewJacobi = (TextView)findViewById(R.id.textView164);
        textViewSolutions = (TextView)findViewById(R.id.textView166);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {

            errorTextView = new ArrayList<String>();

            String matA = extras.getString("matrixA");
            String vecB = extras.getString("vectorB");
            String matX = extras.getString("matrixX");
            String tol = extras.getString("tolerance");
            String del = extras.getString("delta");
            String iter = extras.getString("iterations");

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
                double errorInmediato = 0;
                int cont = 0;
                double error = Double.parseDouble(tol) + 1;
                double norma = 1;

                //System.out.println("x1" + "\t\t" + "x2" + "\t\t" + "x3");
                //textViewJacobi.append("x1" + "\t\t" + "x2" + "\t\t" + "x3");

                while(Math.abs(norma) > Double.parseDouble(del) && error > Double.parseDouble(tol) && cont < Integer.parseInt(iter))
                {
                    error = 0;
                    textViewJacobi.append("\n");
                    textViewJacobi.append("Iteration " + cont);
                    textViewJacobi.append("\n");

                    for(int i = 0; i < n; i++)
                    {

                        System.out.print(x[i] + "\t");
                        textViewJacobi.append("x" + i + " = ");
                        textViewJacobi.append(x[i] + "\n");

                        if(i == n-1)
                        {
                            System.out.println();
                            textViewJacobi.append("\n");
                        }

                        double suma = 0;

                        for (int j = 0; j < n; j++)
                        {
                            if (i != j)
                            {
                                suma += -Double.parseDouble(matrizA[i][j])*Double.parseDouble(matrixX[j]);
                            }
                        }

                        x[i] = (suma + Double.parseDouble(matrizB[i]))/Double.parseDouble(matrizA[i][i]);

                        double [] N = new double[tamaño];

                        for(int k = 0; k < n; k++){

                            for(int l = 0; l < n; l++){
                                N[i] = Double.parseDouble(matrizA[i][l])*Double.parseDouble(matrixX[l])-Double.parseDouble(matrizB[l]);
                            }

                            norma = norma + Math.pow(N[k],2);

                        }
                        norma = Math.pow(norma,2);
                        errorInmediato = x[i]-Double.parseDouble(matrixX[i]);
                        error += Math.pow(errorInmediato, 2);

                    }

                    textViewJacobi.append("\n");

                    error = Math.sqrt(error);

                    //Error para poder empezar a imprimirlo desde la iteracion 2, porque en la iteracion 0 no hay error
                    errorTextView.add(String.valueOf(error));

                    if(cont > 1){

                        textViewJacobi.append("||X" + cont + " - X" + (cont-1) + "|| = ");
                        textViewJacobi.append(errorTextView.get(cont-1));
                        textViewJacobi.append("\n");

                    }

                    cont++;

                    for(int i = 0; i < n; i++)
                    {
                        matrixX[i] = String.valueOf(x[i]);
                    }

                }
                System.out.println();
                textViewJacobi.append("\n");

                if(error < Double.parseDouble(tol))
                {
                    System.out.println("La solucion aproximada del sistema es:" + "\n");
                    textViewSolutions.append("The approximate solution of the system is: \n\n");

                    for(int i = 0; i < n; i++)
                    {
                        System.out.print(x[i] + "\t");
                        textViewSolutions.append("x" + i + " = " + x[i] + "\n");
                    }
                }else{
                    System.out.println("Fracasó en esas iteraciones:" + "\t" + cont);
                    textViewSolutions.append("Exceeded the number of iterations");

                }

        }
    }
}
