package shebang.commands;


import quteshell.Quteshell;
import quteshell.command.Command;
import quteshell.command.Elevation;

import java.util.ArrayList;
import java.util.Random;

@Elevation(Elevation.DEFAULT)
public class inventory implements Command {

    private static ArrayList<User> users = new ArrayList<User>();

    @Override
    public void execute(Quteshell shell, String arguments) {

    }

    public static class User extends Trader {

        public User() {
            for (int i = 0; i < 4; i++) {
                add(Item.ITEMS[new Random().nextInt(Item.ITEMS.length - 1)], new Random(.nextInt(4) + 1));
            }
        }
    }
}
