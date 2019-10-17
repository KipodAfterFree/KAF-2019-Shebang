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
        shell.writeln("╔═════════════════════════════════════════╗");
        shell.writeln("║ Welcome to the 'Shebang!' game!         ║");
        shell.writeln("║ You are now logged in as " + shell.getID() + "           ║");
        shell.writeln("║ You can type 'help' for actions.        ║");
        shell.writeln("║           Game introduction             ║");
        shell.writeln("║ Buy and sell items at the market.       ║");
        shell.writeln("║ Your mission is to find all the flags,  ║");
        shell.writeln("║ then buy them all.                      ║");
        shell.writeln("║ When you have them all, type 'shebang'. ║");
        shell.writeln("╚═════════════════════════════════════════╝");
        shell.execute("money");
    }
}
