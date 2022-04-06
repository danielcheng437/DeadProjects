import java.awt.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.event.*;
import javax.swing.*;

public class HandlingEvents extends JPanel
	implements Runnable
{
	private static int x, y, xW, yH, xFrontMost, yBottom, dHeight,
					   x_LOCTN_ON_SCREEN, GRND_ELEV_ON_SCREEN, t, time;
	private static final int startingHeight = 150;
	private static final int cubeHitbox = 48;
	private static boolean wT;
	
	Canvas canvas;
	BufferStrategy bufferStrategy;
	boolean running = true;
	
	public HandlingEvents()
	{
		JFrame w;
		w = new JFrame("Basic Game");
		w.setExtendedState(JFrame.MAXIMIZED_BOTH);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container panel = w.getContentPane();
		w.setResizable(false);
		w.setVisible(true);
		
		canvas = new Canvas();
		canvas.setBounds(0, 0, 9999, 9999);
		canvas.setIgnoreRepaint(true);
		
		canvas.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				KeyPressed(e);
			}
		});
		
		panel.add(canvas);
		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();
		canvas.requestFocus();
		
		xW = cubeHitbox;
		yH = cubeHitbox;

		x_LOCTN_ON_SCREEN = 1536 / 4;
		GRND_ELEV_ON_SCREEN = (5 * 841) / 8 + yH;
		
		x = x_LOCTN_ON_SCREEN;
		y = GRND_ELEV_ON_SCREEN - yH - startingHeight;
	}
	
	public void Paint(Graphics g)
	{
		g.setColor(Color.BLUE);
	    g.drawLine(0, GRND_ELEV_ON_SCREEN, 3072, GRND_ELEV_ON_SCREEN);
	    
		xFrontMost = x + xW - 1;
		yBottom = y + yH - 1;
		
		if (yBottom < GRND_ELEV_ON_SCREEN - 1) {
	    	y = Math.min(y + (((t + 108) * (t - 108)) / 96) + 121, GRND_ELEV_ON_SCREEN - yH);
	    }
		
		else {
			y = GRND_ELEV_ON_SCREEN - yH;
			t = 0;
		}
		
		if (x < 0) {
			x = 0;
		}
		
		if (x > 1536 - xW + 1) {
			x = 1536 - xW + 1;
		}
		
//		System.out.println(t + " " + x + " " + 1536 + " " + yBottom + " " + GRND_ELEV_ON_SCREEN);
	    
		g.setColor(Color.BLACK);
		g.fillRect(x, y, xW, yH);
	}
	
	public void run()
	{
		while (running = true)
		{
			t++;
			Repaint();

			try {
				Thread.sleep(25);
			}
			
			catch (InterruptedException e) {
			}
		}
	}
	
	public void Repaint()
	{
		Graphics g = (Graphics) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, 9999, 9999);
		Paint(g);
		bufferStrategy.show();
	}
	
	public void KeyPressed(KeyEvent e)
	{
		switch (e.getKeyCode())
		{
			case KeyEvent.VK_UP:
				y-=6;
				break;
				
			case KeyEvent.VK_LEFT:
				x -= 6;
				break;
				
			case KeyEvent.VK_RIGHT:
				x += 6;
				break;
		}
	}
	
	public static void main(String[] args)
	{
		HandlingEvents ex = new HandlingEvents();
		new Thread(ex).start();
	}
}