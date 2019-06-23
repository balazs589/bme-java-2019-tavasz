package shapes.commands;

import shapes.exceptions.CommandException;
import shapes.program.ConsoleMenu;
import shapes.program.ShapeRegistry;

/**
 *  Parancsok listazasa.
 */
public class Help extends Command {
    
    private ConsoleMenu menu;
    
    public Help(ConsoleMenu menu, ShapeRegistry s) {
        super(s);
        this.menu = menu;
    }
    
    @Override
    public void action(String[] cmd) throws CommandException {
        if (cmd.length != 1) {
            throw new CommandException("A parancs csak argumentumok nelkul hivhato.");
        }
        menu.printCommands();
    }
    
    @Override
    public String getManual() {
        String s =      ""
                +   "    Parancsok listazasa, pl.:\n"
                +   "    help\n";
        return s;
    }
    
}
