import java.time.LocalDate;
<<<<<<< HEAD
import java.util.Random;
=======
>>>>>>> 873a9d171a1c18e61906c17c8f889ab4c42e675f

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
<<<<<<< HEAD
        int pastDays = new Random().nextInt(60);
        System.out.println("Количество прошедших дней: " + pastDays);
        LocalDate thisDate = TimeStamp.getTimeStamp().plusDays(pastDays);
        if (thisDate.isAfter(lastFillDate)) {
=======
        if (TimeStamp.getTimeStamp().isAfter(lastFillDate)) {
>>>>>>> 873a9d171a1c18e61906c17c8f889ab4c42e675f
            super.withdrawBalance(amount);
        }
        else {
            System.out.println(getTypeAccount() + " Снятие денег не возможно, т.к. не прошел месяц с последнего пополнения\n========================\n");
        }
    }

    private void setLastFillDate () {
<<<<<<< HEAD
        lastFillDate = TimeStamp.getTimeStamp().now().plusDays(MIN_DAYS_COUNT_TO_WITHDRAW);
    }
}
=======
        lastFillDate = TimeStamp.getTimeStamp().plusDays(MIN_DAYS_COUNT_TO_WITHDRAW);
    }
}
>>>>>>> 873a9d171a1c18e61906c17c8f889ab4c42e675f
