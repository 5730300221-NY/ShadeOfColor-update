package entity;


import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import render.GameBackground;
import render.Button;
import render.RenderableHolder;

public class GameLogic {
	private static GameLogic instance = new GameLogic();
	protected Sheep sheep;
	protected PlayerStatus playerStatus;
	protected Button button;
	protected Beam beam;
	protected GameBackground bg;
	protected static List<Wolf> wolves;
	// private static final int SPAWN_DELAY = 40;
	private int spawnDelayCounter = (int) Math.ceil((Math.random())) * 90;
	private int roundstate = 1;

	public GameLogic() {
		sheep = new Sheep();
		playerStatus = new PlayerStatus();
		button = new Button();
		beam = new Beam();
		bg = new GameBackground();
		wolves = new CopyOnWriteArrayList<Wolf>();
		// wolf = new Wolf(0,100,1,810,310);
		RenderableHolder.getInstance().add(sheep);
		RenderableHolder.getInstance().add(playerStatus);
		RenderableHolder.getInstance().add(button);
		RenderableHolder.getInstance().add(beam);
		RenderableHolder.getInstance().add(bg);
	}

	public static GameLogic getInstance() {
		return instance;
	}

	public static void setInstance(GameLogic instance) {
		GameLogic.instance = instance;
	}

	public Sheep getSheep() {
		return sheep;
	}

	public void setSheep(Sheep sheep) {
		this.sheep = sheep;
	}

	public PlayerStatus getPlayerStatus() {
		return playerStatus;
	}

	public void setPlayerStatus(PlayerStatus playerStatus) {
		this.playerStatus = playerStatus;
	}

	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
	}

	public Beam getBeam() {
		return beam;
	}

	public void setBeam(Beam beam) {
		this.beam = beam;
	}

	public GameBackground getBg() {
		return bg;
	}

	public void setBg(GameBackground bg) {
		this.bg = bg;
	}

	public static List<Wolf> getWolves() {
		return wolves;
	}

	public static void setWolves(List<Wolf> wolves) {
		GameLogic.wolves = wolves;
	}

	public int getSpawnDelayCounter() {
		return spawnDelayCounter;
	}

	public void setSpawnDelayCounter(int spawnDelayCounter) {
		this.spawnDelayCounter = spawnDelayCounter;
	}

	public int getRoundstate() {
		return roundstate;
	}

	public void setRoundstate(int roundstate) {
		this.roundstate = roundstate;
	}

	public void WolfRoundGen() {

		// for(int i=0;i<10+(2*(roundstate-1));i++){
		// for(int j=0;j<randomGenWolf(roundstate);j++){
		// if(roundstate==1){
		int colorlist[] = { 1, 5, 3 ,2,4,6,7,8,9,10,11,12,13,14,0 };
		// (1230-(((int)Math.ceil((Math.random()*2))+2)*100)),
		// (300+(int)(Math.random()*21)))
		int genborn = (1230 - (((int) Math.ceil((Math.random() * 2)) + 2) * 100));
		if (genborn <= 925)
			genborn = 1230;
		Wolf a = new Wolf(colorlist[(int) (Math.random() * (colorlist.length))], 1000, 4,genborn, (300 + (int) (Math.random() * 21)));
		wolves.add(a);
		RenderableHolder.getInstance().getRenderable().add(a);
		// }

		// else if(roundstate==2){
		// int colorlist[] = {1,2,3,4,5,6};
		// Wolf a = new
		// Wolf(colorlist[(int)(Math.random()*(colorlist.length))],100, 10,
		// 1280, (300+(int)(Math.random()*21)));
		// wolves.add(a);
		// RenderableHolder.getInstance().add(a);
		// }
		// else if(roundstate==3){
		// int colorlist[] = {1,2,3,4,5,6,7,8,9};
		// Wolf a = new
		// Wolf(colorlist[(int)(Math.random()*(colorlist.length))],100, 10,
		// 1280, (300+(int)(Math.random()*21)));
		// wolves.add(a);
		// RenderableHolder.getInstance().add(a);
		// }
		// else if(roundstate==4){
		// int colorlist[] = {0,1,2,3,4,5,6,7,8,9,13,14};
		// Wolf a = new
		// Wolf(colorlist[(int)(Math.random()*(colorlist.length))],100, 10,
		// 1280, (300+(int)(Math.random()*21)));
		// wolves.add(a);
		// RenderableHolder.getInstance().add(a);
		// }
		// else if(roundstate==5){
		// int colorlist[] = {0,7,8,9,10,11,12,13,14};
		// Wolf a = new
		// Wolf(colorlist[(int)(Math.random()*(colorlist.length))],100, 10,
		// 1280, (300+(int)(Math.random()*21)));
		// wolves.add(a);
		// RenderableHolder.getInstance().add(a);
		// }
		// else if(roundstate==6){
		// int colorlist[] = {1,2,3,4,5,6,15,16,17};
		// Wolf a = new
		// Wolf(colorlist[(int)(Math.random()*(colorlist.length))],100, 10,
		// 1280, (300+(int)(Math.random()*21)));
		// wolves.add(a);
		// RenderableHolder.getInstance().add(a);
		// }
		// else if(roundstate==7){
		// int colorlist[] = {1,2,3,4,5,6,18,19,20};
		// Wolf a = new
		// Wolf(colorlist[(int)(Math.random()*(colorlist.length))],100, 10,
		// 1280, (300+(int)(Math.random()*21)));
		// wolves.add(a);
		// RenderableHolder.getInstance().add(a);
		// }
		// else{
		// int colorlist[] =
		// {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		// Wolf a = new
		// Wolf(colorlist[(int)(Math.random()*(colorlist.length))],100, 10,
		// 1280, (300+(int)(Math.random()*21)));
		// wolves.add(a);
		// RenderableHolder.getInstance().add(a);
		// }
		// }

	}

	// }

	// public int randomGenWolf(int round){ //return number of wolf per generate
	// int[] genWolf = {1,2,3,4};
	// int[] wolfweight =
	// {(50-(int)(0.25*round)),(35-(int)(0.1*round)),(10+(int)(0.1*round)),(5+(int)(0.2*round))};
	// int totalweight=0;
	// for(int a : wolfweight){
	// totalweight += a;
	// }
	//
	// ArrayList<Integer>weightwolf = new ArrayList<Integer>();
	// int currentwolf=0;
	// while(currentwolf<genWolf.length){
	// for(int i=0;i<wolfweight[currentwolf];i++){
	// weightwolf.add(weightwolf.size(),genWolf[currentwolf]);
	// }
	// currentwolf++;
	// }
	//
	// Collections.shuffle(weightwolf);
	// int randomnumber = (int)(Math.random()*totalweight);
	// int Wgen = weightwolf.get(randomnumber);
	// System.out.println(Wgen);
	// return Wgen;
	// return 1;
	// }

	public static void KillWolf(int color) {
		for (Wolf a : wolves) {
			if (a.getWolfColor() == color) {
				a.setDestroyed(true);
				Sheep.setScore(Sheep.getScore() + a.getWolfPoint());
			}
		}
	}

	public void logicUpdate() {

		for (Wolf a : wolves) {
			a.update();
			if (a.collide(sheep) && !a.isDestroyed()) {
				System.out.println("Collide");
				wolves.remove(a);
				RenderableHolder.getInstance().getRenderable().remove(a);
			}
			if (a.isDestroyed()) {
				System.out.println(a.getWolfPoint());
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							Thread.sleep(40);
							wolves.remove(a);
							RenderableHolder.getInstance().getRenderable()
									.remove(a);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}).start();
			}
		}

		--spawnDelayCounter;
		if (spawnDelayCounter == 0) {
			WolfRoundGen();
			spawnDelayCounter = ((int) (Math.ceil((Math.random() * 2))) + 2) * 20;
		}

	}

}
