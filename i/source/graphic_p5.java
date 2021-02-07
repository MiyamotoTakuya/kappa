import com.nttdocomo.ui.*;
import com.nttdocomo.util.*;
import java.util.Random;
import java.lang.Math;

public class graphic_p5 extends IApplication {
	MainCanvas gc;

	public void start(){
		gc=new MainCanvas();
		Display.setCurrent(gc);
		gc.starttimer();
	}
}

class MainCanvas extends Canvas {

	int num_p=8;//ノードの個数

	int px[]={10,100,30,40,40,50,30,70};//ノードのｘ座標
	int py[]={15,90,20,50,30,80,90,80};//ノードのｙ座標

	int pc1[][]={{0,0,0,0,0,0,0,0,0,0},//各ノードと連結していれば１
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0}};

	int pdeltax[]={-1,-1,1,-1,-1,-1,1,-1};//ノードの進行方向（X軸成分）
	int pdeltay[]={1,-1,1,-1,-1,1,-1,1};//ノードの進行方向（Y軸成分）
	
	int width=getWidth();
	int height=getHeight();

	public Random random =new Random(System.currentTimeMillis());

	public ShortTimer timer;

	public MainCanvas(){//コンストラクタ
		//int i,j;
		//初期値をランダムに設定する

		setSoftLabel(Frame.SOFT_KEY_1,"次へ");
		make();
	}

	public void make(){
		int i,j;
		int k;
		//k=getrandom(3,7);
		num_p=getrandom(5,9);
		for(i=0;i<num_p;i++){
			k=random.nextInt();
			px[i]=getrandom(5,width-5);
			py[i]=getrandom(5,height-5);
			for(j=i+1;j<num_p;j++){

				//if(getrandom(1,15)>5){
				if( (k%2) == 0){
					pc1[i][j]=1;
				}else {
					pc1[i][j]=0;
				}
				k=k>>2;
				//}
			}
			if(getrandom(1,100)>50){
				pdeltax[i]=1;
			}else {
				pdeltax[i]=-1;
			}
			if(getrandom(1,10)>4){
				pdeltay[i]=1;
			}else {
				pdeltay[i]=-1;
			}
			
		}


	}

	public int getrandom(int min ,int max ){
		return Math.abs(random.nextInt()%(max-min))+min;
	}

	public void starttimer(){

		timer =ShortTimer.getShortTimer(this,1,100,true);
		timer.start();

	}

	public void move(){
		int i,j;


		for(i=0;i<num_p;i++){//各ノードについて
			if((px[i]<5)||(px[i]>(width-5))){//画面のはしで方向転換
				pdeltax[i]=pdeltax[i]*-1;
			}
			if((py[i]<5)||(py[i]>(height-5))){//画面のはしで方向転換
				pdeltay[i]=pdeltay[i]*-1;
			}

			px[i]=px[i]+pdeltax[i];
			py[i]=py[i]+pdeltay[i];
		}


	}


	public void paint(Graphics g){
		int i,j;
		g.lock();
		g.setColor(Graphics.getColorOfName(Graphics.BLACK));
		g.fillRect(0,0,getWidth(),getHeight());

		g.setColor(Graphics.getColorOfName(Graphics.WHITE));


		//各ノードから隣接するノードへの辺（エッジ）を描く
		for(i=0;i<num_p;i++){
			for(j=i;j<num_p;j++){
				if(pc1[i][j]==1){
					g.drawLine(px[i],py[i],px[j],py[j]);
				}
			}
		}
		//g.drawString("Graphic_p5",30,70);
		g.unlock(true);

	}

	public void processEvent(int type,int param){

		if(type==Display.TIMER_EXPIRED_EVENT){
			move();
			move();
			repaint();
		}

		if( type== Display.KEY_PRESSED_EVENT && param==Display.KEY_SOFT1){
			make();
			repaint();
		}

	}

}
