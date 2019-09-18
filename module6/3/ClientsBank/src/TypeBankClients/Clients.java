package TypeBankClients;

abstract public class Clients {

    protected int clientAccountNumber;
    protected double clientAccountBalance;
    protected String clientAccountType;

    public double getBalance() {
        return clientAccountBalance;
    }

    public String getType() {
        return clientAccountType;
    }

    public int getClientNumber() {
        return clientAccountNumber;
    }

    abstract public void fillBalance(double amount);

    abstract public void withdrawalBalance(double amount);
}
