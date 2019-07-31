package za.co.wethinkcode.swingy;

import za.co.wethinkcode.controllers.GameController;
import za.co.wethinkcode.database.HeroStorage;
import za.co.wethinkcode.models.Hero;

public class App 
{
    public static void main( String[] args )
    {
    	HeroStorage.connect();
    		
    	GameController controller = new GameController();
    	while (controller.run()) {}
    	
    }
}
