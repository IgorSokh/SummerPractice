import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Rectangle implements Comparable<Rectangle> {
    private double sideA;
    private double sideB;

    public Rectangle(double sideA, double sideB) {
        this.sideA = sideA;
        this.sideB = sideB;
    }

    public double getArea() {
        return sideA * sideB;
    }

    @Override
    public int compareTo(Rectangle other) {
        return Double.compare(this.getArea(), other.getArea());
    }

    @Override
    public String toString() {
        return "Rectangle{sideA=" + sideA + ", sideB=" + sideB + ", area=" + getArea() + "}";
    }
}

public class SquareRectangleComparasion {
    public static void main(String[] args) {
        List<Rectangle> rectangles = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Количество прямоугольников:");
        int numberOfRectangles = scanner.nextInt();

        for (int i = 0; i < numberOfRectangles; i++) {
            System.out.println("Сторона A для прямоугольника " + (i + 1) + ":");
            double sideA = scanner.nextDouble();
            System.out.println("Сторона Б для прямоугольника " + (i + 1) + ":");
            double sideB = scanner.nextDouble();
            rectangles.add(new Rectangle(sideA, sideB));
        }

        System.out.println("Перед сортировкой:");
        for (Rectangle rect : rectangles) {
            System.out.println(rect);
        }

        Collections.sort(rectangles);

        System.out.println("После сортировки:");
        for (Rectangle rect : rectangles) {
            System.out.println(rect);
        }

        scanner.close();
    }
}