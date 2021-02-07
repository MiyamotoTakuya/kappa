//’´ƒ‚ƒ“ƒhƒŠƒAƒ“

import com.nttdocomo.ui.*;
import com.nttdocomo.util.*;
import java.util.Random;
import java.lang.Math;

public class graphic_p6 extends IApplication {
	MainCanvas gc;

	public void start(){
		gc=new MainCanvas();
		Display.setCurrent(gc);
		gc.starttimer();
	}
}

class MainCanvas extends Canvas {

	//Graphics gr;

	public ShortTimer timer;


	public int phase;

	public Random random =new Random(System.currentTimeMillis());

	public boolean field[][]=new boolean[getWidth()][getHeight()];

	public int point_x[]=new int[10];
	public int point_y[]=new int[10];

	public int point_x_a[]=new int[10];
	public int point_x_b[]=new int[10];
	public int point_y_a[]=new int[10];
	public int point_y_b[]=new int[10];

	public boolean point_x_a_b[]=new boolean [10];
	public boolean point_x_b_b[]=new boolean [10];
	public boolean point_y_a_b[]=new boolean [10];
	public boolean point_y_b_b[]=new boolean [10];


	int x_1[]=new int[23];
	int x_2[]=new int[23];
	int y_1[]=new int[23];
	int y_2[]=new int[23];

	int color[]=new int[23];

	int count_rect;

	int [] color_tbl={0,0,0,0,0,0,0,0,0,0,0,0};


	public MainCanvas(){//ƒRƒ“ƒXƒgƒ‰ƒNƒ^

		color_tbl[0]=Graphics.getColorOfRGB(0,0,0);
		color_tbl[1]=Graphics.getColorOfRGB(0,0,0);
		color_tbl[2]=Graphics.getColorOfRGB(0,0,0);

		color_tbl[3]=Graphics.getColorOfRGB(255,0,0);
		color_tbl[4]=Graphics.getColorOfRGB(0,255,0);
		color_tbl[5]=Graphics.getColorOfRGB(0,0,255);

		color_tbl[6]=Graphics.getColorOfRGB(0,0,0);
		color_tbl[7]=Graphics.getColorOfRGB(0,0,0);
		color_tbl[8]=Graphics.getColorOfRGB(0,0,0);

		color_tbl[9]=Graphics.getColorOfRGB(0,0,0);
		color_tbl[10]=Graphics.getColorOfRGB(0,0,0);
		color_tbl[11]=Graphics.getColorOfRGB(0,0,0);

		setSoftLabel(Frame.SOFT_KEY_1,"Ÿ‚Ö");
		initialize();

	}//‚±‚±‚Ü‚ÅƒRƒ“ƒXƒgƒ‰ƒNƒ^

	public void paint_rect(){

		int x,y;
		int x1,y1,x2,y2;
		int i;
		for(y=4;y<getHeight();y=y+8){
			for(x=4;x<getWidth();x=x+8){


				if(field[x][y]==false){//‚±‚±‚Í‚Ü‚¾“h‚ç‚ê‚Ä‚¢‚È‚¢
					for(x2=x;(x2<getWidth()) && (field[x2][y]==false);x2++){//‰E’[‚ğ’T‚·
					}

					for(x1=x; (x1>0) && (field[x1][y]==false);x1--){//¶’[‚ğ’T‚·
					}

					for(y1=y;(y1>0) && (field[x][y1]==false);y1--){//ã’[‚ğ’T‚·

					}

					for(y2=y;(y2<getHeight()) && (field[x][y2]==false);y2++){//‰º’[‚ğ’T‚·
					}

					x_1[count_rect]=x1;
					x_2[count_rect]=x2;
					y_1[count_rect]=y1;
					y_2[count_rect]=y2;
					fill(x1,y1,x2,y2);
					count_rect++;

				}//end-if
			}//end of x loop

		}//end of y loop
	}

	public void fill(int x1,int y1,int x2,int y2){
		int x,y;
		for(x=x1;x<x2;x++){
			for(y=y1;y<y2;y++){
				field[x][y]=true;
			}
		}
	}


	public void initialize(){

		int i,j,w,h;

		phase=0;

		count_rect=0;

		for(i=0;i<20;i++){
			color[i]=color_tbl[Math.abs(random.nextInt()%12)];
		}

	w=getWidth();
	h=getHeight();
	for(i=0;i<w;i++){
		for(j=0;j<h;j++){
			field[i][j]=false;
		}

	}

	for(i=0;i<10;i++){
		point_x_a_b[i]=false;
		point_x_b_b[i]=false;
		point_y_a_b[i]=false;
		point_y_b_b[i]=false;
		
	}


	for(i=0;i<10;i++){//ƒ‰ƒ“ƒ_ƒ€‚É“_‚ğ‘I‚Ô

		j=Math.abs(random.nextInt()%getWidth());
		j=j/16*16;
		if(j==0)j=j+16;
		point_x[i]=j;

		j=Math.abs(random.nextInt()%getHeight());
		j=j/16*16;
		if(j==0)j=j+16;
		point_y[i]=j;
	}


	for(i=0;i<10;i++){
		field[point_x[i]][point_y[i]]=true;

		point_x_a[i]=point_x[i];
		point_x_b[i]=point_x[i];
		point_y_a[i]=point_y[i];
		point_y_b[i]=point_y[i];
	}




	}


	public int getrandom(int min ,int max ){
		return Math.abs(random.nextInt()%(max-min))+min;
	}

	public void starttimer(){

		timer =ShortTimer.getShortTimer(this,1,100,true);
		timer.start();

	}

	public void move(){//¶‰EAã‰º‚ÉL‚Ñ‚é
		int i,j,x,y;

		int flag=0;
		//gr.lock();

		if(phase==1){
			return;
		}

		for(i=0;i<7;i++){//¶‚ÉL‚Ñ‚é
			if(point_x_a_b[i]==false){
				--point_x_a[i];
			}
			x=point_x_a[i];
			y=point_y[i];
			if(point_x_a_b[i]==false ){//‚Ü‚¾L‚Ñ‚é
					if((x > 0) && (field[x][y]==false)){//æ‚É•Ç‚ª‚È‚¢
					field[x][y]=true;//•Ç‚ğ‚Â‚­‚è‚È‚ª‚çL‚Ñ‚é
					flag=1;
//					gr.drawRect(x,y,0,0);
					}else {//•Ç‚ª‚ ‚Á‚ÄL‚Ñ‚ç‚ê‚È‚¢
						point_x_a_b[i]=true;
						//flag=0;
					}
			}
		}

		for(i=0;i<7;i++){//‰E‚ÉL‚Ñ‚é
			if(point_x_b_b[i]==false){
				++point_x_b[i];
			}
			x=point_x_b[i];
			y=point_y[i];
			if(point_x_b_b[i]==false ){//‚Ü‚¾L‚Ñ‚é
					if((x < getWidth()) && (field[x][y]==false)){//æ‚É•Ç‚ª‚È‚¢
					field[x][y]=true;//•Ç‚ğ‚Â‚­‚è‚È‚ª‚çL‚Ñ‚é
					flag=1;
//					gr.drawRect(x,y,0,0);
					}else {//•Ç‚ª‚ ‚Á‚ÄL‚Ñ‚ç‚ê‚È‚¢
						point_x_b_b[i]=true;
						//flag=0;
					}
			}
		}

		for(i=2;i<10;i++){//ã‚ÉL‚Ñ‚é
			if(point_y_a_b[i]==false){
				--point_y_a[i];
			}
			y=point_y_a[i];
				x=point_x[i];
			if(point_y_a_b[i]==false ){//‚Ü‚¾L‚Ñ‚é
					if((y > 0) && (field[x][y]==false)){//æ‚É•Ç‚ª‚È‚¢
					field[x][y]=true;//•Ç‚ğ‚Â‚­‚è‚È‚ª‚çL‚Ñ‚é
					flag=1;
//					gr.drawRect(x,y,0,0);
					}else {//•Ç‚ª‚ ‚Á‚ÄL‚Ñ‚ç‚ê‚È‚¢
						point_y_a_b[i]=true;
						//flag=0;
					}
			}
		}

		for(i=2;i<10;i++){//‰º‚ÉL‚Ñ‚é
			if(point_y_b_b[i]==false){
				++point_y_b[i];
			}
			y=point_y_b[i];
				x=point_x[i];
			if(point_y_b_b[i]==false ){//‚Ü‚¾L‚Ñ‚é
					if((y < getHeight()) && (field[x][y]==false)){//æ‚É•Ç‚ª‚È‚¢
					field[x][y]=true;//•Ç‚ğ‚Â‚­‚è‚È‚ª‚çL‚Ñ‚é
					flag=1;
//					gr.drawRect(x,y,0,0);
					}else {//•Ç‚ª‚ ‚Á‚ÄL‚Ñ‚ç‚ê‚È‚¢
						point_y_b_b[i]=true;
						//flag=0;
					}
			}
		}


		if(flag==0){
			paint_rect();
			phase=1;
		}

		//gr.unlock(true);

	}//‚±‚±‚Ü‚Åmove()


	public void paint(Graphics g){
		int i;
			g.lock();


			g.setColor(Graphics.getColorOfName(Graphics.BLACK));
			g.fillRect(0,0,getWidth(),getHeight());

			g.setColor(Graphics.getColorOfName(Graphics.WHITE));
//			g.drawRect(0,0,100,100);


			if(phase==0){

			for(i=0;i<10;i++){
				g.drawLine(point_x[i],point_y[i],point_x_a[i],point_y[i]);
				//g.drawRect(point_x[i],point_y[i],0,0);
			}
			for(i=0;i<10;i++){
				g.drawLine(point_x[i],point_y[i],point_x_a[i],point_y[i]);
				//g.drawRect(point_x_a[i],point_y[i],0,0);
			}
			for(i=0;i<10;i++){
				g.drawLine(point_x[i],point_y[i],point_x_b[i],point_y[i]);
				//g.drawRect(point_x_b[i],point_y[i],0,0);

			}
			for(i=0;i<10;i++){
				g.drawLine(point_x[i],point_y[i],point_x[i],point_y_a[i]);
				//g.drawRect(point_x[i],point_y_a[i],0,0);
			}
			for(i=0;i<10;i++){
				g.drawLine(point_x[i],point_y[i],point_x[i],point_y_b[i]);
				//g.drawRect(point_x[i],point_y_b[i],0,0);
			}
			}//end-if(phase==0)

			if(phase==1){//(phase==1)
				for(i=0;i<count_rect;i++){
					g.setColor(color[i]);
					g.fillRect(x_1[i],y_1[i]
						,x_2[i]-x_1[i],y_2[i]-y_1[i]);
				}

			g.setColor(Graphics.getColorOfName(Graphics.WHITE));
				for(i=0;i<10;i++){
					g.drawLine(point_x[i],point_y[i],point_x_a[i],point_y[i]);
					//g.drawRect(point_x_a[i],point_y[i],0,0);
				}
				for(i=0;i<10;i++){
					g.drawLine(point_x[i],point_y[i],point_x_b[i],point_y[i]);
					//g.drawRect(point_x_b[i],point_y[i],0,0);

				}
				for(i=0;i<10;i++){
					g.drawLine(point_x[i],point_y[i],point_x[i],point_y_a[i]);
					//g.drawRect(point_x[i],point_y_a[i],0,0);
				}
				for(i=0;i<10;i++){
					g.drawLine(point_x[i],point_y[i],point_x[i],point_y_b[i]);
					//g.drawRect(point_x[i],point_y_b[i],0,0);
				}


			}//end-if(phase==1)

			g.unlock(true);
	}

	public void processEvent(int type,int param){
		//‚¿‚å‚Á‚Æ‚Ã‚Â“®‚¢‚ÄƒAƒjƒ[ƒVƒ‡ƒ“‚·‚é
		if(type==Display.TIMER_EXPIRED_EVENT){
			move();
			repaint();
		}

		else if((type==Display.KEY_PRESSED_EVENT) && (param==Display.KEY_SOFT1)){
			initialize();
			//repaint();
		}
		/**/
	}

}