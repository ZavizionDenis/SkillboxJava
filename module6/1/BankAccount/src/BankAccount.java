import java.util.Locale;

public class BankAccount
{
    private double balance;
    private String typeAccount;

    public BankAccount(double balance, String typeAccount) {
        this.balance = balance;
        this.typeAccount = typeAccount;
    }

    public String getBalance() {
        return String.format(Locale.ENGLISH," Баланс счета: %.2f\n========================\n", balance);
    }

    public String getTypeAccount () {
        return typeAccount;
    }

    public void fillBalance(double amount) {
        this.balance += amount;
        System.out.printf(Locale.ENGLISH,getTypeAccount() + " Баланс пополнен на: %.2f%s%n", amount, getBalance());
    }

    public void withdrawBalance (double amount) {
        if (amount <= balance) {
            this.balance -= amount;
            System.out.printf(Locale.ENGLISH, getTypeAccount() + " Баланс уменьшен на: %.2f%s%n", amount, getBalance());
        }
        else {
            System.out.println("Операция не возможна, запрашиваемая сумма превышает остаток на балансе.");
        }
    }
}
