package lab1;

public class LifeLength {
    public static void main(String[] args) {
        int num = Integer.parseInt(args[0]);
        int n = 1;

        switch(n){
            case 1:
                method1(num);
                break;

            case 2:
                method2(num);
                break;

            case 3:
                int num2 = Integer.parseInt(args[1]);
                method3(num, num2);
                break;

            case 4:
                method4(num);
                break;

            case 6:
                method6(num);
                break;

            default:
                System.out.println("Invalid Input");
                break;
        }
    }

    // Task 1
    public static void method1(int num){
        System.out.println(f1(num));
    }
    
    // Task 2
    public static void method2(int num){
        System.out.print("f1="+f1(num)+" "+"f2="+f2(num)+" "+"f4="+f4(num)+" "+"f8="+f8(num)+" "+"f16="+f16(num)+" "+"f32="+f32(num));
    }

    // Task 3
    public static void method3(int num, int num2){
        iterateF(num, num2);
    }

    // Task 4
    public static void method4(int num){
        System.out.println(intsToString(num));
    }

    // Task 6
    public static void method6(int num){
        System.out.println(rec1(num, 0));
    }

    public static int f1(int a0){
        if (a0 == 1){
            return a0;
        }
        else if (a0 % 2 == 0){
            return (a0/2);
		} 
        else {
            return (3*a0+1);
		}
	}

    public static int f2(int a0){
        int num = f1(f1(a0));
        return num;
	}

    public static int f4(int a0){
        int num = f2(f2(a0));
        return num;
	}

    public static int f8(int a0){
        int num = f4(f4(a0));
        return num;
	}

    public static int f16(int a0){
        int num = f8(f8(a0));
        return num;
	}

    public static int f32(int a0){
        int num = f16(f16(a0));
        return num;
	}

    public static int iterateF(int a0, int n){
        for(int i = 1; i <= n; i++) {
            a0 = f1(a0);
            System.out.println("Step: "+ i +","+a0);
		}
        return 0;
	}

    public static int iterLifeLength(int a0){
        int i = 0;
        while (a0 != 1){
            a0 = f1(a0);
            i++;
		}
        return i;
	}

    public static String intsToString(int a0){
        String returnValue = "";
        for(int i = 1; i <= a0; i++){
            String newValue = " \n For number "+i + ", life length is "+ iterLifeLength(i);
            returnValue = returnValue + newValue;
		}
        return returnValue;
	}

    public static int rec1(int a0, int i){
        if (a0 == 1){
            return i;
		}
        else {
            a0 = f1(a0);
            return (rec1(a0, i + 1));
		}
    }

    public static int rec2(int a0, int i){
        if (a0 == 1){
            return i;
		}
        else {
            a0 = f1(a0);
            return (rec2(a0, i + 1));
		}
    }
}