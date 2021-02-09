import javax.swing.JOptionPane;

public class ExImposibil extends Exception{
	ExImposibil(String s)
	{
		JOptionPane.showMessageDialog(null, s );
	}

}
