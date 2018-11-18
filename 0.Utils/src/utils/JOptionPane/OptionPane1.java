package utils.JOptionPane;

import javax.swing.JOptionPane;

public interface OptionPane1 {
	int a = 0;
	a = JOptionPane.showConfirmDialog(null, "La secuencia no esta guardada", "WARNING", JOptionPane.WARNING_MESSAGE);
	if (a == JOptionPane.YES_OPTION) {
        a = JOptionPane.showConfirmDialog(null, "Realmente estas seguro?");
            if (a == JOptionPane.YES_OPTION) {
            	//lo que sea
            	
            }
	}
}}

