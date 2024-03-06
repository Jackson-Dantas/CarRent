import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
public class Rental {
    private String carModel;
    private LocalDateTime pickup, devolution;
    private Invoice invoice;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    public Rental() {  };
    public Rental(String carModel, double priceHour, double priceDay, String pickup, String devolution) {
        this.carModel = carModel;
        this.setPickup(pickup);
        this.setDevolution(devolution);
        invoice = new Invoice(this.calcDuration(), priceHour, priceDay);
    }

    public void invoice( ){
        this.invoice.calcInvoice();
    }
    public double calcDuration( ){
        Duration diference = Duration.between(this.pickup, this.devolution);
        double minutes = diference.toMinutes();
        return minutes;
    }

    /*Getters e Setters*/
    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public LocalDateTime getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {

        LocalDateTime date = LocalDateTime.parse(pickup, dateTimeFormatter);
        this.pickup = date;
    }

    public LocalDateTime getDevolution() {
        return devolution;
    }

    public void setDevolution(String devolution) {
        LocalDateTime date = LocalDateTime.parse(devolution, dateTimeFormatter);
        this.devolution = date;
    }

    @Override
    public String toString() {
        return this.invoice.toString();
    }
}
