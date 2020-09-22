package cloud.asperathos.controller.model.plugins.strategy;

import java.util.HashMap;
import java.util.Map;

import cloud.asperathos.controller.model.ControlStrategyState;

public class PIDControlStrategyState implements ControlStrategyState {

	private Double integratedError;
	
	public PIDControlStrategyState(Double integratedError) {
		this.integratedError = integratedError;
	}

	@Override
	public Map<String, String> getStateParams() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("integrated_error", integratedError.toString());
		return params;
	}
}
