package shebang.commands;

import quteshell.Console;
import quteshell.Quteshell;
import quteshell.command.Command;
import quteshell.command.Elevation;
import shebang.Item;
import shebang.Market;
import shebang.Tuple;

@Elevation(Elevation.DEFAULT)
public class inventory implements Command {
    @Override
    public void execute(Quteshell shell, String arguments) {
        int summery = 0;
        shell.writeln("Your inventory:");
        for (Tuple<Integer, Item> entry : Market.getUser(shell.getID()).getInventory()) {
            shell.write(entry.getRight().getName() + ", " + entry.getLeft() + "pcs, totaling ");
            shell.write(String.valueOf(entry.getLeft() * entry.getRight().getValue()), Console.Color.LightGreen);
            shell.writeln("$");
            summery += entry.getLeft() * entry.getRight().getValue();
        }
        shell.write("Your inventory totals to ");
        shell.write(String.valueOf(summery), Console.Color.LightGreen);
        shell.write("$, and you total to ");
        shell.write(String.valueOf(Market.getUser(shell.getID()).getBalance() + summery), Console.Color.LightGreen);
        shell.writeln("$");
    }

}
