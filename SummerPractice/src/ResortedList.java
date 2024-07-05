import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ResortedList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите числа списка через пробел:");
        String input = scanner.nextLine();
        String[] numbers = input.split(" ");
        ArrayList<Integer> inputList = new ArrayList<>();
        for (String number : numbers) {
            inputList.add(Integer.parseInt(number));
        }

        System.out.println("Введите число n:");
        int n = scanner.nextInt();

        ArrayList<Integer> resultList = processList(inputList, n);
        System.out.println("Результат: " + resultList);

        scanner.close();
    }

    public static ArrayList<Integer> processList(ArrayList<Integer> inputList, int n) {
        ArrayList<Integer> resultList = new ArrayList<>();

        for (Integer num : inputList) {
            resultList.add(num + n);
        }

        int sum = 0;
        for (Integer num : resultList) {
            sum += num;
        }

        if (sum / n <= n) {
            resultList.sort(Collections.reverseOrder());
        } else {
            Collections.sort(resultList);
        }

        return resultList;
    }
}