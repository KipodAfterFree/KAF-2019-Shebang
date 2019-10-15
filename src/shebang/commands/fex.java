package shebang.commands;

import quteshell.Quteshell;
import quteshell.command.Command;
import quteshell.command.Elevation;
import quteshell.commands.Help;
import shebang.FSMan;
import shebang.File;

import java.util.ArrayList;

@Elevation(Elevation.DEFAULT)
@Help.Description("FileEXecute takes a file name and runs it in a new virtual environment.")
public class fex implements Command {
    @Override
    public void execute(Quteshell shell, String arguments) {
        shell.writeln("Loading filesystem u-" + shell.getID());
        ArrayList<File> fs = FSMan.getFS("u-" + shell.getID());
        if (fs != null) {
            File fileToExecute = null;
            for (File f : fs) {
                if (f.name.equals(arguments)) {
                    fileToExecute = f;
                    break;
                }
            }
            if (fileToExecute != null) {
                shell.writeln("File found");
                String fsid = "temp-" + shell.random(4) + "-" + shell.getID();
                shell.writeln("Creating filesystem " + fsid);
            } else {
                shell.writeln("File not found");
            }
        } else {
            shell.writeln("Unable to load filesystem u-" + shell.getID());
        }
    }
}
