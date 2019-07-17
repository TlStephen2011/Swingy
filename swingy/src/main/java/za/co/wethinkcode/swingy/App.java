package za.co.wethinkcode.swingy;

import za.co.wethinkcode.controllers.GameController;

public class App 
{
    public static void main( String[] args )
    {
    	GameController controller = new GameController();
    	while (controller.run()) {}
    }
}
