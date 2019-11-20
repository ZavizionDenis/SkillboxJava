public class Transaction
{
    private String operationDesc;
    private double moneyIn;
    private double moneyOut;

    public Transaction(String operationDesc, double moneyIn, double moneyOut) {
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
