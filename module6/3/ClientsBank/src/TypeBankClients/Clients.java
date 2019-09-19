package TypeBankClients;

abstract public class Clients {

    protected final int clientAccountNumber;
    protected double clientAccountBalance;

    public Clients(int clientAccountNumber, double clientAccountBalance) {
        this.clientAccountNumber = clientAccountNumber;
        this.clientAccountBalance = clientAccountBalance;
    }

    public double getBalance() {
        return clientAccountBalance;
    }

    abstract public String getType();

    public int getClientNumber() {
        return clientAccountNumber;
    }

    abstract public void fillBalance(double amount);

    abstract public boolean withdrawalBalance(double amount);

    public void transferFunds (Clients secondAccount, double amount) {
        if (withdrawalBalance(amount)) {
            secondAccount.fillBalance(amount);
        }
    }
}
