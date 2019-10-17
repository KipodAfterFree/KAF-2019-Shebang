package quteshell.commands.shebang;

import quteshell.Console;
import quteshell.Quteshell;
import quteshell.command.Command;
import quteshell.command.Elevation;
import quteshell.commands.Help;
import shebang.Item;

@Elevation(Elevation.DEFAULT)
@Help.Description("valueof tells you the value of a specific item.\ne.g. 'valueof phone'")
public class valueof implements Command {
    @Override
    public void execute(Quteshell shell, String arguments) {
        if (arguments != null) {
            Item item = null;
            for (Item i : Item.ITEMS) {
                if (i.getName().toLowerCase().equals(arguments.toLowerCase()))
                    item = i;
            }
            if (item != null) {
                shell.write("The value of 1 " + item.getName() + " is ");
                shell.write(String.valueOf(item.getValue()), Console.Color.LightGreen);
                shell.writeln("$.");
            }else{
                shell.writeln("No such item", Console.Color.Red);
            }
        } else {
            shell.writeln("Missing arguments", Console.Color.Red);
        }
    }
}
