package barcodegenerator;

import java.io.FileWriter;
import java.io.IOException;

public class BarcodeServlet {
    private static int checkedDigit;
    private static String finalString;

    public static void main(String[] args) {
        String value = "RC457149995ZA";

        String startIndex = value.substring(0,2);
        String endIndex = value.substring(11);
        String intValue = value.substring(2, 10);
        Integer startInt = Integer.valueOf(intValue);
        System.out.println("Integer Value " + startInt);
        System.out.println(startIndex);
        System.out.println(endIndex);

        String checkDigit = calcualateCheckDigit(intValue);
        String toWrite = produceFinalString(startIndex, startInt, endIndex);


    }

    private static void writeDataInFile(String toWrite) {
        try {
            FileWriter writer = new FileWriter("MyFile.txt", true);
            writer.write(toWrite);
            writer.write("\r\n");   // write new line
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String produceFinalString(String startIndex, Integer intValue, String endIndex) {
        for(int i =  intValue; i<=45715010; i++) {
            finalString = startIndex + String.valueOf(i) + calcualateCheckDigit(String.valueOf(i)) + endIndex;
            writeDataInFile(finalString);
//            System.out.println("write in CSV" + finalString);
        }
        return null;
    }

    private static String calcualateCheckDigit(String toCalculate) {
        char[] chars = toCalculate.toCharArray();

//        calculate = 8*first + 6*second + 4*third + 2*fourth + 3*fifth + 5*sixth + 9*seventh + 7*eight = sum
//        calculation = sum/11;
//        if(rema == 0){
//            checkdigit = 5;
//        } if(remainder = 1){
//            checkdigit = 0;
//        } else {
//            checkdigit = 11-remainder;
//        }

        int sum = 8 * chars[0] + 6 * chars[1] + 4 * chars[2] + 2 * chars[3] + 3 * chars[4] + 5 * chars[5] + 9 * chars[6] + 7 * chars[7];

        int remainder = sum % 11;
//        System.out.println(remainder);

        if(remainder == 0){
            checkedDigit = 5;
        } else if(remainder ==1) {
            checkedDigit = 0;
        } else {
            checkedDigit = 11-remainder;
        }


        return String.valueOf(checkedDigit);
    }

}

