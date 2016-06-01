package pipe.numericalanalisys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DoolittleResult extends AppCompatActivity {

    TextView textViewAugmentedMatrix;
    TextView textViewDoolittle;
    TextView textViewSolutions;

    private int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doolittle_result);
        setTitle("Doolittle");

        textViewAugmentedMatrix = (TextView)findViewById(R.id.textView149);
        textViewDoolittle = (TextView)findViewById(R.id.textView151);
        textViewSolutions = (TextView)findViewById(R.id.textView153);

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
                    L[k][k] = 1;
                    double suma1 = 0;

                    for(int p = 0; p < k; p++)
                    {
                        suma1 += L[k][p]*U[p][k];
                    }

                    U[k][k] = Double.parseDouble(matrizA[k][k]) - suma1;

                    for(int i = k+1; i < n; i++)
                    {
                        double suma2 = 0;

                        for(int q = 0; q < k; q++)
                        {
                            suma2 += L[i][q]*U[q][k];
                        }

                        L[i][k] = (Double.parseDouble(matrizA[i][k]) - suma2)/U[k][k];
                    }

                    for(int j = k+1; j < n; j++)
                    {
                        double suma3 = 0;

                        for(int r = 0; r < k; r++)
                        {
                            suma3 += L[k][r]*U[r][j];
                        }

                        U[k][j] = Double.parseDouble(matrizA[k][j]) - suma3;
                    }

                    textViewDoolittle.append("--------- Stage " + (k + 1) + " ---------");
                    textViewDoolittle.append("\n");
                    textViewDoolittle.append("Matrix L");
                    textViewDoolittle.append("\n\n");
                    System.out.println();
                    System.out.println("Matrix L");

                    for(int i = 0; i < n; i++)
                    {
                        for(int j = 0; j < n; j++)
                        {
                            textViewDoolittle.append(L[i][j] + "   ");
                            System.out.print(L[i][j] + "\t");
                        }
                        textViewDoolittle.append("\n\n");
                        System.out.println();
                    }
                    textViewDoolittle.append("\n");

                    textViewDoolittle.append("Matrix U");
                    textViewDoolittle.append("\n\n");
                    System.out.println();
                    System.out.println("Matrix U");

                    for(int i = 0; i < n; i++)
                    {
                        for(int j = 0; j < n; j++)
                        {
                            textViewDoolittle.append(U[i][j] + "   ");
                            System.out.print(U[i][j] + "\t");
                        }
                        textViewDoolittle.append("\n\n");
                        System.out.println();
                    }
                    textViewDoolittle.append("\n");
                }

                double detU = 1;
                double detL = 1;

                for(int i = 1; i < n; i++)
                {
                    detU *= U[i][i];
                }

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

                    System.out.println("El sitema tiene infinitas soluciones o no tiene");
                }



                textViewSolutions.append("Values of C");
                textViewSolutions.append("\n\n");
                System.out.println();
                System.out.println("Values of C");

                for(int i = 0; i < n; i++)
                {
                    textViewSolutions.append("c" + (i+1) + " = " +z[i]+"\n");
                    System.out.println(z[i]);
                }

                textViewSolutions.append("\n");
                textViewSolutions.append("Values of X");
                textViewSolutions.append("\n\n");
                System.out.println();
                System.out.println("Values of X");

                for(int i = 0; i < n; i++)
                {
                    textViewSolutions.append("x" + (i+1) + " = " + x[i]+"\n");
                    System.out.println(x[i]);
                }

        }
    }
}
