package za.co.wethinkcode.views;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import za.co.wethinkcode.models.GameBoard;
import za.co.wethinkcode.models.Hero;

public class Console implements Viewable {
	
	public Console() {
		
	}

	public String[] createNewHero() {
		String[] str = new String[2];
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter hero name: ");
			str[0] = br.readLine();
			System.out.println("Enter hero class: ");
			str[1] = br.readLine();			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return str;
	}
	
	public String[] showHeroMenu() {
		//TODO present hero options from file
		ArrayList<String> heroes = new ArrayList<String>();
		System.out.println("These are your available heroes:");
		
		
		
		//TODO present int to select which hero you want.
		
		//TODO take int and parse hero
		
		//TODO create Hero and return ... maybe throw exception or ask again
		
		//TODO if int is create hero call Create hero options
		
		return null;
	}
	
	public inputType getInput() {
		BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            
            String input = br.readLine();
            input = input.toUpperCase();
            
            if (input.equals("N")) {
            	return inputType.NORTH;
            } else if (input.equals("S")) {
            	return inputType.SOUTH;
            } else if (input.equals("W")) {
            	return inputType.WEST;
            } else if (input.equals("E")) {
            	return inputType.EAST;
            } else if (input.equals("FIGHT")) {
            	return inputType.FIGHT;
            } else if (input.equals("RUN")) {
            	return inputType.RUN;
            } else if (input.equals("TAKE")) {
            	return inputType.TAKE_ITEM;
            } else {
            	return inputType.UNKNOWN;
            }
            
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
		return inputType.UNKNOWN;
	}

	public void display(GameBoard g, String x) {
		System.out.println(x);
	}

	public Hero newHero() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
