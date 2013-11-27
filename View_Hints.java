import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;


public class View_Hints extends JPanel implements IView{

	private Model jottoModel;
	private JLabel showWordHints = new JLabel();
	//one time jList creation
	private JList possibleWordsJList = new JList();
	//container for list of words
	private JScrollPane scrollPane = new JScrollPane();
	//default message
	private JTextField messagesField = new JTextField(41);
	private JButton newGameButton;
	private JButton giveUpButton;
	private JButton showHintButton;
	private ImageIcon showHint;



	public View_Hints(Model model){
		jottoModel = model;
		initialize();
	}

	public void initialize(){

		JPanel hintsPane = new JPanel();
		JPanel settingAndHintsPane = new JPanel();
		JPanel buttonsPane = new JPanel();
		JPanel wordHintsTitle = new JPanel();
		JPanel wordHints = new JPanel();
		JPanel settingsPane = new JPanel();
		JPanel messagesPane = new JPanel();
		JPanel overallPane = new JPanel();

		hintsPane.setLayout(new BoxLayout(hintsPane, BoxLayout.PAGE_AXIS));
		buttonsPane.setLayout(new BoxLayout(buttonsPane, BoxLayout.LINE_AXIS));
		wordHintsTitle.setLayout(new BoxLayout(wordHintsTitle, BoxLayout.LINE_AXIS));
		wordHints.setLayout(new BoxLayout(wordHints, BoxLayout.PAGE_AXIS));
		settingAndHintsPane.setLayout(new BoxLayout(settingAndHintsPane, BoxLayout.LINE_AXIS));
		settingsPane.setLayout(new BoxLayout(settingsPane, BoxLayout.PAGE_AXIS));
		hintsPane.setPreferredSize(new Dimension(280,240));
		settingsPane.setPreferredSize(new Dimension(350,240));
		messagesPane.setLayout(new BoxLayout(messagesPane, BoxLayout.LINE_AXIS));
		overallPane.setLayout(new BoxLayout(overallPane, BoxLayout.PAGE_AXIS));


		//game messages
		JLabel messageTitle = new JLabel(" Game Says:");
		//initial default message, all subsequent messages are set via the model's setMessage()
		messagesField.setEditable(false);
		messagesField.setBackground(Color.getHSBColor(67, 44, 100));
		messagesPane.add(messageTitle);
		messagesPane.add(Box.createRigidArea(new Dimension(10,0)));
		messagesPane.add(messagesField);
		messagesPane.add(Box.createRigidArea(new Dimension(0,40)));

		overallPane.add(messagesPane);
		overallPane.add(Box.createRigidArea(new Dimension(0,20)));

		//setting up hints pane
		wordHintsTitle.add(showWordHints);
		wordHintsTitle.add(Box.createRigidArea(new Dimension(310,0)));
		wordHints.add(scrollPane);
		hintsPane.add(Box.createRigidArea(new Dimension(0,5)));
		hintsPane.add(wordHintsTitle);
		hintsPane.add(wordHints);
		hintsPane.add(buttonsPane);
		hintsPane.setBorder(BorderFactory.createTitledBorder(null, "Hints", TitledBorder.CENTER, TitledBorder.CENTER, null, null));


		//setting up settings pane
		ImageIcon newGame = new ImageIcon("images/newGame.png");
		newGameButton = new JButton(newGame);
		newGameButton.setText("<html>Start a New Game Please<br/></html>");
		newGameButton.setFocusable(false);

		ImageIcon giveUp = new ImageIcon("images/giveUp.png");
		giveUpButton = new JButton(giveUp);
		giveUpButton.setText("<html> I Give Up!!!! Show Target<br/>and Start a New Game</html>");
		giveUpButton.setFocusable(false);

		showHintButton = new JButton(showHint);
		showHintButton.setFocusable(false);

		JLabel info = new JLabel("Dictionary list (filtering ON) eliminates words");
		JLabel info2 = new JLabel("that can't be the target based on past guesses");
		info.setForeground(Color.DARK_GRAY);
		info2.setForeground(Color.DARK_GRAY);

		settingsPane.add(Box.createRigidArea(new Dimension(5,5)));
		settingsPane.add(showHintButton);
		settingsPane.add(Box.createRigidArea(new Dimension(0,5)));
		settingsPane.add(newGameButton);
		settingsPane.add(Box.createRigidArea(new Dimension(0,5)));
		settingsPane.add(giveUpButton);
		settingsPane.add(Box.createRigidArea(new Dimension(0,5)));
		settingsPane.add(info);
		settingsPane.add(info2);
		settingsPane.add(Box.createRigidArea(new Dimension(0,50)));
		

		settingsPane.setBorder(BorderFactory.createTitledBorder(null, "Settings", TitledBorder.CENTER, TitledBorder.CENTER, null, null));
		settingAndHintsPane.add(hintsPane);
		settingAndHintsPane.add(settingsPane);


		overallPane.add(settingAndHintsPane);

		this.add(overallPane);
		updateJList();
		registerControllers();
	}

	public void registerControllers(){
		newGameButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				jottoModel.newGame();
			}
		});

		giveUpButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				jottoModel.giveUp();
			}
		});

		showHintButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				jottoModel.setHintsRelatedText();
			}
		});
	}

	public void updateMessage(){
		messagesField.setText(jottoModel.getGameMessage());
	}


	public void updateJList(){	
		ArrayList<String> jListWords = jottoModel.getUpdatedDictionaryWordList();
		DefaultListModel jListModel = new DefaultListModel();
		for(String tmpWord : jListWords){
			jListModel.addElement(tmpWord);
		}
		possibleWordsJList.setModel(jListModel);

		scrollPane.setViewportView(possibleWordsJList);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(130,150));
	}

	@Override
	public void updateView() {
		updateJList();
		updateMessage();
		showHint = jottoModel.getShowHintIcon();
		showHintButton.setIcon(showHint);
		showHintButton.setText(jottoModel.getShowHintButtonText());
		showWordHints.setText(jottoModel.getListIntroText());
		if(jottoModel.getGuessedTargetCorrectly()){
			giveUpButton.setEnabled(false);
		}
		else{
			giveUpButton.setEnabled(true);
		}
	
	}

}
