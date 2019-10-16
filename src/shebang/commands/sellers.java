package shebang.commands;

import quteshell.Console;
import quteshell.Quteshell;
import quteshell.command.Command;
import quteshell.command.Elevation;
import shebang.Market;

import java.util.ArrayList;

@Elevation(Elevation.DEFAULT)
public class sellers implements Command {

    @Override
    public void execute(Quteshell shell, String arguments) {
        ArrayList<Market.Seller> sellers = Market.getMarket(shell.getID());
        for (Market.Seller seller : sellers) {
            shell.write(seller.name + " - ");
            shell.writeln((seller.bankrupt ? "Bankrupt" : "OK"), seller.bankrupt ? Console.Color.LightRed : Console.Color.LightGreen);
        }
    }

}
