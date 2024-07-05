import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class RewriteFile {

    public static void main(String[] args) {
        try {
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Введите имя файла:");
            String fileName = consoleReader.readLine();
            File file = new File(fileName);
            boolean fileExists = file.exists();

            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

            if (!fileExists) {
                file.createNewFile();
            } else {
                writer.newLine();
            }

            while (true) {
                System.out.println("Введите текст (или 'exit' для выхода):");
                String input = consoleReader.readLine();

                if ("exit".equalsIgnoreCase(input)) {
                    break;
                }

                writer.write(input);
                writer.newLine();
                writer.flush();
            }

            consoleReader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}