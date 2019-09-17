public class CardAccount extends BankAccount {

    public CardAccount(double balance, String typeAccount) {
        super(balance, typeAccount);
    }

    @Override
    public void withdrawBalance(double amount) {
        System.out.println("Сумма для снятия с карточного счета: " + amount);
        double commision = amount / 100;
        System.out.println("Комиссия за снятие средств с карточного счета: " + commision);
        super.withdrawBalance(amount + commision);
    }
}
