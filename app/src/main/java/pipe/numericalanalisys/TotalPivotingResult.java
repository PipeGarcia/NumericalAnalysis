package pipe.numericalanalisys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TotalPivotingResult extends AppCompatActivity {

    TextView textViewAugmentedMatrix;
    TextView textViewTotalPivoting;
    TextView textViewSolutions;

    private int n;
    private double multiplicador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_pivoting_result);

        textViewAugmentedMatrix = (TextView)findViewById(R.id.textView119);
        textViewTotalPivoting = (TextView)findViewById(R.id.textView121);
        textViewSolutions = (TextView)findViewById(R.id.textView123);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {

            String matA = extras.getString("matrixA");
            String vecB = extras.getString("vectorB");

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

                int [] marca = new int[n];

                for(int i = 0; i < n; i++)
                {
                    marca[i] = i;
                }

                for(int k = 0; k < n; k++)
                {
                    double mayor = Math.abs(Double.parseDouble(matrizA[k][k]));
                    int filaMayor = k;
                    int columnaMayor = k;

                    for(int p = k+1; p < n; p++)
                    {
                        for(int q = k; q < n; q++)
                        {
                            if(Math.abs(Double.parseDouble(matrizA[p][q])) > mayor)
                            {
                                mayor = Math.abs(Double.parseDouble(matrizA[p][q]));
                                filaMayor = p;
                                columnaMayor = q;
                            }
                        }
                    }

                    if(mayor == 0)
                    {
                        System.out.println("El sistema tiene infinitas soluciones o no tiene solucion");

                    }else if(filaMayor != k)
                    {
                        for(int j = 0; j < n; j++)
                        {
                            double aux = Double.parseDouble(matrizA[k][j]);
                            matrizA[k][j] = String.valueOf(Double.parseDouble(matrizA[filaMayor][j]));
                            matrizA[filaMayor][j] = String.valueOf(aux);
                        }

                        double aux2 = Double.parseDouble(matrizB[k]);
                        matrizB[k] = String.valueOf(Double.parseDouble(matrizB[filaMayor]));
                        matrizB[filaMayor] = String.valueOf(aux2);
                    }

                    if(columnaMayor != k)
                    {
                        for(int i = 0; i < n; i++)
                        {
                            double aux = Double.parseDouble(matrizA[i][k]);
                            matrizA[i][k] = String.valueOf(Double.parseDouble(matrizA[i][columnaMayor]));
                            matrizA[i][columnaMayor] = String.valueOf(aux);

                        }

                        int aux = marca[k];
                        marca[k] = marca[columnaMayor];
                        marca[columnaMayor] = aux;
                    }

                    for(int i = k+1; i < n; i++)
                    {
                        multiplicador = Double.parseDouble(matrizA[i][k])/Double.parseDouble(matrizA[k][k]);

                        for(int j = k; j <n; j++)
                        {
                            matrizA[i][j] = String.valueOf(Double.parseDouble(matrizA[i][j]) - multiplicador*Double.parseDouble(matrizA[k][j]));
                        }
                        matrizB[i] = String.valueOf(Double.parseDouble(matrizB[i]) - (multiplicador * Double.parseDouble(matrizB[k])));
                    }

                    textViewTotalPivoting.append("\n");
                    textViewTotalPivoting.append("Step " + (k+1));
                    textViewTotalPivoting.append("\n\n");
                    //matrizAumentada(); Imprimir la matriz resultante
                    for (int i = 0; i < matrizA.length; i++) {
                        for (int j = 0; j < matrizA.length; j++) {
                            System.out.print(matrizA[i][j] + " ");
                            textViewTotalPivoting.append(matrizA[i][j] + "   ");
                        }
                        System.out.print(matrizB[i]);
                        System.out.print("\n");

                        textViewTotalPivoting.append(matrizB[i]);
                        textViewTotalPivoting.append("\n\n");
                    }

                    for(int i = n; i < 1; i--)
                    {
                        for(int j = 1; j < n; j++)
                        {
                            if(marca[j] == i)
                            {
                                k = j;
                            }
                        }
                    }

                }

                double suma;
                double[] x = new double[n];

                for(int i = n-1; i >= 0; i--)
                {

                    suma = 0;
                    for(int j = i+1;j < n; j++)
                    {
                        suma = suma + (Double.parseDouble(matrizA[i][j])*x[j]);
                    }

                    x[i] = (Double.parseDouble(matrizB[i])-suma)/Double.parseDouble(matrizA[i][i]);
                    textViewSolutions.append("x" + (marca[i] + 1) + " = " + x[i]+"\n");
                    System.out.println(x[i]);
                }



        }
    }
}
