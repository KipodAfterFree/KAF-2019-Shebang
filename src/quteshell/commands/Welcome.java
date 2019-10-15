package quteshell.commands;

import quteshell.Quteshell;
import quteshell.command.Command;
import quteshell.command.Elevation;

/**
 * Copyright (c) 2019 Nadav Tasher
 * https://github.com/NadavTasher/Quteshell/
 **/

@Elevation(Elevation.DEFAULT)
@Help.Description("The welcome command displays a welcome message.")
public class Welcome implements Command {
    @Override
    public void execute(Quteshell shell, String arguments) {
        shell.writeln("╔═══════════════════════════════════╗");
        shell.writeln("║ You are now logged in as " + shell.getID() + "@qs. ║");
        shell.writeln("║ You can type 'help' for commands. ║");
        shell.writeln("║ For filesystem access, use 'mkfs' ║");
        shell.writeln("╚═══════════════════════════════════╝");
    }
}
