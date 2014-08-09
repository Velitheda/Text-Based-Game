// Written by Jasmine Vickery

/* 
 * Villains for the player to kill!
 */


public class Monster extends Thing{

	private int health;
	private int maxHealth;
	private int attack;
	
	public Monster(int health, int attack) {
		this.health = health;
		maxHealth = health;
		this.attack = attack;
	}
	
	public Monster(int health, int maxHealth, int attack) {
		this.health = health;
		this.maxHealth = maxHealth;
		this.attack = attack;
	}

	public void getHurt(int damage){
		health = health - damage;
	}
	
	public void heal(int moreHealth){
		health = health + moreHealth;
		if(health > maxHealth)
			health = maxHealth;
	}
	
	//Later a more fancy way of determining the damage could be implemented
	public int getDamageDealt(){
		return attack;
	}
	
	public int getHealth(){
		return health;
	}
	
	public void setHealth(int health){
		this.health = health;
	}
}
