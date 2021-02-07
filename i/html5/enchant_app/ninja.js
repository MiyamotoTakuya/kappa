
enchant();

var CHARA_SIZE= 24;

var enemy_count=0;
var enemy_probability=0.15;

var pre_atari=null;
var pre_atari=null;
var pre_yarare=null;

window.onload = function(){

	var game = new Game(360,240);
	
		game.preload("ninja_24_f1.gif",
				"fire_ball.gif",
				"cave_bg.gif",
				"zombie.gif",
				"bat.gif",
				"atari.mp3",
				"atari2.mp3",
				"yarare.mp3");

	pre_atari=Sound.load('atari.mp3');
	pre_atari2=Sound.load('atari2.mp3');
	pre_yarare=Sound.load('yarare.mp3');

	game.onload = function(){

		var scene = game.rootScene;
		scene.backgroundColor="white";

		var bg=new Sprite(360,240);
		var image= new Surface(360,240);
		var maptip=game.assets["cave_bg.gif"];

		//wall
		for(var i=0;i<360;i+=CHARA_SIZE){
			for(var j=0;j<240;j+=CHARA_SIZE){
				image.draw(maptip,0,0,24,24,i,j,24,24);
			}
		}
		
		//ground
		for(var i=0;i<360;i+=CHARA_SIZE){
			for(var j=0+24;j<240-CHARA_SIZE;j+=CHARA_SIZE){
				image.draw(maptip,24,0,24,24,i,j,24,24);
			}
		}

		bg.image=image;
		scene.addChild(bg);

		
		var score_label= new ScoreLabel(0,0);
		score_label.easing=0;
		score_label.score=0;
		scene.addChild(score_label);
		
/*		var info_label=new Label("info");
		info_label.x=16;
		info_label.y=200;
		scene.addChild(info_label);
*/		
		var pad=new Pad();
		pad.moveTo(0,240-100);
		scene.addChild(pad);
		
		var ninja = new Sprite(24,24);
		ninja.flag=0;
		ninja.tick=0;
		ninja.image=game.assets["ninja_24_f1.gif"];
		ninja.moveTo(120-12,120);

		ninja.onenterframe=function(){
			var input = game.input;
			
			if(game.frame%10 == 0){
				this.frame=1-this.frame;
			}
			
			if(this.flag==1){
				this.tick++;
				this.rotation=this.tick*-6;
				if(this.tick>=45){
					//game over
					game.end(score_label.score,
					"score:"+score_label.score);
				}
				return;
			}
			
			if(input.left ){
				this.x -=3;
				if(this.x<24)this.x=24;
			}
			if(input.right){
				this.x +=3;
				if(this.x>360-24-24)this.x=360-24-24;
			
			}
			if(input.up ){
				this.y -=3;
				if(this.y<24)this.y=24;
			}
			if(input.down){
				this.y +=3;
				if(this.y>240-24-24)this.y=240-24-24;
			}
		};


		var kaen = new Sprite(24,24);
		var theta = 0;
		kaen.image=game.assets["fire_ball.gif"];
		kaen.moveTo(ninja.x,ninja.y+36);


		kaen.onenterframe=function(){
			if(game.frame%10 == 0){
				this.frame=1-this.frame;
			}
			
			theta=(theta+3)% 360;
			kaen.moveTo( ninja.x+Math.sin(theta*Math.PI/180)*24,
						ninja.y+Math.cos(theta*Math.PI/180)*24);
		};

		var Mozomozo= Class.create(Sprite,{
			initialize: function(){
				Sprite.call(this,24,24);
				this.moveTo(this.x=360,24+Math.random()*(240-48-24));
				this.image=game.assets["zombie.gif"];
			},

			onenterframe: function(){
				if(Math.random()> 0.35){
					this.x+=-2.0;
				}

				if(this.x<0){
					this.parentNode.removeChild(this);
				}

				if( this.intersect(kaen) ){
					var atari= pre_atari2.clone();
					atari.play();

					var label=point_up("100",this.x,this.y,"white");
					score_label.score+=100;
					scene.addChild(label);
					this.parentNode.removeChild(this);
				}

				if( this.within(ninja,20) ){
						//dead end
						ninja.flag=1;
						pre_yarare.play();
						
				}
			}
		
		});

		var Bat= Class.create(Sprite,{
			initialize: function(){
				Sprite.call(this,24,24);
				this.moveTo(Math.random()*360,-24);
				this.image=game.assets["bat.gif"];
				this.distance=Math.sqrt( (this.x - ninja.x)*(this.x - ninja.x)
									+(this.y - ninja.y)*(this.y - ninja.y));
				this.speed=3.5;
				this.vx=(ninja.x - this.x)/this.distance*this.speed;
				this.vy=(ninja.y - this.y)/this.distance*this.speed;
			},

			onenterframe: function(){
				if(game.frame%10 == 0){
					this.frame=1-this.frame;
				}
				

				this.x+=this.vx;
				this.y+=this.vy;

				if(this.y>240){
					this.parentNode.removeChild(this);
				}

				if( this.intersect(kaen) ){
					var atari= pre_atari.clone();
					atari.play();

					var label=point_up("300",this.x,this.y,"white");
					score_label.score+=300;
					scene.addChild(label);
					this.parentNode.removeChild(this);
				}

				if( this.within(ninja,20) ){
						//dead end
						ninja.flag=1;
						pre_yarare.play();
				}
			}
		
		});

		var point_up=function(text,x,y,color){
			var count=0;
			var label =new Label(text);
			label.font="12px monospace";
			label.moveTo(x,y);
			label.color=color;
			
			label.onenterframe=function(){
				count++;
				if(count>20){
					this.parentNode.removeChild(this);
				}
			
			}
			
			return label;
		
		}


		scene.onenterframe=function(){
			if( (game.frame%15==0) && (Math.random()< enemy_probability)){
				var mozomozo=new Mozomozo();
				scene.addChild(mozomozo);
				var mozomozo=new Mozomozo();
				scene.addChild(mozomozo);
				enemy_count++;
				enemy_count++;
				if( (enemy_count%6)==0){
					enemy_probability+=0.04;
					if(enemy_probability>0.80){
						enemy_probability=0.80;
					}
				}
			}
			
			//info_label.text="c:"+enemy_count+"p:"+enemy_probability;
			
			if(game.frame%6==0 && Math.random()> 0.1){
				var bat=new Bat();
				scene.addChild(bat);
			}

		};

		scene.addChild(ninja);
		scene.addChild(kaen);

	};

	game.start();
	
};
