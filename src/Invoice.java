import java.time.Duration;

public class Invoice {
    private double priceHour, priceDay;
    private double invoiceHour, invoiceDay, totalPayment;
    private double minutesOfDuration;
    private RentalHours rentalHours = RentalHours.RENTAL_12HOURS_IN_MINUTES;
    private Tax tax;
    private boolean isInvoiceHour;
    private boolean isInvoiceDay;

    public Invoice(double minutesOfDuration, double priceHour, double priceDay){
        this.minutesOfDuration = minutesOfDuration;
        this.priceHour = priceHour;
        this.priceDay = priceDay;
        this.isInvoiceHour = this.minutesOfDuration <= rentalHours.minutes;
        this.isInvoiceDay = this.minutesOfDuration > rentalHours.minutes;
    }


    public void calcInvoice( ){

        if(isInvoiceHour){
            this.calcInvoiceHour();
        }else if (isInvoiceDay) {
            this.calcInvoiceDay();
        }
    }

    private void calcInvoiceDay( ){
        this.invoiceDay = this.priceDay * this.quantityOfDays();
        this.tax = new Tax(this.invoiceDay);
        this.tax.calcTax();
        totalPayment = this.invoiceDay + this.tax.getTax();
    }
    private void calcInvoiceHour(){
        this.invoiceHour = this.priceHour * this.quantityHours();
        this.tax = new Tax(this.invoiceHour);
        this.tax.calcTax();
        this.totalPayment = this.invoiceHour + this.tax.getTax();
    }

    private double quantityHours(){
       double hours = Math.ceil(this.minutesOfDuration / 60);

       /* boolean restMinutes = this.minutesOfDuration % 60 != 0;
       if(restMinutes){
           hours += 1;
       } */

       return hours;
    }

    private double quantityOfDays(){
        Duration inMinutes = Duration.ofMinutes((long) this.minutesOfDuration);
        double inHours =  inMinutes.toHours();
        double days = Math.ceil(inHours / 24);

        /*boolean restDays = days % 24 != 0;
        if(restDays){
            days += 1;
        } */

        return days;
    }

    //********************Getters and Setters**********************************


    public double getInvoiceHour() {
        return invoiceHour;
    }

    public double getInvoiceDay() {
        return invoiceDay;
    }

    public Tax getTax() {
        return tax;
    }


    public double getTotalPayment() {
        return totalPayment;
    }

    public double getPriceHour() {
        return priceHour;
    }

    public void setPriceHour(double priceHour) {
        this.priceHour = priceHour;
    }

    public double getPriceDay() {
        return priceDay;
    }

    public void setPriceDay(double priceDay) {
        this.priceDay = priceDay;
    }

    public double getMinutesOfDuration() {
        return minutesOfDuration;
    }

    //----------------> PRINT <---------------------
    @Override
    public String toString() {

        String invoice = "";

        if(isInvoiceDay){
            invoice = String.format("INVOICE: \nBasic payment: R$%.2f \nTax: R$%.2f \nTotal payment: R$%.2f",
                    this.invoiceDay,
                    this.tax.getTax(),
                    this.totalPayment
            );
        }else{
            invoice = String.format("INVOICE: \nBasic payment: R$%.2f \nTax: R$%.2f \nTotal payment: R$%.2f",
                    this.invoiceHour,
                    this.tax.getTax(),
                    this.totalPayment
            );
        }

        return invoice;
    }
}
