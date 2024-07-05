import java.util.Scanner;

public class RomanNumbersConverter {
    private static final int[] VALUES = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] SYMBOLS = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public static String toRoman(int num) {
        if (num < 1 || num > 9999) {
            throw new IllegalArgumentException("Число от 1 до 9999");
        }

        StringBuilder roman = new StringBuilder();
        for (int i = 0; i < VALUES.length; i++) {
            while (num >= VALUES[i]) {
                num -= VALUES[i];
                roman.append(SYMBOLS[i]);
            }
        }
        return roman.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введи число от 1 до 9999: ");
        int number = scanner.nextInt();
        scanner.close();

        String romanNumber = toRoman(number);
        System.out.println("Римское число " + number + " будет " + romanNumber);
    }
}