import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Created by Davorin on 4/4/2017.
 */

public class Quiz extends JFrame implements ActionListener {
    JLabel label;
    JRadioButton radioButton[] = new JRadioButton[5];
    JButton nextButton,bookmarkButton;
    ButtonGroup buttonGroup;
    int CorrectCount = 0,currentQuestion = 0,x = 1,yCoordinate = 1,now = 0;
    int m[] = new int[10];

    //Constructor
    Quiz(String s) {
        super(s);
        label = new JLabel();
        add(label);
        buttonGroup = new ButtonGroup();

        for(int i = 0; i < 5; i++) {
            radioButton[i] = new JRadioButton();
            add(radioButton[i]);
            buttonGroup.add(radioButton[i]);
        }

        nextButton = new JButton("Next Question");
        bookmarkButton = new JButton("Bookmark");
        nextButton.addActionListener(this);
        bookmarkButton.addActionListener(this);
        add(nextButton);add(bookmarkButton);
        setupQuestion();
        label.setBounds(50,40,500,20);
        radioButton[0].setBounds(50,80,100,20);
        radioButton[1].setBounds(50,110,100,20);
        radioButton[2].setBounds(50,140,100,20);
        radioButton[3].setBounds(50,170,100,20);
        nextButton.setBounds(100,240,150,30);
        bookmarkButton.setBounds(270,240,100,30);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(400,400);//Sets the location of the window on screen
        setVisible(true);
        setSize(500,400);//Sets the size of the window
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == nextButton) {
            if(checkAnswer())
                CorrectCount = CorrectCount + 1;
            currentQuestion++;
            setupQuestion();
            if(currentQuestion == 9) {
                nextButton.setEnabled(false);
                bookmarkButton.setText("Result");
            }
        }

        if(e.getActionCommand().equals("Bookmark")) {
            JButton bk = new JButton("Bookmark" + x);
            bk.setBounds(480,20 + 30 * x,100,30);
            add(bk);
            bk.addActionListener(this);
            m[x] = currentQuestion;
            x++;
            currentQuestion++;
            setupQuestion();
            if(currentQuestion == 9)
                bookmarkButton.setText("Result");
            setVisible(false);
            setVisible(true);
        }

        for(int i = 0, y = 1; i < x; i++, y++) {
            if(e.getActionCommand().equals("Bookmark" + y)) {
                if(checkAnswer())
                    CorrectCount = CorrectCount + 1;
                now = currentQuestion;
                currentQuestion = m[y];
                setupQuestion();
                ((JButton)e.getSource()).setEnabled(false);
                currentQuestion = now;
            }
        }

        if(e.getActionCommand().equals("Result")) {
            if(checkAnswer())
                CorrectCount = CorrectCount + 1;
            currentQuestion++;
            //System.out.println("correct ans="+count);
            JOptionPane.showMessageDialog(this,"Correct: " + CorrectCount);
            System.exit(0);
        }
    }

    //Sets up the question on screen.
    void setupQuestion() {
        radioButton[4].setSelected(true);
        if(currentQuestion == 0) {
            label.setText("Question 1: What year was Java originally developed?");
            radioButton[0].setText("1974");radioButton[1].setText("1988");radioButton[2].setText("1995");radioButton[3].setText("2001");
        }
        if(currentQuestion == 1) {
            label.setText("Question 2: Which of the following is not a programming language?");
            radioButton[0].setText("C");radioButton[1].setText("Apple");radioButton[2].setText("Python");radioButton[3].setText("PHP");
        }
        if(currentQuestion == 2) {
            label.setText("Question 3: Who was one of the co-founders of Apple?");
            radioButton[0].setText("Steve Jobbs");radioButton[1].setText("Ashton Kutcher");radioButton[2].setText("Bill Gates");radioButton[3].setText("Warren Buffett");
        }
        if(currentQuestion == 3) {
            label.setText("Question 4: Where is Silicon Valley located?");
            radioButton[0].setText("Texas");radioButton[1].setText("New York");radioButton[2].setText("Colorado");radioButton[3].setText("California");
        }
        if(currentQuestion == 4) {
            label.setText("Question 5: What does 'OOP' stand for in computer science?");
            radioButton[0].setText("Ordered Opposites Property");radioButton[1].setText("Object Oriented Programming");radioButton[2].setText("Outside Outline Proper");radioButton[3].setText("Online Order Purchase");
        }
        if(currentQuestion == 5) {
            label.setText("Question 6: Which of the following is an Internet Browser?");
            radioButton[0].setText("Godzilla");radioButton[1].setText("World View");radioButton[2].setText("Firefox");radioButton[3].setText("Silver");
        }
        if(currentQuestion == 6) {
            label.setText("Question 7: Which of the following is not a social media site?");
            radioButton[0].setText("MySpace");radioButton[1].setText("Facebook");radioButton[2].setText("Amazon");radioButton[3].setText("Twitter");
        }
        if(currentQuestion == 7) {
            label.setText("Question 8: Which of the following is a Java keyword?");
            radioButton[0].setText("toString");radioButton[1].setText("class");radioButton[2].setText("win");radioButton[3].setText("document");
        }
        if(currentQuestion == 8) {
            label.setText("Question 9: How lame is Davorin's quiz?");
            radioButton[0].setText("Not lame");radioButton[1].setText("Sorta");radioButton[2].setText("Very");radioButton[3].setText("Extremely");
        }
        if(currentQuestion == 9) {
            label.setText("Question 10: Which company did the founders of Java work for?");
            radioButton[0].setText("Sun Microsystems");radioButton[1].setText("Microsoft");radioButton[2].setText("Starbucks");radioButton[3].setText("GE");
        }
        label.setBounds(30,40,450,20);
        for(int i = 0,j = 0;i <= 90;i += 30,j++)
            radioButton[j].setBounds(50,80+i,200,20);
    }

    boolean checkAnswer() {
        if(currentQuestion == 0)
            return(radioButton[2].isSelected());
        if(currentQuestion == 1)
            return(radioButton[1].isSelected());
        if(currentQuestion == 2)
            return(radioButton[0].isSelected());
        if(currentQuestion == 3)
            return(radioButton[3].isSelected());
        if(currentQuestion == 4)
            return(radioButton[1].isSelected());
        if(currentQuestion == 5)
            return(radioButton[2].isSelected());
        if(currentQuestion == 6)
            return(radioButton[2].isSelected());
        if(currentQuestion == 7)
            return(radioButton[1].isSelected());
        if(currentQuestion == 8)
            return(radioButton[0].isSelected());
        if(currentQuestion == 9)
            return(radioButton[0].isSelected());
        return false;
    }
    public static void main(String s[]) {
        new OnlineTest("Online Quiz on Technology");
    }
}
