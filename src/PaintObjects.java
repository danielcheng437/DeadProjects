import java.awt.Color;
import java.awt.Graphics;

public class PaintObjects
{
	public int x, y, xW, yH, xFrontMost, yBottom, dHeight,
	   x_LOCTN_ON_SCREEN, GRND_ELEV_ON_SCREEN, t, time;
	
	public void PaintPlayer(Graphics g)
	{
		g.setColor(Color.BLUE);
	    g.drawLine(0, GRND_ELEV_ON_SCREEN, 1600, GRND_ELEV_ON_SCREEN);
	    
	    g.setColor(Color.RED);
	    g.drawLine(Math.abs((6 * time) % 3072 - 1536), GRND_ELEV_ON_SCREEN - 31,
	    		   Math.abs((6 * time) % 3072 - 1536), GRND_ELEV_ON_SCREEN - 1);
	    g.drawLine(Math.abs((3 * time + 1536) % 3072 - 1536), GRND_ELEV_ON_SCREEN - 31,
	    		   Math.abs((3 * time + 1536) % 3072 - 1536), GRND_ELEV_ON_SCREEN - 1);
	    
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

		System.out.println(Math.abs((2 * time - 1536) % 3072 - 1536));
	    
		g.setColor(Color.BLACK);
		g.fillRect(x, y, xW, yH);
	}
}
