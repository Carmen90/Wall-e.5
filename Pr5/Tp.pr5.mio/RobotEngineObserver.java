package tp.pr5;

public interface RobotEngineObserver {

	void raiseError(java.lang.String msg);
	
	void communicationHelp(java.lang.String help);
	
	void engineOff(boolean atShip);
	
	void communicationCompleted();
	
	void robotUpdate(int fuel,
            int recycledMaterial);
	
	void robotSays(java.lang.String message);
}
