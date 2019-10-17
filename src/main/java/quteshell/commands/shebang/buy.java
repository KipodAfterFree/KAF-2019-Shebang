package quteshell.commands.shebang;

import quteshell.Console;
import quteshell.Quteshell;
import quteshell.command.Command;
import quteshell.command.Elevation;
import quteshell.commands.Help;
import shebang.Item;
import shebang.Market;
import shebang.Trader;
import shebang.Trader.User;
import shebang.Tuple;

@Elevation(Elevation.DEFAULT)
@Help.Description("buy - buy from a trader.\ne.g. 'buy 1 mouse maya 36' or 'buy [Amount] [Item] [Trader] [Price per Piece]'")
public class buy implements Command {
    @Override
    public void execute(Quteshell shell, String arguments) {
        if (arguments != null) {
            String[] args = arguments.split(" ");
            if (args.length == 4) {
                String amountWord = args[0];
                String itemName = args[1];
                String traderName = args[2];
                String pppWord = args[3];
                try {
                    int amount = Integer.parseInt(amountWord);
                    int ppp = Integer.parseInt(pppWord);
                    User user = Market.getUser(shell.getID());
                    Trader trader = null;
                    for (Trader t : Market.getMarket(shell.getID())) {
                        if (t.getName().toLowerCase().equals(traderName.toLowerCase()))
                            trader = t;
                    }
                    if (trader != null) {
                        if (!trader.isBankrupt()) {
                            Item item = null;
                            for (Tuple<Integer, Item> i : trader.getInventory()) {
                                if (i.getRight().getName().toLowerCase().equals(itemName.toLowerCase()))
                                    item = i.getRight();
                            }
                            if (item != null) {
                                shell.write("You", Console.Color.LightCyan);
                                shell.write(" offered to buy " + amount + " " + item.getName() + " from " + trader.getName() + " at a price of " + ppp * amount + "$ - ", Console.Color.LightBlue);
                                if (ppp >= (0.8 * ((double) item.getValue()))) {
                                    shell.writeln("Approved", Console.Color.LightGreen);
                                    user.buy(shell, trader, item, amount, ppp, true);
                                } else {
                                    shell.writeln("Declined", Console.Color.LightRed);
                                }
                            } else {
                                shell.writeln(trader.getName() + " doesn't have that item", Console.Color.Red);
                            }
                        } else {
                            shell.writeln(trader.getName() + " is bankrupt", Console.Color.Red);
                        }
                    } else {
                        shell.writeln("No such trader", Console.Color.Red);
                    }
                } catch (Exception e) {
                    shell.writeln("Number conversion error", Console.Color.Red);
                }
            } else {
                shell.writeln("Too many/few arguments", Console.Color.Red);
            }
        } else {
            shell.writeln("Missing arguments", Console.Color.Red);
        }
    }
}
