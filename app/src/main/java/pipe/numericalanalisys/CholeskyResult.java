package pipe.numericalanalisys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CholeskyResult extends AppCompatActivity {

    TextView textViewAugmentedMatrix;
    TextView textViewCholesky;
    TextView textViewSolutions;

    private int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cholesky_result);

        textViewAugmentedMatrix = (TextView)findViewById(R.id.textView139);
        textViewCholesky = (TextView)findViewById(R.id.textView141);
        textViewSolutions = (TextView)findViewById(R.id.textView143);

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

            int w = 0;
            for (String row : matrix) {
                matrizA[w++] = row.split(" ");
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

                double[][] U = new double[n][n];
                double[][] L = new double[n][n];
                double[] z = new double[n];
                double[] x = new double[n];

                for(int k = 0; k < n; k++)
                {
                    double suma1 = 0;

                    for(int p = 0; p < k; p++)
                    {
                        suma1 += L[k][p]*U[p][k];
                    }

                    L[k][k] = Math.sqrt(Double.parseDouble(matrizA[k][k]) - suma1);
                    U[k][k] = L[k][k];

                    for(int i = k+1; i < n; i++)
                    {
                        double suma2 = 0;

                        for(int q = 0; q < k; q++)
                        {
                            suma2 += L[i][q]*U[q][k];
                        }

                        L[i][k] = (Double.parseDouble(matrizA[i][k]) - suma2)/ L[k][k];
                    }

                    for(int j = k+1; j < n; j++)
                    {
                        double suma3 = 0;

                        for(int r = 0; r < k; r++)
                        {
                            suma3 += L[k][r]*U[r][j];
                        }

                        U[k][j] = (Double.parseDouble(matrizA[k][j]) - suma3)/L[k][k];
                    }

                    textViewCholesky.append("--------- Stage " + (k + 1) + " ---------");
                    textViewCholesky.append("\n");
                    textViewCholesky.append("Matrix L");
                    textViewCholesky.append("\n\n");
                    System.out.println();
                    System.out.println("Matrix L");

                    for(int i = 0; i < n; i++)
                    {
                        for(int j = 0; j < n; j++)
                        {
                            textViewCholesky.append(L[i][j] + "   ");
                            System.out.print(L[i][j] + "\t");
                        }
                        textViewCholesky.append("\n\n");
                        System.out.println();
                    }
                    textViewCholesky.append("\n");

                    textViewCholesky.append("Matrix U");
                    textViewCholesky.append("\n\n");
                    System.out.println();
                    System.out.println("Matrix U");

                    for(int i = 0; i < n; i++)
                    {
                        for(int j = 0; j < n; j++)
                        {
                            textViewCholesky.append(U[i][j] + "   ");
                            System.out.print(U[i][j] + "\t");
                        }
                        textViewCholesky.append("\n\n");
                        System.out.println();
                    }
                    textViewCholesky.append("\n");
                }

                double detU = 1;
                double detL = 1;

                double detA = detU*detL;

                if(detA != 0)
                {
                    for(int i = 0; i < n; i++)
                    {
                        double suma = 0;

                        for(int p = 0; p < i; p++)
                        {
                            suma += L[i][p]*z[p];
                        }
                        z[i] = (Double.parseDouble(matrizB[i]) - suma)/L[i][i];
                    }

                    for(int i = n-1; i >= 0; i--)
                    {
                        double suma = 0;

                        for(int p = i+1; p < n; p++)
                        {
                            suma += U[i][p]*x[p];
                        }
                        x[i] = (z[i] - suma)/U[i][i];
                    }
                }else{
                    System.out.println("El sistema tiene infinitas soluciones o no tiene");
                }

                textViewSolutions.append("Values of C");
                textViewSolutions.append("\n\n");
                System.out.println();
                System.out.println("Values of C");

                for(int i = 0; i < n; i++)
                {
                    textViewSolutions.append("c" + (i + 1) + " = " + z[i] + "\n");
                    System.out.println(z[i]);
                }

                textViewSolutions.append("\n");
                textViewSolutions.append("Values of X");
                textViewSolutions.append("\n\n");
                System.out.println();
                System.out.println("Values of x");

                for(int i = 0; i < n; i++)
                {
                    textViewSolutions.append("x" + (i+1) + " = " + x[i] + "\n");
                    System.out.println(x[i]);
                }

        }
    }
}
