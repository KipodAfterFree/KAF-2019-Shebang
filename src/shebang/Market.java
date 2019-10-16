package shebang;

import quteshell.Console;
import quteshell.Quteshell;

import java.util.ArrayList;
import java.util.Random;

public class Market {

    private static final ArrayList<Tuple<String, ArrayList<Trader>>> markets = new ArrayList<>();

    public static void begin(Quteshell shell) {
        ArrayList<Trader> traders = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            Trader trader = new Trader(traders);
            for (int a = 0; a < 5; a++) {
                trader.add(new Tuple<>(Item.ALL_ITEMS[new Random().nextInt(Item.ALL_ITEMS.length)], new Random().nextInt(10) + 1));
            }
            traders.add(trader);
        }
        markets.add(new Tuple<>(shell.getID(), traders));
        new Thread(() -> {
            while (shell.isRunning()) {
                try {
                    Seller a = choose(traders);
                    Seller b = choose(traders);
                    Tuple<Item, Integer> item = b.inventory.get(new Random().nextInt(b.inventory.size()));
                    int amount = 1;
                    if (item.getRight() > 1)
                        amount += new Random().nextInt(item.getRight() - 1);
                    int pricePerPiece = item.getLeft().value;
                    if (pricePerPiece > 1)
                        pricePerPiece += new Random().nextInt(pricePerPiece / 2) * (new Random().nextInt(2) - 1);
                    shell.writeln();
                    shell.write(a.name + " buys " + amount + " " + item.getLeft().name + " from " + b.name + " at a price of " + pricePerPiece * amount + "$ - ", Console.Color.LightBlue);
                    if (a.buy(pricePerPiece, amount, item.getLeft(), false)) {
                        item.setRight(item.getRight() - amount);
                        if (item.getRight() == 0) {
                            b.inventory.remove(item);
                        }
                        b.money += amount * pricePerPiece;
                        shell.writeln("OK", Console.Color.LightGreen);
                    } else {
                        shell.writeln(a.name + " gone bankrupt.", Console.Color.LightRed);
                    }
                } catch (Exception e) {
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
            }
        }).start();
    }

    private static Seller choose(ArrayList<Seller> sellers) {
        int random = new Random().nextInt(sellers.size());
        if (!sellers.get(random).bankrupt)
            return sellers.get(random);
        return choose(sellers);
    }

    public static ArrayList<Trader> getMarket(String id) {
        for (Tuple<String, ArrayList<Trader>> m : markets) {
            if (m.getLeft().equals(id))
                return m.getRight();
        }
        return new ArrayList<>();
    }
}
