package shebang;

import quteshell.Quteshell;
import shebang.Trader.User;

import java.util.ArrayList;
import java.util.Random;

public class Market {

    private static final ArrayList<User> users = new ArrayList<User>();

    private static final ArrayList<Tuple<String, ArrayList<Trader>>> markets = new ArrayList<>();

    public static void begin(Quteshell shell) {
        ArrayList<Trader> traders = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            Trader trader = new Trader(traders);

            traders.add(trader);
        }
        markets.add(new Tuple<>(shell.getID(), traders));
        new Thread(() -> {
            while (shell.isRunning()) {
                try {
                    Trader buyer = choose(traders);
                    Trader seller = choose(traders);
                    Tuple<Integer, Item> item = seller.getInventory().get(new Random().nextInt(seller.getInventory().size()));
                    int amount = 1;
                    if (item.getLeft() > 1)
                        amount += new Random().nextInt(item.getLeft() - 1);
                    int pricePerPiece = item.getRight().getValue();
                    if (pricePerPiece > 1)
                        pricePerPiece += new Random().nextInt(pricePerPiece / 2) * (new Random().nextInt(2) - 1);
                    buyer.buy(shell, seller, item.getRight(), amount, pricePerPiece, false);
                } catch (Exception e) {
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
            }
        }).start();
    }

    private static Trader choose(ArrayList<Trader> traders) {
        int random = new Random().nextInt(traders.size());
        if (!traders.get(random).isBankrupt())
            return traders.get(random);
        return choose(traders);
    }

    public static ArrayList<Trader> getMarket(String id) {
        for (Tuple<String, ArrayList<Trader>> m : markets) {
            if (m.getLeft().equals(id))
                return m.getRight();
        }
        return new ArrayList<>();
    }

    public static User getUser(String id){
        for (User user:users){
            if (user.getID().equals(id)){
                return user;
            }
        }
        User user = new User(id);
        users.add(user);
        return user;
    }
}
