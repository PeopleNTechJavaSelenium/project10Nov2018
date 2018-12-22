package unit.testing;

public class Calculator {

    public int addition(int a, int b){
        int total = a + b;
        return total;
    }

    public int subtraction(int a, int b){
        int total = a - b;
        return total;
    }

    //example. not implemented properly
    public int matrix(int a, int b){
        addition(12,19);
        subtraction(14,18);
        int total = a - b;
        return total;
    }

}
