package za.co.wethinkcode.database;

import java.sql.Statement;
import java.util.ArrayList;

import za.co.wethinkcode.models.*;
import za.co.wethinkcode.utilities.Coordinates;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HeroStorage {
	private static Connection c = null;
	
	public static void connect() {
		try {
			String url = "jdbc:sqlite:swingy.db";
            // create a connection to the database
            c = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            
            Statement stmnt = c.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS heroes"
            + "  (id           INTEGER PRIMARY KEY NOT NULL,"
            + "   name         VARCHAR(55),"
            + "   class		   VARCHAR(55),"
            + "	  experience   INTEGER,"
            + "   level        INTEGER,"
            + "   attack       INTEGER,"
            + "   defense      INTEGER,"
            + "   hp           INTEGER,"
            + "   armor        VARCHAR(55),"
            + "   armorPoints  INTEGER,"
            + "   helm         VARCHAR(55),"
            + "   helmPoints   INTEGER,"
            + "   weapon       VARCHAR(55),"
            + "   weaponPoints INTEGER)";
            stmnt.executeUpdate(query);
            
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to connect to SQLite");
		}
	}
	
	private static String buildInsertQuery(Hero h) {
		StringBuilder sb = new StringBuilder();
		StringBuilder values = new StringBuilder();
		
		sb.append("INSERT INTO heroes(");
		values.append(" VALUES(");
		
		sb.append("name,");
		values.append("\"" + h.getHeroName() + "\",");
		sb.append("class,");
		values.append("\"" + h.getHeroClass() + "\",");
		sb.append("experience,");
		values.append(h.getExperience() + ",");
		sb.append("level,");
		values.append(h.getLevel() + ",");
		sb.append("attack,");
		values.append(h.getDamage() + ",");
		sb.append("defense,");
		values.append(h.getDefense() + ",");
		sb.append("hp,");
		values.append(h.getHP() + ",");
		
		Armor armor = h.getArmor();
		if (armor != null) {
			sb.append("armor,");
			values.append("\"" + armor.name + "\",");
			sb.append("armorPoints,");
			values.append(armor.getDefense() + ",");
		}
		
		Helm helm = h.getHelm();
		if (helm != null) {
			sb.append("helm,");
			values.append("\"" + helm.name + "\",");
			sb.append("helmPoints,");
			values.append("\"" + helm.getHP() + "\",");			
		}
		
		Weapon weapon = h.getWeapon();
		if (weapon != null) {
			sb.append("weapon,");
			values.append("\"" + weapon.name + "\",");
			sb.append("weaponPoints,");
			values.append(weapon.getDamage() + ",");
		}		
		
		String query = sb.toString().substring(0, sb.length() - 1) + ") " + values.toString().substring(0, values.length() - 1) + ")";
		
		return query;
	}
	
	private static String buildUpdateQuery(Hero h) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Update heroes SET name=?, class=?, experience=?, level=?, attack=?, defense=?, hp=?, armor=?, armorPoints=?, helm=?, helmPoints=?, weapon=?, weaponPoints=?");
		sb.append(" WHERE id=" + h.getId());
		
		
		return sb.toString();
	}
	
	public static void saveHero(Hero h) {			
		if (h == null) {
			return;
		}
		
		String query;
		if (h.getId() == 0) {
			query = buildInsertQuery(h);			
			try {
				Statement statement = c.createStatement();
				statement.executeUpdate(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			query = buildUpdateQuery(h);
			try {
				PreparedStatement statement = c.prepareStatement(query);
				statement.setString(1, h.getHeroName());
				statement.setString(2, h.getHeroClass());
				statement.setInt(3, h.getExperience());
				statement.setInt(4, h.getLevel());
				statement.setInt(5, h.getDamage());
				statement.setInt(6, h.getDefense());
				statement.setInt(7, h.getHP());
				
				Armor armor = h.getArmor();
				if (armor != null) {
					statement.setString(8, armor.name);
					statement.setInt(9, armor.getDefense());
				} else {
					statement.setString(8, "");
					statement.setInt(9, 0);
				}
				
				Helm helm = h.getHelm();
				if (helm != null) {
					statement.setString(10, helm.name);
					statement.setInt(11, helm.getHP());
				} else {
					statement.setString(10, "");
					statement.setInt(11, 0);
				}
				
				Weapon weapon = h.getWeapon();
				if (weapon != null) {
					statement.setString(12, weapon.name);
					statement.setInt(13, weapon.getDamage());
				} else {
					statement.setString(12, "");
					statement.setInt(13, 0);
				}
				
				statement.executeUpdate();				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static ArrayList<Hero> getAllHeroes() {
		ArrayList<Hero> heroes = null;
		try {
			String query = "SELECT * FROM heroes";
			Statement statement = c.createStatement();
			ResultSet res = statement.executeQuery(query);
			heroes = new ArrayList<Hero>();
			
			while (res.next()) {
				Hero h = new Hero(
					res.getString("name"),
					res.getString("class"),
					res.getInt("level"),
					res.getInt("experience"),
					res.getInt("attack"),
					res.getInt("defense"),
					res.getInt("hp"),
					new Coordinates(0, 0));
				h.setId(res.getInt("id"));
				
				String weapon = res.getString("weapon");
				if (weapon.length() > 0) {
					h.equip(new Weapon(weapon, res.getInt("weaponPoints")));
				}
				
				String armor = res.getString("armor");
				if (armor.length() > 0) {
					h.equip(new Armor(armor, res.getInt("armorPoints")));
				}
				
				String helm = res.getString("helm");
				if (helm.length() > 0) {
					h.equip(new Helm(helm, res.getInt("helmPoints")));
				}
				
				heroes.add(h);				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error fetching heroes");
		}
		return heroes;
	}
	
}
