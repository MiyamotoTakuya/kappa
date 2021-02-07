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

	int num_p=8;//�m�[�h�̌�

	int px[]={10,100,30,40,40,50,30,70};//�m�[�h�̂����W
	int py[]={15,90,20,50,30,80,90,80};//�m�[�h�̂����W

	int pc1[][]={{0,0,0,0,0,0,0,0,0,0},//�e�m�[�h�ƘA�����Ă���΂P
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0}};

	int pdeltax[]={-1,-1,1,-1,-1,-1,1,-1};//�m�[�h�̐i�s�����iX�������j
	int pdeltay[]={1,-1,1,-1,-1,1,-1,1};//�m�[�h�̐i�s�����iY�������j
	
	int width=getWidth();
	int height=getHeight();

	public Random random =new Random(System.currentTimeMillis());

	public ShortTimer timer;

	public MainCanvas(){//�R���X�g���N�^
		//int i,j;
		//�����l�������_���ɐݒ肷��

		setSoftLabel(Frame.SOFT_KEY_1,"����");
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


		for(i=0;i<num_p;i++){//�e�m�[�h�ɂ���
			if((px[i]<5)||(px[i]>(width-5))){//��ʂ̂͂��ŕ����]��
				pdeltax[i]=pdeltax[i]*-1;
			}
			if((py[i]<5)||(py[i]>(height-5))){//��ʂ̂͂��ŕ����]��
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


		//�e�m�[�h����אڂ���m�[�h�ւ̕Ӂi�G�b�W�j��`��
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
