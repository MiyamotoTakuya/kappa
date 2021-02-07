
//酔っ払いゲーム　TYPE- A
//JET HERO(固定画面　サイドビュー方式)
 

import com.nttdocomo.ui.*;
import com.nttdocomo.util.*;
//import java.util.Random;
//import java.lang.Math;

public class drinker extends IApplication {
	public static MainCanvas gc;

	static Thread runner;

	public void start(){
		gc=new MainCanvas();
		Display.setCurrent(gc);
		runner = new Thread(gc);
		runner.start();
	}


	class MainCanvas extends Canvas implements Runnable {


						//0=通路 1=壁 2=チェックポイント
						//3=白雲（未実装）4=自機の初期位置

						//           5        10        15 
	public byte [][][] field =

							//０面
					      {{{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,4,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,2,0,0,0,0,0,0,0,0,0,0},//5
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,2,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,2,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//10
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,2,0,0,0,0,0,0,0,0,2,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}},//15

							//１面
						   {{0,0,0,1,1,1,1,1,1,1,1,0,0,0,0},
							{0,4,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,2,0,0},//5
							{1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,1,1,1,0,0,0,0,0,1,1,1,1},
							{0,0,0,0,0,1,0,0,2,0,0,1,0,0,0},//10
							{1,0,0,0,0,1,0,0,0,0,0,1,0,2,0},
							{1,0,0,0,0,1,1,1,1,1,1,1,0,0,0},
							{0,0,0,2,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}},//15


							//２面
						   {{0,0,0,1,0,0,0,0,1,0,0,1,0,0,0},
							{0,4,0,1,0,0,0,0,1,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
							{0,0,0,0,0,0,1,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,1,0,0,0,0,1,0,0,0},//5
							{0,0,0,1,1,1,1,1,1,1,1,1,0,0,0},
							{0,0,0,1,0,0,0,1,0,0,0,1,0,0,0},
							{1,1,1,1,0,0,0,0,0,2,0,1,2,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
							{0,2,0,0,0,0,0,1,0,0,0,1,1,0,0},//10
							{0,0,0,1,1,1,1,1,1,1,1,1,0,0,0},
							{0,0,0,1,0,0,0,1,0,0,0,0,0,0,0},
							{0,0,0,1,0,0,0,1,0,0,0,0,2,0,0},
							{1,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
							{1,0,0,0,0,1,0,0,0,0,1,0,0,0,0}},//15

							//３面
						   {{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
							{0,4,0,1,1,1,1,0,0,0,0,0,0,0,0},
							{0,0,0,1,0,0,0,0,0,0,0,1,0,0,0},
							{0,0,0,1,0,0,0,0,1,0,0,1,0,0,0},
							{0,0,0,1,1,0,0,0,1,0,0,1,0,0,0},//5
							{0,2,0,1,0,0,2,0,1,0,0,1,0,2,0},
							{0,0,0,1,0,0,0,0,1,0,0,1,0,0,0},
							{0,0,0,1,0,0,1,1,1,0,0,1,1,1,1},
							{0,0,2,1,0,0,1,0,0,0,0,1,0,0,0},
							{0,0,0,1,0,0,1,0,0,0,1,1,0,2,0},//10
							{0,2,0,1,0,0,1,0,0,0,0,1,0,0,0},
							{0,0,0,1,0,0,1,1,1,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,1,1,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,1,0,0,0,0,0,0}},//15

							//４面
						   {{0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
							{0,4,0,0,0,0,0,0,0,2,0,1,0,0,0},
							{0,0,0,0,0,0,1,1,0,0,0,1,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,1,0,2,0,0,0,0},//5
							{0,0,1,1,1,0,0,2,1,0,0,0,0,0,0},
							{0,0,0,0,0,0,1,0,0,0,0,0,1,0,0},
							{0,0,2,0,0,0,1,0,0,1,0,0,1,0,0},
							{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
							{0,0,0,0,1,1,1,0,0,1,0,0,0,0,0},//10
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,1,0,0,2,0,0,0,0,0,2,0,1,0,0},
							{0,1,0,0,0,0,1,1,0,0,0,0,0,1,1},
							{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}},//15


							//５面
		    			   {{0,0,0,1,1,1,1,1,1,1,1,1,1,1,1},
							{0,4,0,1,1,1,0,0,0,0,1,1,1,1,1},
							{0,0,0,1,1,0,0,0,0,0,1,0,0,0,1},
							{0,0,0,1,1,0,0,1,0,0,0,0,0,0,0},
							{0,0,1,1,0,0,0,1,1,0,0,0,1,0,0},//5
							{0,0,1,1,0,2,1,1,1,1,1,1,1,0,0},
							{0,0,0,1,1,0,0,1,1,1,1,1,0,2,0},
							{0,0,0,0,0,0,0,1,1,1,0,0,0,0,1},
							{1,1,0,0,0,0,1,1,1,0,0,0,0,1,1},
							{1,1,1,1,1,1,1,0,1,0,0,1,1,1,1},//10
							{0,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
							{0,0,2,0,0,0,0,0,0,0,1,1,1,1,1},
							{1,0,0,0,0,0,1,1,1,1,1,1,0,0,0},
							{1,1,0,0,0,0,0,0,0,0,0,0,0,2,0},
							{1,1,1,1,0,0,0,0,0,0,0,0,0,0,0}},//15


							//６面
						   {{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
							{0,4,0,1,0,0,2,0,0,2,0,0,0,0,0},
							{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,1,1,1,1,0,0,1,0,0,1,1,1},
							{0,0,0,1,0,0,0,0,0,1,0,0,0,0,0},//5
							{1,0,2,1,0,0,0,0,0,1,0,0,0,0,0},
							{0,0,0,1,0,0,1,1,1,1,1,1,0,2,0},
							{0,0,0,1,0,0,0,0,0,1,0,0,0,0,0},
							{0,0,1,1,0,0,0,0,0,1,0,0,0,1,1},
							{0,0,0,1,1,1,1,0,0,1,0,0,0,0,0},//10
							{0,0,0,1,0,0,0,0,0,1,0,0,0,0,0},
							{0,0,0,1,0,0,0,0,0,1,1,1,1,0,0},
							{0,0,0,0,0,0,1,1,1,1,0,0,0,0,0},
							{0,0,2,0,0,0,0,0,0,1,0,0,0,2,0},
							{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0}},//15

							//７面
						   {{0,0,1,0,0,0,1,0,0,0,0,0,0,0,0},
							{0,4,1,0,0,0,1,0,0,0,0,0,0,2,0},
							{0,0,1,1,0,2,1,0,0,0,0,1,1,0,0},
							{0,0,1,0,0,0,1,0,0,0,2,0,1,0,0},
							{0,0,1,0,0,1,1,0,0,0,0,0,1,0,0},//5
							{0,0,1,2,0,0,1,0,0,1,1,1,1,0,0},
							{0,0,1,1,0,0,1,0,0,1,0,0,0,0,0},
							{0,0,1,0,0,0,0,0,0,1,0,0,0,0,0},
							{0,0,1,0,0,0,0,0,1,1,0,0,0,1,1},
							{0,0,1,1,1,1,1,1,1,1,1,1,0,0,0},//10
							{0,0,1,0,0,0,0,0,1,0,0,0,0,0,0},
							{0,0,1,0,0,2,0,0,1,0,2,0,0,2,0},
							{0,0,1,0,0,1,0,0,1,0,0,1,0,0,0},
							{0,0,0,0,0,1,0,0,0,0,2,0,0,2,0},
							{0,0,0,0,0,1,0,0,0,0,0,0,0,0,0}},//15




							//８面
						   {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,4,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,1,1,1,0,0,1,1,1,0,0,0,0},//5
							{0,0,0,1,1,1,0,0,1,1,1,0,0,0,0},
							{0,0,0,1,1,1,0,0,1,1,1,0,0,0,0},
							{0,0,0,1,1,1,0,0,2,1,1,0,0,0,0},
							{0,0,0,1,1,1,0,0,1,1,1,0,0,0,0},
							{0,0,0,1,1,1,0,0,1,1,1,0,0,0,0},//10
							{0,0,0,1,1,1,0,0,1,1,1,0,0,0,0},
							{0,0,0,1,1,1,0,0,1,1,1,0,0,0,0},
							{0,0,0,1,1,1,0,0,1,1,1,0,0,0,0},
							{0,0,0,1,1,1,0,0,1,1,1,0,0,0,0},
							{0,0,0,1,1,1,0,0,1,1,1,0,0,0,0}}};//15




	public byte [][] work_field= new byte[15][15];

	Graphics gr=getGraphics();
	public int state;// 0=初回のみマップなど描画処理

	public int pause_flag=0;//いきなりゲームが開始されると戸惑うので
							//ソフトキーが押されるまで待つためのフラグ

	public int y_pos;//自機のY座標
	public int delta_y;//自機のY方向加速度（＋３～－３まで）

	public int x_pos;//自機のX座標
	public int delta_x;//自機のY方向加速度（＋３～－３まで）

	public int old_x_pos;
	public int old_y_pos;

	public int stage;//現在のステージ

	public int total_check_point;//そのステージの総チェックポイント数

	public int get_check_point;//現在獲得しているチェックポイント数

	public int key_flag;//キー入力反応感度調整のフラグ

	public Timer timer;

	//public Random random =new Random(System.currentTimeMillis());

	public int time_score;

	public MainCanvas(){//コンストラクタ
	}//ここまでコンストラクタ

	public void run(){
			stage=0;//開始ステージを０面に設定
			time_score=0;
			initialize();
			draw_map();

		//ゲームのメインループ
		while(true){
			
			try{
				Thread.sleep(100);
			}catch(Exception e){
				
			}
			
			time_score++;
			//キー入力の間隔があまり速すぎると加速がつき過ぎて難しいので調節する
			if( key_flag == 0){
				key_check();
			}else {
				key_flag=0;
			}
			
			y_pos+=delta_y;//移動処理
			x_pos+=delta_x;
			if( (y_pos<=0) || (y_pos>=120) || (x_pos<=0) || (x_pos>=120) ){
				//ゲームオーバー
				gr.setColor(Graphics.getColorOfName(Graphics.RED));
				gr.drawLine(x_pos-4,y_pos,x_pos+4,y_pos);
				gr.drawLine(x_pos,y_pos-4,x_pos,y_pos+4);
				gr.drawLine(x_pos+4,y_pos+4,x_pos-4,y_pos-4);
				gr.drawLine(x_pos+4,y_pos-4,x_pos-4,y_pos+4);
				try{
					Thread.sleep(2000);
				}catch(Exception e){
					
				}

				initialize();
				draw_map();

			}else {
				my_draw();
				
			}
			crash();

		}//メインループ

		
	}

	public void initialize(){
		int x,y;
		get_check_point=0;
		total_check_point=0;
		for(y=0;y<15;y++){
			for(x=0;x<15;x++){

				//field[][][]からwork_field[][]へマップデータをコピー
				work_field[y][x]=field[stage][y][x];

				//チェックポイント数をカウントする
				if(work_field[y][x]==2){
					total_check_point++;
				}

				//自機の初期位置をマップデータから設定する
				if(work_field[y][x]==4){
						y_pos=y*8+4;
						old_y_pos=y_pos;
						x_pos=x*8+4;
						old_x_pos=x_pos;
				}

			}
		}
		
		delta_y=0;//加速度をゼロに設定
		delta_x=0;
		key_flag=0;

		
	}


	public void key_check(){
		//キー入力受付

		int work=getKeypadState();

		if(0!=(work & (1<< Display.KEY_0))){
				delta_y--;
				if(delta_y<-2) delta_y=-2;
		}else {
				delta_y++;
				if(delta_y>3) delta_y=3;
		}

		if(0!=(work & (1<< Display.KEY_RIGHT))){
			delta_x++;
			if(delta_x>+3) delta_x=3;
			key_flag=1;
		}
		if(0!=(work & (1<< Display.KEY_LEFT))){
			delta_x--;
			if(delta_x<-3)delta_x=-3;
			key_flag=1;
		}
				
	}

	public void crash(){//衝突判定
		int work_x,work_y;

		work_x=x_pos>>3;//８で割る
		work_y=y_pos>>3;//８で割る
			
			
		if(work_field[work_y][work_x]==2){//チェックポイントをゲットしたか？

				//チェックポイント消去
				gr.setColor(Graphics.getColorOfName(Graphics.BLUE));
				gr.fillRect(work_x*8,work_y*8,8,8);
				gr.setColor(Graphics.getColorOfName(Graphics.WHITE));

				get_check_point++;
				work_field[work_y][work_x]=0;
				if(get_check_point== total_check_point){//総てのチェックポイントを回収した？
								try{
									Thread.sleep(2000);
								}catch(Exception e){
								
								}

					stage++;
					if(stage==9){
						//全ステージクリア！
						
						gr.setColor(Graphics.getColorOfName(Graphics.RED));
						gr.drawLine(x_pos-4,y_pos,x_pos+4,y_pos);
						gr.drawLine(x_pos,y_pos-4,x_pos,y_pos+4);
						gr.drawLine(x_pos+4,y_pos+4,x_pos-4,y_pos-4);
						gr.drawLine(x_pos+4,y_pos-4,x_pos-4,y_pos+4);

						gr.drawString("HERO ?",8,20);
						gr.drawString("time:"+time_score/10,8,90);

						try{
							Thread.sleep(10000);
							}catch(Exception e){
					
						}
						IApplication.getCurrentApp().terminate();

					}else {//次のステージへ
						
						initialize();
						draw_map();
					}
				}


		}else if( work_field[work_y][work_x]==1 ){//壁（障害物）に激突したか？
			//ゲームオーバー

			gr.setColor(Graphics.getColorOfName(Graphics.RED));
			gr.drawLine(x_pos-4,y_pos,x_pos+4,y_pos);
			gr.drawLine(x_pos,y_pos-4,x_pos,y_pos+4);
			gr.drawLine(x_pos+4,y_pos+4,x_pos-4,y_pos-4);
			gr.drawLine(x_pos+4,y_pos-4,x_pos-4,y_pos+4);
			try{
				Thread.sleep(2000);
			}catch(Exception e){
				
			}

			initialize();
			draw_map();
		}
	}

	public void paint(Graphics g){
		//実装は空っぽ
	}

	public void draw_map(){
		int x,y;
			//初回のみの背景等の描画処理
				
				//120*120の外側にも黒い壁を描画する
				gr.setColor(Graphics.getColorOfName(Graphics.BLACK));
				gr.fillRect(0,0,getWidth(),getHeight());

				//背景の青空を描画
				//gr.setColor(Graphics.getColorOfName(Graphics.BLUE));
				//gr.fillRect(0,0,120,120);

				for(y=0;y<15;y++){
					for(x=0;x<15;x++){


						if(work_field[y][x]==0 || work_field[y][x]==4){
							//青空（通路部分）及び初期位置を青で描画
							gr.setColor(Graphics.getColorOfName(Graphics.BLUE));
							gr.fillRect(x*8,y*8,8,8);
							
						}else if(work_field[y][x]==1){
							//障害物（壁）を描画
							gr.setColor(Graphics.getColorOfName(Graphics.BLACK));
							gr.fillRect(x*8,y*8,8,8);
						}else if(work_field[y][x]==2){
							//チェックポイントを描画
							gr.setColor(Graphics.getColorOfName(Graphics.YELLOW));
							gr.fillRect(x*8,y*8,8,8);
						}

					}
					//徐々にフェードインするための０．１秒の間合い
					try{
							Thread.sleep(100);
					}catch(Exception e){ }
					
				}

				//自機の初期位置のマークを描画
				gr.setColor(Graphics.getColorOfName(Graphics.WHITE));
				gr.drawLine(x_pos-2,y_pos,x_pos+2,y_pos);
				gr.drawLine(x_pos,y_pos-2,x_pos,y_pos+2);

	}//end of draw_map()

	public void my_draw(){
			gr.setColor(Graphics.getColorOfName(Graphics.WHITE));
			gr.drawLine(old_x_pos,old_y_pos,x_pos,y_pos);
			old_x_pos=x_pos;
			old_y_pos=y_pos;
		
	}//my_paint()

	}

}

