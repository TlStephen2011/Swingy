package za.co.wethinkcode.database;

import java.sql.Statement;
import java.util.ArrayList;

import za.co.wethinkcode.models.*;

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
		
		sb.append("Update heroes SET name=?, class=?, experience=?, level=?, armor=?, armorPoints=?, helm=?, helmPoints=?, weapon=?, weaponPoints=?");
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
				
				Armor armor = h.getArmor();
				if (armor != null) {
					statement.setString(5, armor.name);
					statement.setInt(6, armor.getDefense());
				} else {
					statement.setString(5, "");
					statement.setInt(6, 0);
				}
				
				Helm helm = h.getHelm();
				if (helm != null) {
					statement.setString(7, helm.name);
					statement.setInt(8, helm.getHP());
				} else {
					statement.setString(7, "");
					statement.setInt(8, 0);
				}
				
				Weapon weapon = h.getWeapon();
				if (weapon != null) {
					statement.setString(9, weapon.name);
					statement.setInt(10, weapon.getDamage());
				} else {
					statement.setString(9, "");
					statement.setInt(10, 0);
				}
				
				statement.executeUpdate();				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("QUERY: " + query);		

	}
	
	public static ArrayList<Hero> getAllHeroes() {
		ArrayList<Hero> heroes = null;
		try {
			String query = "SELECT * FROM heroes";
			Statement statement = c.createStatement();
			ResultSet res = statement.executeQuery(query);
			heroes = new ArrayList<Hero>();
			
			while (res.next()) {
				//TODO build proper hero
				Hero h = new Hero(res.getString("name"), res.getString("class"));
				h.setId(res.getInt("id"));
				System.out.println(res.getInt("id"));
				heroes.add(h);				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error fetching heroes");
		}
		return heroes;
	}
	
}
