
var WIDTH=300;
var HEIGHT=300;
var canvas;
var ctx;
var field;
var work_field;

window.onload = function(){
var x,y;
	
field=new Array(HEIGHT+2);
work_field=new Array(HEIGHT+2);
for(y = 0; y < HEIGHT+2; y++){
    field[y] = new Array(WIDTH+2);
    work_field[y]=new Array(WIDTH+2);
}

for(y = 0; y < HEIGHT+2; y++){
    for(x = 0; x < WIDTH+2; x++){
        field[y][x] = false;
        work_field[y][x]= false;
    }
}

//�����Ɏl�������̃Z����u��
field[HEIGHT/2 ][WIDTH/2]=true;
field[HEIGHT/2+1 ][WIDTH/2]=true;
field[HEIGHT/2 ][WIDTH/2+1]=true;
field[HEIGHT/2 ][WIDTH/2]=true;
field[HEIGHT/2+1 ][WIDTH/2+1]=true;

canvas=document.createElement("canvas");
canvas.width=WIDTH;
canvas.height=HEIGHT;

//
canvas.onmousedown = function(e) {
	step();
	draw();

 }

document.body.appendChild(canvas);

draw();

}

function step(){
	var x,y,k;
		//�����ɂ���Ċe�Z���̏�Ԃ���ĂɍX�V����

	for(y=0+1;y<HEIGHT+1;y++){
		for(x=0+1;x<WIDTH+1;x++){
		judge(y,x);
		}
	}

	for(y=0+1;y<HEIGHT+1;y++){
		for(x=0+1;x<WIDTH+1;x++){
				if(work_field[y][x]==true){
					field[y][x]=true;
				}else {
				field[y][x]=false;
				}
			
		}
	}


}

function judge( y , x){
	var k;
	//���͂̂W�܂��Ƃ��̃Z�����g�𒲂ׂď����ɍ����ΐ�����
	//����ȊO�͎�
	k=0;

	//	field[y][x]

	if(field[y][x])//�������g
		k++;

	if(field[y-1][x-1]==true)//����
		k++;
	
	if(field[y-1][x]==true)//��
		k++;
	
	if(field[y-1][x+1]==true)//�E��
		k++;
	
	if(field[y][x-1]==true)//��
		k++;
		
	if(field[y][x+1]==true)//�E
		k++;
		
	if(field[y+1][x-1]==true)//����
		k++;
		
	if(field[y+1][x]==true)//��
		k++;
		
	if(field[y+1][x+1]==true)//�E��
		k++;
	
	if((k==1) || (k==2) || (k==3)  ){
		work_field[y][x]=true;
	}else {
		work_field[y][x]=false;
	}




}


function draw(){

var x,y;

ctx=canvas.getContext('2d');
ctx.fillStyle = "rgb(0,0,255)";
ctx.fillRect(0,0,WIDTH,HEIGHT);

ctx.fillStyle = "rgb(255,255,255)";

	for(y=0;y<=HEIGHT;y++){
		for(x=0;x<=WIDTH;x++){

			if(field[x+1][y+1]==true){//���̃Z��
			ctx.fillRect(x,y,1,1);
			}
		}
	}


}


