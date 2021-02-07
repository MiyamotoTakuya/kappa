//�O�F�藝�i�R���H���e���[ Sun show cool terry.�j

import com.nttdocomo.ui.*;
import com.nttdocomo.util.*;
import java.util.Random;
import java.lang.Math;

public class colored_map505 extends IApplication {
	MainCanvas gc;

	public void start(){
		gc=new MainCanvas();
		Display.setCurrent(gc);
	}
}

class MainCanvas extends Canvas {

	public Random random =new Random(System.currentTimeMillis());

	public int field[][]=new int[10][10];

	public int label_field[][]=new int[10][10];

	int  color_tbl[]=new int[5];

	int cursor_x;
	int cursor_y;

	int ink_color;//�C���N�̐F�i�ԁ��΁����ԁj

	int flag;//�O���Q�[�����A�P������
			//���������Ƃ��A�\�t�g�L�[�P���������܂Ŋ����摜��\�����邽�߂̏�ԕϐ�

	public MainCanvas(){//�R���X�g���N�^

		color_tbl[0]=Graphics.getColorOfRGB(255,255,255);

		color_tbl[1]=Graphics.getColorOfRGB(255,128,128);
		color_tbl[2]=Graphics.getColorOfRGB(128,255,128);
		color_tbl[3]=Graphics.getColorOfRGB(128,128,255);

		setSoftLabel(Frame.SOFT_KEY_1,"�V�K");

		init();
		repaint();

	}//�����܂ŃR���X�g���N�^

	public int getrandom(int max ){
		return Math.abs(random.nextInt()%max);
	}

	public void init(){
		make_a();
		make_label_field();
		clear_field();
		cursor_x=4;
		cursor_y=4;
		ink_color=1;
		flag=0;
		setSoftLabel(Frame.SOFT_KEY_2,"��");
	}


	public void clear_field(){
		//�t�B�[���h���N���A����
		int x,y;
		for(y=0;y<10;y++)
			for(x=0;x<10;x++)
				field[x][y]=0;
	}


	public void make_a(){
		int x,y;
		//�t�B�[���h�Ƀ����_���ɐF���Z�b�g����
		for(y=0;y<10;y++){
			for(x=0;x<10;x++){
				field[x][y]=getrandom(3)+1;
			}
		}
	}//end of make_a()



	public void make_b(){//
		int i,j;
		int r;
		//�����_���ɐF���Z�b�g����
		//make_a()���������F���A�����ĕ��т₷���͂�
		r=getrandom(3)+1;
		for(i=0;i<10;i++){
			for(j=0;j<10;j++){
				if(getrandom(10)<1){
					field[i][j]=r;
				}
				else{
					r=getrandom(3)+1;
				}
				field[i][j]=r;
			}
		}

	}

	public void make_label_field(){
/*
	field[][]����ɂ���label_field[][]�Ƀ��x����ݒ肷��


	field[][]				label_field[][]
	12312					1 2  3  4  5
	13321					1 3  3  6  7
	33321				��	3 3  3  8  7
	32212					3 9  9 10 11
	32332					3 9 12 12 13
	
*/
		int x,y,i,c;

		for(x=0;x<10;x++)
			for(y=0;y<10;y++)
				label_field[x][y]=0;

		i=1;
		for(y=0;y<10;y++){
			for(x=0;x<10;x++){
				/*�������܂��}�[�L���O����ĂȂ����
					���͂̓����F�̕����Ƀ}�[�L���O����*/
				if(label_field[x][y]==0){
					c=field[x][y];
					marking(x,y,i,c);
					i++;
				}
			}

		}
		i--;//���[�v�𔲂������Ɂ{�P����Ă�̂łP�߂�

	}//�����܂� make_label_field()

	public void marking(int x,int y, int i,int c){
		//�㉺���E�ɓ����F���A�����������ɓ������x��������
		label_field[x][y]=i;
		if( (y<9) && (label_field[x][y+1]!=i) && (field[x][y+1]==c) ) marking(x,y+1,i,c);
		if( (y>0) && (label_field[x][y-1]!=i) && (field[x][y-1]==c) ) marking(x,y-1,i,c);
		if( (x<9) && (label_field[x+1][y]!=i) && (field[x+1][y]==c) ) marking(x+1,y,i,c);
		if( (x>0) && (label_field[x-1][y]!=i) && (field[x-1][y]==c) ) marking(x-1,y,i,c);
	}

	public void paint_color(int x,int y,int color){
		//�������x���𓯂��F�œh��
		field[x][y]=color;
		if( (y<9) && (field[x][y+1]!= color) && label_field[x][y+1]== label_field[x][y] )paint_color(x,y+1,color);
		if( (y>0) && (field[x][y-1]!= color) &&label_field[x][y-1]== label_field[x][y]) paint_color(x,y-1,color);
		if( (x<9) && (field[x+1][y]!= color) &&label_field[x+1][y]== label_field[x][y]) paint_color(x+1,y,color);
		if( (x>0) && (field[x-1][y]!= color) &&label_field[x-1][y]== label_field[x][y]) paint_color(x-1,y,color);
		
	}


	public void judge(){
		int x,y;

		//�E�ׂ������F�Ń��x�����Ⴄ�Ȃ烋�[���𖞂����Ȃ�
		for(y=0;y<10;y++){
			for(x=0;x<9;x++){
				if( (field[x][y]== field[x+1][y] )
						&& label_field[x][y] != label_field[x+1][y]){
					return;
				}
			}
		}

		//���ׂ������F�Ń��x�����Ⴄ�Ȃ烋�[���𖞂����Ȃ�
		for(y=0;y<9;y++){
			for(x=0;x<10;x++){
				if( (field[x][y]==field[x][y+1]) 
					&& (label_field[x][y]!= label_field[x][y+1]) ){
					return;
				}
			}
		}

		//�h��c��������΃��[���𖞂����Ȃ�
		for(y=0;y<10;y++){
			for(x=0;x<10;x++){
				if(field[x][y]==0){
					return;
				}
			}
		}

	//�����܂ł����烋�[���𖞂����Ă���

	flag=1;
	repaint();


	}//�����܂�judge()

	public void paint(Graphics g){
		int x,y;
		Font font;

		g.lock();
			g.setColor(Graphics.getColorOfName(Graphics.WHITE));
			g.fillRect(0,0,getWidth(),getHeight());

			for(y=0;y<10;y++){
				for(x=0;x<10;x++){
					g.setColor(color_tbl[ field[x][y] ] );
					g.fillRect(x*24,y*24,24,24);
					if( y!=9 && label_field[x][y] !=label_field[x][y+1]){//
						g.setColor(Graphics.getColorOfName(Graphics.BLACK));
						g.drawLine(x*24-1,y*24+24-1,x*24+24-1,y*24+24-1);
					}

					if( x!= 9 && label_field[x][y]!=label_field[x+1][y]){//
						g.setColor(Graphics.getColorOfName(Graphics.BLACK));
						g.drawLine(x*24+24-1,y*24-1,x*24+24-1,y*24+24-1);
					}
				}
			}

			//�O�g��\��
			g.setColor(Graphics.getColorOfName(Graphics.BLACK));
			//g.fillRect(0,0,2,120);
			//g.fillRect(0,0,120,2);

			g.drawRect(0,0,240-1,240-1);

			//�J�[�\����\������
			if(flag==0){
				g.setColor(Graphics.getColorOfName(Graphics.BLACK));
				g.fillRect(cursor_x*24+7,cursor_y*24+7,8,8);
			}

			if(flag==1){//������ԂȂ�Ώj�����b�Z�[�W��\��
			g.setColor(Graphics.getColorOfName(Graphics.WHITE));
			font=Font.getFont(Font.SIZE_MEDIUM);
			g.setFont(font);
			g.drawString("����!!",120-24,120-24);

			}

		g.unlock(true);

	}//end of paint()

	public void processEvent(int type,int param){

		if((type==Display.KEY_PRESSED_EVENT) && (param==Display.KEY_SOFT1)){
			//�V�K�Q�[��
			init();
		}


		if(type == Display.KEY_PRESSED_EVENT && param==Display.KEY_SOFT2){
			//�\�t�g�L�[�Q�ŃC���N�̐F��ς���
			if(ink_color==1){
				setSoftLabel(Frame.SOFT_KEY_2,"��");
				ink_color=2;
			}else if(ink_color==2){
				setSoftLabel(Frame.SOFT_KEY_2,"��");
				ink_color=3;

			}else if(ink_color==3){
				setSoftLabel(Frame.SOFT_KEY_2,"��");
				ink_color=1;
			}
		}

		//�Q�[�����s���̃L�[������󂯕t����
		if( (flag==0) && (type==Display.KEY_PRESSED_EVENT) ){
			if(param==Display.KEY_UP){
				if(cursor_y==0)cursor_y=9;
					else cursor_y--;
			}else if(param==Display.KEY_DOWN){
				cursor_y=(cursor_y+1)%10;
			}else if(param==Display.KEY_LEFT){
				if(cursor_x==0)cursor_x=9;
					else cursor_x--;
			}else if(param==Display.KEY_RIGHT){
				cursor_x=(cursor_x+1)%10;
			}else if(param==Display.KEY_SELECT){

				if( field[cursor_x][cursor_y]!=ink_color){
					paint_color(cursor_x,cursor_y,ink_color);
					//�h�蕪�����菈��
					judge();
				}
			}
		}
		repaint();
	}//end of processEvent()

}
