// Class Hang
// 29/09/2019

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Hang extends JFrame{
   private ArrayList<String> under, a;
   private char[] n;
   private char[] m;
   private boolean result;
	private String mysteryW = null;
	private Random random;
	private WordList wordlist;
	private ArrayList<Word> list;
	private final int guesses = 10;
	private int i = 2;
	private JTextField randomW, text, item, field, level;
	private JButton button, quit, easy, hard;
	private JPanel panel;
	private ImageIcon icon;
	private JLabel label;
   private int v = 0;
   private String k = "";
	
	public Hang(){
		super("HANGMAN Game!");
		try{
			wordlist = WordList.readFromFile("dictionary.txt");
         a = new ArrayList<String>();
         under = new ArrayList<String>();
			list = new ArrayList<Word>();
			setLayout(new FlowLayout(FlowLayout.LEFT));
			random = new Random();
			label = new JLabel(new ImageIcon("state1.GIF"));
			panel = new JPanel();
			item = new JTextField("Enter a Character: ");
			text = new JTextField(3);
			randomW = new JTextField(13);
         panel.add(randomW);
			level = new JTextField("Level");
			easy = new JButton("Easy");
			hard = new JButton("Hard");
			panel.add(level);
			easy.setBackground(Color.GREEN);
			panel.add(easy);
			hard.setBackground(Color.RED);
			panel.add(hard);
			randomW.setEditable(false);
			panel.add(label);
			item.setEditable(false);
			panel.add(item);
			level.setEditable(false);
			panel.add(text);
         quit = new JButton("Quit");
         panel.add(quit);
			button = new JButton("Guesses remaining");
			panel.add(button);
			Handler h = new Handler();
         quit.addActionListener(h);
			easy.addActionListener(h);
			hard.addActionListener(h);
			text.addActionListener(h);
			button.addActionListener(h);
			add(panel);
		}
		catch(Exception e){System.out.println(e);}
		}
      public void removeAll(ArrayList<String> l, String element){
         int index;
         while ((index = l.indexOf(element)) >= 0){
            l.remove(index);
         }
      }
	
		private class Handler implements ActionListener{
			int c = 0;
			int counter = guesses;
			public void actionPerformed(ActionEvent event){
				if (event.getSource() == text && counter != 0){
               try{
   					char s = event.getActionCommand().charAt(0);
   					for (int z =0;z<mysteryW.length();z++){
                     String u = "" + mysteryW.charAt(z);
                     a.add(u);
                     m[z] = mysteryW.charAt(z);
                  }
                  String u = "" + s;
                  if (a.contains(u)){
                     removeAll(a,u);
                     for (int z = 0;z<mysteryW.length();z++){
                        if (m[z] == s){
                           under.remove("-");
                           n[z] = s;
                           result = true;
                        }
                     }
                  }
                  else{counter--;}
                  if (!(result)){
      					label.setIcon(new ImageIcon("state" + i + ".GIF"));
      					i++;
                     result = false;
                  }
                  if (!(under.contains("-"))){
                     JOptionPane.showMessageDialog(null,"You found the Mystery Word! :  " + mysteryW + "  You WIN! Congratulation.");
                     System.exit(0);
                  }
                  for (int z = 0;z<mysteryW.length();z++){
                     k += "" + n[z];
                  }
                  randomW.setText(k);
                  k = "";
                  result = false;
               }
               catch(Exception e){System.out.println(e);}
				}
				else if (event.getSource() == button){
					if (c == 0){
						button.setBackground(Color.BLUE);
						JOptionPane.showMessageDialog(null,counter);
						c++;
					}
					else if (c == 1){
						button.setBackground(Color.RED);
						JOptionPane.showMessageDialog(null,counter);
						c++;
					}
					else if (c == 2){
						button.setBackground(Color.GREEN);
						JOptionPane.showMessageDialog(null,counter);
						c++;
					}
					else{
						button.setBackground(Color.YELLOW);
						JOptionPane.showMessageDialog(null,counter);
						c = -1;
					}
				}
				else if (event.getSource() == easy && v == 0){
               try{
   					for (Word w: wordlist){
   						String s = w.toString();
   						if (s.length()< 5){
   							list.add(w);
   						}
   					}
   					int e = random.nextInt(list.size());
   					mysteryW = list.get(e).toString();
                  m = new char[mysteryW.length()];
                  n = new char[mysteryW.length()];
                  String uni = "";
   					for (int l = 0; l<mysteryW.length();l++){
   						n[l] = '-';
                     under.add("-");
                     uni += "-";
   					}
           	      randomW.setText(uni);
                  v++;
               }
               catch (Exception e){System.out.println(e);}
				}
            else if (event.getSource() == quit){System.exit(0);}
				else if (event.getSource() == hard && v == 0){
					for (Word w:wordlist){
						String s = w.toString();
						if (s.length()> 5){
							list.add(w);
						}
					}
					int e = random.nextInt(list.size());
               mysteryW = list.get(e).toString();
               m = new char[mysteryW.length()];
               n = new char[mysteryW.length()];
               String uni = "";
               for (int z = 0; z <mysteryW.length();z++){
				      n[z] = '-';
                  under.add("-");
                  uni += "-";
			      }
        	      randomW.setText(uni);
               v++;
				}
				else if (counter == 0){
					JOptionPane.showMessageDialog(null, "Out of guesses !!  You've been had.  GAME OVER!!!!!!");
					System.exit(0);
				}
			}
		}
	}