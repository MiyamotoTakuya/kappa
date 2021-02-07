
import com.nttdocomo.ui.*;

public class trans extends IApplication{
	MainPanel mainPanel;

	public void start(){
		mainPanel= new MainPanel();
		Display.setCurrent(mainPanel);
	}
}//end of trans class

class MainPanel extends Panel implements ComponentListener{

	Label	label_angou;//暗号文
	TextBox	t_box;//文章入力エリア
	Label	label_kaisetu;//解説文
	Button	button;

	public String str_a[]=//暗号文
		{
		"とらい          ",
		"めぐる          ",
		"りろん          ",
		"きいと          ",
		"みちこく        ",
		"りんすから      ",
		"うまるずぼ      ",
		"あるとから      ",
		"かまたぐる      ",
		"ぼくかれん      ",
		"はちはらぶ      ",
		"ぱんとくい      ",
		"こんどいじくれ  ",
		"まんがやせ      ",
		"べんけいほつた  ",
		"うぶれいこ      ",
		"おれかめん      ",
		"たらいおそうじ  ",
		"きへんじんじん  ",
		"よばいめんかい  ",
		"おりがなく      ",
		"しやぶがつくり  ",
		"いつぱつふくか  ",
		"へんじいうおよめ",
		"よこくしじき    ",
		"あかじだいす    ",
		"くうきぶろ      ",
		"おしりがひ      ",
		"そうこうばん    ",
		"ふらすこさんしん"
		};

	public String str_b[]=//正解文
		{
		"らいと",
		"ぐるめ",
		"ろんり",
		"といき",
		"くちこみ",
		"すりらんか",
		"まるぼうず",
		"あらかると",
		"かたぐるま",
		"かくれんぼ",
		"はらはちぶ",
		"いんぱくと",
		"こくじんどれい",
		"やせがまん",
		"べつけんたいほ",
		"ぶれいこう",
		"かめれおん",
		"らじおたいそう",
		"きじんへんじん",
		"めいよばんかい",
		"おくりがな",
		"ぶつりがくしや",
		"いつぱくふつか",
		"おめいへんじよう",
		"きこくしじよ",
		"あすかじだい",
		"うきぶくろ",
		"しおひがり",
		"ばんそうこう",
		"さんふらんしすこ"
		};

	public String str_c[]=new String[30];//解説文

	public int count_q;

	MainPanel(){


/*		str_c[0]="トライ";//解説文
		str_c[1]="巡る";
		str_c[2]="理論";
		str_c[3]="生糸";
		str_c[4]="未遅刻";
		str_c[5]="すらん借り？";
		str_c[6]="埋まるズボ";
		str_c[7]="アルトから";
		str_c[8]="鎌手繰る";
		str_c[9]="僕可憐";
		str_c[10]="蜂はラブ";
		str_c[11]="パン得意";
		str_c[12]="今度いじくれ";
		str_c[13]="漫画痩せ";
		str_c[14]="弁慶彫った";
		str_c[15]="ウブ麗子";
		str_c[16]="俺仮面";
		str_c[17]="たらいお掃除";
		str_c[18]="木偏ジンジン";
		str_c[19]="夜這い面会";
		str_c[20]="ORIGA泣く";
		str_c[21]="しゃぶガックリ";
		str_c[22]="一発吹くか";
		str_c[23]="返事言うお嫁";
		str_c[24]="予告指示器";
		str_c[25]="赤字ダイス";
		str_c[26]="空気風呂";
		str_c[27]="お尻が火";
		str_c[28]="装甲版";
		str_c[29]="フラスコ三振";
*/
		count_q=0;

		label_angou=new Label(str_a[0]);
		add(label_angou);

		//label_kaisetu=new Label(str_c[0]);
		//add(label_kaisetu);

		t_box=new TextBox("",16,1,TextBox.DISPLAY_ANY);
		add(t_box);

		button=new Button("入力");
		add(button);
		setComponentListener(this);
	}

	public void componentAction(Component c,int type,int param){
		if(c==button ){//ボタンが押された
			if( t_box.getText().equals(str_b[count_q]) ){
				Dialog d=new Dialog(Dialog.DIALOG_INFO,"");
				d.setText("ブラボーじゃ。！");//タンゴ（ダンス）なので(^^;
				d.show();

				count_q++;
				if(count_q== 30){//全問クリア
					//Dialog d=new Dialog(Dialog.DIALOG_INFO,"");
					Dialog d2=new Dialog(Dialog.DIALOG_INFO,"");

					d.setText("みごと全問クリアじゃ。");
					d.show();
					d2.setText("おめでとうございました。");
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
				d.setText("違うぞい。");
				d.show();
			}
		}
	}

}
