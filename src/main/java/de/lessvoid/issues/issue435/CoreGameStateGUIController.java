package de.lessvoid.issues.issue435;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class CoreGameStateGUIController implements ScreenController{

	
	public static void C(){
		CharInfoManager.showCharBox(GameClient.nifty);
		CharInfoManager.createInventoryGUI(GameClient.nifty);
	}
	
	

	
	@Override
	public void bind(Nifty arg0, Screen arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEndScreen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStartScreen() {
		// TODO Auto-generated method stub
		
	}
	


}
