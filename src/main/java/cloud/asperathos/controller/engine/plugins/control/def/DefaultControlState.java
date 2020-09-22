package cloud.asperathos.controller.engine.plugins.control.def;

import java.util.Map;

import cloud.asperathos.controller.engine.ControlState;
import cloud.asperathos.controller.model.ControlStrategyState;

public class DefaultControlState implements ControlState {

	private Map<String, String> state;
	
	public DefaultControlState(ControlStrategyState controlStrategyState) {
		state = controlStrategyState.getStateParams();
	}

	@Override
	public Map<String, String> getStateParams() {
		return state;
	}
}
