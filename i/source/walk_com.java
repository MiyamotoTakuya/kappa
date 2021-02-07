
import com.nttdocomo.ui.*;
import java.lang.Math;
import java.util.Random;
import com.nttdocomo.io.ConnectionException;
import java.io.*;
import javax.microedition.io.*;

/*�X�N���b�`�p�b�h���S�o�C�g�g�p
	�O	max_combo_count�̏�ʂW�r�b�g
	�P	max_combo_count�̉��ʂW�r�b�g
	�Q	combo_count�̏�ʂW�r�b�g
	�R	combo_count�̉��ʂW�r�b�g
*/


public class walk_com extends IApplication {
	MainCanvas gc;

	public void start(){
		gc=new MainCanvas();
		Display.setCurrent(gc);
	}
}

class MainCanvas extends Canvas {

	public int mode=0;// 0=>�^�C�g�����[�h�A// 2=>�R���{�A�^�b�N�v���C���[�h

	public int flag=0;// 0=>�ʏ���   1=>�����\�����,2=>�s�����\�����

	public int combo_count;//���݂̃R���{�B����

	public int max_combo_count;//�ō��R���{

	public Random rnd =new Random(System.currentTimeMillis()); 

	public MediaImage mi[]=new MediaImage[14];

	public Image img[]=new Image[14];

	public int num[]={1,2,3,4,5,6,7,8,9,2,3,4,5};

	public int kou[]={0,0,0,0,0,0};//�悎��

	//�Q�[����ʂƂ̑Ή�

	/* 4 + 3 - 9 + 7 + 5
		= 8

	 kou[0] +kou[1]+kou[2]+kou[3]+kou[4]
	     = kou[5]
	*/

	public int cur_pos=1;//�J�[�\�����悎���������Ă��邩������

	public MainCanvas(){
		int i;

		setSoftLabel(Frame.SOFT_KEY_1,"�J�n");
		setSoftLabel(Frame.SOFT_KEY_2,"�I��");

		try{
			//�摜�f�[�^�i������L���j�ǂݍ���
			for(i=0;i<=13;i++){
				mi[i]=MediaManager.getImage("resource:///p"+i+"x"+".gif");
				mi[i].use();
				img[i]=mi[i].getImage();
			}

			//�X�N���b�`�p�b�h����ǂݍ���
			DataInputStream in = Connector.openDataInputStream("scratchpad:///0");
			max_combo_count=in.read()*256+in.read();
			combo_count=in.read()*256+in.read();
			in.close();

		}catch(Exception e){

		}

	}

	public void make(){//���쐬
		int i,j,w;

		for(i=0;i<13;i++){//�V���b�t��
			j=Math.abs(rnd.nextInt()%13);
			w=num[j];
			num[j]=num[i];
			num[i]=w;
		}

		kou[0]=num[0];//������ݒ�

		//��P����S�܂ł̍��i���Ӂj��ݒ�i�����̓����_���Ɍ���j
		for(i=1;i<5;i++){
			
			if(rnd.nextInt()>0){
				kou[i]=num[i];
			}else {
				kou[i]=-num[i];
			}
		}
		

		//�E�ӂ̒l�i���ӂ̍��v�l�j��ݒ�
		j=0;
		for(i=0;i<5;i++){
			j=j+kou[i];
		}
		kou[5]=j;

		//��������A���ӂ̍���S�Đ��̐��ɂ��Ƃ�
		for(i=1;i<5;i++){
			kou[i]=Math.abs(kou[i]);
		}

	}

	public int check(){//�����������Ă��邩���肷��
		int i,j;
		for(j=0,i=0;i<5;i++){
			j=j+kou[i];
		}
		if(j==kou[5]){
			return 1;//��������
		}else {
			return 0;//�s��������
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


		if(mode==0){//�^�C�g����ʃ��[�h
			if(type== Display.KEY_PRESSED_EVENT){
				if(param==Display.KEY_SOFT1){
					mode=2;//�R���{�A�^�b�N���[�h
					flag=0;
					setSoftLabel(Frame.SOFT_KEY_1,"����");
					make();
					repaint();
				}
			}

		}else if(type ==Display.KEY_PRESSED_EVENT){//�Q�[�����[�h
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
				kou[cur_pos]=kou[cur_pos]*-1;//�����𔽓]
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

		if(mode == 0){//�^�C�g����ʃ��[�h
			g.setColor(Graphics.getColorOfName(Graphics.GREEN));
			g.fillRect(0,0,getWidth(),getHeight());
			g.setColor(Graphics.getColorOfName(Graphics.WHITE));

			g.drawString("��������",20,40);



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
					g.drawImage(img[10],j*12+4,30);//�h�{�h��\��
				}else {
					g.drawImage(img[11],j*12+4,30);//�h�|�h��\��
				}
				j++;
				g.drawImage(img[Math.abs(kou[i])],j*12+4,30);
				j++;
			}

			j=1;
			g.drawImage(img[12],j*12+4,30+16+2);//�h���h�̕\��

			if(kou[5]<0){
				j++;
				g.drawImage(img[11],j*12+4,30+16+2);//�h�|�h��\��
			}
			if( (kou[5]>=10)||(kou[5]<=-10) ){
				j++;
				g.drawImage(img[Math.abs(kou[5])/10],j*12+4,30+16+2);//�P�O�̈ʕ\��

			}
			j++;
			g.drawImage(img[Math.abs(kou[5])%10],j*12+4,30+16+2);//�P�̈ʕ\��

			if(flag==1){//������
				g.drawString("��������B",5,90);
			}else if(flag==2){//�s������
				//g.setColor(Graphics.getColorOfName(Graphics.WHITE));
				g.drawString("�s���Ȏ�����B",5,90);
				g.setColor(Graphics.getColorOfName(Graphics.RED));
				g.drawString("�s���Ȏ�����B",6,91);

			}
			g.drawString("COMBO : "+combo_count,5,110);

			g.unlock(true);
		}
	}

}

