package shebang.commands;

import quteshell.Quteshell;
import quteshell.command.Command;
import shebang.Tuple;

import java.util.ArrayList;
import java.util.Random;

public class sellers implements Command {

    @Override
    public void execute(Quteshell shell, String arguments) {
        ArrayList<shebang.Market.Seller> sellers = Market.getMarket(shell.getID());
    }

}
