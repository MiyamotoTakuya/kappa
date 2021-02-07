
import	com.nttdocomo.ui.*;
import	java.util.*;

public class dish_stack extends IApplication {
	MainCanvas gc;
	public void start(){
		gc = new MainCanvas();
		Display.setCurrent(gc);
	}
}

class MainCanvas extends Canvas {
	public int clr_tbl[]={0,0,0,0};
	public int field[]=new int[7*16];
	public int cnt_stack[]={0,0,0,0,0,0,0};
	public int place[]= new int[112];
	public int next_color;
	public int get_pos;

	public int total_number;

	public int state;

	public Random rand = new Random(System.currentTimeMillis());


	MainCanvas(){
		int i;
		int j;
		int w;
		clr_tbl[0]=Graphics.getColorOfName(Graphics.WHITE);
		clr_tbl[1]=Graphics.getColorOfName(Graphics.YELLOW);
		clr_tbl[2]=Graphics.getColorOfName(Graphics.BLUE);
		clr_tbl[3]=Graphics.getColorOfName(Graphics.RED);

		setSoftLabel(Frame.SOFT_KEY_2,"èIóπ");

		state=0;
		get_pos=3;
		total_number=12*4;


		for(i=0;i<112;i++){
			place[i]=i%7;
		}


	}

	public void make(){
		//shuffle
		int i;
		int j;
		int w;

		for(i=0;i<112;i++){
			field[i]=-99;
		}

		for(i=0;i<112;i++){
			j=Math.abs(rand.nextInt()%7);
			
			w=place[i];
			place[i]=place[j];
			place[j]=w;
		}

		for(i=0;i<total_number;i=i+1){
			j=place[i];
			field[cnt_stack[j]*7+j]=clr_tbl[i%4];
			cnt_stack[j]=cnt_stack[j]+1;
		}

		next_color=(total_number-1)%4;

	}


	public void paint(Graphics g){
		int i;

		if(state==1){
		g.lock();
		g.setColor(Graphics.getColorOfName(Graphics.LIME));
		g.fillRect(0,0,getWidth(),getHeight());

		g.setColor(Graphics.getColorOfName(Graphics.WHITE));
		g.drawString("next is",6,10);
		g.setColor(clr_tbl[next_color]);
		g.fillRect(52,6,15,4);

		g.setColor(Graphics.getColorOfName(Graphics.BLACK));

/*		g.drawString("1",4+4,24);
		g.drawString("2",4+4+16,24);
		g.drawString("3",4+4+16*2,24);
		g.drawString("4",4+4+16*3,24);
		g.drawString("5",4+4+16*4,24);
		g.drawString("6",4+4+16*5,24);
		g.drawString("7",4+4+16*6,24);
		//g.drawString("a"+cnt_stack[0],4+4+16*6,10);
*/

		//g.drawRect(get_pos*16+4,100-cnt_stack[get_pos]*6+20,15,4);

		g.drawLine(get_pos*16+4,100-cnt_stack[get_pos]*6+20,
			get_pos*16+4+8,100-cnt_stack[get_pos]*6+20+4);

		g.drawLine(get_pos*16+4+8,100-cnt_stack[get_pos]*6+20+4,
			get_pos*16+4+8+8,100-cnt_stack[get_pos]*6+20);

		g.drawLine(get_pos*16+4,100-cnt_stack[get_pos]*6+20,
			get_pos*16+4+8+8,100-cnt_stack[get_pos]*6+20);



		for(i=0;i<112;i=i+1){
			if(field[i]!=-99){
			g.setColor(field[i]);
			g.fillRect(i%7*16+4,100-(i/7*6)+20,15,4);
			}
		}
		g.unlock(true);
		}else if(state==0){
		g.lock();
		g.setColor(Graphics.getColorOfName(Graphics.LIME));
		g.fillRect(0,0,getWidth(),getHeight());
		g.setColor(Graphics.getColorOfName(Graphics.WHITE));

		g.drawString("ñáêîÅ©Å®: "+total_number,10,40);
		g.unlock(true);
		}else if(state==2){
		g.lock();
		g.setColor(Graphics.getColorOfName(Graphics.WHITE));
		g.fillRect(0,0,getWidth(),getHeight());
		g.setColor(Graphics.getColorOfName(Graphics.BLACK));
		g.drawString("You are ",20,40);
		g.drawString("congraturation!",20,50);
		g.drawString("Oh yeah! aha!",20,60);
		g.unlock(true);

		}

	}

	public void processEvent(int type,int param){



		//get_pos=-1;

		if(type == Display.KEY_PRESSED_EVENT && param ==Display.KEY_SOFT2){
			IApplication.getCurrentApp().terminate();
		}

		if(type == Display.KEY_PRESSED_EVENT){

			if(state==0){
			if(param== Display.KEY_RIGHT){
				if(total_number>=112){
					total_number=5*4;
				}else {
					total_number=total_number+4;
				}
				repaint();
			}else if(param == Display.KEY_LEFT){
				if(total_number<=5*4){
					total_number=112;
				}else {
					total_number=total_number-4;
				}
				repaint();
			}else if(param== Display.KEY_SELECT){
				state=1;
				make();
				repaint();
			}
			}

			if(state==1){
/*			if(param == Display.KEY_1){
				get_pos=0;
			}else if(param== Display.KEY_2){
				get_pos=1;
			}else if(param==Display.KEY_3){
				get_pos=2;
			}else if(param==Display.KEY_4){
				get_pos=3;
			}else if(param==Display.KEY_5){
				get_pos=4;
			}else if(param==Display.KEY_6){
				get_pos=5;
			}else if(param==Display.KEY_7){
				get_pos=6;
			}
*/			

			if(param==Display.KEY_RIGHT){
				if(get_pos>=6){
					get_pos=0;
				}else {
					get_pos++;
				}
				repaint();
			}else if(param==Display.KEY_LEFT){
				if(get_pos<=0){
					get_pos=6;
				}else {
				get_pos=get_pos-1;
				}
				repaint();
			}

			else if(param==Display.KEY_DOWN){
				if(cnt_stack[get_pos]>0){
					if(clr_tbl[next_color] == field[cnt_stack[get_pos]*7+get_pos-7]){
						field[cnt_stack[get_pos]*7+get_pos-7]=-99;
						cnt_stack[get_pos]=cnt_stack[get_pos]-1;
						total_number=total_number-1;
						if(total_number==0){
							state=2;
						}
						if(next_color== 0){
							next_color=3;
						}else {
							next_color=next_color-1;
						}
						repaint();
					}
				}
			}

			}//state==1;

			if(state==2){
				if(type == Display.KEY_PRESSED_EVENT
					&& param == Display.KEY_SELECT){
					state=0;
					total_number=12*4;
					get_pos=3;
					repaint();
				}
			}


		}

	}


}

