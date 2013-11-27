Jotto Application
-----------------

How to Run:
	-Type "make run" from the command line


Notes:
	1) MVC
		- 3 distinct views are: 
			i) Letter-board view (View_AlphabetStatus.java)
			ii) Game-board view (View_GameBoard.java)
			iii) Hints and settings view (View_Hints.java)
		- 1 model (Model.java)
		- Controllers are tightly coupled with the views

	2) The SplashScreen.java is not strictly a 'view'. It is more of an extension of Main.java as it is where the JFrame is created 

	3) JTable used for 'game-board' where user's guesses and corresponding exact/partial match data is shown


Enhancements:
	- Responsive design used seeing as the layout adapts to the frame size. In particular, the game-board (ie. JTable) expands in height when frame is vertically stretched. As the frame is vertically stretched, the rowHeight increases linearly with the frame height to create more padding between rows. This ensures that the contents of the JTable always utilize the entire table height 

	- Added splash screen

	- Added Key bindings: 
		i) Press enter to submit guess from text field to table, instead of clicking guess button
		ii) Press cntrl-Q to quit or choose to quit by navigating the menu, File -> Quit

	- Added a letter-board that highlights in orange the letters that have been used in guess words

	- Added a Hints option that allows the user to choose between seeing the whole dictionary or a subset of possible target words. The subset is determined by eliminating words with letters that cannot be in target; these letters are computed based on past guesses 

	- Added images to buttons in Settings panel to enhance visual appeal

	- Settings panel offers users ability to start a new game at any time, give up, and toggle between seeing 'filtered possible target words' and the whole dictionary (all words recognized by the game)

	- Added a Game Message display where the user gets important feedback to guide them in their gameplay

	- Clear 'won game' state when user has guessed target word and must start a new game to enter subsequent guesses


