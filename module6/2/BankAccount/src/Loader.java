public class Loader
{
    private static final long TIME_DELAY = 2000;

    private static final String [] TYPE_OPERATION = {"GET_BALANCE", "FILL_BALANCE", "WITHDRAW_BALANCE"};

    public static void main(String[] args) {

        BankAccount [] accounts = new BankAccount [3];
        accounts [0] = new BankAccount(getStartBalance(), "Расчетный счет");
        accounts [1] = new DepositAccount(getStartBalance(), "Депозитарный счет");
        accounts [2] = new CardAccount(getStartBalance(), "Карточный счет");

        for (BankAccount bankAccount : accounts) {
            System.out.println(bankAccount.getTypeAccount() + bankAccount.getBalance());
        }

        for (; ;) {
            try {
                Thread.currentThread().sleep(TIME_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (BankAccount bankAccount : accounts) {

                int operationIndex = (int) Math.round(0 + 2 * Math.random());
                if (TYPE_OPERATION[operationIndex].equals("GET_BALANCE")) {
                    System.out.println("Запрос баланса:\n========================\n");
                    System.out.println(bankAccount.getTypeAccount() + bankAccount.getBalance());
                } else if (TYPE_OPERATION[operationIndex].equals("FILL_BALANCE")) {
                    System.out.println("Пополнение счета:\n========================\n");
                    bankAccount.fillBalance(generateAmount());
                } else if (TYPE_OPERATION[operationIndex].equals("WITHDRAW_BALANCE")) {
                    System.out.println("Вывод средств со счета:\n========================\n");
                    bankAccount.withdrawBalance(generateAmount());
                }
            }
        }
    }

    private static double getStartBalance () {
        return Math.round(100_000 + 100_000 * Math.random());
    }
    private static double generateAmount () {
        return Math.round(100 + 1_000 * Math.random());
    }
}