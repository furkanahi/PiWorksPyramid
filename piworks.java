import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;


//please enter manually the N value which is #of rows in the pyramid
//i scanned from input.txt file. You should do it as well as you already know.
public class piworks {
    public static void main(String[] args) throws IOException {
        int N = 15;
        int[][] arr = new int[N][];

        for (int i = 0; i < N; ++i)
            arr[i] = new int[i + 1];

        FileInputStream fis = new FileInputStream("input.txt");
        Scanner sc = new Scanner(fis);

        int i = 0;
        while (sc.hasNextLine())
            for (int j = 0; i < N; i++)
                for (j = 0; j <= i; j++)
                    arr[i][j] = Integer.parseInt(sc.next());

        System.out.println(maxPathSum(arr, N));
    }

    static int maxPathSum(int tri[][], int n) {
        if (checkForPrime(tri[n - 1][n - 1]))
            tri[n - 1][n - 1] = -1;

        // loop for bottom-up calculation
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {

                if (i == n - 2) {
                    if (checkForPrime(tri[i + 1][j]))
                        tri[i + 1][j] = -1;
                    if (checkForPrime(tri[i + 1][j + 1]))
                        tri[i + 1][j + 1] = -1;
                }

                if (checkForPrime(tri[i][j]) || ((tri[i + 1][j] < 0 && tri[i + 1][j + 1] < 0))) {
                    tri[i][j] = -1;
                    continue;
                }
                if (tri[i + 1][j] > tri[i + 1][j + 1])
                    tri[i][j] += tri[i + 1][j];
                else
                    tri[i][j] += tri[i + 1][j + 1];

                if (i == 0)
                    if (tri[i][j] == -1) {
                        System.out.println("no valid path");
                        return -999;
                    } else return tri[i][j];
            }

            /*for (int k = 0; k < n; k++) {
                for (int m = 0; m <= k; m++)
                    System.out.print(tri[k][m] + " ");
                System.out.println();
            }
            System.out.println("------------------------");*/
        }

        // return the top element which stores the maximum sum
        return tri[0][0];
    }

    public static boolean checkForPrime(int num) {
        if (num <= 1)
            return false;

        for (int i = 2; i <= Math.sqrt(num); i++)
            if (num % i == 0)
                return false;
        return true;
    }
}