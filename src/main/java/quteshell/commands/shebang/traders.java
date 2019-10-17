package quteshell.commands.shebang;

import quteshell.Console;
import quteshell.Quteshell;
import quteshell.command.Command;
import quteshell.command.Elevation;
import shebang.Market;
import shebang.Trader;

import java.util.ArrayList;

@Elevation(Elevation.DEFAULT)
public class traders implements Command {

    @Override
    public void execute(Quteshell shell, String arguments) {
        ArrayList<Trader> traders = Market.getMarket(shell.getID());
        for (Trader trader : traders) {
            shell.write(trader.getName() + " - ");
            shell.writeln((trader.isBankrupt() ? "Bankrupt" : "OK"), trader.isBankrupt() ? Console.Color.LightRed : Console.Color.LightGreen);
        }
    }

}
