// ストループテストアプリ
// （セルフサービス方式）
import com.nttdocomo.ui.*;
import com.nttdocomo.util.*;
import java.util.Random;
import java.lang.Math;

public class cognition1 extends IApplication {
	public static MainCanvas gc;

	public static Thread runner;

	public void start(){
		gc=new MainCanvas();
		Display.setCurrent(gc);
		runner = new Thread(gc);
		runner.start();
	}

}
	class MainCanvas extends Canvas implements Runnable{
						//0=地 1=図 
						//           5        10        15 
	public byte [][][] kanji_fig =
							//赤
						//           5         10        15        20
					      {{{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0},//5
							{0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0},//10
							{0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
							{0,0,0,0,0,0,0,1,1,0,0,1,1,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,1,1,0,0,1,1,0,0,1,1,0,0,0},
							{0,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,1,0,0},
							{0,0,0,1,1,0,0,1,1,0,0,1,1,0,0,0,1,1,1,0},//15
							{0,0,0,1,1,0,0,1,1,0,0,1,1,0,0,0,0,1,1,0},
							{0,0,1,1,0,0,0,1,1,0,0,1,1,0,0,0,0,0,0,0},
							{0,0,1,1,0,0,1,1,0,0,0,1,1,0,0,0,0,0,0,0},
							{0,0,0,0,0,1,1,0,0,0,1,1,1,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}},//20

							//緑
						//           5        10        15 
						   {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,1,1,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0},
							{0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0},//5
							{0,1,1,0,0,0,1,0,0,0,0,0,0,0,0,1,1,1,0,0},
							{0,0,1,1,0,1,1,0,0,0,0,1,1,1,1,1,1,1,0,0},
							{0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1,0,0},
							{0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0},
							{0,0,1,1,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,0},//10
							{0,1,1,0,0,0,0,1,1,0,0,0,0,1,1,1,0,0,0,0},
							{0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,0,1,1,0},
							{0,0,0,0,1,1,0,1,1,0,0,1,1,1,1,1,1,1,0,0},
							{0,1,1,0,1,1,0,0,0,0,0,0,0,1,1,1,0,0,0,0},
							{0,1,1,0,1,1,0,1,1,0,0,0,0,1,1,1,0,0,0,0},//15
							{0,1,1,0,1,1,0,1,1,0,0,1,1,1,1,1,1,1,0,0},
							{0,1,1,0,1,1,0,1,1,0,0,1,1,1,1,1,1,1,0,0},
							{0,1,1,0,1,1,0,0,0,0,1,1,0,1,1,1,0,1,1,0},
							{0,0,0,0,1,1,0,0,0,0,1,1,0,1,1,1,0,1,1,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0}},//20


							//青
						//           5        10        15 
						   {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0},//5
							{0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0},
							{0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0},//10
							{0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0},
							{0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0},
							{0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0},
							{0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0},
							{0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0},//15
							{0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0},
							{0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0},
							{0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0},
							{0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0},
							{0,0,0,0,1,1,0,0,0,0,0,0,1,1,1,0,0,0,0,0}},//20

							//黄色
						//           5        10        15 
						   {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,1,1,0,0,1,1,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,1,1,0,0,1,1,0,0,0,0,0,0,0},
							{0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
							{0,0,0,0,0,0,0,1,1,0,0,1,1,0,0,0,0,0,0,0},//5
							{0,0,0,0,0,0,0,1,1,0,0,1,1,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,1,1,0,0,1,1,0,0,0,0,0,0,0},
							{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
							{0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},//10
							{0,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,0},
							{0,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,0},
							{0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
							{0,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,0},
							{0,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,0},//15
							{0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,1,1,0,0,1,1,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,0,0},
							{0,0,0,0,1,1,1,0,0,0,0,0,0,1,1,1,0,0,0,0}},//20

							//白
						//           5        10        15 
						   {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0},//5
							{0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
							{0,0,0,1,1,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0},
							{0,0,0,1,1,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0},
							{0,0,0,1,1,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0},
							{0,0,0,1,1,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0},//10
							{0,0,0,1,1,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0},
							{0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
							{0,0,0,1,1,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0},
							{0,0,0,1,1,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0},
							{0,0,0,1,1,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0},//15
							{0,0,0,1,1,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0},
							{0,0,0,1,1,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0},
							{0,0,0,1,1,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0},
							{0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}}};//20

	Graphics gr=getGraphics();

	int key_state;

	int old_key_state;

	int [] color_tbl={0,0,0,0,0};

	int [] kanji_no={0,0,0,0,0,0,0,0,0};//文字の形

	int [] color_no={0,0,0,0,0,0,0,0,0};//文字の色

	ShortTimer timer;

	Random random =new Random(System.currentTimeMillis());

	int time_score;//全問題を解くのにかかった時間

	int sheet_no;//今何枚目のシート（用紙）か

	int w=getWidth();

	Font font;

	public MainCanvas(){//コンストラクタ

	}//ここまでコンストラクタ

	public void run(){
		//初期設定
		setSoftLabel(Frame.SOFT_KEY_1,"終了");
		setSoftLabel(Frame.SOFT_KEY_2,"次へ");

		color_tbl[0]=Graphics.getColorOfName(Graphics.RED);
		color_tbl[1]=Graphics.getColorOfName(Graphics.LIME);
		color_tbl[2]=Graphics.getColorOfName(Graphics.BLUE);
		color_tbl[3]=Graphics.getColorOfName(Graphics.YELLOW);
		color_tbl[4]=Graphics.getColorOfName(Graphics.WHITE);

		initialize();
		paint_fig(gr);

		time_score=0;
		sheet_no=1;
		key_state=0;

		while(true){//メインループ

			try{
			Thread.sleep(100);
			}catch(Exception e){}

			//キー入力処理
			key_state=getKeypadState();
			//ソフトキー１終了処理
			if( (old_key_state!= key_state) 
			&& (0!=( getKeypadState() & (1<< Display.KEY_SOFT1)))){
				IApplication.getCurrentApp().terminate();
			}

			//ソフトキー２（次へ）処理
			if(  (old_key_state!=key_state)
			 && (0!=( getKeypadState() & (1<< Display.KEY_SOFT2)))){
				if(sheet_no<8){
					sheet_no++;
					initialize();
					paint_fig(gr);
				}else {//
					gr.lock();

					gr.setColor(Graphics.getColorOfName(Graphics.WHITE));
					gr.fillRect(0,0,getWidth(),getHeight());
					gr.setColor(Graphics.getColorOfName(Graphics.BLACK));
					font=Font.getFont(Font.SIZE_MEDIUM);
					gr.setFont(font);

					gr.drawString("結果 :"+time_score+"POINT",w/8,w/2);

					gr.unlock(true);
					try{
						Thread.sleep(1000);
					}catch(Exception e){}


					while(true){
						try{
							Thread.sleep(100);
						}catch(Exception e){}

						if(0!=( getKeypadState() & (1<< Display.KEY_SOFT2))){
							break;
						}
						if(0!=( getKeypadState() & (1<< Display.KEY_SOFT1))){
							IApplication.getCurrentApp().terminate();
						}
						
					}


					time_score=0;
					sheet_no=1;
					initialize();
					paint_fig(gr);
				}
			}

			old_key_state=key_state;

			time_score++;//０．１秒ごとにスコア加算

		}

	}

	public void initialize(){

		int i;

		for(i=0;i<9;i++){
			kanji_no[i]=getrandom(0,4);//０から３をランダムに設定
			color_no[i]=getrandom(0,4);//０から３をランダムに設定
		}

	}// end of initialize

	public int getrandom(int min ,int max ){
		return Math.abs(random.nextInt()%(max-min))+min;
	}

	public void paint(Graphics g){

	}


	public void paint_fig(Graphics gr){
		int y,x,i;


		gr.lock();

		gr.setColor(Graphics.getColorOfName(Graphics.WHITE));
		gr.fillRect(0,0,getWidth(),getHeight());

		for(i=0;i<9;i++){//文字図形を９個描画する

			gr.setColor(color_tbl[color_no[i]]);//文字図形の色を設定
			int work=kanji_no[i];
			for(y=0;y<20;y++){//文字図形を描画
				for(x=0;x<20;x++){
					if( kanji_fig[work][y][x]==1 )  {
								//gr.fillRect(x*2+i%3*40,y*2+i/3*40,2,2);
								gr.fillRect( x*(w/60)+(i%3)*w/3
									,y * (w/60)+(i/3)*w/3,w/60,w/60);
					}
				}
			}
		}
		//gr.setColor(Graphics.getColorOfName(Graphics.BLACK));
		//gr.drawString(""+sheet_no,10,10);

		gr.unlock(true);

	}//end of my_paint


	}//class MainCanvas()
