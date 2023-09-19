public class CaculableDemo {
    public static void main(String[] args) {
        double x = 5.3;
        double y = 3.7;
        Calculable add = (a, b) -> a + b;
        Calculable subtract = (a, b) -> a - b;
        Calculable divide = (a, b) -> a / b;
        Calculable multiply = (a, b) -> a * b;
        Calculable average = (a, b) -> divide.calculate(add.calculate(a, b), 2);
        Calculable power = (a, b) -> Math.pow(a, b);

        System.out.println(average.calculate(x, y));
    }
}
