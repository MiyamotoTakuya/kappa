
import com.nttdocomo.ui.*;
import java.util.*;
import com.nttdocomo.system.*;


public class graphic_p3 extends IApplication {
	MainCanvas gc;

	public void start(){
		gc=new MainCanvas();
		Display.setCurrent(gc);
		gc.setSoftLabel(Frame.SOFT_KEY_1,"保存");
		gc.setSoftLabel(Frame.SOFT_KEY_2,"終了");

		gc.init();
	}
}

class MainCanvas extends Canvas {
	public boolean dots[][]= new boolean[getHeight()][getWidth()];
	public boolean dots_state[][]= new boolean[getHeight()][getWidth()];
	public Random random= new Random(System.currentTimeMillis());

	public int times;

	public int	rate=(getWidth()*10/12)*(getHeight()*10/12);

	public int cond=5;

	public void MainCanvas(){

	}

	public void init(){
	int x,y,i,j,k;

		times=0;


		//セルを初期化
		for(y=0;y<getHeight();y++){
			for(x=0;x<getWidth();x++){
				dots[y][x]=false;
			}
		}

		for(y=0;y<getHeight();y++){
			for(x=0;x<getWidth();x++){
				dots_state[y][x]=false;
			}
		}

		for(i=0;i<rate;i++){
			j=random.nextInt();
			j=Math.abs(j);
			j=j%getHeight();

			k=random.nextInt();
			k=Math.abs(k);
			k=k%getWidth();
			
			
			dots[j][k]=true;
		}

		repaint();

	}

	public void move(){
		int x,y,k;

			for(y=0;y<getHeight();y++){
				for(x=0;x<getWidth();x++){
					judge(y,x);
				}
			}

			for(y=0;y<getHeight();y++){
				for(x=0;x<getWidth();x++){
					if(dots_state[y][x]==true){
							dots[y][x]=true;
						}else {
							dots[y][x]=false;
						}
				}
			}


	
	}//end of move()

	public void judge(int y,int x){

		int yu,yd;
		int xr,xl;
		int k;

		if(y==0){
				yu=getHeight()-1;
			}else {
				yu=y-1;
			}
		if(y==getHeight()-1){
				yd=0;
			}else {
				yd=y+1;
			}

		if(x==0){
				xl=getWidth()-1;
			}else {
				xl=x-1;
			}

		if(x==getWidth()-1){
				xr=0;
			}else{
				xr=x+1;
			}


		k=0;
		if(dots[y][x]==true)//そのセル
			{k++;}
		if(dots[yu][xl]==true)//左上
			{k++;}
		if(dots[yu][x]==true)//上
			{k++;}
		if(dots[yu][xr]==true)//右上
			{k++;}
		if(dots[y][xl]==true)//左
			{k++;}
		if(dots[y][xr]==true)//右
			{k++;}
		if(dots[yd][xl]==true)//左下
			{k++;}
		if(dots[yd][x]==true)//下
			{k++;}
		if(dots[yd][xr]==true)//右下
			{k++;}

		if(k>=cond){
			dots_state[y][x]=true;
			}else {
			dots_state[y][x]=false;
			}

	}

	public void save(){
	//生成した画像の保存処理
	//iモードjavaプログラミング　FOMA対応版　アスキー　を参考にした

	ImageEncoder ie = ImageEncoder.getEncoder("JPEG");
	ie.setAttribute(ImageEncoder.QUALITY,
			ImageEncoder.ATTR_QUALITY_STANDARD);
	EncodedImage ei = ie.encode(this,0,0,getWidth(),getHeight());
	MediaImage mi = ei.getImage();
	try{
		mi.use();
		ImageStore.addEntry(mi);
		}catch(Exception e){
		Dialog dlg = new Dialog(Dialog.DIALOG_ERROR,"error");
		dlg.setText(e.toString());
		dlg.show();
		}


	}


	public void processEvent(int type,int param){
		if((type==Display.KEY_PRESSED_EVENT) && (param==Display.KEY_SOFT1)){
			//生成した画像を保存する
			//
			save();

		}else if( (type == Display.KEY_PRESSED_EVENT) && (param==Display.KEY_SOFT2)){
				IApplication.getCurrentApp().terminate();

		}else if( type == Display.KEY_PRESSED_EVENT ){
			move();
			times++;
			repaint();
		}


	}



	public void paint(Graphics g){
	int x,y;
		g.lock();
		g.setColor(Graphics.getColorOfName(Graphics.BLUE));
		g.fillRect(0,0,getWidth(),getHeight());

		g.setColor(Graphics.getColorOfName(Graphics.WHITE));
		for(y=0;y<getHeight();y++){
			for(x=0;x<getWidth();x++){
				if(dots[y][x]==true){
					g.drawRect(x,y,0,0);
				}
			}
		}

		/*g.setColor(Graphics.getColorOfName(Graphics.BLACK));
		g.drawString("Press any key.",10,20);
		g.drawString("step:"+times,10,40);
		g.drawString("rate:"+rate,10,60);
		g.drawString("形のアプリ３",10,80);
		*/

		g.unlock(true);
	}//end of paint()
}
