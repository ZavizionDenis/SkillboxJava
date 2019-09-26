package TypeBankClients;

import java.util.Locale;

public class Physical extends Clients {

    public Physical(int clientAccountNumber, double clientAccountBalance) {
        super(clientAccountNumber, clientAccountBalance);
    }

    @Override
    public String getType() {
        return "Физ. лицо";
    }

    @Override
    public void fillBalance(double amount) {
        System.out.printf(Locale.ENGLISH,"Баланс на начало операции: %.2f%n", getBalance());
        this.clientAccountBalance += amount;
        System.out.printf(Locale.ENGLISH, "Сумма зачисления: %.2f, остаток счета: %.2f, операция выполнена успешно.%n====================%n", amount, getBalance());
    }

    @Override
    public boolean withdrawalBalance(double amount) {
        boolean isMonyEnough;
        if (amount <= clientAccountBalance) {
            isMonyEnough = true;
            System.out.printf(Locale.ENGLISH,"Баланс на начало операции: %.2f%n", getBalance());
            this.clientAccountBalance -= amount;
            System.out.printf(Locale.ENGLISH, "Сумма снятия: %.2f, остаток счета: %.2f, операция выполнена успешно.%n====================%n", amount, getBalance());
        }
        else {
            isMonyEnough = false;
            System.out.printf(Locale.ENGLISH, "Сумма снятия: %.2f больше баланса счета: %.2f, операция не возможна.%n====================%n", amount, getBalance());
        }
        return isMonyEnough;
    }
}
