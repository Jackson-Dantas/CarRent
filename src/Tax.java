public class Tax {
    private double invoice;
    private double tax;
    static private double value = 100.00;
    public Tax(double invoice) {
        this.invoice = invoice;
    }
    public void calcTax(){
        this.tax = Math.round(this.invoice * this.taxPercent());
    }
    private double taxPercent(){
        return (this.invoice > value) ? 0.15 : 0.20;
    }

    public double getInvoice() {
        return invoice;
    }

    public double getTax() {
        return tax;
    }

}
