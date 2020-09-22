package cloud.asperathos.controller.api;

import java.util.Map;

import cloud.asperathos.controller.engine.ControlConfiguration;
import cloud.asperathos.controller.engine.ControlState;
import cloud.asperathos.controller.engine.ControllerManager;

public class ControllerFacade {

	private ControllerManager manager;
	
	private static ControllerFacade instance;
	
	private ControllerFacade() {
		this.manager = new ControllerManager();
	}
	
	public static ControllerFacade getInstance() {
		if (instance == null) {
			instance = new ControllerFacade(); 			
		}
		return instance;
	}
	
	public String startControl(String appId, Map<String, ControlConfiguration> configuration) {
		return manager.startControl(appId, configuration);
	}
	
	public void stopControl(String appId) {
		manager.stopControl(appId);
	}
	
	public Map<String, ControlState> getControlState(String appId) {
		return manager.getControlState(appId);
	}
	
	public void changeControlConfiguration(String appId, String controlId, ControlConfiguration configuration) {
		manager.changeControlConfiguration(appId, controlId, configuration);
	}
}
