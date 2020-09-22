package cloud.asperathos.controller.model.plugins.strategy;

import cloud.asperathos.controller.api.rest.request.PIDControlConfiguration;
import cloud.asperathos.controller.engine.ControlConfiguration;
import cloud.asperathos.controller.model.ActionTarget;
import cloud.asperathos.controller.model.ControlStrategy;
import cloud.asperathos.controller.model.plugins.actions.ActionBuilder;

public class ControlStrategyBuilder {

	public static ControlStrategy getControlStrategy(String controlStrategy, 
			ControlConfiguration configuration) throws Exception {
		// FIXME should check target from conf
		ActionBuilder actionBuilder = new ActionBuilder(ActionTarget.REPLICA_NUMBER);
		switch (controlStrategy) {
			case "pid": {
				PIDControlConfiguration pidConf = (PIDControlConfiguration) configuration;
				// FIXME hardcoded
				return new PIDControlStrategy(pidConf.getHeuristicOptions().get("proportional_gain"), 
											  pidConf.getHeuristicOptions().get("derivative_gain"), 
											  pidConf.getHeuristicOptions().get("integral_gain"), actionBuilder);
			}
			default: throw new Exception("Unknown control strategy");
		}
	}
}
