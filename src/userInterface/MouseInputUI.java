package userInterface;

import javax.swing.JPanel;			 // Creates panel to draw figures.
import javax.swing.JFrame;			 // Creates window to place panels.
import javax.swing.JLabel;			 // Creates mouse-action labels.
import javax.swing.JComponent;		 // Creates objects of JComponent type.
// import javax.swing.BorderFactory; // Adds borders with titles to panels.
import java.awt.BorderLayout;		 // Sets position of panels in frames.
import java.awt.Color;				 // Paints panels and figures.
import java.awt.Dimension;			 // Sets object dimensions.
import java.awt.Font;				 // Sets properties for fonts.
import java.awt.Graphics;			 // Creates figures.
import java.awt.event.MouseListener; // Detects mouse actions performed.
import java.awt.event.MouseEvent;	 // Indicates which actions to perform.
import java.util.Random;			 // Generates objects of Random type.

public class MouseInputUI
{
	// ================
	// MEMBER VARIABLES
	// ================

	// Define objects:
	// - Frame object for main window.
	// - Panel object for mouse input.
	// - Label object to indicate actions performed by the mouse
	// (i.e., mouse inside/outside panel and mouse button presses).
	
	private JFrame mouseInputFrame;
	private JPanel mouseInputPanel;
	private JLabel mouseInputPanelLabel;

	private final MouseInputListener mouseInputListener;

	public MouseInputUI()
	{
		// ===========
		// CONSTRUCTOR
		// ===========
	
		mouseInputListener = new MouseInputListener();
		initComponents();
	}

	private void initComponents()
	{
		initMouseInputPanel();
		initMouseInputFrame();
	}
	
	private void initMouseInputPanel()
	{
		// Create panel and label objects.
		mouseInputPanel = new JPanel();
		mouseInputPanelLabel = new JLabel();
		
		// Add titled border, label, and mouse listener to panel.
//		String title = "Mouse Input Panel";
//		mouseInputPanel.setBorder(BorderFactory.createTitledBorder(title));
		mouseInputPanel.add(mouseInputPanelLabel);
		mouseInputPanel.addMouseListener(mouseInputListener);
	}
	
	private void initMouseInputFrame()
	{
		// Create frame and set some properties.
		mouseInputFrame = new JFrame("Mouse Input Frame");
		mouseInputFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mouseInputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mouseInputFrame.setResizable(false);

		// Add panel to the center of the frame and make the frame visible.
		mouseInputFrame.add(mouseInputPanel, BorderLayout.CENTER);
		mouseInputFrame.setVisible(true);
	}
	
//	public void paintComponent(Graphics g)
//	{ 
//		xW = cubeHitbox;
//		yH = cubeHitbox;
//		
//		x_LOCTN_ON_SCREEN = getWidth() / 4;
//		GRND_ELEV_ON_SCREEN = 5 * getHeight() / 8 + yH;
////		xSpeed = t;
//		
//		x = x_LOCTN_ON_SCREEN + xSpeed;
//		y = Math.min(GRND_ELEV_ON_SCREEN - yH + ySpeed - startingHeight, GRND_ELEV_ON_SCREEN - yH);
//		
//		xFrontMost = x + xW - 1;
//		yBottom = Math.min(y + yH - 1, GRND_ELEV_ON_SCREEN - 1);
//
//		Color bg;
//    	bg = (Color.WHITE);
//	    setBackground(bg);
//	    
//	    super.paintComponent(g);
//	    
//	    g.setColor(Color.BLUE);
//	    g.drawLine(0, GRND_ELEV_ON_SCREEN, 15360, GRND_ELEV_ON_SCREEN);
//
////	    public void MouseClicked (MouseEvent e) {
////	    	ySpeed = (int)((t * (t - 216)) / 96);
////	    }
//	    
//	    System.out.println(t + " " + y + " " + yBottom + " " + GRND_ELEV_ON_SCREEN);
//	    fillRect2p(g, x, y, xFrontMost, yBottom, Color.BLACK);
//	    
////	    if (yBottom < GRND_ELEV_ON_SCREEN - 1) {
////	    	ySpeed = (((t + 108) * (t - 108)) / 96) + 121;
////	    }
////	    
////	    else {
////	    	ySpeed = startingHeight;
////	    	t = -1;
////	    }
//	}
	
	public void fillRect2p(Graphics g, int topLeftX, int topLeftY, int bottomRightX, int bottomRightY, Color c)
	{
		g.setColor(c);
		g.fillRect(topLeftX, topLeftY, bottomRightX - topLeftX + 1, bottomRightY - topLeftY + 1);
	}

	public void paintRectangle(Graphics g, int width, int height, int x, int y, int R, int G, int B)
	{
		g.setColor(new Color(R,G,B));
		g.fillRect(x, y, width, height);
	}

	public void paintCircle(Graphics g, int xCenter, int yCenter, int radius, int R, int G, int B)
	{
		g.setColor(new Color(R,G,B));
		g.fillOval(xCenter - radius + 1, yCenter - radius - 1, radius * 2, radius * 2);
	}

	// =================================
	// INNER CLASSES TO CREATE LISTENERS
	// =================================

	/**
	 * Creates mouse input listener for panel.
	 */

private class MouseInputListener implements MouseListener
{
	@Override
	public void mouseClicked(MouseEvent e)
	{
		// If the shift key is pressed, reset the panel and its label.
		if (e.isShiftDown())
		{
			mouseInputPanelLabel.setText("");
			mouseInputPanel.repaint();
		}

		int x = e.getX();
		int y = e.getY();

		// Create graphics object to paint figures on it.
		Graphics g = mouseInputPanel.getGraphics();

		// Create random object for random number generation.
		Random r = new Random();
		
		// Set actions for right and left mouse clicks (press & release).
		// - If right click, draw rectangles/squares of random size/color.
		// - If left click, draw ovals/circles of random size/color.
		
		int width, height, radius;
		
		if (e.isMetaDown())
		{
			mouseInputPanelLabel.setText("Right button clicked.");

			width = r.nextInt(40) + 20;
			height = r.nextInt(40) + 20;

			paintRectangle(g, width, height, x-width/2, y-height/2, r.nextInt(255), r.nextInt(255), r.nextInt(255));
		}
		
		else
		{
			mouseInputPanelLabel.setText("Left button clicked.");
			mouseInputPanelLabel.setBackground(Color.RED);
		
			radius = r.nextInt(50) + 20;
			
			paintCircle(g, x, y, radius, r.nextInt(255), r.nextInt(255), r.nextInt(255));
		}
		
		mouseInputPanelLabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 18));
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		mouseInputPanel.setBackground(Color.WHITE);
		
		if (e.isMetaDown())
		{
			mouseInputPanelLabel.setText("Right button pressed.");
		}
			
		else
		{
			mouseInputPanelLabel.setText("Left button pressed.");
			mouseInputPanelLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
		}
	}
		
	@Override
	public void mouseReleased(MouseEvent e)
	{
		mouseInputPanelLabel.setText("Mouse button released.");
		mouseInputPanelLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
	}
		
	@Override
	public void mouseEntered(MouseEvent e)
	{
		// Paint the panel of the given color when mouse enters the panel.
		mouseInputPanel.setBackground(Color.LIGHT_GRAY);
		mouseInputPanelLabel.setText("Mouse IN");
		mouseInputPanelLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
		mouseInputPanelLabel.setForeground(Color.BLACK);
	}
		
	@Override
	public void mouseExited(MouseEvent e) 
	{
		// Paint the panel of the given color when mouse exits the panel.
		mouseInputPanel.setBackground(Color.DARK_GRAY);
		mouseInputPanelLabel.setText("Mouse OUT");
		mouseInputPanelLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
		mouseInputPanelLabel.setForeground(Color.WHITE);
	}	
  }
}
