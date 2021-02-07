
//地図の塗り分け

import com.nttdocomo.ui.*;
import com.nttdocomo.util.*;
import java.util.Random;
import java.lang.Math;

public class graphic_p7 extends IApplication {
	MainCanvas gc;

	public void start(){
		gc=new MainCanvas();
		Display.setCurrent(gc);
		//gc.starttimer();
	}
}

class MainCanvas extends Canvas {

	//Graphics gr;
	//public ShortTimer timer;

	public Random random =new Random(System.currentTimeMillis());

	public int field[][]=new int[getHeight()/12][getWidth()/12];

	int  color_tbl[]=new int[5];

	public MainCanvas(){//コンストラクタ

		color_tbl[0]=Graphics.getColorOfName(Graphics.YELLOW);
		color_tbl[1]=Graphics.getColorOfName(Graphics.LIME);
		color_tbl[2]=Graphics.getColorOfName(Graphics.BLUE);
		color_tbl[3]=Graphics.getColorOfName(Graphics.RED);
		color_tbl[4]=Graphics.getColorOfName(Graphics.WHITE);

		setSoftLabel(Frame.SOFT_KEY_1,"次へ");

		make();
		repaint();

	}//ここまでコンストラクタ


	public int getrandom(int max ){
		return Math.abs(random.nextInt()%max);
	}

	public void make(){
		int x,y;
		int num_color;
		
		num_color=getrandom(4)+2;
		for(y=0;y<(getHeight()/12);y++){
			for(x=0;x<(getWidth()/12);x++){
				//フィールドに色をセットする
				field[y][x]=color_tbl[getrandom(num_color)];
			}
		}


	}//end of make()


	public void paint(Graphics g){
		int x,y;

		g.lock();
			g.setColor(Graphics.getColorOfName(Graphics.WHITE));
			g.fillRect(0,0,getWidth(),getHeight());


			for(y=0;y<(getHeight()/12);y++){
				for(x=0;x<(getWidth()/12);x++){
					g.setColor(field[y][x]);
					g.fillRect(x*12,y*12,12,12);
				}
			}


			for(y=0;y<(getHeight()/12);y++){
				for(x=0;x<(getWidth()/12);x++){
					if( y!=(getHeight()/12-1) && (field[y][x] !=field[y+1][x]) ){
						g.setColor(Graphics.getColorOfName(Graphics.BLACK));
						
						g.drawLine(x*12-1,y*12+12-1,x*12+12-1,y*12+12-1);
					}
					if( x!= (getWidth()/12-1) && (field[y][x]!=field[y][x+1]) ){
						g.setColor(Graphics.getColorOfName(Graphics.BLACK));
						
						g.drawLine(x*12+12-1,y*12-1,x*12+12-1,y*12+12-1);
					}
				}
			}

			g.setColor(Graphics.getColorOfName(Graphics.BLACK));
			g.drawRect(0,0,getWidth()/12*12-1,getHeight()/12*12-1);

		g.unlock(true);

	}//end of paint()

	public void processEvent(int type,int param){

		if((type==Display.KEY_PRESSED_EVENT) && (param==Display.KEY_SOFT1)){
			make();
			repaint();
		}
	}//end of processEvent()


}

