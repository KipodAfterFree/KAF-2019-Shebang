package shebang;

import quteshell.Console;
import quteshell.Quteshell;

import java.util.ArrayList;
import java.util.Random;

public class Market {

    private static final ArrayList<Tuple<String, ArrayList<Seller>>> markets = new ArrayList<>();

    public static void begin(Quteshell shell) {
        new Thread(() -> {
            ArrayList<Seller> sellers = new ArrayList<>();

            Tuple<String, ArrayList<Seller>> market = new Tuple<>(shell.getID(), sellers);
            markets.add(market);
            while (shell.isRunning()) {
                Seller a = market.getB().get(new Random().nextInt(market.getB().size()));
                Seller b = market.getB().get(new Random().nextInt(market.getB().size()));
                shell.write(a.nickname + " to buy from " + b.nickname + ": ", Console.Color.LightCyan);
                if (!a.bankrupt && !b.bankrupt) {
                    Tuple<Item, Integer> iItem = b.inventory.get(new Random().nextInt(b.inventory.size()));
                    Item sell = iItem.getA();
                    int amount = new Random().nextInt(iItem.getB() - 1) + 1;
                    int pricePerPiece = sell.value + new Random().nextInt(sell.value / 2) * (new Random().nextInt(2) - 1);
                    shell.write("Moneyworth: " + pricePerPiece * amount);
                    if (a.buy(pricePerPiece, amount, sell)) {
                        iItem.setB(iItem.getB() - amount);
                        if (iItem.getB() == 0) {
                            b.inventory.remove(iItem);
                        }
                    } else {
                        shell.writeln(a.nickname + " gone bankrupt over " + amount + " " + sell.name);
                    }
                } else {
                    shell.writeln("Bankruptcy error");
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
        }).start();
    }

    public static ArrayList<Seller> getMarket(String id){
        for (Tuple<String, ArrayList<Seller>> m:markets){
            if (m.getA().equals(id))
                return m.getB();
        }
        return new ArrayList<>();
    }

    static class Seller {

        private static final String[] NICKNAMES = {
                "Liam",
                "Emma",
                "Noah",
                "Olivia",
                "William",
                "Ava",
                "James",
                "Isabella",
                "Oliver",
                "Sophia",
                "Benjamin",
                "Charlotte",
                "Elijah",
                "Mia",
                "Lucas",
                "Amelia",
                "Mason",
                "Harper",
                "Logan",
                "Evelyn",
                "Alexander",
                "Abigail",
                "Ethan",
                "Emily",
                "Jacob",
                "Elizabeth",
                "Michael",
                "Mila",
                "Daniel",
                "Ella",
                "Henry",
                "Avery",
                "Jackson",
                "Sofia",
                "Sebastian",
                "Camila",
                "Aiden",
                "Aria",
                "Matthew",
                "Scarlett",
                "Samuel",
                "Victoria",
                "David",
                "Madison",
                "Joseph",
                "Luna",
                "Carter",
                "Grace",
                "Owen",
                "Chloe",
                "Wyatt",
                "Penelope",
                "John",
                "Layla",
                "Jack",
                "Riley",
                "Luke",
                "Zoey",
                "Jayden",
                "Nora",
                "Dylan",
                "Lily",
                "Grayson",
                "Eleanor",
                "Levi",
                "Hannah",
                "Isaac",
                "Lillian",
                "Gabriel",
                "Addison",
                "Julian",
                "Aubrey",
                "Mateo",
                "Ellie",
                "Anthony",
                "Stella",
                "Jaxon",
                "Natalie",
                "Lincoln",
                "Zoe",
                "Joshua",
                "Leah",
                "Christopher",
                "Hazel",
                "Andrew",
                "Violet",
                "Theodore",
                "Aurora",
                "Caleb",
                "Savannah",
                "Ryan",
                "Audrey",
                "Asher",
                "Brooklyn",
                "Nathan",
                "Bella",
                "Thomas",
                "Claire",
                "Leo",
                "Skylar",
                "Isaiah",
                "Lucy",
                "Charles",
                "Paisley",
                "Josiah",
                "Everly",
                "Hudson",
                "Anna",
                "Christian",
                "Caroline",
                "Hunter",
                "Nova",
                "Connor",
                "Genesis",
                "Eli",
                "Emilia",
                "Ezra",
                "Kennedy",
                "Aaron",
                "Samantha",
                "Landon",
                "Maya",
                "Adrian",
                "Willow",
                "Jonathan",
                "Kinsley",
                "Nolan",
                "Naomi",
                "Jeremiah",
                "Aaliyah",
                "Easton",
                "Elena",
                "Elias",
                "Sarah",
                "Colton",
                "Ariana",
                "Cameron",
                "Allison",
                "Carson",
                "Gabriella",
                "Robert",
                "Alice",
                "Angel",
                "Madelyn",
                "Maverick",
                "Cora",
                "Nicholas",
                "Ruby",
                "Dominic",
                "Eva",
                "Jaxson",
                "Serenity",
                "Greyson",
                "Autumn",
                "Adam",
                "Adeline",
                "Ian",
                "Hailey",
                "Austin",
                "Gianna",
                "Santiago",
                "Valentina",
                "Jordan",
                "Isla",
                "Cooper",
                "Eliana",
                "Brayden",
                "Quinn",
                "Roman",
                "Nevaeh",
                "Evan",
                "Ivy",
                "Ezekiel",
                "Sadie",
                "Xavier",
                "Piper",
                "Jose",
                "Lydia",
                "Jace",
                "Alexa",
                "Jameson",
                "Josephine",
                "Leonardo",
                "Emery",
                "Bryson",
                "Julia",
                "Axel",
                "Delilah",
                "Everett",
                "Arianna",
                "Parker",
                "Vivian",
                "Kayden",
                "Kaylee",
                "Miles",
                "Sophie",
                "Sawyer",
                "Brielle",
                "Jason",
                "Madeline"
        };

        private String name = Quteshell.random(4);
        private String nickname = NICKNAMES[new Random().nextInt(NICKNAMES.length)];
        private int money = (1 + new Random().nextInt(10)) * 50;
        private boolean bankrupt = false;
        private ArrayList<Tuple<Item, Integer>> inventory = new ArrayList<>();

        private boolean buy(int ppp, int amount, Item item) {
            if (amount * ppp > money) {
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

    static class Item {

        private static final Item[] ALL_ITEMS = {
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
                new Item("Laptop", 580),
                new Item("Mouse", 50),
                new Item("Paperweight", 25),
                new Item("Cucumber", 5),
                new Item("Tomato", 6),
                new Item("Gameconsole", 80),
                new Item("Flag", 600)
        };

        private String name;
        private int value;

        public Item(String name, int value) {
            this.name = name;
            this.value = value;
        }
    }
}
