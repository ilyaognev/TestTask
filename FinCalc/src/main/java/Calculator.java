import java.io.*;
import java.util.Properties;

public class Calculator {
    private final String inputData;
    private double dollarToEuro = 0;
    private double euroToDollar = 0;

    public Calculator(String inputData) {
        this.inputData = inputData;
        try (InputStream inputStream = new FileInputStream("src/main/resources/config.properties")){
            Properties prop = new Properties();
            prop.load(inputStream);
            this.dollarToEuro = Double.parseDouble(prop.getProperty("dollarToEuro"));
            this.euroToDollar = Double.parseDouble(prop.getProperty("euroToDollar"));
        } catch (IOException e){
            System.out.println("Where our file?");
        }

    }

    public String getResult() {
        return divString(inputData);
    }

    private String divString(String inputData) {
        //если нет конвертора И если нет арифметических операторов, то решение это inputData
        if (!inputData.contains("+") && !inputData.contains("-") && !inputData.contains("(")) {
            return inputData;
        }

        //если нет конвертора (соответственно есть только арифметические операции), то вызываем simpleCalc() (у нас же не более двух аргументов по условию)
        if (!inputData.contains("(")) {
            return simpleCalc(inputData);
        }

        //если в выражении есть конвертор и присутствует один или два аргумента
        String behindBrackets;
        String forConvertor;
        boolean sign; //арифметически знак после аргумента (true) или нет (false)

        if (inputData.indexOf("t") != 0) { //если конвертор не в начале, то первый аргумент это просто аргумент
            behindBrackets = inputData.substring(0, inputData.indexOf("t"));
            forConvertor = inputData.substring(inputData.indexOf("t"));
            sign = true;
        } else { //иначе конвертор в начале, а за скобками аргумент с ариф знаком
            int index = inputData.length() - 1;
            while (index != 0) {
                if (inputData.charAt(index) == ')') {
                    break;
                }
                index--;
            }
            behindBrackets = inputData.substring(index + 1);
            forConvertor = inputData.substring(0, index + 1);
            sign = false;
        }

        //если за скобками конвертора ничего нет, то возвращаем результат метода convertor()
        if (behindBrackets.equals("")) {
            return convertor(inputData);
        } else {
            if (sign) {
                return divString(behindBrackets + convertor(forConvertor));
            } else {
                return divString(convertor(forConvertor) + behindBrackets);
            }
        }
    }

    private String simpleCalc(String dataForCalculations) { //input: 5eur + 75eur or $5.00 + $75 -> output: 80eur or $80
        String result;
        String[] values = dataForCalculations.split("[+\\-]");
        double val1 = Double.parseDouble(values[0].replaceAll("[^0-9.]", ""));
        double val2 = Double.parseDouble(values[1].replaceAll("[^0-9.]", ""));

        if (dataForCalculations.contains("+")) {
            result = Double.toString(val1 + val2);
        } else if (dataForCalculations.contains("-")) {
            result = Double.toString(val1 - val2);
        } else return "wrong data in simpleCalc";

        if (dataForCalculations.contains("$") && !dataForCalculations.contains("eur")) {
            return "$" + result;
        } else if (dataForCalculations.contains("eur") && !dataForCalculations.contains("$")) {
            return result + "eur";
        } else return "wrong data in simpleCalc, need only one type of currency";
    }

    private String convertor(String inputToCurrency) { //input == toEuro($10.00) or toDollar(10.00eur) -> output: 8.00eur or $15.00
        String value;
        double digit;
        String[] inputData = inputToCurrency.split("\\(");
        String toCurrency = inputData[0];

        if (inputData.length > 2) {
            //если внутри конвертора есть другой конвертор; toDollar(toEuro($10.00)+5eur) -> toEuro($10.00)+5eur
            inputData[1] = divString(inputToCurrency.substring(inputToCurrency.indexOf("(") + 1, inputToCurrency.length() - 1));
        }

        if (toCurrency.equals("toEuro") && inputData[1].charAt(0) == '$') {
            value = inputData[1].substring(1, inputData[1].length() - 1);
            digit = Double.parseDouble(value);
            return digit * dollarToEuro + "eur";
        } else if (toCurrency.equals("toDollar") && inputData[1].contains("eur")) {
            value = inputData[1].substring(0, inputData[1].length() - 4);
            digit = Double.parseDouble(value);
            return "$" + digit * euroToDollar;
        } else return "wrong data in convertor";
    }
}
