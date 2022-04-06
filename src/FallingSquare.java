import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FallingSquare extends JPanel
	implements ActionListener
{
	private static int x, y, xW, yH, xSpeed, ySpeed, xFrontMost, yBottom,
					   x_LOCTN_ON_SCREEN, GRND_ELEV_ON_SCREEN, t, time;
	private static final int startingHeight = 150;
	private static final int cubeHitbox = 48;
	private static boolean wT;

	public FallingSquare()
	{
		t = 0;
		Timer clock = new Timer(1, this); 
		clock.start();
	}
	
	public void paintComponent(Graphics g)
	{ 
		xW = cubeHitbox;
		yH = cubeHitbox;
		
		x_LOCTN_ON_SCREEN = getWidth() / 4;
		GRND_ELEV_ON_SCREEN = 5 * getHeight() / 8 + yH;
//		xSpeed = t;
		
		x = x_LOCTN_ON_SCREEN + xSpeed;
		y = Math.min(GRND_ELEV_ON_SCREEN - yH + ySpeed - startingHeight, GRND_ELEV_ON_SCREEN - yH);
		
		xFrontMost = x + xW - 1;
		yBottom = Math.min(y + yH - 1, GRND_ELEV_ON_SCREEN - 1);

		Color bg;
    	bg = (Color.WHITE);
	    setBackground(bg);
	    
	    super.paintComponent(g);
	    
	    g.setColor(Color.BLUE);
	    g.drawLine(0, GRND_ELEV_ON_SCREEN, 15360, GRND_ELEV_ON_SCREEN);

//	    public void MouseClicked (MouseEvent e) {
//	    	ySpeed = (int)((t * (t - 216)) / 96);
//	    }
	    
	    System.out.println(t + " " + y + " " + yBottom + " " + GRND_ELEV_ON_SCREEN);
	    fillRect2p(g, x, y, xFrontMost, yBottom, Color.BLACK);
	    
	    if (yBottom < GRND_ELEV_ON_SCREEN - 1) {
	    	ySpeed = (((t + 108) * (t - 108)) / 96) + 121;
	    }
	    
	    else {
	    	ySpeed = startingHeight;
	    	t = -1;
	    }
	}

//	public boolean wallTouched()
//	{
//		if ((xFrontMost2 > getWidth() || x < 0)) {
//			wT = true;
//		}
//		
//		else {
//			wT = false;
//		}
//		
//		return wT;
//	}

	public void fillRect2p(Graphics g, int topLeftX, int topLeftY, int bottomRightX, int bottomRightY, Color c)
	{
		g.setColor(c);
		g.fillRect(topLeftX, topLeftY, bottomRightX - topLeftX + 1, bottomRightY - topLeftY + 1);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		t++;
		repaint();
	}

	public static void main(String[] args)
	{
		JFrame w = new JFrame("Falling Square");
		w.setExtendedState(JFrame.MAXIMIZED_BOTH);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = w.getContentPane();
		c.add(new FallingSquare());
		w.setResizable(false);
		w.setVisible(true);
	}
}
