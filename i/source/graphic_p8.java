import com.nttdocomo.device.*;
import com.nttdocomo.ui.*;
import com.nttdocomo.util.*;
import com.nttdocomo.system.*;
import java.util.Random;
import java.lang.Math;

//�`�̃A�v���W�E�Ȗ͗l�̍쐬

public class graphic_p8 extends IApplication {
	MainCanvas mc;



	public void start(){
		mc=new MainCanvas();
		Display.setCurrent(mc);
		mc.setSoftLabel(Frame.SOFT_KEY_1,"�ۑ�");
		mc.setSoftLabel(Frame.SOFT_KEY_2,"����");

	}
}

class MainCanvas extends Canvas {


	public void MainCanvas(){
	}

	public void save(){
	//���������摜�̕ۑ�����
	//i���[�hjava�v���O���~���O�@FOMA�Ή��Ł@�A�X�L�[�@���Q�l�ɂ���

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
		dlg.setText("������");
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

		//�ӂ��ǂ�̐F���w��
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

			//�ӂ��ǂ�
			g.setColor(color_tbl[5]);
			g.fillRect(0,i,width,2);

		}//end of for()


		g.unlock(true);

	}

	public void processEvent(int type,int param){

		if((type==Display.KEY_PRESSED_EVENT) && (param==Display.KEY_SOFT1)){
			//���������摜��ۑ�����
			//
			save();
		}
		if((type==Display.KEY_PRESSED_EVENT) && (param==Display.KEY_SOFT2)){
			//�ݒ�
			//
			repaint();

		}


	}


}