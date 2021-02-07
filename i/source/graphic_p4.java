//Œ`‚ÌƒAƒvƒŠ‚S
import com.nttdocomo.ui.*;
import java.util.*;
import java.lang.Math;

public class graphic_p4 extends IApplication {
	MainCanvas gc;


	public void start(){
		gc=new MainCanvas();
		Display.setCurrent(gc);
	}
}

class MainCanvas extends Canvas {

	public Random random= new Random(System.currentTimeMillis());

	public int getrandom(int min ,int max ){
		return Math.abs(random.nextInt()%(max-min))+min;
	}

	public MainCanvas(){
		setSoftLabel(Frame.SOFT_KEY_2,"ŽŸ‚Ö");
	}

	public void drawtree(Graphics gr){
		int x0,y0,
			x1,y1,
			x2,y2,
			x3,y3;
		int ii,jj;



		for(jj=0;jj<3;jj++){
		
		x0=getrandom(getWidth()/10,getWidth()-1);
		y0=getHeight()-1;
		
		x1=getrandom(0,getWidth());
		y1=getrandom(getHeight()/2,getHeight());

		x2=getrandom(getWidth()/10,getWidth()-getWidth()/10);
		y2=getrandom(getWidth()/10,getHeight()/2);

		x3=getrandom(getWidth()/10,getWidth()-getWidth()/10);
		y3=getrandom(getWidth()/10,getHeight()/2);

		gr.setColor(Graphics.getColorOfName(Graphics.BLACK));
		gr.drawLine(x0,y0,x1,y1);
		gr.drawLine(x1,y1,x2,y2);
		gr.drawLine(x1,y1,x3,y3);

		
		//—t‚ð•`‚­
		gr.setColor(Graphics.getColorOfName(Graphics.GREEN));
		for(ii=0;ii<20;ii++){
			gr.drawLine(x2,y2,
				x2+getrandom(-1*getWidth()/10,getWidth()/10),
				y2+getrandom(-1*getWidth()/10,getWidth()/10));
			gr.drawLine(x3,y3,
				x3+getrandom(-1*getWidth()/10,getWidth()/10),
				y3+getrandom(-1*getWidth()/10,getWidth()/10));
		}//end of for ii
		

		}//end of for jj

		

	}


	public void paint(Graphics g){
		int i;

		g.lock();
		g.setColor(Graphics.getColorOfName(Graphics.WHITE));
		g.fillRect(0,0,getWidth(),getHeight());


		g.setColor(Graphics.getColorOfName(Graphics.BLACK));
		drawtree(g);
		//g.drawString("Graphic_p4 a",30,70);

		g.unlock(true);

	}

	public void processEvent(int type,int param){
		if(type == Display.KEY_PRESSED_EVENT && param == Display.KEY_SOFT2){
			repaint();
		}
	}

}

