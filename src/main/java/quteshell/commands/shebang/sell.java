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
@Help.Description("sell - offer a trader an item from your inventory.\ne.g. 'sell 1 potato nadav 12' or 'sell [Amount] [Item] [Trader] [Price per Piece]'")
public class sell implements Command {
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
                    Item item = null;
                    for (Tuple<Integer, Item> i : user.getInventory()) {
                        if (i.getRight().getName().toLowerCase().equals(itemName.toLowerCase()))
                            item = i.getRight();
                    }
                    Trader trader = null;
                    for (Trader t : Market.getMarket(shell.getID())) {
                        if (t.getName().toLowerCase().equals(traderName.toLowerCase()))
                            trader = t;
                    }
                    if (trader != null) {
                        if (!trader.isBankrupt()) {
                            if (item != null) {
                                shell.write("You", Console.Color.LightCyan);
                                shell.write(" offered " + trader.getName() + " to buy " + amount + " " + item.getName() + " at a price of " + ppp * amount + "$ - ", Console.Color.LightBlue);
                                if (ppp <= (1.3 * ((double) item.getValue()))) {
                                    shell.writeln("Approved", Console.Color.LightGreen);
                                    trader.buy(shell, user, item, amount, ppp, true);
                                } else {
                                    shell.writeln("Declined", Console.Color.LightRed);
                                }
                            } else {
                                shell.writeln("You don't have that item", Console.Color.Red);
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
                shell.writeln("Too many arguments", Console.Color.Red);
            }
        } else {
            shell.writeln("Missing arguments", Console.Color.Red);
        }
    }
}
