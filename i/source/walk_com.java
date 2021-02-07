
import com.nttdocomo.ui.*;
import java.lang.Math;
import java.util.Random;
import com.nttdocomo.io.ConnectionException;
import java.io.*;
import javax.microedition.io.*;

/*スクラッチパッドを４バイト使用
	０	max_combo_countの上位８ビット
	１	max_combo_countの下位８ビット
	２	combo_countの上位８ビット
	３	combo_countの下位８ビット
*/


public class walk_com extends IApplication {
	MainCanvas gc;

	public void start(){
		gc=new MainCanvas();
		Display.setCurrent(gc);
	}
}

class MainCanvas extends Canvas {

	public int mode=0;// 0=>タイトルモード、// 2=>コンボアタックプレイモード

	public int flag=0;// 0=>通常状態   1=>成立表示状態,2=>不成立表示状態

	public int combo_count;//現在のコンボ達成状況

	public int max_combo_count;//最高コンボ

	public Random rnd =new Random(System.currentTimeMillis()); 

	public MediaImage mi[]=new MediaImage[14];

	public Image img[]=new Image[14];

	public int num[]={1,2,3,4,5,6,7,8,9,2,3,4,5};

	public int kou[]={0,0,0,0,0,0};//第ｎ項

	//ゲーム画面との対応

	/* 4 + 3 - 9 + 7 + 5
		= 8

	 kou[0] +kou[1]+kou[2]+kou[3]+kou[4]
	     = kou[5]
	*/

	public int cur_pos=1;//カーソルが第ｎ項をさしているかを示す

	public MainCanvas(){
		int i;

		setSoftLabel(Frame.SOFT_KEY_1,"開始");
		setSoftLabel(Frame.SOFT_KEY_2,"終了");

		try{
			//画像データ（数字や記号）読み込み
			for(i=0;i<=13;i++){
				mi[i]=MediaManager.getImage("resource:///p"+i+"x"+".gif");
				mi[i].use();
				img[i]=mi[i].getImage();
			}

			//スクラッチパッドから読み込み
			DataInputStream in = Connector.openDataInputStream("scratchpad:///0");
			max_combo_count=in.read()*256+in.read();
			combo_count=in.read()*256+in.read();
			in.close();

		}catch(Exception e){

		}

	}

	public void make(){//問題作成
		int i,j,w;

		for(i=0;i<13;i++){//シャッフル
			j=Math.abs(rnd.nextInt()%13);
			w=num[j];
			num[j]=num[i];
			num[i]=w;
		}

		kou[0]=num[0];//初項を設定

		//第１から４までの項（左辺）を設定（正負はランダムに決定）
		for(i=1;i<5;i++){
			
			if(rnd.nextInt()>0){
				kou[i]=num[i];
			}else {
				kou[i]=-num[i];
			}
		}
		

		//右辺の値（左辺の合計値）を設定
		j=0;
		for(i=0;i<5;i++){
			j=j+kou[i];
		}
		kou[5]=j;

		//いったん、左辺の項を全て正の数にしとく
		for(i=1;i<5;i++){
			kou[i]=Math.abs(kou[i]);
		}

	}

	public int check(){//式が成立しているか判定する
		int i,j;
		for(j=0,i=0;i<5;i++){
			j=j+kou[i];
		}
		if(j==kou[5]){
			return 1;//正解じゃ
		}else {
			return 0;//不正解じゃ
		}
	}

	public void processEvent(int type,int param){

			if( (type==Display.KEY_PRESSED_EVENT) && (param==Display.KEY_SOFT2)){
				//IApplication.getCurrentApp().terminate();
				try{
				DataOutputStream out= Connector.openDataOutputStream("scratchpad:///0");
				out.write((max_combo_count/256)& 0xff);
				out.write((max_combo_count%256)& 0xff);
				out.write( (combo_count/256) & 0xff);
				out.write( (combo_count%256) & 0xff);
				out.close();

				//IApplication.getCurrentApp().terminate();
				}catch(Exception e){

				}
				IApplication.getCurrentApp().terminate();

			}


		if(mode==0){//タイトル画面モード
			if(type== Display.KEY_PRESSED_EVENT){
				if(param==Display.KEY_SOFT1){
					mode=2;//コンボアタックモード
					flag=0;
					setSoftLabel(Frame.SOFT_KEY_1,"判定");
					make();
					repaint();
				}
			}

		}else if(type ==Display.KEY_PRESSED_EVENT){//ゲームモード
			if( (flag==1) || (flag== 2) ){
				flag=0;
				make();
				repaint();
			}else if(param==Display.KEY_RIGHT){
				if(cur_pos>=4){
					cur_pos=1;
				}else {
					cur_pos++;
				}
				repaint();
			}else if(param==Display.KEY_LEFT){
				if(cur_pos<=1){
					cur_pos=4;
				}else {
					cur_pos--;
				}
				repaint();
			}else if( (param==Display.KEY_UP) 
					|| (param == Display.KEY_DOWN) ||(param==Display.KEY_SELECT)){
				kou[cur_pos]=kou[cur_pos]*-1;//符号を反転
				repaint();
			}else if(param==Display.KEY_SOFT1){
				if(flag==0){
					if(check()==1){
							flag=1;
							combo_count++;
							if(combo_count>max_combo_count){
								max_combo_count=combo_count;
							}
					}else {
							flag=2;
							combo_count=0;
					}
					try{
					DataOutputStream out= Connector.openDataOutputStream("scratchpad:///0");
					out.write((max_combo_count/256)&0xff);
					out.write((max_combo_count%256)&0xff);
					out.write((combo_count/256)&0xff);
					out.write((combo_count%256)&0xff);
					out.close();
					}catch(Exception e){

					}
				}
				repaint();
			}
			

		}
	}


	public void paint(Graphics g){
		int i,j;

		if(mode == 0){//タイトル画面モード
			g.setColor(Graphics.getColorOfName(Graphics.GREEN));
			g.fillRect(0,0,getWidth(),getHeight());
			g.setColor(Graphics.getColorOfName(Graphics.WHITE));

			g.drawString("いい加減",20,40);



			g.drawString("MAX COMBO : "+max_combo_count,5,110);


		}else if( (mode ==1) || (mode ==2) ){

			g.lock();
			g.setColor(Graphics.getColorOfName(Graphics.GREEN));
			g.fillRect(0,0,getWidth(),getHeight());

			g.setColor(Graphics.getColorOfName(Graphics.WHITE));

			g.drawImage(img[13],cur_pos*12*2-12+4,30-16);
			g.drawImage(img[Math.abs(kou[0])],0*12+4,30);

			j=1;
			for(i=1;i<=4;i++){
				if(kou[i]>0){
					g.drawImage(img[10],j*12+4,30);//”＋”を表示
				}else {
					g.drawImage(img[11],j*12+4,30);//”－”を表示
				}
				j++;
				g.drawImage(img[Math.abs(kou[i])],j*12+4,30);
				j++;
			}

			j=1;
			g.drawImage(img[12],j*12+4,30+16+2);//”＝”の表示

			if(kou[5]<0){
				j++;
				g.drawImage(img[11],j*12+4,30+16+2);//”－”を表示
			}
			if( (kou[5]>=10)||(kou[5]<=-10) ){
				j++;
				g.drawImage(img[Math.abs(kou[5])/10],j*12+4,30+16+2);//１０の位表示

			}
			j++;
			g.drawImage(img[Math.abs(kou[5])%10],j*12+4,30+16+2);//１の位表示

			if(flag==1){//正解時
				g.drawString("成立じゃ。",5,90);
			}else if(flag==2){//不正解時
				//g.setColor(Graphics.getColorOfName(Graphics.WHITE));
				g.drawString("不正な式じゃ。",5,90);
				g.setColor(Graphics.getColorOfName(Graphics.RED));
				g.drawString("不正な式じゃ。",6,91);

			}
			g.drawString("COMBO : "+combo_count,5,110);

			g.unlock(true);
		}
	}

}

