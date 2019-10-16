package shebang.commands;

import quteshell.Console;
import quteshell.Quteshell;
import quteshell.command.Command;
import quteshell.command.Elevation;
import shebang.Market;

import java.util.ArrayList;

@Elevation(Elevation.DEFAULT)
public class money implements Command {

    @Override
    public void execute(Quteshell shell, String arguments) {
        shell.write("You have ");
        shell.write(String.valueOf(Market.getUser(shell.getID())), Console.Color.LightGreen);
        shell.writeln("$.");
    }
}
