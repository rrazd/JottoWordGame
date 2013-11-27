import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;


public class SplashScreen extends JWindow{
	
	private JFrame frame = new JFrame("JOTTO");
	private Model model;
	private JPanel mainPanel = new JPanel();
	
	
	public void showSplashScreen() {

		int splashWidth = 700;
		int splashHeight = 700;

		JPanel content = (JPanel)getContentPane();
		content.setBackground(Color.white);

		int[] splashData = autoAdjustPosition(splashWidth, splashHeight);
		
		setBounds(splashData[0], splashData[1],splashWidth,splashHeight);

		ImageIcon icon = createImageIcon("splash.jpg");
		JLabel splash;
		splash = new JLabel(icon, SwingConstants.CENTER);
		add(splash);

		// Show splash screen
		setVisible(true);
		
		//initialize game view on click
		this.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
				
				model = new Model();
				
				//making connection between model and view
				View_GameBoard gameBoardView = new View_GameBoard(model);
				View_AlphabetStatus alphabetStatusView = new View_AlphabetStatus(model);
				View_Hints settingsView = new View_Hints(model);
				
				model.addView(gameBoardView);
				model.addView(alphabetStatusView);
				model.addView(settingsView);
				
				//start new game
				model.newGame();
				
				// create the main underlying layout
				BorderLayout mainLayout = new BorderLayout();
				mainPanel.setLayout(mainLayout);

				
				//position the main views in main Panel
				mainPanel.add(gameBoardView, BorderLayout.CENTER);
				mainPanel.add(alphabetStatusView, BorderLayout.PAGE_START);
				mainPanel.add(settingsView, BorderLayout.PAGE_END);
				
				//add our main panel to frame
				frame.getContentPane().add(mainPanel);			
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				int frameWidth = 700;
				int frameHeight = 700;
				int[] frameData = autoAdjustPosition(frameWidth, frameHeight);
				frame.setBounds(frameData[0], frameData[1], frameWidth, frameHeight);
				frame.pack();
				frame.setMinimumSize(new Dimension(630, 735));
				//show the main game play screen 
				frame.setVisible(true);			
			}
		});
		
		mainPanel.addComponentListener(new ComponentListener() {

			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentResized(ComponentEvent arg0) {
				Component c = (Component) arg0.getSource();
				//System.out.println("frame height: " + c.getSize().height);
				model.setFrameDim(c.getSize());	
				
			}

			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	public JFrame getFrame(){
		return frame;
	}
	
	private int[] autoAdjustPosition(int splashWidth, int splashHeight) {
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		//auto adjust to desired position
		int[] data = new int[2];
		data[0] = (screenDimension.width-splashWidth)/2;
		data[1] = (screenDimension.height-splashHeight)/2;
		return data;
	}
	
	protected static ImageIcon createImageIcon(String filePath) {
		java.net.URL url = SplashScreen.class.getResource(filePath);
		if (url != null) {
			return new ImageIcon(url);
		}
		return null;
	}

	public Model getModel(){
		return model;
	}
	
}