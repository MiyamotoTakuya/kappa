import com.nttdocomo.ui.*;
import com.nttdocomo.util.*;


public class graphic_p1 extends IApplication {
	MainCanvas gc;



	public void start(){
		gc=new MainCanvas();
		Display.setCurrent(gc);
		gc.starttimer();
	}
}

class MainCanvas extends Canvas {

	int x1;
	int y1;
	int x2;
	int y2;
	int x3;
	int y3;
	int x4;
	int y4;

	int px1,py1;
	int px2,py2;
	int px3,py3;
	int px4,py4;

	int wx1,wy1;
	int wx2,wy2;
	int wx3,wy3;
	int wx4,wy4;


	int delta=getWidth()/2;

	int rate_m=delta-delta/4,rate_n=delta/4;

	int dir =1;

	ShortTimer timer;

	public void MainCanvas(){

	}

	public void starttimer(){

		timer =ShortTimer.getShortTimer(this,1,50,true);
		timer.start();

	}

	public void paint(Graphics g){
		int i;

	x1=0;
	y1=0;
	x2=getWidth()-1;
	y2=0;
	x3=getWidth()-1;
	y3=getHeight()-1;
	x4=0;
	y4=getHeight()-1;



		g.lock();
		g.setColor(Graphics.getColorOfName(Graphics.BLACK));
		g.fillRect(0,0,getWidth(),getHeight());

		g.setColor(Graphics.getColorOfName(Graphics.RED));

		for(i=0;i<8;i++){

		px1=(rate_m*x2+rate_n*x1)/delta;
		py1=(rate_m*y2+rate_n*y1)/delta;

		px2=(rate_m*x3+rate_n*x2)/delta;
		py2=(rate_m*y3+rate_n*y2)/delta;

		px3=(rate_m*x4+rate_n*x3)/delta;
		py3=(rate_m*y4+rate_n*y3)/delta;

		px4=(rate_m*x1+rate_n*x4)/delta;
		py4=(rate_m*y1+rate_n*y4)/delta;

		g.drawLine(px1,py1,px2,py2);
		g.drawLine(px2,py2,px3,py3);
		g.drawLine(px3,py3,px4,py4);
		g.drawLine(px4,py4,px1,py1);

		x1=px1;y1=py1;
		x2=px2;y2=py2;
		x3=px3;y3=py3;
		x4=px4;y4=py4;

		}

		//g.setColor(Graphics.getColorOfName(Graphics.WHITE));
		//g.drawString("Graphic_p1",30,70);
		g.unlock(true);

	}

	public void processEvent(int type,int param){

		if(type == Display.KEY_PRESSED_EVENT){
			if((rate_m>1 )&& (rate_m<delta)){
				dir=dir*-1;
			}
		}


		if(type==Display.TIMER_EXPIRED_EVENT){

			rate_m=rate_m+dir;
			if(dir==-1){
				if(rate_m==0)
					rate_m=delta+1;
			}else {
				if(rate_m==delta)
					rate_m=0;
					
			}

			rate_n=delta-rate_m;
			repaint();
		}

	}


}