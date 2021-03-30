
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

//中央に四個だけ生のセルを置く
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
		//条件によって各セルの状態を一斉に更新する

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
	//周囲の８ますとそのセル自身を調べて条件に合えば生きる
	//それ以外は死
	k=0;

	//	field[y][x]

	if(field[y][x])//自分自身
		k++;

	if(field[y-1][x-1]==true)//左上
		k++;
	
	if(field[y-1][x]==true)//上
		k++;
	
	if(field[y-1][x+1]==true)//右上
		k++;
	
	if(field[y][x-1]==true)//左
		k++;
		
	if(field[y][x+1]==true)//右
		k++;
		
	if(field[y+1][x-1]==true)//左下
		k++;
		
	if(field[y+1][x]==true)//下
		k++;
		
	if(field[y+1][x+1]==true)//右下
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

			if(field[x+1][y+1]==true){//生のセル
			ctx.fillRect(x,y,1,1);
			}
		}
	}


}


