import com.nttdocomo.ui.*;
import java.lang.Math;
import java.util.Random;


public class graphic_p2 extends IApplication {
	MainCanvas gc;

	public void start(){
		gc=new MainCanvas();
		Display.setCurrent(gc);
	}
}

class MainCanvas extends Canvas {

	public Random rnd =new Random(System.currentTimeMillis()); 

	public int tate_color[]= new int[getWidth()/16];
	public int yoko_color[]= new int[getHeight()/16];

	public int color_tbl[]=new int[5];//白、青、赤、緑、黒

	public int i,j;
	public int w=getWidth();
	public int h=getHeight();

	//int count_tate=getWidth()/16;//縦糸の本数
	//int count_yoko=getHeight()/16;//横糸の本数
	int count_tate=getWidth()/16;
	int count_yoko=getHeight()/16;

	public MainCanvas(){
		setSoftLabel(Frame.SOFT_KEY_1,"次へ");

	}

	public void initialize(){

	}

	public void paint(Graphics g){
		//initialize
		color_tbl[0]=Graphics.getColorOfName(Graphics.WHITE);
		color_tbl[1]=Graphics.getColorOfName(Graphics.BLUE);
		color_tbl[2]=Graphics.getColorOfName(Graphics.RED);
		color_tbl[3]=Graphics.getColorOfName(Graphics.LIME);
		color_tbl[4]=Graphics.getColorOfName(Graphics.BLACK);

		g.lock();

		g.setColor(Graphics.getColorOfName(Graphics.BLACK));//画面をクリア
		g.fillRect(0,0,w,h);

		for(i=0;i<count_tate;i++){//縦糸の色を設定
			tate_color[i]=color_tbl[ Math.abs( rnd.nextInt()%4 ) ];
		}
		for(i=0;i<count_yoko;i++){//横糸の色を設定
			yoko_color[i]=color_tbl[ Math.abs( rnd.nextInt()%4 ) ];
		}



		//たての線
		for(i=0;i<count_tate;i++){
			g.setColor( tate_color[i] );
			g.fillRect(8+i*16,0,8,h);
		}


		//横の線
		for(i=0;i<count_yoko;i++){
			g.setColor( yoko_color[i]);
			g.fillRect(0,8+i*16,w,8);
		}

		for(i=0;i<count_yoko;i++){//横のforループ
			for(j=0;j<count_tate;j++){//縦のforループ
				if(rnd.nextInt()>0){//
					g.setColor(tate_color[j]);
				}
				else{
					g.setColor(yoko_color[i]);
				}
				g.fillRect(8+j*16,8+i*16,8,8);
			}//end of j-for()
		}//end of i-for()

		g.unlock(true);

	}

	public void processEvent(int type,int prm){

		if(type == Display.KEY_PRESSED_EVENT && prm == Display.KEY_SOFT1){
			repaint();
		}

	}

}

