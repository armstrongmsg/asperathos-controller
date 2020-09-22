package cloud.asperathos.controller.api.rest.request;

import java.util.HashMap;
import java.util.Map;

import cloud.asperathos.controller.engine.ControlConfiguration;

public class StartControlInfo {

	private Map<String, Map<String, Object>> control_info;

	public Map<String, Map<String, Object>> getControl_info() {
		return control_info;
	}

	public void setControl_info(Map<String, Map<String, Object>> control_info) {
		this.control_info = control_info;
	}

	@Override
	public String toString() {
		return control_info.toString();
	}
	
	public Map<String, ControlConfiguration> getControlConfiguration() {
		HashMap<String, ControlConfiguration> controlConfiguration = new HashMap<String, ControlConfiguration>();
		
		for (String confId : this.control_info.keySet()) {
			System.out.println("here");
			Map<String, Object> info = control_info.get(confId);
			
			String plugin = (String) info.get("plugin");
			String scheduleStrategy = (String) info.get("schedule_strategy");
			System.out.println(plugin);
			System.out.println(scheduleStrategy);
			
			if (plugin.equals("kubejobs")) {
				if (scheduleStrategy.equals("pid")) {
					System.out.println("here2");
					PIDControlConfiguration pidConf = new PIDControlConfiguration(plugin, scheduleStrategy, info);
					controlConfiguration.put(confId, pidConf);
				}
			}
		}
		
		return controlConfiguration;
	}
}
