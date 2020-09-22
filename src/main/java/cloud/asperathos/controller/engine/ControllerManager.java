package cloud.asperathos.controller.engine;

import java.util.HashMap;
import java.util.Map;

import cloud.asperathos.controller.engine.plugins.control.def.DefaultController;

public class ControllerManager {

	private Map<String, Map<String, Controller>> controlledApps;
	
	public ControllerManager() {
		controlledApps = new HashMap<String, Map<String, Controller>>();
	}
	
	public String startControl(String appId, Map<String, ControlConfiguration> configuration) {
		System.out.println(configuration);
		Map<String, Controller> controllerMap = new HashMap<String, Controller>();

		for (String confId : configuration.keySet()) {
			System.out.println(confId);
			ControlConfiguration controlConfiguration = configuration.get(confId);
			String plugin = controlConfiguration.getControlPlugin();
			
			if (plugin.equals("kubejobs")) {
				System.out.println("kubejobs");
				DefaultController controller = new DefaultController(controlConfiguration);
				controllerMap.put(confId, controller);
				System.out.println(controllerMap);
			}
			
			controlledApps.put(appId, controllerMap);
		}
		
//		System.out.println(controlledApps);
//		
//		Map<String, Controller> controllerMap = controlledApps.get(appId);
//		
		for (Controller controller : controllerMap.values()) {
			controller.startControl();
		}
		
		return appId;
	}
	
	public void stopControl(String appId) {
		Map<String, Controller> controllerMap = controlledApps.get(appId);
		
		for (Controller controller : controllerMap.values()) {
			controller.stopControl();
		}
		
		controllerMap.remove(appId);
	}
	
	public Map<String, ControlState> getControlState(String appId) {
		Map<String, ControlState> controllerStateMap = new HashMap<String, ControlState>();
		Map<String, Controller> controllerMap = controlledApps.get(appId);
		
		for (String confId : controllerMap.keySet()) {
			Controller controller = controllerMap.get(confId);
			controllerStateMap.put(confId, controller.getControlState());
		}
		
		return controllerStateMap;
	}
	
	public void changeControlConfiguration(String appId, String controlId, ControlConfiguration configuration) {
		
	}
}
