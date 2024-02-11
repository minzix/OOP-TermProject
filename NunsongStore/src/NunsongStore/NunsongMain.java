package NunsongStore;

import javax.swing.SwingUtilities;

public class NunsongMain {
	public static void main(String[] args) {
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            new LoginFrame().setVisible(true);
	        }
	    });
	}
}
