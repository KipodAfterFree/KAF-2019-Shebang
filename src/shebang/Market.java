package shebang;

import quteshell.Console;
import quteshell.Quteshell;

import java.util.ArrayList;
import java.util.Random;

public class Market {

    private static final ArrayList<Tuple<String, ArrayList<Seller>>> markets = new ArrayList<>();

    public static void begin(Quteshell shell) {
        ArrayList<Seller> sellers = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            Seller s = new Seller(sellers);
            for (int a = 0; a < 5; a++) {
                s.inventory.add(new Tuple<>(Item.ALL_ITEMS[new Random().nextInt(Item.ALL_ITEMS.length)], new Random().nextInt(10) + 1));
            }
            sellers.add(s);
        }
        markets.add(new Tuple<>(shell.getID(), sellers));
        new Thread(() -> {
            while (shell.isRunning()) {
                try {
                    Seller a = choose(sellers);
                    Seller b = choose(sellers);
                    Tuple<Item, Integer> item = b.inventory.get(new Random().nextInt(b.inventory.size()));
                    int amount = 1;
                    if (item.getB() > 1)
                        amount += new Random().nextInt(item.getB() - 1);
                    int pricePerPiece = item.getA().value;
                    if (pricePerPiece > 1)
                        pricePerPiece += new Random().nextInt(pricePerPiece / 2) * (new Random().nextInt(2) - 1);
                    shell.writeln();
                    shell.write(a.name + " buys " + amount + " " + item.getA().name + " from " + b.name + " at a price of " + pricePerPiece * amount + "$ - ", Console.Color.LightBlue);
                    if (a.buy(pricePerPiece, amount, item.getA(), false)) {
                        item.setB(item.getB() - amount);
                        if (item.getB() == 0) {
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

    public static ArrayList<Seller> getMarket(String id) {
        for (Tuple<String, ArrayList<Seller>> m : markets) {
            if (m.getA().equals(id))
                return m.getB();
        }
        return new ArrayList<>();
    }

    public static class Seller {

        private static final String[] NICKNAMES = {
                "Liam",
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

        public String name = Quteshell.random(4);
        public int money = (1 + new Random().nextInt(10)) * 50;
        public boolean bankrupt = false;
        private ArrayList<Tuple<Item, Integer>> inventory = new ArrayList<>();

        public Seller(ArrayList<Seller> sellers) {
            chooseName(sellers);
        }

        private void chooseName(ArrayList<Seller> sellers) {
            name = NICKNAMES[new Random().nextInt(NICKNAMES.length)];
            for (Seller seller : sellers) {
                if (seller.name.equals(name)) {
                    chooseName(sellers);
                }
            }
        }

        public boolean buy(int ppp, int amount, Item item, boolean bankrupcyProtection) {
            if (amount * ppp > money) {
                if (!bankrupcyProtection)
                    bankrupt = true;
                return false;
            } else {
                boolean added = false;
                money -= amount * ppp;
                for (Tuple<Item, Integer> i : inventory) {
                    if (i.getA().name.equals(item.name)) {
                        i.setB(i.getB() + amount);
                        added = true;
                    }
                }
                if (!added) {
                    inventory.add(new Tuple<>(item, amount));
                }
                return true;
            }
        }
    }

    public static class Item {

        public static final Item[] ALL_ITEMS = {
                new Item("Shampoo", 6),
                new Item("Deoderant", 8),
                new Item("Lemon", 2),
                new Item("Orange", 3),
                new Item("Apple", 4),
                new Item("Potato", 10),
                new Item("GummyBear", 1),
                new Item("Beef", 14),
                new Item("Burger", 10),
                new Item("Egg", 4),
                new Item("WhiteBread", 6),
                new Item("FullBread", 8),
                new Item("Sourcandy", 3),
                new Item("Cutlery", 20),
                new Item("Smartphone", 100),
                new Item("Headphones", 50),
                new Item("Donut", 8),
                new Item("Laptop", 105),
                new Item("Mouse", 50),
                new Item("Paperweight", 25),
                new Item("Cucumber", 5),
                new Item("Tomato", 6),
                new Item("Kipod", 4),
                new Item("Gameconsole", 40),
                new Item("Flag", 150)
        };

        public String name;
        private int value;

        public Item(String name, int value) {
            this.name = name;
            this.value = value;
        }
    }
}
