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
	implements Runnable, ActionListener
{
	public static final int startingHeight = 150;
	public static final int cubeHitbox = 30;
	
	PaintObjects p = new PaintObjects();
	Canvas canvas;
	BufferStrategy bufferStrategy;
	boolean running = true;
	
	public HandlingEvents()
	{
		p.xW = cubeHitbox;
		p.yH = cubeHitbox;

		p.x_LOCTN_ON_SCREEN = 1536 / 4;
		p.GRND_ELEV_ON_SCREEN = (5 * 841) / 8 + p.yH;
		
		p.x = p.x_LOCTN_ON_SCREEN;
		p.y = p.GRND_ELEV_ON_SCREEN - p.yH - startingHeight;
		
		JFrame w;
		w = new JFrame("Basic Game");
		w.setExtendedState(JFrame.MAXIMIZED_BOTH);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container panel = w.getContentPane();
		w.setResizable(false);
		w.setVisible(true);
		
		canvas = new Canvas();
		canvas.setBounds(0, 0, 1600, 900);
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
		
		p.time = 0;
		Timer clock = new Timer(1, this); 
		clock.start();
	}
	
	public void run()
	{
		while (running = true)
		{
			p.t++;
			Repaint();

			try {
				Thread.sleep(20);
			}
			
			catch (InterruptedException e) {
			}
		}
	}
	
	public void Repaint()
	{
		Graphics g = (Graphics) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, 1600, 900);
		p.PaintPlayer(g);
		bufferStrategy.show();
	}
	
	public void actionPerformed(ActionEvent e) {
		p.time++;
		repaint();
	}
	
	public void KeyPressed(KeyEvent e)
	{
		switch (e.getKeyCode())
		{
			case KeyEvent.VK_UP:
				p.y-=8;
				break;
				
			case KeyEvent.VK_LEFT:
				p.x-=5;
				break;
				
			case KeyEvent.VK_RIGHT:
				p.x+=5;
				break;
		}
	}
	
	public static void main(String[] args)
	{
		HandlingEvents ex = new HandlingEvents();
		new Thread(ex).start();
	}
}