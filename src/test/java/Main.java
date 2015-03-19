import ru.cwl.kon.model.Account;

/**
 * Created by vad on 19.03.2015 20:56.
 */
public class Main {
    public static void main(String[] args) {
        Account accounts[] = {
                createAccount(1, "кошелек", 600, "руб"),
                createAccount(2, "конверт руб", 120000, "руб"),
                createAccount(3, "конверт евр", 1850, "евро"),
                createAccount(4, "конверт дол", 2440, "долларов"),
                createAccount(5, "карта МДМ виза", 56999, "руб"),
                createAccount(6, "карта МДМ мастеркард", 0, "руб"),
                createAccount(7, "карта Сбер", 7031, "руб"),
                createAccount(8, "киви мегафон", 944/*.46*/, "руб"),
                createAccount(9, "киви билайн", 2570, "руб")
        };


    }

    private static Account createAccount(int id, String name, int amount, String currency) {
        return new Account(id, name, amount, currency);
    }
    //static Account createAccount
}
