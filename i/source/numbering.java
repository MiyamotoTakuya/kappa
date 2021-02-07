
//目目連の誤算
//計数能力覚醒アプリ
 

import com.nttdocomo.ui.*;
import com.nttdocomo.util.*;
import java.util.*;

public class numbering extends IApplication {
	public static MainCanvas gc;

	public void start(){
		gc=new MainCanvas();
		Display.setCurrent(gc);
		gc.setSoftLabel(Frame.SOFT_KEY_1,"答え");
		gc.setSoftLabel(Frame.SOFT_KEY_2,"次へ");

	}


	class MainCanvas extends Canvas {


	Random	rnd=new Random();

	boolean	state[][]= new boolean[9][9];

	int		num_stone;

	int		rate=20;

	public MainCanvas(){
		init();
	}

	public void init(){//初期設定
		int x,y;

		num_stone=0;

		for(y=0;y<9;y++){
			for(x=0;x<9;x++){
				if( ((rnd.nextInt()>>>1)%100) < rate ){
					state[y][x]=true;
					num_stone++;
				}else {
					state[y][x]=false;
				}
			}
		}
	}

	public void processEvent(int type,int param){

		if((type==Display.KEY_PRESSED_EVENT) && (param==Display.KEY_SOFT1)){
			//答えを表示する
			//
			Dialog dialog=new Dialog(Dialog.DIALOG_INFO,"");
			dialog.setFont(Font.getFont(Font.SIZE_MEDIUM));
			dialog.setText("\n　答えは\n　"+num_stone+"目じゃ。");
			dialog.show();

		}else if( (type == Display.KEY_PRESSED_EVENT) && (param==Display.KEY_SOFT2)){
			//次へ　新しいパターンの生成
			init();
			repaint();
		}else if( (type == Display.KEY_PRESSED_EVENT) && (param==Display.KEY_1)){
			//LEVEL 1
			rate=10;
			init();
			repaint();
		}else if( (type == Display.KEY_PRESSED_EVENT) && (param==Display.KEY_2)){
			//LEVEL 2
			rate=20;
			init();
			repaint();
		}else if( (type == Display.KEY_PRESSED_EVENT) && (param==Display.KEY_3)){
			//LEVEL 3
			rate=30;
			init();
			repaint();
		}else if( (type == Display.KEY_PRESSED_EVENT) && (param==Display.KEY_4)){
			//LEVEL 4
			rate=40;
			init();
			repaint();
		}else if( (type == Display.KEY_PRESSED_EVENT) && (param==Display.KEY_5)){
			//LEVEL 5
			rate=50;
			init();
			repaint();
		}
	}

	public void paint(Graphics g){
		int x,y;
		int w=getWidth();
		Font	font;

		g.lock();
		g.setColor(Graphics.getColorOfName(Graphics.WHITE));
		g.fillRect(0,0,getWidth(),getHeight());

		g.setColor(Graphics.getColorOfName(Graphics.BLACK));

		for(y=0;y<9;y++){
			for(x=0;x<9;x++){
				if( state[y][x] ){

				//g.fillArc(12+x*24+6,y*24+6,24-6,24-6,0,360);
				g.fillArc(w/10/2+x*w/10+w/30,y*w/10+w/30,w/10-w/30,w/10-w/30,0,360);

				}
			}
		}

		g.setColor(Graphics.getColorOfName(Graphics.BLACK));

		font=Font.getFont(Font.SIZE_MEDIUM);
		g.setFont(font);
		g.drawString("ＬＥＶＥＬ　0"+rate/10,0,getHeight()-1);


		g.unlock(true);

	}//end of paint



	}//end of MainCanvas

}//end of class shirt

