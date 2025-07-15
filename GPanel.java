package SNg;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GPanel extends JPanel implements ActionListener,KeyListener{
	
	//ALL SNAKE MOVEMENT AREA AND ITS LENGTH
	
	private int[] snakexlength = new int[750]; //snakes x direction movement array
	private int[] snakeylength = new int[750]; //snakes y direction movement array
	private int lengthofsnake=3; //Initial length of snake
	
	//ENEMY POSITION
	
	private int[] xpose = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
	private int[] ypose = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
	
	private Random random = new Random();
	private int enemyX,enemyY;
	
	//ALL MOVEMENT CONDITIONS
	
	private boolean left = false; //left movement condition
	private boolean right = true;//right movement condition
	private boolean up = false;//up movement condition
	private boolean down = false;//down movement condition
	
	//INITIAL STAGE
	
	private int moves=0;
	
	//Score
	
	private int score=0;
	
	//Score
	
	private boolean gameOver=false;
	
 private ImageIcon snaketitle = new ImageIcon(getClass().getResource("snaketitle.jpg"));
 private ImageIcon leftmouth = new ImageIcon(getClass().getResource("leftmouth.png"));
 private ImageIcon rightmouth = new ImageIcon(getClass().getResource("rightmouth.png"));
 private ImageIcon upmouth= new ImageIcon(getClass().getResource("upmouth.png"));
 private ImageIcon downmouth = new ImageIcon(getClass().getResource("downmouth.png"));
 private ImageIcon snakeimage = new ImageIcon(getClass().getResource("snakeimage.png"));
 private ImageIcon enemy = new ImageIcon(getClass().getResource("enemy.png"));
 
 private Timer timer;
 private int delay = 100;

        GPanel() {
	    addKeyListener(this);//To add keylistener to Gpanel
	    setFocusable(true);
	    setFocusTraversalKeysEnabled(true);
	    timer = new Timer(delay, this);
	    timer.start();
	    newEnemy();
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.white);//COLOR OF PANEL
		g.drawRect(24,10,851,55);//TITLE RECTANGLE
		g.drawRect(24,74,851,576);//GAME BOARD RECTANGLE
		snaketitle.paintIcon(this, g, 25, 11); //TITLE ICON
		g.setColor(Color.black);//GAME BOARD RECTANGLE
		g.fillRect(25, 75, 850, 575); //filling of the rectangle
		
		
		if(moves==0) {
			snakexlength[0]=100;//position of the snake in array in x direction
			snakexlength[1]=75;
			snakexlength[2]=50;
			
			snakeylength[0]=100;//position of the snake in array in y direction
			snakeylength[1]=100;
			snakeylength[2]=100;
			//moves=1; //IT IS REQUIRED TO NOT RESET THE MOVE OF SNAKE AS MOVES==0;
		//	now moves=1 removed because we want to start the snake at a point not always 
			//so we use move++ at every key event
		}
		
		
		if(left) {
			leftmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
		}
		if(right) {
			rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
		}
		if(up) {
			upmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
		}
		if(down) {
			downmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
		}
		
		
		for(int i=0;i<lengthofsnake;i++) {
			snakeimage.paintIcon(this, g, snakexlength[i], snakeylength[i]);
		
	}
		enemy.paintIcon(this, g, enemyX, enemyY);
		
		if(gameOver) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Aerial",Font.BOLD,50));
			g.drawString("Game Over", 300,300);
			g.setFont(new Font("Aerial",Font.PLAIN,20));
			g.drawString("Press Enter To Start", 320,350);
		}
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Aerial",Font.BOLD,14));
		g.drawString("Score :"+score, 750,30);
		g.drawString("Length :"+lengthofsnake, 750,50);
		
		
		g.dispose();
		
}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		 for (int i = lengthofsnake - 1; i > 0; i--) {
		        snakexlength[i] = snakexlength[i - 1];
		        snakeylength[i] = snakeylength[i - 1];
		    }
		 
		if(left) {
			snakexlength[0]=snakexlength[0]-25;
		}
		 if(right) {
			snakexlength[0]=snakexlength[0]+25;
		}
		 if(up) {
			snakeylength[0]=snakeylength[0]-25;
		}
		 if(down) {
			snakeylength[0]=snakeylength[0]+25;
		}
		 
		    if (snakexlength[0] > 850) snakexlength[0] = 25;
		    if (snakexlength[0] < 25) snakexlength[0] = 850;
		    if (snakeylength[0] > 625) snakeylength[0] = 75;
		    if (snakeylength[0] < 75) snakeylength[0] = 625;
		    
		    collideWithEnemy();
		    collideWithBody();
	
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			restart();
		}

	if(e.getKeyCode()==KeyEvent.VK_LEFT && (!right)) {
		left=true;
		right=false;
		up=false;
		down=false;
		moves++;
	}
	if(e.getKeyCode()==KeyEvent.VK_RIGHT && (!left)) {
		left=false;
		right=true;
		up=false;
		down=false;
		moves++;
	}
	if(e.getKeyCode()==KeyEvent.VK_UP && (!down)) {
		left=false;
		right=false;
		up=true;
		down=false;
		moves++;
	}
	if(e.getKeyCode()==KeyEvent.VK_DOWN && (!up)) {
		left=false;
		right=false;
		up=false;
		down=true;
		moves++;
	}
	
	}

	private void newEnemy() {
		enemyX = xpose[random.nextInt(34)];
		enemyY = ypose[random.nextInt(23)];
		for(int i=lengthofsnake-1;i>=0;i--) {
			if(snakexlength[i]== enemyX && snakeylength[i]== enemyY ) {
				newEnemy();
			}
		}
		
	}
	private void collideWithEnemy() {
		if(snakexlength[0]== enemyX && snakeylength[0]== enemyY ) {
			newEnemy();
			lengthofsnake++;
			score++;
		}
		
	}
	private void collideWithBody() {
		for(int i=lengthofsnake-1;i>0;i--) {
		if(snakexlength[i]== snakexlength[0] && snakeylength[i]== snakeylength[0] ) {
			timer.stop();
			gameOver=true;
		}
		}
		
	}
	private void restart() {
		gameOver=false;
		moves=0;
		score=0;
		lengthofsnake=3;
		left=false;
		right=true;
		up=false;
		down=false;
		timer.start();
		repaint();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
}

	
	
	
	
