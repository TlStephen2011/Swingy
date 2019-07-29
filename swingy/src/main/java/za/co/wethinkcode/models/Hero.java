package za.co.wethinkcode.models;

import java.util.Random;

import za.co.wethinkcode.utilities.Coordinates;

public class Hero extends Character {
	private int Id;
	private String heroClass;
	private int experience;
	private Weapon weapon;
	private Armor armor;
	private Helm helm;
	
	public Hero(String name, String heroClass) {
		super(name, 0, 20, 10, 100, new Coordinates(0, 0));
		this.heroClass = heroClass;
		this.experience = 0;
	}
	
	public String getHeroClass() {
		return this.heroClass;
	}
	
	public String getHeroName() {
		return this.name;
	}
	
	public int getExperience() {
		return this.experience;
	}
	
	public Hero(	String name,
					String heroClass,
					int level,
					int experience,
					int attackDamage,
					int defensePoints,
					int hitPoints,
					Coordinates position,
					Weapon weapon,
					Armor armor,
					Helm helm) {
		super(name, level, attackDamage, defensePoints, hitPoints, position);
		
		if (weapon != null) {
			this.weapon = weapon;
			this.attackDamage += this.weapon.getDamage();
		}
		
		if (armor != null) {
			this.armor = armor;
			this.defensePoints += this.armor.getDefense();
		}
		
		if (helm != null) {
			this.helm = helm;
			this.hitPoints += this.helm.getHP();
		}
		
		this.experience = experience;
		this.heroClass = heroClass;
	}
	
	public int getId() {
		return this.Id;
	}
	
	public Hero(int Id,
			String name,
			String heroClass,
			int level,
			int experience,
			int attackDamage,
			int defensePoints,
			int hitPoints,
			Coordinates position,
			Weapon weapon,
			Armor armor,
			Helm helm) {
		super(name, level, attackDamage, defensePoints, hitPoints, position);
		
		this.Id = Id;
		
		if (weapon != null) {
			this.weapon = weapon;
			this.attackDamage += this.weapon.getDamage();
		}
		
		if (armor != null) {
			this.armor = armor;
			this.defensePoints += this.armor.getDefense();
		}
		
		if (helm != null) {
			this.helm = helm;
			this.hitPoints += this.helm.getHP();
		}
		
		this.experience = experience;
		this.heroClass = heroClass;
	}
	
	public Hero(	String name,
					String heroClass,
					int level,
					int experience,
					int attackDamage,
					int defensePoints,
					int hitPoints,
					Coordinates position) {
		super(name, level, attackDamage, defensePoints, hitPoints, position);		
		
		this.experience = experience;
		this.heroClass = heroClass;
		
	}
	
	public void equip(Artifact a) {
		if (a instanceof Weapon) {			
			int currentDamageBonus = this.weapon == null ? 0 : this.weapon.getDamage();			
			this.attackDamage = this.attackDamage - currentDamageBonus + ((Weapon)a).getDamage();
			this.weapon = (Weapon) a;
		} else if (a instanceof Armor) {
			int currentArmorBonus = this.armor == null ? 0 : this.armor.getDefense();	
			this.defensePoints = this.defensePoints - currentArmorBonus + ((Armor)a).getDefense();
			this.armor = (Armor) a;
		} else if (a instanceof Helm) {
			int currentHPBonus = this.helm == null ? 0 : this.helm.getHP();	
			this.hitPoints = this.hitPoints - currentHPBonus + ((Helm)a).getHP();
			this.helm = (Helm) a;
		}
	}
	
	public int getXp(int xp) {
		this.experience += xp;
		
		while (this.experience >= (this.level*1000 + Math.pow((this.level - 1), 2)*450)) {
			this.level++;
		}
		
		return this.experience;
	}
	
	public int getDamage() {
		return this.attackDamage;
	}
	
	public void takeDamage(int x) {
		this.hitPoints = this.hitPoints - x < 0 ? 0 : this.hitPoints - x;
	}
	
	public void move(Coordinates co) {
		this.position = co;
	}
	
	public boolean attack(Villain v) {
		//TODO better attack calculation
		if (this.attackDamage > v.attackDamage) {
			return true;
		}
		return false;		
	}
	
	public boolean run(Villain v) {
		//TODO better run algorithm
		Random rand = new Random(System.currentTimeMillis());
		
		if ((rand.nextInt() % 100 + 1) % 2 == 0) {
			return true;
		}		
		return false;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public void setWeapon(Weapon a) {
		if (this.weapon != null) {
			this.attackDamage -= this.weapon.getDamage();
		}
		this.weapon = a;
		this.attackDamage += this.weapon.getDamage();
	}
	
	public void setHelm(Helm a) {
		if (this.helm != null) {
			this.hitPoints -= this.helm.getHP();
		}
		this.helm = a;
		this.hitPoints += this.helm.getHP();
	}
	
	public void setArmor(Armor a) {
		if (this.armor != null) {
			this.defensePoints -= this.armor.getDefense();
		}
		this.armor = a;
		this.defensePoints += this.armor.getDefense();
	}
	
	public String toString() {
		String s =  "Hero: " + this.name + "\n" +
					"Class: " + this.heroClass + "\n" +
					"Level: " + this.level + "\n" +
					"Position: (" + this.position.getRow() + ", " + this.position.getCol() + ")" + "\n" +
					"Attack: " + this.attackDamage + "\n" +
					"Defense: " + this.defensePoints + "\n" +
					"HP: " + this.hitPoints;
		
		if (armor != null) {
			s += "\n" + this.armor.toString();
		}
		
		if (helm != null) {
			s += "\n" + this.helm.toString();
		}
		
		if (weapon != null) {
			s += "\n" + this.weapon.toString();
		}
		
		return s;
	}
	
	public void setId(int id) {
		this.Id = id;
	}
	
	public Weapon getWeapon() {
		return this.weapon;
	}
	
	public Helm getHelm() {
		return this.helm;
	}
	
	public Armor getArmor() {
		return this.armor;
	}
	
}
