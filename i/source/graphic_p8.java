import com.nttdocomo.device.*;
import com.nttdocomo.ui.*;
import com.nttdocomo.util.*;
import com.nttdocomo.system.*;
import java.util.Random;
import java.lang.Math;

//形のアプリ８・縞模様の作成

public class graphic_p8 extends IApplication {
	MainCanvas mc;



	public void start(){
		mc=new MainCanvas();
		Display.setCurrent(mc);
		mc.setSoftLabel(Frame.SOFT_KEY_1,"保存");
		mc.setSoftLabel(Frame.SOFT_KEY_2,"次へ");

	}
}

class MainCanvas extends Canvas {


	public void MainCanvas(){
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

	public void setting(){
		Dialog dlg = new Dialog(Dialog.DIALOG_INFO,"");
		dlg.setText("未実装");
		dlg.show();

	}


	public void paint(Graphics g){
		int i;

		int y;

		int width = getWidth();
		int height = getHeight();

		Random random =new Random(System.currentTimeMillis());

		int  color_tbl[]=new int[6];


		color_tbl[0]=Graphics.getColorOfRGB(255,255,255);

		color_tbl[1]=Graphics.getColorOfRGB(128,255,128);
		color_tbl[2]=Graphics.getColorOfRGB(128+64,255,128+64);
		color_tbl[3]=Graphics.getColorOfRGB(128+64+32, 255,128+64+32);
		color_tbl[4]=Graphics.getColorOfRGB(128+64+32+16,255 ,128+64+32+16);

		//ふちどりの色を指定
		color_tbl[5]=Graphics.getColorOfRGB(0,0,0);


		g.lock();

		g.setColor(Graphics.getColorOfRGB(128,255,128));
		g.fillRect(0,0,getWidth(),getHeight());


		for(i=0;i<height;i=i+16){

			if(i>getHeight()){
				i=getHeight();
			}
			
			g.setColor(Graphics.getColorOfRGB( Math.abs(random.nextInt()%256),
											Math.abs(random.nextInt()%256) ,
											Math.abs(random.nextInt()%256)));
			
			//g.setColor(color_tbl[Math.abs(random.nextInt()%5)]);
			g.fillRect(0,i,width,16);

			//ふちどり
			g.setColor(color_tbl[5]);
			g.fillRect(0,i,width,2);

		}//end of for()


		g.unlock(true);

	}

	public void processEvent(int type,int param){

		if((type==Display.KEY_PRESSED_EVENT) && (param==Display.KEY_SOFT1)){
			//生成した画像を保存する
			//
			save();
		}
		if((type==Display.KEY_PRESSED_EVENT) && (param==Display.KEY_SOFT2)){
			//設定
			//
			repaint();

		}


	}


}