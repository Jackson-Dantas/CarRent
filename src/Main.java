import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {

        Rental teste = new Rental("Civic", 10.00, 130.00,"25/06/2018 10:30","25/06/2018 14:40");
        teste.invoice();
        System.out.println(teste.toString());

      


    }
}