import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class View_AlphabetStatus extends JPanel implements IView{
	private Model jottoModel;
	private JLabel A;
	private JLabel B;
	private JLabel C;
	private JLabel D;
	private JLabel E;
	private JLabel F;
	private JLabel G;
	private JLabel H;
	private JLabel I;
	private JLabel J;
	private JLabel K;
	private JLabel L;
	private JLabel M;
	private JLabel N;
	private JLabel O;
	private JLabel P;
	private JLabel Q;
	private JLabel R;
	private JLabel S;
	private JLabel T;
	private JLabel U;
	private JLabel V;
	private JLabel W;
	private JLabel X;
	private JLabel Y;
	private JLabel Z;


	public View_AlphabetStatus(Model model){
		jottoModel = model;
		initialize();
	}

	public void initialize(){

		JPanel lettersPane = new JPanel();
		JPanel colorPrefPane = new JPanel ();
		JPanel lettersAndColorPane = new JPanel();
		lettersPane.setLayout(new BoxLayout(lettersPane, BoxLayout.LINE_AXIS));
		colorPrefPane.setLayout(new BoxLayout(colorPrefPane, BoxLayout.LINE_AXIS));
		lettersAndColorPane.setLayout(new BoxLayout(lettersAndColorPane, BoxLayout.PAGE_AXIS));

		createLabel();

		lettersPane.add(Box.createRigidArea(new Dimension(6,0)));
		lettersPane.add(A);
		lettersPane.add(Box.createRigidArea(new Dimension(6,0)));
		lettersPane.add(B);
		lettersPane.add(Box.createRigidArea(new Dimension(6,0)));
		lettersPane.add(C);
		lettersPane.add(Box.createRigidArea(new Dimension(6,0)));
		lettersPane.add(D);
		lettersPane.add(Box.createRigidArea(new Dimension(6,0)));
		lettersPane.add(E);
		lettersPane.add(Box.createRigidArea(new Dimension(6,0)));
		lettersPane.add(F);
		lettersPane.add(Box.createRigidArea(new Dimension(6,0)));
		lettersPane.add(G);
		lettersPane.add(Box.createRigidArea(new Dimension(6,0)));
		lettersPane.add(H);
		lettersPane.add(Box.createRigidArea(new Dimension(6,0)));
		lettersPane.add(I);
		lettersPane.add(Box.createRigidArea(new Dimension(6,0)));
		lettersPane.add(J);
		lettersPane.add(Box.createRigidArea(new Dimension(6,0)));
		lettersPane.add(K);
		lettersPane.add(Box.createRigidArea(new Dimension(6,0)));
		lettersPane.add(L);
		lettersPane.add(Box.createRigidArea(new Dimension(6,0)));
		lettersPane.add(M);
		lettersPane.add(Box.createRigidArea(new Dimension(6,0)));
		lettersPane.add(N);
		lettersPane.add(Box.createRigidArea(new Dimension(6,0)));
		lettersPane.add(O);
		lettersPane.add(Box.createRigidArea(new Dimension(6,0)));
		lettersPane.add(P);
		lettersPane.add(Box.createRigidArea(new Dimension(6,0)));
		lettersPane.add(Q);
		lettersPane.add(Box.createRigidArea(new Dimension(6,0)));
		lettersPane.add(R);
		lettersPane.add(Box.createRigidArea(new Dimension(6,0)));
		lettersPane.add(S);
		lettersPane.add(Box.createRigidArea(new Dimension(6,0)));
		lettersPane.add(T);
		lettersPane.add(Box.createRigidArea(new Dimension(6,0)));
		lettersPane.add(U);
		lettersPane.add(Box.createRigidArea(new Dimension(6,0)));
		lettersPane.add(V);
		lettersPane.add(Box.createRigidArea(new Dimension(6,0)));
		lettersPane.add(W);
		lettersPane.add(Box.createRigidArea(new Dimension(6,0)));
		lettersPane.add(X);
		lettersPane.add(Box.createRigidArea(new Dimension(6,0)));
		lettersPane.add(Y);
		lettersPane.add(Box.createRigidArea(new Dimension(6,0)));
		lettersPane.add(Z);
		lettersPane.add(Box.createRigidArea(new Dimension(6,0)));


		JLabel selectColorLabel = new JLabel("ORANGE Color Indicates Letters Already Used In Guesses");
		selectColorLabel.setFont(selectColorLabel.getFont().deriveFont(10.0f));
		selectColorLabel.setForeground(Color.BLACK);
		lettersAndColorPane.setBorder(BorderFactory.createTitledBorder(null, "Letters", TitledBorder.CENTER, TitledBorder.CENTER, null, null));
		lettersAndColorPane.add(lettersPane);
		colorPrefPane.add(Box.createRigidArea(new Dimension(0,25)));
		colorPrefPane.add(selectColorLabel);
		lettersAndColorPane.add(colorPrefPane);
		lettersAndColorPane.setPreferredSize(new Dimension(625, 75));
		this.add(lettersAndColorPane);

	}

	public void createLabel(){
		A = new JLabel(" A ");
		B = new JLabel(" B ");
		C = new JLabel(" C ");
		D = new JLabel(" D ");
		E = new JLabel(" E ");
		F = new JLabel(" F ");
		G = new JLabel(" G ");
		H = new JLabel(" H ");
		I = new JLabel(" I ");
		J = new JLabel(" J ");
		K = new JLabel(" K ");
		L = new JLabel(" L ");
		M = new JLabel(" M ");
		N = new JLabel(" N ");
		O = new JLabel(" O ");
		P = new JLabel(" P ");
		Q = new JLabel(" Q ");
		R = new JLabel(" R ");
		S = new JLabel(" S ");
		T = new JLabel(" T ");
		U = new JLabel(" U ");
		V = new JLabel(" V ");
		W = new JLabel(" W ");
		X = new JLabel(" X ");
		Y = new JLabel(" Y ");
		Z = new JLabel(" Z ");
		A.setOpaque(true);
		B.setOpaque(true);
		C.setOpaque(true);
		D.setOpaque(true);
		E.setOpaque(true);
		F.setOpaque(true);
		G.setOpaque(true);
		H.setOpaque(true);
		I.setOpaque(true);
		J.setOpaque(true);
		K.setOpaque(true);
		L.setOpaque(true);
		M.setOpaque(true);
		N.setOpaque(true);
		O.setOpaque(true);
		P.setOpaque(true);
		Q.setOpaque(true);
		R.setOpaque(true);
		S.setOpaque(true);
		T.setOpaque(true);
		U.setOpaque(true);
		V.setOpaque(true);
		W.setOpaque(true);
		X.setOpaque(true);
		Y.setOpaque(true);
		Z.setOpaque(true);
		A.setBackground(Color.GRAY);
		B.setBackground(Color.GRAY);
		C.setBackground(Color.GRAY);
		D.setBackground(Color.GRAY);
		E.setBackground(Color.GRAY);
		F.setBackground(Color.GRAY);
		G.setBackground(Color.GRAY);
		H.setBackground(Color.GRAY);
		I.setBackground(Color.GRAY);
		J.setBackground(Color.GRAY);
		K.setBackground(Color.GRAY);
		L.setBackground(Color.GRAY);
		M.setBackground(Color.GRAY);
		N.setBackground(Color.GRAY);
		O.setBackground(Color.GRAY);
		P.setBackground(Color.GRAY);
		Q.setBackground(Color.GRAY);
		R.setBackground(Color.GRAY);
		S.setBackground(Color.GRAY);
		T.setBackground(Color.GRAY);
		U.setBackground(Color.GRAY);
		V.setBackground(Color.GRAY);
		W.setBackground(Color.GRAY);
		X.setBackground(Color.GRAY);
		Y.setBackground(Color.GRAY);
		Z.setBackground(Color.GRAY);

	}

	public void updateView() {
		for( String tmpLetter : jottoModel.getLabelsToColor()){
			if(tmpLetter.equals("A")){
				A.setBackground(Color.orange);
			}
			if(tmpLetter.equals("B")){
				B.setBackground(Color.orange);
			}
			if(tmpLetter.equals("C")){
				C.setBackground(Color.orange);
			}
			if(tmpLetter.equals("D")){
				D.setBackground(Color.orange);
			}
			if(tmpLetter.equals("E")){
				E.setBackground(Color.orange);
			}
			if(tmpLetter.equals("F")){
				F.setBackground(Color.orange);
			}
			if(tmpLetter.equals("G")){
				G.setBackground(Color.orange);
			}
			if(tmpLetter.equals("H")){
				H.setBackground(Color.orange);
			}
			if(tmpLetter.equals("I")){
				I.setBackground(Color.orange);
			}
			if(tmpLetter.equals("J")){
				J.setBackground(Color.orange);
			}
			if(tmpLetter.equals("K")){
				K.setBackground(Color.orange);
			}
			if(tmpLetter.equals("L")){
				L.setBackground(Color.orange);
			}
			if(tmpLetter.equals("M")){
				M.setBackground(Color.orange);
			}
			if(tmpLetter.equals("N")){
				N.setBackground(Color.orange);
			}
			if(tmpLetter.equals("O")){
				O.setBackground(Color.orange);
			}
			if(tmpLetter.equals("P")){
				P.setBackground(Color.orange);
			}
			if(tmpLetter.equals("Q")){
				Q.setBackground(Color.orange);
			}
			if(tmpLetter.equals("R")){
				R.setBackground(Color.orange);
			}
			if(tmpLetter.equals("S")){
				S.setBackground(Color.orange);
			}
			if(tmpLetter.equals("T")){
				T.setBackground(Color.orange);
			}
			if(tmpLetter.equals("U")){
				U.setBackground(Color.orange);
			}
			if(tmpLetter.equals("V")){
				V.setBackground(Color.orange);
			}
			if(tmpLetter.equals("W")){
				W.setBackground(Color.orange);
			}
			if(tmpLetter.equals("X")){
				X.setBackground(Color.orange);
			}
			if(tmpLetter.equals("Y")){
				Y.setBackground(Color.orange);
			}
			if(tmpLetter.equals("Z")){
				Z.setBackground(Color.orange);
			}
		}
		
		//set back to grey for New Game 
		for(String tmpLetter : jottoModel.getLettersInAlphabet()){
			if(!jottoModel.getLabelsToColor().contains(tmpLetter)){
				if(tmpLetter == "A"){
					A.setBackground(Color.GRAY);
				}
				if(tmpLetter == "B"){
					B.setBackground(Color.GRAY);
				}
				if(tmpLetter == "C"){
					C.setBackground(Color.GRAY);
				}
				if(tmpLetter == "D"){
					D.setBackground(Color.GRAY);
				}
				if(tmpLetter == "E"){
					E.setBackground(Color.GRAY);
				}
				if(tmpLetter == "F"){
					F.setBackground(Color.GRAY);
				}
				if(tmpLetter == "G"){
					G.setBackground(Color.GRAY);
				}
				if(tmpLetter == "H"){
					H.setBackground(Color.GRAY);
				}
				if(tmpLetter == "I"){
					I.setBackground(Color.GRAY);
				}
				if(tmpLetter == "J"){
					J.setBackground(Color.GRAY);
				}
				if(tmpLetter == "K"){
					K.setBackground(Color.GRAY);
				}
				if(tmpLetter == "L"){
					L.setBackground(Color.GRAY);
				}
				if(tmpLetter == "M"){
					M.setBackground(Color.GRAY);
				}
				if(tmpLetter == "N"){
					N.setBackground(Color.GRAY);
				}
				if(tmpLetter == "O"){
					O.setBackground(Color.GRAY);
				}
				if(tmpLetter == "P"){
					P.setBackground(Color.GRAY);
				}
				if(tmpLetter == "Q"){
					Q.setBackground(Color.GRAY);
				}
				if(tmpLetter == "R"){
					R.setBackground(Color.GRAY);
				}
				if(tmpLetter == "S"){
					S.setBackground(Color.GRAY);
				}
				if(tmpLetter == "T"){
					T.setBackground(Color.GRAY);
				}
				if(tmpLetter == "U"){
					U.setBackground(Color.GRAY);
				}
				if(tmpLetter == "V"){
					V.setBackground(Color.GRAY);
				}
				if(tmpLetter == "W"){
					W.setBackground(Color.GRAY);
				}
				if(tmpLetter == "X"){
					X.setBackground(Color.GRAY);
				}
				if(tmpLetter == "Y"){
					Y.setBackground(Color.GRAY);
				}
				if(tmpLetter == "Z"){
					Z.setBackground(Color.GRAY);
				}
			
			}	
		}
	}

}
