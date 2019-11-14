import java.util.Date;

public class Trasaction
{
    private String type;
    private String number;
    private String currency;
    private Date operationDate;
    private String referTrans;
    private String operationDesc;
    private double moneyIn;
    private double moneyOut;

    public Trasaction (String type, String namber, String currency, Date operationDate, String referTrans, String operationDesc, double moneyIn, double moneyOut) {
        this.type = type;
        this.number = namber;
        this.currency = currency;
        this.operationDate = operationDate;
        this.referTrans = referTrans;
        this.operationDesc = operationDesc;
        this.moneyIn = moneyIn;
        this.moneyOut = moneyOut;
    }

    public String getOperationDesc() {
        return operationDesc;
    }

    public double getMoneyIn() {
        return moneyIn;
    }

    public double getMoneyOut() {
        return moneyOut;
    }
}
