package helper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadingAndWritingToFileHelper {
    private static final String FILE_NAME = "/home/valiit/IdeaProjects/toDoApplication/src/data.txt";

    public static List<String> initStringsFromFile() throws FileNotFoundException {
        List<String> toDosFromFile = new ArrayList<>();
        File file = new File(FILE_NAME);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String e = scanner.nextLine();
            toDosFromFile.add(e);
            System.out.println(e);
        }
        scanner.close();
        return toDosFromFile;
    }

    public static void writeToDosToFile(List<String> toDosFromFile) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
        for (String toDo : toDosFromFile) {
            writer.write(toDo);
            writer.newLine();
        }
        writer.close();
    }


}
