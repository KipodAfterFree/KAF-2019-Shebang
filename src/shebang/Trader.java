package shebang;

import quteshell.Console;
import quteshell.Quteshell;

import java.util.ArrayList;
import java.util.Random;

public class Trader {

    private static final String[] NICKNAMES = {
            "Charlotte",
            "Emma",
            "Noah",
            "Emily",
            "Jacob",
            "Nir",
            "Michael",
            "Jackson",
            "Nadav",
            "Amit",
            "Jack",
            "Riley",
            "Luke",
            "Stella",
            "Leah",
            "Caleb",
            "Brooklyn",
            "Nathan",
            "Thomas",
            "Claire",
            "Leo",
            "Lucy",
            "Anna",
            "Caroline",
            "Connor",
            "Emilia",
            "Aaron",
            "Samantha",
            "Landon",
            "Maya",
            "Ruby",
            "Eva",
            "Jaxson",
            "Ian",
            "Quinn",
            "Roman",
            "Ivy",
            "Sadie",
            "Xavier",
            "Jose",
            "Lydia",
            "Alexa",
            "Leonardo",
            "Bryson",
            "Arianna",
            "Parker",
            "Sophie",
            "Jason",
            "Madeline"
    };

    private int balance;
    private String name;
    private boolean bankrupt = false;
    private ArrayList<Tuple<Integer, Item>> inventory = new ArrayList<>();

    public Trader() {
        balance = 200;
        name = null;
    }

    public Trader(ArrayList<Trader> companions) {
        balance = (new Random().nextInt(9) + 1) * 50;
        pickName(companions);
        for (int a = 0; a < 5; a++) {
            add(Item.ITEMS[new Random().nextInt(Item.ITEMS.length)], new Random().nextInt(9) + 1);
        }
    }

    private void pickName(ArrayList<Trader> companions) {
        name = NICKNAMES[new Random().nextInt(NICKNAMES.length)];
        for (Trader companion : companions)
            if (companion.name.equals(name))
                pickName(companions);
    }

    public boolean buy(Quteshell quteshell, Trader from, Item item, int amount, int price, boolean bankrupcyProtection) {
        int totalPrice = amount * price;
        if (name == null) {
            quteshell.write("You", Console.Color.LightCyan);
            quteshell.write(" buy ", Console.Color.LightBlue);
        } else {
            quteshell.write(name + " buys ", Console.Color.LightBlue);
        }
        quteshell.write(amount + " " + item.getName() + " from ", Console.Color.LightBlue);
        quteshell.write(from.name == null ? "You" : from.name, from.name == null ? Console.Color.LightCyan : Console.Color.LightBlue);
        quteshell.write(" at a price of " + totalPrice + "$ - ", Console.Color.LightBlue);
        if (totalPrice > balance) {
            // Can cause bankrupcy
            if (bankrupcyProtection) {
                quteshell.writeln("Not enough money", Console.Color.LightRed);
            } else {
                quteshell.writeln(name + " went bankrupt", Console.Color.LightRed);
                bankrupt = true;
            }
            return false;
        } else {
            Tuple<Integer, Item> entry = from.find(item);
            if (entry != null) {
                if (entry.getLeft() >= amount) {
                    balance -= totalPrice;
                    from.remove(item, amount);
                    this.add(item, amount);
                    quteshell.writeln("OK", Console.Color.LightGreen);
                    return true;
                } else {
                    quteshell.writeln("Not enough cargo", Console.Color.LightRed);
                    return false;
                }
            } else {
                quteshell.writeln("No such cargo", Console.Color.LightRed);
                return false;
            }
        }
    }

    protected Tuple<Integer, Item> find(Item item) {
        for (Tuple<Integer, Item> i : inventory) {
            if (i.getRight().equals(item)) {
                return i;
            }
        }
        return null;
    }

    protected void add(Item item, int amount) {
        Tuple<Integer, Item> entry = find(item);
        if (entry != null) {
            entry.setLeft(entry.getLeft() + amount);
        } else {
            entry = new Tuple<>(amount, item);
            inventory.add(entry);
        }
    }

    protected void remove(Item item, int amount) {
        Tuple<Integer, Item> entry = find(item);
        if (entry != null) {
            entry.setLeft(entry.getLeft() - amount);
            if (entry.getLeft() <= 0)
                inventory.remove(entry);
        }
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public ArrayList<Tuple<Integer, Item>> getInventory() {
        return inventory;
    }

    public boolean isBankrupt() {
        return bankrupt;
    }

    public static class User extends Trader {

        private String id;

        public User(String id) {
            this.id = id;
            for (int i = 0; i < 4; i++) {
                add(Item.ITEMS[new Random().nextInt(Item.ITEMS.length - 1)], new Random().nextInt(4) + 1);
            }
        }

        public String getID() {
            return id;
        }
    }
}
