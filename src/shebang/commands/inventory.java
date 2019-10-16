package shebang.commands;


import quteshell.Quteshell;
import quteshell.command.Command;
import quteshell.command.Elevation;
import shebang.Item;
import shebang.Trader;

import java.util.ArrayList;
import java.util.Random;

@Elevation(Elevation.DEFAULT)
public class inventory implements Command {
    @Override
    public void execute(Quteshell shell, String arguments) {
        shell.writeln("Your inventory:");
        for (shebang.Market)
    }

}
