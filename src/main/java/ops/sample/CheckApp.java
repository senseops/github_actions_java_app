package ops.sample;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CheckApp {
    public static void main(String[] args) {
        System.out.println("Vanakkam! Namakaram Hello, World! CheckApp ");
        // Obtain the current date and time
        LocalDateTime current = LocalDateTime.now();

        // Define a formatter to specify the desired output pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Format the current date and time using the formatter
        String formatted = current.format(formatter);

        // Display the formatted date and time
        System.out.println("Current Date and Time: " + formatted);

    }
}
