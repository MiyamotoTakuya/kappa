import	com.nttdocomo.ui.*;
import java.util.*;

public	class	sqpzl_a extends IApplication {
	MainCanvas gc;
	public void start(){
		gc = new MainCanvas();
		Display.setCurrent(gc);
	}
}

class MainCanvas extends Canvas {
	int [] field_up=   new int[8];;
	int [] field_down =new int[8];
	int [] field_right=new int[8];
	int [] field_left =new int[8];

	int [] color_tbl={0,0,0,0,0};

	int flag_first = -1;

	int src_pos = 1;
	int dst_pos = -1;

	int size_tile;

	Random rand = new Random(System.currentTimeMillis());

	public MainCanvas(){

		//問題作成
		makeptn();

		setSoftLabel(Frame.SOFT_KEY_1,"判定");
		setSoftLabel(Frame.SOFT_KEY_2,"終了");

		size_tile=getWidth()/4;

	}

	public void paint(Graphics g){
		int i,j;

		if(flag_first==-1){
		color_tbl[0]=Graphics.getColorOfName(Graphics.YELLOW);
		color_tbl[1]=Graphics.getColorOfName(Graphics.RED);
		color_tbl[2]=Graphics.getColorOfName(Graphics.WHITE);
		color_tbl[3]=Graphics.getColorOfName(Graphics.LIME);
		color_tbl[4]=Graphics.getColorOfName(Graphics.BLUE);
		flag_first=1;
		}
		g.lock();
		g.setColor(Graphics.getColorOfName(Graphics.BLACK));
		g.fillRect(0,0,getWidth(),getHeight());
		
		for(i=0;i<=7;i=i+1){
			drawcard(g,size_tile*(i%4),size_tile*(i/4),i);
		}
		
		//移動元カーソル枠
		for(i=0;i<size_tile/12;i++){
		g.setColor(Graphics.getColorOfName(Graphics.WHITE));
		g.drawRect(i+src_pos%4*size_tile,i+src_pos/4*size_tile,size_tile-i*2,size_tile-i*2);
		}

		//移動先カーソル枠

		if(dst_pos!=-1){
			for(i=0;i<size_tile/12;i++){
			g.setColor(Graphics.getColorOfName(Graphics.RED));
			g.drawRect(i+dst_pos%4*size_tile,i+dst_pos/4*size_tile,size_tile-i*2,size_tile-i*2);
			}
		}



		
		//g.setColor(Graphics.getColorOfName(Graphics.WHITE));
		//g.drawString("タイルdeパズるＡ test",0,size_tile*3);
		g.unlock(true);

	}

	public void processEvent(int type,int param){
		if(type == Display.KEY_PRESSED_EVENT ){
		   if(param == Display.KEY_SOFT2){
			IApplication.getCurrentApp().terminate();
		   }

		   else if(param == Display.KEY_SOFT1){
			judge();
		   }

		   else if(param==Display.KEY_UP){
			if(dst_pos!=-1){
				if(dst_pos<=3){
					dst_pos=dst_pos+4;
				}else {
					dst_pos=dst_pos-4;
				}
				repaint();
			}else {
				if(src_pos<=3){
					src_pos=src_pos+4;
					}else {
					src_pos=src_pos-4;
					}
					repaint();
			   }
			}

		   else if(param==Display.KEY_DOWN){
			if(dst_pos!=-1){
				if(dst_pos>=4){
					dst_pos=dst_pos-4;
				}else {
					dst_pos=dst_pos+4;
				}
				repaint();
			}else {
				if(src_pos>=4){
				    src_pos=src_pos-4;
				}else {
					src_pos=src_pos+4;
				}
				repaint();
			}
		   }

		   else if(param==Display.KEY_RIGHT){
			if(dst_pos!=-1){
				if(dst_pos%4==3){
					dst_pos=dst_pos-3;
				}else {
					dst_pos=dst_pos+1;
				}
			}else{
				if(src_pos%4==3){
					src_pos=src_pos-3;
				}else {
					src_pos=src_pos+1;
				}
			}
			repaint();
		   }

		   else if(param==Display.KEY_LEFT){
			if(dst_pos!=-1){
				if(dst_pos%4==0){
					dst_pos=dst_pos+3;
				}else {
					dst_pos=dst_pos-1;
				}
			}else {
				if(src_pos%4==0){
					src_pos=src_pos+3;
				}else {
					src_pos=src_pos-1;
				}
			}
			repaint();
		   }

		   else if(param==Display.KEY_SELECT){
			/*if(src_pos==dst_pos){
			rotate(src_pos);
			dst_pos=-1;
			}else*/

			if(dst_pos==-1){
			dst_pos=src_pos;
			}else {
				swap(dst_pos,src_pos);
				src_pos=dst_pos;
				dst_pos=-1;
			}
			repaint();
			
		   }

		}
	}

	public void judge(){
		int i,flag;
		flag=1;

		Dialog d = new Dialog(Dialog.DIALOG_INFO,"判定結果");
		d.setFont(Font.getFont(Font.SIZE_MEDIUM));
		d.setText("完成じゃ。\nどんどん行くぞ。");

		for(i=0;i<=3;i=i+1){
			if(field_down[i]!=field_up[i+4]){
					flag=0;
			}
			
		}
		for(i=0;i<=2;i=i+1){
			if(field_right[i]!=field_left[i+1]){
				flag=0;
			}

		}
		for(i=4;i<=6;i=i+1){
			if(field_right[i]!=field_left[i+1]){
				flag=0;
			}

		}


		if(flag==0){
			d.setText("まだ、だめじゃ。");
			d.show();
		}else {
		src_pos=1;
		dst_pos=-1;
		makeptn();
		//repaint();
		d.show();
		repaint();
		}
	}

	public int getrandom(){
		return Math.abs(rand.nextInt()%5);
	}

	public void makeptn(){
		int i;

		for(i=0;i<=7;i=i+1){//上辺の設定
			field_up[i]=getrandom();
		}

		for(i=0;i<=3;i=i+1){//一行目の下辺の設定
			field_down[i]=field_up[i+4];
		}

		for(i=4;i<=7;i=i+1){//二行目の下辺の設定
			field_down[i]=getrandom();
		}



		for(i=0;i<=7;i=i+1){//左辺をランダムに設定
			field_left[i]=getrandom();
		}

		for(i=0;i<=7;i=i+1){//右辺を設定
			if(i%4==3){
				field_right[i]=getrandom();
			}else {
				field_right[i]=field_left[i+1];
			}
		}

		shuffle();

	}

/*	public void rotate(int i){
		int work;
		work=field_up[i];
		field_up[i]=field_left[i];
		field_left[i]=field_down[i];
		field_down[i]=field_right[i];
		field_right[i]=work;
	}
*/

	public void shuffle(){
		int i,j;
		for(i=0;i<=7;i=i+1){
		
			j=Math.abs(rand.nextInt()%7);
			swap(i,j);
		}

	}



	public void swap(int src, int dst){
		int work;
		work=field_up[src];
		field_up[src]=field_up[dst];
		field_up[dst]=work;

		work=field_down[src];
		field_down[src]=field_down[dst];
		field_down[dst]=work;

		work=field_right[src];
		field_right[src]=field_right[dst];
		field_right[dst]=work;

		work=field_left[src];
		field_left[src]=field_left[dst];
		field_left[dst]=work;


	}

	public void drawcard(Graphics g,int x,int y,int i){
	int j;

	int vx[]=new int[3];
	int vy[]=new int[3];


	//上辺
	vx[0]=x+0;vy[0]=y+0;
	vx[1]=x+size_tile/2;vy[1]=y+size_tile/2;
	vx[2]=x+size_tile;vy[2]=y+0;
	g.setColor(color_tbl[field_up[i]]);
	g.fillPolygon(vx,vy,3);

	//下辺
	g.setColor(color_tbl[field_down[i]]);
	vx[0]=x+0;vy[0]=y+size_tile;
	vx[1]=x+size_tile/2;vy[1]=y+size_tile/2;
	vx[2]=x+size_tile;vy[2]=y+size_tile;
	g.fillPolygon(vx,vy,3);

	//左辺
	g.setColor(color_tbl[field_left[i]]);
	vx[0]=x+0;vy[0]=y+0;
	vx[1]=x+size_tile/2;vy[1]=y+size_tile/2;
	vx[2]=x+0;vy[2]=y+size_tile;
	g.fillPolygon(vx,vy,3);

	//右辺
	g.setColor(color_tbl[field_right[i]]);
	vx[0]=x+size_tile;vy[0]=y+0;
	vx[1]=x+size_tile/2;vy[1]=y+size_tile/2;
	vx[2]=x+size_tile;vy[2]=y+size_tile;

	g.fillPolygon(vx,vy,3);

	g.setColor(Graphics.getColorOfName(Graphics.BLACK));


	g.drawRect(x,y,size_tile,size_tile);
	for(j=0;j<size_tile/6;j++){
		g.drawRect(x+j,y+j,size_tile-j-j,size_tile-j-j);
	}

	}

}
