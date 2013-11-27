NAME = Main
JFLAGS = -g
JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java
	javac -cp ./vecmath-1.3.1.jar:. *.java
CLASSES = \
        IWordList.java \
        IWordPredicate.java \
        Main.java \
        Model.java \
        SplashScreen.java \
        View_AlphabetStatus.java \
        View_GameBoard.java \
        View_Hints.java \
        Word.java \
        WordList.java \
        Row.java

default: classes 

run: default
	java -cp ./vecmath-1.3.1.jar:. $(NAME)

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class


