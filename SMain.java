package SNg;
import java.awt.Color;

import javax.swing.*;
public class SMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		JFrame frame = new JFrame("Snake Game"); //it gives the frame of the game
		
		frame.setBounds(10,10,950,700);  //this gives (top,left,height,width) of the frame
		
		frame.setResizable(false); //to resist user from changing the frame size
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //it closes the game on exiting
		
		GPanel panel = new GPanel();  // 
		
		panel.setBackground(Color.BLACK); // always set visible er age sob operation korte hobe
		
		frame.add(panel);//frame a gamepanel add korar jonno  extends kore object banano holo gpanal er
		
		frame.setVisible(true);
		
		panel.setBackground(Color.BLACK);
		
	}

}
