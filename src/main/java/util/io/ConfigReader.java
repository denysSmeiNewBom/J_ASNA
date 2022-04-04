package util.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ConfigReader {
    public static String readFile(String path){
        StringBuilder returnData = new StringBuilder();
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                returnData.append(myReader.nextLine());
                returnData.append("\n");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while file reading.");
            e.printStackTrace();
        }
        return returnData.toString();
    }
}
