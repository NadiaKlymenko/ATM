package main.myatm;

public class ATM {
    private double moneyInATM;
    private Card card;

    //Можно задавать количество денег в банкомате 
    public ATM(double moneyInATM) {
        if (moneyInATM < 0) {
            throw new IllegalArgumentException();
        }
        this.moneyInATM = moneyInATM;
        this.card = null;
    }

    // Возвращает каоличестов денег в банкомате
    public double getMoneyInATM() {
        return this.moneyInATM;
    }

    //С вызова данного метода начинается работа с картой
    //Метод принимает карту и пин-код, проверяет пин-код карты и не заблокирована ли она
    //Если неправильный пин-код или карточка заблокирована, возвращаем false. При этом, вызов всех последующих методов у ATM с данной картой должен генерировать исключение NoCardInserted
    public boolean validateCard(Card card, int pinCode) {
        if (card == null) {
            throw new NullPointerException("Enter real card");
        } else if (card.isBlocked() && !card.checkPin(pinCode)) {
            return false;
        } else {
            this.card = card;
            return true;
        }
    }

    //Возвращает сколько денег есть на счету
    public double checkBalance() throws NoCardExeption {
        checkCard();
        return card.getAccount().getBalance();

    }

    //Метод для снятия указанной суммы
    //Метод возвращает сумму, которая у клиента осталась на счету после снятия
    //Кроме проверки счета, метод так же должен проверять достаточно ли денег в самом банкомате
    //Если недостаточно денег на счете, то должно генерироваться исключение NotEnoughMoneyInAccount  +
    //Если недостаточно денег в банкомате, то должно генерироваться исключение NotEnoughMoneyInATM +
    //При успешном снятии денег, указанная сумма должна списываться со счета, и в банкомате должно уменьшаться количество денег


    public double getCash(double amount) throws NoCardExeption, NotEnoughMoneyOnCardException, NotEnoughMoneyInATMExeption {
        checkCard();
        Account account = this.card.getAccount();
        if (account.getBalance() < amount) {
            throw new NotEnoughMoneyOnCardException();
        } else if (this.getMoneyInATM() < amount) {
            throw new NotEnoughMoneyInATMExeption();
        } else {
            this.moneyInATM -= account.withdrow(amount);
            return account.getBalance();
        }

    }

    private void checkCard() throws NoCardExeption {
        if (this.card == null) {
            throw new NoCardExeption();
        }
    }
}