import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;


public class Main {
	public static void main(String[] args) {

		final SplashScreen splashStart = new SplashScreen();
	    splashStart.showSplashScreen();
	    
		//Setup the menu
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		final JMenuItem quit = new JMenuItem("Quit");
		file.add(quit);
		menuBar.add(file);
		//open menu hit Q key to quit
		quit.setMnemonic('Q');
		//any time in application press CNTRL-Q to quit
		quit.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.Event.CTRL_MASK));
		splashStart.getFrame().setJMenuBar(menuBar);
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				if(quit.getText().equals("Quit")){
					System.exit(0);
				}
				else{
					assert false;
				}
			}

		});	
		//end of menu setup
	  }
}
