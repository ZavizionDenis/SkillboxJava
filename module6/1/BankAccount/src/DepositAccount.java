import java.util.Random;

public class DepositAccount extends BankAccount{

    private static Boolean isMonthPassed;

    public DepositAccount(double balance, String typeAccount) {
        super(balance,typeAccount);
    }

    @Override
    public void withdrawBalance(double amount) {
        isMonthPassed = new Random().nextBoolean();
        if (isMonthPassed) {
            super.withdrawBalance(amount);
        }
        else {
            System.out.println("Снятие денег не возможно, т.к. не прошел месяц с последнего пополнения\n========================\n");
        }
    }
}
