public class HelloWho {
    public static void main(String[] args) {
        var name = args[0];
        var number = Integer.parseInt(args[1]); // to read in a number from an array of strings
        System.out.printf("Hello, %s -  %d", name, number );
    }
}