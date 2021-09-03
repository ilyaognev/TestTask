import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FinCalc {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputData = br.readLine();

        Calculator calculator = new Calculator(inputData);
        System.out.println(calculator.getResult());
    }
}
