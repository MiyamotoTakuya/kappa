
//���������Q�[���@TYPE- A
//JET HERO(�Œ��ʁ@�T�C�h�r���[����)
 

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


						//0=�ʘH 1=�� 2=�`�F�b�N�|�C���g
						//3=���_�i�������j4=���@�̏����ʒu

						//           5        10        15 
	public byte [][][] field =

							//�O��
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

							//�P��
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


							//�Q��
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

							//�R��
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

							//�S��
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


							//�T��
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


							//�U��
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

							//�V��
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




							//�W��
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
	public int state;// 0=����̂݃}�b�v�ȂǕ`�揈��

	public int pause_flag=0;//�����Ȃ�Q�[�����J�n�����ƌ˘f���̂�
							//�\�t�g�L�[���������܂ő҂��߂̃t���O

	public int y_pos;//���@��Y���W
	public int delta_y;//���@��Y���������x�i�{�R�`�|�R�܂Łj

	public int x_pos;//���@��X���W
	public int delta_x;//���@��Y���������x�i�{�R�`�|�R�܂Łj

	public int old_x_pos;
	public int old_y_pos;

	public int stage;//���݂̃X�e�[�W

	public int total_check_point;//���̃X�e�[�W�̑��`�F�b�N�|�C���g��

	public int get_check_point;//���݊l�����Ă���`�F�b�N�|�C���g��

	public int key_flag;//�L�[���͔������x�����̃t���O

	public Timer timer;

	//public Random random =new Random(System.currentTimeMillis());

	public int time_score;

	public MainCanvas(){//�R���X�g���N�^
	}//�����܂ŃR���X�g���N�^

	public void run(){
			stage=0;//�J�n�X�e�[�W���O�ʂɐݒ�
			time_score=0;
			initialize();
			draw_map();

		//�Q�[���̃��C�����[�v
		while(true){
			
			try{
				Thread.sleep(100);
			}catch(Exception e){
				
			}
			
			time_score++;
			//�L�[���͂̊Ԋu�����܂葬������Ɖ��������߂��ē���̂Œ��߂���
			if( key_flag == 0){
				key_check();
			}else {
				key_flag=0;
			}
			
			y_pos+=delta_y;//�ړ�����
			x_pos+=delta_x;
			if( (y_pos<=0) || (y_pos>=120) || (x_pos<=0) || (x_pos>=120) ){
				//�Q�[���I�[�o�[
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

		}//���C�����[�v

		
	}

	public void initialize(){
		int x,y;
		get_check_point=0;
		total_check_point=0;
		for(y=0;y<15;y++){
			for(x=0;x<15;x++){

				//field[][][]����work_field[][]�փ}�b�v�f�[�^���R�s�[
				work_field[y][x]=field[stage][y][x];

				//�`�F�b�N�|�C���g�����J�E���g����
				if(work_field[y][x]==2){
					total_check_point++;
				}

				//���@�̏����ʒu���}�b�v�f�[�^����ݒ肷��
				if(work_field[y][x]==4){
						y_pos=y*8+4;
						old_y_pos=y_pos;
						x_pos=x*8+4;
						old_x_pos=x_pos;
				}

			}
		}
		
		delta_y=0;//�����x���[���ɐݒ�
		delta_x=0;
		key_flag=0;

		
	}


	public void key_check(){
		//�L�[���͎�t

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

	public void crash(){//�Փ˔���
		int work_x,work_y;

		work_x=x_pos>>3;//�W�Ŋ���
		work_y=y_pos>>3;//�W�Ŋ���
			
			
		if(work_field[work_y][work_x]==2){//�`�F�b�N�|�C���g���Q�b�g�������H

				//�`�F�b�N�|�C���g����
				gr.setColor(Graphics.getColorOfName(Graphics.BLUE));
				gr.fillRect(work_x*8,work_y*8,8,8);
				gr.setColor(Graphics.getColorOfName(Graphics.WHITE));

				get_check_point++;
				work_field[work_y][work_x]=0;
				if(get_check_point== total_check_point){//���Ẵ`�F�b�N�|�C���g����������H
								try{
									Thread.sleep(2000);
								}catch(Exception e){
								
								}

					stage++;
					if(stage==9){
						//�S�X�e�[�W�N���A�I
						
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

					}else {//���̃X�e�[�W��
						
						initialize();
						draw_map();
					}
				}


		}else if( work_field[work_y][work_x]==1 ){//�ǁi��Q���j�Ɍ��˂������H
			//�Q�[���I�[�o�[

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
		//�����͋����
	}

	public void draw_map(){
		int x,y;
			//����݂̂̔w�i���̕`�揈��
				
				//120*120�̊O���ɂ������ǂ�`�悷��
				gr.setColor(Graphics.getColorOfName(Graphics.BLACK));
				gr.fillRect(0,0,getWidth(),getHeight());

				//�w�i�̐��`��
				//gr.setColor(Graphics.getColorOfName(Graphics.BLUE));
				//gr.fillRect(0,0,120,120);

				for(y=0;y<15;y++){
					for(x=0;x<15;x++){


						if(work_field[y][x]==0 || work_field[y][x]==4){
							//��i�ʘH�����j�y�я����ʒu��ŕ`��
							gr.setColor(Graphics.getColorOfName(Graphics.BLUE));
							gr.fillRect(x*8,y*8,8,8);
							
						}else if(work_field[y][x]==1){
							//��Q���i�ǁj��`��
							gr.setColor(Graphics.getColorOfName(Graphics.BLACK));
							gr.fillRect(x*8,y*8,8,8);
						}else if(work_field[y][x]==2){
							//�`�F�b�N�|�C���g��`��
							gr.setColor(Graphics.getColorOfName(Graphics.YELLOW));
							gr.fillRect(x*8,y*8,8,8);
						}

					}
					//���X�Ƀt�F�[�h�C�����邽�߂̂O�D�P�b�̊ԍ���
					try{
							Thread.sleep(100);
					}catch(Exception e){ }
					
				}

				//���@�̏����ʒu�̃}�[�N��`��
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

