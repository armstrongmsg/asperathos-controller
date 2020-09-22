package cloud.asperathos.controller.engine;

import java.util.List;

public interface ControlConfiguration {

	String getControlPlugin();
	String getControlStrategy();
	Integer getCheckInterval();
	// FIXME should be enum
	String getMetricSourceType();
	List<String> getMetricSourcesNames();
	String getActuatorType();
}
