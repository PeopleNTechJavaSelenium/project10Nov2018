package unit.testing;

import org.testng.Assert;

public class TestCalculator {
    public static void main(String[] args) {
        Calculator cal = new Calculator();
        int resultOfAddition = cal.addition(10, 15);
        Assert.assertEquals(resultOfAddition, 25);
        int resultOfSubtraction = cal.subtraction(20, 15);
        Assert.assertEquals(resultOfSubtraction, 5);
    }
}
