
var WIDTH=400;
var HEIGHT=400;
var GRID_SIZE=44;//  400/9=44.444...

var UP=1;
var RIGHT=2;
var DOWN=3;
var LEFT=4;

var canvas;
var ctx;

//��H��
var board=		  [[0,0,0,0,0,0,0,0,0],
			       [0,0,0,0,0,0,0,0,0],
			       [0,0,0,1,1,1,0,0,0],
			       [0,0,0,0,1,1,0,0,0],
			       [0,0,1,0,0,1,0,0,0],
			       [0,0,1,1,0,0,0,0,0],
			       [0,0,1,1,1,0,0,0,0],
			       [0,0,1,1,1,1,0,0,0],
			       [0,0,0,0,0,0,0,0,0]];
var str=[
"�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@",
"�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@",
"�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@",
"�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@",
"�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@",
"�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@",
"�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@",
"�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@",
"�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@",
"�@�@�@�@�@�@�������@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@",
"�@�@�@�@�@�����������@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@",
"�@�@�@�@�@�����@�����@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@",
"�@�@�@�@�@�����������@�@�@�@�@�@�@�@�@���@�@�@�@�@�@",
"�@�@�@�@�@�@�������@�@�@�@�@�@�@�@�@���@���@�@�@�@�@",
"�@�@�@�@�@�@�@���@�@�@�@�@�@�@�@�@�@�@���@�@�@�@�@�@",
"�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@",
"�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@���@�@�@�@�@�@",
"�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@���@�@�@�@�@�@",
"�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@",
"�@�@�@�@���������@�@�@���������@�@�@�@�@�@�@�@�@�@�@",
"�@�@�@�@���c�c���@�@�@���c�c���@�@�@�@�@���������@�@",
"�^�P�_�@���c�c�����������c�c���@�^�P�_�@���������@�@",
"���c���@���c�c�����������c�c���@�������@���������@�@"];



var str2=[
"�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@",
"�@�@�@�@���������@�@�@�@�@�@�@�@�@�@�@���@���@�@�@�@",
"�@�@�@�@���@�@�@�@�@�@�@�@�@�@�@�@�@�@���@���@�@�@�@",
"�@�@�@�@���@�����@�������@�������@�������@���@�@�@�@",
"�@�@�@�@���@�@���@���@���@���@���@���@���@�@�@�@�@�@",
"�@�@�@�@���������@�������@�������@�������@���@�@�@�@",
"�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@",
"�@�@�@�@�@�@�������@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@",
"�@�@�@�@�@���E�E�E���@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@",
"�@�@�@�@���E�������E���@�@�@�@�@�@�@�@�@�@�@�@�@�@�@",
"�@�@�@���E�����E�����E���@�@�@�@�@�@�@�@�@�@�@�@�@�@",
"�@�@�@���E���E�@�E���E���@�@�@�@�@�@�������@�@�@�@�@",
"�@�@�@���E�����E�����E���@�@�@�@�@�����E�����@�@�@�@",
"�@�@�@�@���E�������E���@�@�@�@�@�@���E�~�E���@�@�@�@",
"�@�@�@�@�@���E�E�E���@�@�@�@�@�@�@�����E�����@�@�@�@",
"�@�@�@�@�@�@�������@�@�@�@�@�@�@�@�@�������@�@�@�@�@",
"�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@",
"�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@",
"�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@",
"�@�@�@�@���������@�@�@���������@�@�@�@�@�@�@�@�@�@�@",
"�@�@�@�@���c�c���@�@�@���c�c���@�@�@�@�@���������@�@",
"�^�P�_�@���c�c�����������c�c���@�^�P�_�@���������@�@",
"���c���@���c�c�����������c�c���@�������@���������@�@"];


var beat=0;
var beat_table=[5,5,4,3,3,4];

var state_flag=0;

var old_x=-1,old_y=-1;//���O
var old_x_2=-1,old_y_2=-1;//���O
var step=1;//����A���A�O��A�l��A�E�E�E
var direction=-1;//�i�s����

window.onload = function(){

	canvas=document.createElement("canvas");
	canvas.width=WIDTH;
	canvas.height=HEIGHT;
	canvas.onmousedown = function(e) {
	mousedown(e);
	}
	document.body.appendChild(canvas);


	draw();

	setInterval(func_beat,100);


}

function func_beat(){
//�΁i�ږژA�̖ځj�̌ۓ�
		beat++;
		beat=beat%beat_table.length;
		draw();
}


function check_rule_1(x,y){
//�c�̓��A���̓��̒��i�`�F�b�N
	if(old_x== x || old_y==y){
		return true;
	}else {
		return false;
	}
}

function check_rule_2(x,y){
//��щz���֎~�`�F�b�N
	var i;

	if(x>old_x){//�E����
		if(old_x+1== x) return true;
		i=old_x+1;
		do{
			if(board[y][i]==1) return false;
			i++;
		}while(i<=x-1);
		return true;
	}

	if(x<old_x){//������
		if(old_x-1== x) return true;
		i=old_x-1;
		do{
			if(board[y][i]==1) return false;
			i--;
		}while(i>=x+1);
		return true;
	}

	if(y<old_y){//�����
		if(old_y-1== y) return true;
		i=old_y-1;
		do{
			if(board[i][x]==1) return false;
			i--;
		}while(i>= y+1);
		return true;
	}

	if(y>old_y){//������
		if(old_y+1== y) return true;
		i=old_y+1;
		do{
			if(board[i][x]==1) return false;
			i++;
		}while(i<= y-1);
		return true;
	}


}

function check_rule_3(x,y){
//�^�[���o�b�N�֎~�`�F�b�N
	if(direction==RIGHT && x<old_x)	return false;

	if(direction==LEFT && x>old_x) return false;

	if(direction==UP && y>old_y) return false;
	
	if(direction==DOWN && y<old_y) return false;
	
	return true;

}

function check_all_remove(){
//�Տォ��S�Ă̐΂���菜������
	var x,y;

	for(y=0;y<=8;y++){
		for(x=0;x<=8;x++){
			if(board[y][x]==1) return false;
		}
	}

	return true;

}


function mousedown(e){

	var x,y;

	x=Math.floor(e.clientX/GRID_SIZE);
	y=Math.floor(e.clientY/GRID_SIZE);

	if(board[y][x]==0) return;

	if(step==1){
			board[y][x]=0;
			old_x=x;old_y=y;
			step++;
	}else if(step>=2){
		if( check_rule_1(x,y) && check_rule_2(x,y) && check_rule_3(x,y)	){
		
		
			board[y][x]=0;
			if(x>old_x) direction=RIGHT;
			if(x<old_x) direction=LEFT;
			if(y>old_y) direction=DOWN;
			if(y<old_y) direction=UP;
			//alert("direction"+direction);
			old_x_2=old_x;old_y_2=old_y;
			old_x=x;old_y=y;

			step++;
		}

	}

	if(check_all_remove()){
		state_flag=1;
		beat=0;
		return;
	}

	draw();
	

}

function draw(){

	var x,y;

	if(state_flag==1){
		draw_ascii_art();
		return;
	}

	ctx=canvas.getContext('2d');
	ctx.fillStyle = "rgb(255,255,255)";
	ctx.fillRect(0,0,WIDTH,HEIGHT);

	ctx.fillStyle = "rgb(0,0,0)";

	for(x=0;x<=8;x++){
		ctx.beginPath();
		ctx.moveTo(x*GRID_SIZE+GRID_SIZE/2+1, GRID_SIZE/2+1);
		ctx.lineTo(x*GRID_SIZE+GRID_SIZE/2+1,8*GRID_SIZE+GRID_SIZE/2+1);
		ctx.closePath();
		ctx.stroke();
	}
	for(y=0;y<=8;y++){
		ctx.beginPath();
		ctx.moveTo(GRID_SIZE/2+1,y*GRID_SIZE+GRID_SIZE/2+1);
		ctx.lineTo(8*GRID_SIZE+GRID_SIZE/2+1,y*GRID_SIZE+GRID_SIZE/2+1);
		ctx.closePath();
		ctx.stroke();
	}


	for(y=0;y<=8;y++){
		for(x=0;x<=8;x++){
			if(board[y][x]==1){
				ctx.beginPath();
				ctx.arc(x*GRID_SIZE+GRID_SIZE/2+1,
					 y*GRID_SIZE+GRID_SIZE/2+1, 
					(GRID_SIZE-beat_table[beat])/2, 0, Math.PI*2, false);
				ctx.fill();
			}
		}
	}

	if(old_x!=-1 && old_y!=-1){
		//���O�̈ʒu��`��
		ctx.fillStyle = "rgb(0,0,0)";
				ctx.beginPath();
				ctx.arc(old_x*GRID_SIZE+GRID_SIZE/2+1,
					 old_y*GRID_SIZE+GRID_SIZE/2+1, 
					(GRID_SIZE-9)/2, 0, Math.PI*2, false);
				ctx.stroke();

	}
	if(old_x_2!=-1 && old_y_2!=-1){
		//���O�̈ʒu��`��
		ctx.fillStyle = "rgb(0,0,0)";
				ctx.beginPath();
				ctx.arc(old_x_2*GRID_SIZE+GRID_SIZE/2+1,
					 old_y_2*GRID_SIZE+GRID_SIZE/2+1, 
					(GRID_SIZE-17)/2, 0, Math.PI*2, false);
				ctx.stroke();

	}

}

function draw_ascii_art(){

	var y;

	ctx=canvas.getContext('2d');
	ctx.fillStyle = "rgb(255,255,255)";
	ctx.fillRect(0,0,WIDTH,HEIGHT);


	ctx.fillStyle = "rgb(0,0,0)";
	ctx.font = "16px monospace";
	for(y=0;y<str.length;y++){
		if(beat>=3){
				ctx.fillText(str[y],0 ,16*y+16);
			}else {
				ctx.fillText(str2[y],0 ,16*y+16);
			
			}
			
	}
	


}
