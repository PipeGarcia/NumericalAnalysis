package pipe.numericalanalisys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GaussElimResult extends AppCompatActivity {

    private int n;
    private double [][] A;
    private double []b;
    private double multiplicador;

    TextView textViewAugmentedMatrix;
    TextView textViewGaussianElim;
    TextView textViewSolutions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gauss_elim_result);

        textViewAugmentedMatrix = (TextView)findViewById(R.id.textView94);
        textViewGaussianElim = (TextView)findViewById(R.id.textView101);
        textViewSolutions = (TextView)findViewById(R.id.textView103);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {

            String matA = extras.getString("matrixA");
            String vecB = extras.getString("vectorB");

            //textView.append(matA);

            String matrix[] = matA.split(";");
            String matrizB[] = vecB.split(";");
            int tamaño = matrix.length;
            n = tamaño;
            String matrizA[][] = new String[tamaño][];

            int r = 0;
            for (String row : matrix) {
                matrizA[r++] = row.split(" ");
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

            for(int k = 0; k < n-1; k++)
                    {

                        for(int i = k+1; i < n; i++)
                        {

                            if(Double.parseDouble(matrizA[k][k]) == 0)
                            {
                                System.out.println("Hay posible división por cero");
                                break;
                            }
                            multiplicador = Double.parseDouble(matrizA[i][k])/Double.parseDouble(matrizA[k][k]);

                            for(int j = k; j < n; j++)
                            {
                                matrizA[i][j] = String.valueOf(Double.parseDouble(matrizA[i][j]) - (multiplicador * Double.parseDouble(matrizA[k][j])));

                            }
                            matrizB[i] = String.valueOf(Double.parseDouble(matrizB[i]) - (multiplicador * Double.parseDouble(matrizB[k])));

                        }
                        textViewGaussianElim.append("\n");
                        textViewGaussianElim.append("Step " + (k+1));
                        textViewGaussianElim.append("\n\n");
                        //matrizAumentada(); Imprimir la matriz resultante
                        for (int i = 0; i < matrizA.length; i++) {
                            for (int j = 0; j < matrizA.length; j++) {
                                System.out.print(matrizA[i][j] + " ");
                                textViewGaussianElim.append(matrizA[i][j] + "   ");
                            }
                            System.out.print(matrizB[i]);
                            System.out.print("\n");

                            textViewGaussianElim.append(matrizB[i]);
                            textViewGaussianElim.append("\n\n");
                        }
                    }

                    double suma;
                    double[] x = new double[n];

                    for(int i = n-1; i >= 0; i--)
                    {

                        suma = 0;
                        for(int j = i+1; j < n; j++)
                        {
                            suma = suma + (Double.parseDouble(matrizA[i][j])*x[j]);
                        }

                        x[i] = (Double.parseDouble(matrizB[i])-suma)/Double.parseDouble(matrizA[i][i]);
                        textViewSolutions.append("x" + (i+1) + " = " + x[i]+"\n");
                        System.out.println(x[i]);
                    }

        }
    }
}
