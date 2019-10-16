package shebang.commands;

import quteshell.Quteshell;
import quteshell.command.Command;
import quteshell.command.Elevation;
import shebang.Market;
import shebang.Market.Item;
import shebang.Tuple;

import java.util.ArrayList;
import java.util.Random;

@Elevation(Elevation.DEFAULT)
public class inventory implements Command {

    private static ArrayList<User> users = new ArrayList<User>();

    @Override
    public void execute(Quteshell shell, String arguments) {

    }

    public static class User {
        private String name;
        private int amount = 200;
        private ArrayList<Tuple<Market.Item, Integer>> inventory = new ArrayList<Tuple<Market.Item, Integer>>();

        public User(String name) {
            this.name = name;
            for (int i = 0; i < 4; i++) {
                inventory.add(new Tuple<>(Item.ALL_ITEMS[Item.ALL_ITEMS.length - 1], new Random().nextInt(4) + 1));
            }
        }

        private boolean sell(shebang.Market.Seller to, Item what, int howMany, int ppp){
            boolean success = to.buy(ppp, amount, what, true);

        }

        private boolean buy(int ppp, int amount, Item item) {
            if (amount * ppp > amount) {
                return false;
            } else {
                boolean added = false;
                amount -= amount * ppp;
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
}
