package quteshell.commands.shebang;

import quteshell.Console;
import quteshell.Quteshell;
import quteshell.command.Command;
import quteshell.command.Elevation;
import shebang.Item;
import shebang.Market;
import shebang.Trader;
import shebang.Tuple;

import java.util.ArrayList;

@Elevation(Elevation.DEFAULT)
public class shebang implements Command {
    @Override
    public void execute(Quteshell shell, String arguments) {
        ArrayList<Trader> traders = Market.getMarket(shell.getID());
        boolean hadAll = true;
        for (Trader trader : traders)
            for (Tuple<Integer, Item> entry : trader.getInventory())
                if (entry.getRight().equals(Item.ITEMS[Item.ITEMS.length - 1])) hadAll = false;
        if (hadAll) {
            shell.writeln("Shebang!", Console.Color.LightGreen);
            shell.writeln("KAF{wr1t3_a_5cript_t0_w1nn_th1s_0n3}", Console.Color.LightPurple);
        } else {
            shell.writeln("You don't have all of the flags!", Console.Color.LightRed);
            shell.execute("exit");
        }
    }
}
