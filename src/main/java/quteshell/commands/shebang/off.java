package quteshell.commands.shebang;

import quteshell.Quteshell;
import quteshell.command.Command;
import quteshell.command.Elevation;
import quteshell.commands.Help;

@Elevation(Elevation.DEFAULT)
@Help.Description("off pauses the output of one command, then prints the buffered output.")
public class off implements Command {
    @Override
    public void execute(Quteshell shell, String arguments) {
        shell.pause();
    }
}
