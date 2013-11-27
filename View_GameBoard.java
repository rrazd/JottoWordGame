import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.Action;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

public class View_GameBoard extends JPanel implements IView{

	private JTextField enterGuessField = new JTextField(5);
	private Model jottoModel;
	private JButton enterWordButton = new JButton("Guess");
	private JScrollPane tableContainer = new JScrollPane();
	//just needed for first time initialization so that don't get null exception
	private ArrayList<Row> tableRows = new ArrayList<Row>();
	//one time creation of guess entry table
	private JTable dataTable = new JTable();
	private JPanel guessAndBoardPane;

	public View_GameBoard(Model model){
		jottoModel = model;
		initialize();
	}

	public void initialize(){	
		guessAndBoardPane = new JPanel();
		JPanel guessPane = new JPanel();

		//create guessing pane for word entry
		JLabel wordEntryLabel = new JLabel("Enter 5 letter word ");
		guessPane.setLayout(new BoxLayout(guessPane, BoxLayout.LINE_AXIS));
		guessAndBoardPane.setLayout(new BoxLayout(guessAndBoardPane, BoxLayout.PAGE_AXIS));

		guessAndBoardPane.add(Box.createRigidArea(new Dimension(0,10)));

		enterGuessField.setColumns(5);
		wordEntryLabel.setLabelFor(enterGuessField);
		guessPane.add(wordEntryLabel);
		guessPane.add(enterGuessField);
		guessPane.add(Box.createRigidArea(new Dimension(5,0)));
		guessPane.add(enterWordButton);
		//align to the left via rigid box use
		guessPane.add(Box.createRigidArea(new Dimension(300,0)));

		//creating the table container 
		updateTable(tableRows);

		TableCellRenderer rendererHeader = dataTable.getTableHeader().getDefaultRenderer();
		JLabel headerTitle = (JLabel) rendererHeader;
		headerTitle.setHorizontalAlignment(JLabel.CENTER);	 

		//add controllers
		registerControllers();

		//populate the guess and board panel
		guessAndBoardPane.add(guessPane);
		guessAndBoardPane.add(Box.createRigidArea(new Dimension(0,5)));
		guessAndBoardPane.add(Box.createVerticalGlue());
		guessAndBoardPane.add(tableContainer);

		//show up on window
		this.add(guessAndBoardPane);
	}

	public void  registerControllers(){
		//controller for word entry
		enterWordButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				String guessText = enterGuessField.getText();
				jottoModel.setGuessArray(guessText);
				jottoModel.setGameMessage(guessText);
				jottoModel.populateTable();
				jottoModel.setLabelsToColor();
			}
		});

		//allows user the option of simply hitting the ENTER key (instead of clicking button) to submit guess
		enterGuessField.addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent e) {
			}

			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					String guessText = enterGuessField.getText();
					jottoModel.setGuessArray(guessText);
					jottoModel.setGameMessage(guessText);
					jottoModel.populateTable();
					jottoModel.setLabelsToColor();
				}
			}
		});
	}

	public void updateTable(ArrayList<Row> tableRowList){		
		Vector<String> columnNames = new Vector<String>(3);
		columnNames.add("Words");
		columnNames.add("Exact");
		columnNames.add("Partial");

		Vector<Object> data = new Vector<Object>(10);

		for(Row row : tableRowList){
			Vector<Object> rowOfTable = new Vector<Object>(3);
			rowOfTable.add(row.getGuessWord()); 
			rowOfTable.add(row.getExactMatch());
			rowOfTable.add(row.getPartialMatch());
			data.add(rowOfTable);
		}

		DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
		tableModel.fireTableDataChanged();
		dataTable.setModel(tableModel);

		tableContainer.setViewportView(dataTable);
		tableContainer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		int height = jottoModel.getFrameHeight();
		//this code block is part of having responsive design
		if(height <= 710){
			tableContainer.setPreferredSize(new Dimension(624, 250));
		}
		else{
			tableContainer.setPreferredSize(new Dimension(624, height - 441));
		}
		revalidate();

		tableContentFormatting(height);
	}

	public void tableContentFormatting(int height){
		JTableHeader header = dataTable.getTableHeader();
		header.setBackground(Color.getHSBColor(67, 44, 100));	
		header.setFont(new Font("San Serif", Font.BOLD, 13));
		header.setEnabled(false);
		DefaultTableCellRenderer rendererCell = new DefaultTableCellRenderer();
		rendererCell.setHorizontalAlignment( JLabel.CENTER );
		dataTable.getColumnModel().getColumn(0).setCellRenderer(rendererCell);
		dataTable.getColumnModel().getColumn(1).setCellRenderer(rendererCell);
		dataTable.getColumnModel().getColumn(2).setCellRenderer(rendererCell);
		dataTable.setFont(new Font("San Serif", Font.PLAIN, 12));
		dataTable.setShowGrid(false);
		dataTable.setEnabled(false);
		tableContainer.getViewport().setBackground(Color.white);
		//this code block is part of having responsive design
		if(height <= 710){
			dataTable.setRowHeight(22);
		}
		else{
			//responsive table: increase cell padding as height of the table increases according to the frame size
			int rowHeight = (int)((0.1*height) - 49);
			dataTable.setRowHeight(rowHeight);
		}

	}

	@Override
	public void updateView() {
		enterGuessField.setText(" ");
		updateTable(jottoModel.getRowList());
	}

}
