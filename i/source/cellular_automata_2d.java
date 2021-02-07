import com.nttdocomo.ui.*;
import java.util.*;
import com.nttdocomo.system.*;

public class cellular_automata_2d extends IApplication {
	MainCanvas gc;

	public void start(){
		gc=new MainCanvas();
		Display.setCurrent(gc);
		gc.setSoftLabel(Frame.SOFT_KEY_1,"�ۑ�");
		gc.setSoftLabel(Frame.SOFT_KEY_2,"�I��");

		gc.init();
	}
}

class MainCanvas extends Canvas {
	boolean dots[][]= new boolean[getHeight()/2*2+2][getWidth()/2*2+2];
	boolean dots_work[][]= new boolean[getHeight()/2*2+2][getWidth()/2*2+2];
	public Random random= new Random(System.currentTimeMillis());

	int	width=getWidth()/2*2;
	int	height=getHeight()/2*2;

	//int times;//��



	public void MainCanvas(){

	}

	public void init(){
	int x,y;
	int size;

		//size=40;

		if(width>height){
			width=height;
		}else {
			height=width;
		}


		//�����Ɏl�����u��
		dots[height/2 ][width/2]=true;
		dots[height/2+1 ][width/2]=true;
		dots[height/2 ][width/2+1]=true;
		dots[height/2 ][width/2]=true;
		dots[height/2+1 ][width/2+1]=true;

		

		/*
		for(y=240/2-size;y<240/2+size;y++){
			for(x=240/2-size;x<240/2+size;x++){
				dots[y][x]=true;
			}
		}
		//*/

		repaint();

	}

	public void step(){
		int x,y,k;
			//�����ɂ���Ċe�Z���̏�Ԃ���ĂɍX�V����

		for(y=0+1;y<height+1;y++){
			for(x=0+1;x<width+1;x++){
			judge(y,x);
			}
		}

		for(y=0+1;y<height+1;y++){
			for(x=0+1;x<width+1;x++){
					if(dots_work[y][x]==true){
						dots[y][x]=true;
					}else {
					dots[y][x]=false;
					}
				
			}
		}


	}




	public void judge(int i,int j){
		int k;
		//���͂̂W�܂��Ƃ��̃Z�����g�𒲂ׂĂ��鐔�ȏ�אڂ���ΐ�����
		//����ȊO�͎�
		k=0;

		//	dots[y][x]

		if(dots[i][j])
		{
			k++;
		}
		if(dots[i-1][j-1]==true)//����
			k++;
		
		if(dots[i-1][j]==true)//��
			k++;
		
		if(dots[i-1][j+1]==true)//�E��
			k++;
		
		if(dots[i][j-1]==true)//��
			k++;
		
		if(dots[i][j+1]==true)//�E
			k++;
		
		if(dots[i+1][j-1]==true)//����
			k++;
		
		if(dots[i+1][j]==true)//��
			k++;
		
		if(dots[i+1][j+1]==true)//�E��
			k++;
		
		//if((k==1) ||  (k==3)|| (k==2) || (k==2)  || (k==2)){
		//if((k==1) || (k==2)   ){
		if((k==1) || (k==2) || (k==3)  ){
		//if((k==1) || (k==2) || (k==3)  ){
		//if((k==1) || (k==2) || (k==3) || (k==4) ){
		//if((k==1) || (k==4)   ){
			dots_work[i][j]=true;
		}else {
			dots_work[i][j]=false;
		}




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



	public void processEvent(int type,int param){

		if((type==Display.KEY_PRESSED_EVENT) && (param==Display.KEY_SOFT1)){
			//���������摜��ۑ�����
			//
			save();

		}else if( (type == Display.KEY_PRESSED_EVENT) && (param==Display.KEY_SOFT2)){
				IApplication.getCurrentApp().terminate();

		}else if( type == Display.KEY_PRESSED_EVENT ){
			step();
			repaint();
		}


	}



	public void paint(Graphics g){
	int i,j;
		g.lock();
		g.setColor(Graphics.getColorOfName(Graphics.BLUE));
		g.fillRect(0,0,getWidth(),getHeight());

		g.setColor(Graphics.getColorOfName(Graphics.WHITE));
		for(i=0;i<=height;i++){
			for(j=0;j<=width;j++){

				if(dots[i+1][j+1]==true){//���̃Z��
					g.drawRect(j,i,0,0);
				}
			}
		}

		g.setColor(Graphics.getColorOfName(Graphics.BLACK));
		//g.drawString("press any key.",10,20);
		//g.drawString("times:"+times,10,40);


		g.unlock(true);
	}
}

