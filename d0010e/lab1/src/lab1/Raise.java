package lab1;

import java.lang.Math;

public class Raise {

    public static void main(String[] args) {
        double num1 = Double.parseDouble(args[0]);
        int num2 = Integer.parseInt(args[1]);
        double fun1 = recRaiseHalf(num1, num2, 0);
        double fun2 = recRaiseOne(num1, num2, 0);
        System.out.println(fun1);
        System.out.println(fun2);
    }

    public static double recRaiseHalf(double x, int k, int count){
        count++;
        if(k == 0){
            count = count;
            System.out.println("Number of recursions it took for half: "+count);
            return 1;
		}
        double recHalf = recRaiseHalf(x, k/2, count);
        if (k % 2 == 0) {
            return recHalf * recHalf;
        }

        else {
            return x * recHalf * recHalf; 
		}
	}

    public static double recRaiseOne(double x, int k, int count){
        count++;
        if(k==0){
            System.out.println("Number of recursions it took for one: "+count);
            return 1;  
		}
        else{
            return x * recRaiseOne(x, k-1, count);  
		}
	}
}