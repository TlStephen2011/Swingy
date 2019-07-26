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
            + "   class		   VARCHAR(55))";
            stmnt.executeUpdate(query);
            
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to connect to SQLite");
		}
	}
	
	public static void saveHero(Hero h) {		
		try {
			String query = "INSERT INTO heroes(name,class) VALUES(?,?)";
			PreparedStatement statement = c.prepareStatement(query);
			statement.setString(1, h.getHeroName());
			statement.setString(2, h.getHeroClass());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
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
