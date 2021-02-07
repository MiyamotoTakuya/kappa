
import com.nttdocomo.ui.*;

public class trans extends IApplication{
	MainPanel mainPanel;

	public void start(){
		mainPanel= new MainPanel();
		Display.setCurrent(mainPanel);
	}
}//end of trans class

class MainPanel extends Panel implements ComponentListener{

	Label	label_angou;//�Í���
	TextBox	t_box;//���͓��̓G���A
	Label	label_kaisetu;//�����
	Button	button;

	public String str_a[]=//�Í���
		{
		"�Ƃ炢          ",
		"�߂���          ",
		"����          ",
		"������          ",
		"�݂�����        ",
		"��񂷂���      ",
		"���܂邸��      ",
		"����Ƃ���      ",
		"���܂�����      ",
		"�ڂ������      ",
		"�͂��͂��      ",
		"�ς�Ƃ���      ",
		"����ǂ�������  ",
		"�܂񂪂₹      ",
		"�ׂ񂯂��ق�  ",
		"���Ԃꂢ��      ",
		"���ꂩ�߂�      ",
		"���炢��������  ",
		"���ւ񂶂񂶂�  ",
		"��΂��߂񂩂�  ",
		"���肪�Ȃ�      ",
		"����Ԃ�����  ",
		"���ςӂ���  ",
		"�ւ񂶂��������",
		"�悱��������    ",
		"������������    ",
		"�������Ԃ�      ",
		"�����肪��      ",
		"���������΂�    ",
		"�ӂ炷�����񂵂�"
		};

	public String str_b[]=//����
		{
		"�炢��",
		"�����",
		"����",
		"�Ƃ���",
		"��������",
		"������",
		"�܂�ڂ���",
		"���炩���",
		"���������",
		"��������",
		"�͂�͂���",
		"����ς���",
		"��������ǂꂢ",
		"�₹���܂�",
		"�ׂ��񂽂���",
		"�Ԃꂢ����",
		"���߂ꂨ��",
		"�炶����������",
		"������ւ񂶂�",
		"�߂���΂񂩂�",
		"�����肪��",
		"�Ԃ肪������",
		"���ς��ӂ�",
		"���߂��ւ񂶂悤",
		"������������",
		"������������",
		"�����Ԃ���",
		"�����Ђ���",
		"�΂񂻂�����",
		"����ӂ�񂵂���"
		};

	public String str_c[]=new String[30];//�����

	public int count_q;

	MainPanel(){


/*		str_c[0]="�g���C";//�����
		str_c[1]="����";
		str_c[2]="���_";
		str_c[3]="����";
		str_c[4]="���x��";
		str_c[5]="�����؂�H";
		str_c[6]="���܂�Y�{";
		str_c[7]="�A���g����";
		str_c[8]="����J��";
		str_c[9]="�l��";
		str_c[10]="�I�̓��u";
		str_c[11]="�p������";
		str_c[12]="���x��������";
		str_c[13]="���摉��";
		str_c[14]="�ٌc������";
		str_c[15]="�E�u��q";
		str_c[16]="������";
		str_c[17]="���炢���|��";
		str_c[18]="�ؕ΃W���W��";
		str_c[19]="�锇���ʉ�";
		str_c[20]="ORIGA����";
		str_c[21]="����ԃK�b�N��";
		str_c[22]="�ꔭ������";
		str_c[23]="�Ԏ���������";
		str_c[24]="�\���w����";
		str_c[25]="�Ԏ��_�C�X";
		str_c[26]="��C���C";
		str_c[27]="���K����";
		str_c[28]="���b��";
		str_c[29]="�t���X�R�O�U";
*/
		count_q=0;

		label_angou=new Label(str_a[0]);
		add(label_angou);

		//label_kaisetu=new Label(str_c[0]);
		//add(label_kaisetu);

		t_box=new TextBox("",16,1,TextBox.DISPLAY_ANY);
		add(t_box);

		button=new Button("����");
		add(button);
		setComponentListener(this);
	}

	public void componentAction(Component c,int type,int param){
		if(c==button ){//�{�^���������ꂽ
			if( t_box.getText().equals(str_b[count_q]) ){
				Dialog d=new Dialog(Dialog.DIALOG_INFO,"");
				d.setText("�u���{�[����B�I");//�^���S�i�_���X�j�Ȃ̂�(^^;
				d.show();

				count_q++;
				if(count_q== 30){//�S��N���A
					//Dialog d=new Dialog(Dialog.DIALOG_INFO,"");
					Dialog d2=new Dialog(Dialog.DIALOG_INFO,"");

					d.setText("�݂��ƑS��N���A����B");
					d.show();
					d2.setText("���߂łƂ��������܂����B");
					d2.show();
					IApplication.getCurrentApp().terminate();
				}
				label_angou.setText(str_a[count_q]);
				//add(label_angou);
				//label_kaisetu.setText(str_c[count_q]);
				//add(label_kaisetu);
				t_box.setText("");
			}else{
				Dialog d=new Dialog(Dialog.DIALOG_INFO,"");
				d.setText("�Ⴄ�����B");
				d.show();
			}
		}
	}

}
