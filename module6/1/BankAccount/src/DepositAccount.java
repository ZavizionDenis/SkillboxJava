import java.time.LocalDate;
import java.util.Random;

public class DepositAccount extends BankAccount{

    private LocalDate lastFillDate;
    private static final int MIN_DAYS_COUNT_TO_WITHDRAW = 30;

    public DepositAccount(double balance, String typeAccount) {
        super(balance,typeAccount);
        setLastFillDate();
    }

    @Override
    public void fillBalance(double amount) {
        setLastFillDate();
        super.fillBalance(amount);
    }

    @Override
    public void withdrawBalance(double amount) {
        int pastDays = new Random().nextInt(60);
        System.out.println("Количество прошедших дней: " + pastDays);
        LocalDate thisDate = TimeStamp.getTimeStamp().plusDays(pastDays);
        if (thisDate.isAfter(lastFillDate)) {
            super.withdrawBalance(amount);
        }
        else {
            System.out.println("Снятие денег не возможно, т.к. не прошел месяц с последнего пополнения\n========================\n");
        }
    }

    private void setLastFillDate () {
        lastFillDate = TimeStamp.getTimeStamp().now().plusDays(MIN_DAYS_COUNT_TO_WITHDRAW);
    }
}
