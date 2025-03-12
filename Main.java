import java.io.PrintStream;
import java.util.Scanner;
public class Main {
    public static Scanner in = new Scanner(System.in);
    public static PrintStream out = System.out;
    public static void main(String[] args) {
        int n =in.nextInt();
        int m =in.nextInt();
        double[][] a  = new double[n][m];
        double[] sred = new double[m];
        double[] disp = new double[m];
        double[] mediani = new double[m*n];
        for(int i=0;i<n;i++){
            for(int y=0;y<m;y++){
                a[i][y]=in.nextDouble();
                mediani[y+i*m] = a[i][y];
            }
        }



        for(int y=0;y<m;y++){
            double sum=0;
            disp[y]=0;
            for(int i=0;i<n;i++){
                sum+=a[i][y];
                if(Math.abs(a[i][y])>Math.abs(disp[y])){
                    disp[y]=Math.abs(a[i][y]);
                }
            }
            sred[y]=sum/n;
            disp[y]=Math.abs(sred[y]-disp[y]);
        }

        for (int i = 0; i< m- 1; i++) {
            for (int j = 0; j<m-1-i; j++) {
                if (sred[j]>sred[j + 1]) {
                    double z = sred[j];
                    sred[j] = sred[j + 1];
                    sred[j + 1] = z;
                    for(int h=0;h<n;h++){
                        double z1=a[h][j];
                        a[h][j]=a[h][j+1];
                        a[h][j+1]=z1;
                    }
                }
                else if(sred[j]==sred[j+1] && disp[j]>disp[j+1]){
                    double z2 = disp[j];
                    disp[j] = disp[j + 1];
                    disp[j + 1] = z2;
                    for(int h=0;h<n;h++){
                        double z3=a[h][j];
                        a[h][j]=a[h][j+1];
                        a[h][j+1]=z3;
                    }
                }
            }
        }



        for (int t = 0; t< m*n-1; t++) {
            for (int e = 0; e< m*n-1-t; e++) {
                if(mediani[e]>mediani[e+1]){
                    double r = mediani[e];
                    mediani[e]=mediani[e+1];
                    mediani[e+1]=r;
                }
            }
        }

        double mediana = 0;
        if(mediani.length!=0){
            mediana = mediani[mediani.length/2];
        }
        else{
            mediana = (mediani[mediani.length/2]+mediani[mediani.length/2-1])/2;
        }
        out.println(mediana);



        double[] temp = new double[mediani.length];
        int[] count = new int[mediani.length];
        int j = 0;
        for (int i=0; i<mediani.length-1; i++){
            if (mediani[i] != mediani[i+1]){
                temp[j] = mediani[i];
                count[j]+=1;
                j++;
            }
            else if(mediani[i] == mediani[i+1]){
                count[j]+=1;
            }
        }
        temp[j] = mediani[mediani.length-1];
        count[j]+=1;
        j+=1;
        for(int c=0;c<j;c++){
            out.print("["+temp[c]+"]"+" ");
            for(int d=0; d<count[c];d++){
                out.print("#");
            }
            out.print("\n");
        }



        double mmin=2147483647;
        double mmax =-2147483648;
        for(int i=0;i<n;i++) {
            for (int y = 0; y < m; y++) {
                if(a[i][y]>mmax){
                    mmax=a[i][y];
                }
                if(a[i][y]<mmin){
                    mmin=a[i][y];
                }
            }
        }

        for(int i=0;i<n;i++) {
            for (int y = 0; y < m; y++) {
                a[i][y]=((a[i][y]-mmin)/(mmax-mmin));
                out.print(a[i][y]+" ");
            }
            out.print("\n");
        }
    }
}