package cloud.asperathos.controller.model;

import java.util.List;

public interface ControlStrategy {
	
	List<Action> getResponse(List<MetricError> errors);
	ControlStrategyState getState();
}
