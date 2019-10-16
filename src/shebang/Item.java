package shebang;

public class Item {
    public static final Item[] ITEMS = {
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

    private int value;
    private String name;

    public Item(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
