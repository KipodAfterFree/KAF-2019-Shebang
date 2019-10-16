package shebang.commands;

import quteshell.Console;
import quteshell.Quteshell;
import quteshell.command.Command;
import quteshell.command.Elevation;
import shebang.Tuple;

import java.util.ArrayList;

@Elevation(Elevation.DEFAULT)
public class money implements Command {

    private static final int INITIAL_MONEY = 200;

    private static final ArrayList<Tuple<String, Integer>> moneys = new ArrayList<>();

    @Override
    public void execute(Quteshell shell, String arguments) {
        shell.write("You have ");
        shell.write(String.valueOf(getMoney(shell.getID())), Console.Color.LightGreen);
        shell.writeln("$.");
    }

    static int getMoney(String id) {
        for (Tuple<String, Integer> t : moneys) {
            if (t.getA().equals(id))
                return t.getB();
        }
        moneys.add(new Tuple<>(id, INITIAL_MONEY));
        return INITIAL_MONEY;
    }

    static void add(String id, int amount) {
        for (Tuple<String, Integer> t : moneys) {
            if (t.getA().equals(id))
                t.setB(t.getB() + amount);
        }
    }

    static boolean deduct(String id, int amount) {
        for (Tuple<String, Integer> t : moneys) {
            if (t.getA().equals(id))
                if (t.getB() - amount < 0)
                    return false;
            t.setB(t.getB() - amount);
        }
        return false;
    }
}
