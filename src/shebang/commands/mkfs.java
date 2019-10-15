package shebang.commands;

import quteshell.Console;
import quteshell.Quteshell;
import quteshell.command.Command;
import quteshell.command.Elevation;
import shebang.FSMan;

@Elevation(Elevation.DEFAULT)
public class mkfs implements Command {
    @Override
    public void execute(Quteshell shell, String arguments) {
        if (FSMan.getFS("u-" + shell.getID()) == null) {
            FSMan.createFS("u-" + shell.getID());
        } else {
            shell.writeln("Filesystem u-" + shell.getID() + " exists already.", Console.Color.LightRed);
        }
    }
}
