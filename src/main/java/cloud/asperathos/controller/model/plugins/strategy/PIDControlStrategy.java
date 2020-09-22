package cloud.asperathos.controller.model.plugins.strategy;

import java.util.ArrayList;
import java.util.List;

import cloud.asperathos.controller.model.Action;
import cloud.asperathos.controller.model.MetricError;
import cloud.asperathos.controller.model.ControlStrategy;
import cloud.asperathos.controller.model.ControlStrategyState;
import cloud.asperathos.controller.model.plugins.actions.ActionBuilder;

public class PIDControlStrategy implements ControlStrategy {

	private Double proportionalGain;
	private Double derivativeGain;
	private Double integralGain;
	
	private Double integratedError;
	private Double lastErrorValue;
	
	private ActionBuilder actionBuilder;
	
	public PIDControlStrategy(Double proportionalGain,
			Double derivativeGain, Double integralGain, 
			ActionBuilder actionBuilder) {
		this.proportionalGain = proportionalGain;
		this.derivativeGain = derivativeGain;
		this.integralGain = integralGain;
		
		integratedError = 0.0;
		lastErrorValue = 0.0;
		
		this.actionBuilder = actionBuilder;
	}
	
	@Override
	public List<Action> getResponse(List<MetricError> errors) {
		MetricError error = errors.get(0);
		Double errorValue = error.getValue();
		
		Double proportionalComponent = calculateProportionalComponent(errorValue);
		Double derivativeComponent = calculateDerivativeComponent(errorValue);
		Double integralComponent = calculateIntegralComponent(errorValue); 
		
		Double actionValue = calculateFinalActionValue(proportionalComponent, 
				derivativeComponent, integralComponent);
		
		List<Action> action = createActionList(actionValue);
		return action;
	}

	private Double calculateProportionalComponent(Double errorValue) {
		Double proportionalComponent = this.proportionalGain * errorValue;
		return proportionalComponent;
	}

	private Double calculateDerivativeComponent(Double errorValue) {
		Double derivativeComponent = this.derivativeGain * (errorValue - this.lastErrorValue);
		lastErrorValue = errorValue;
		return derivativeComponent;
	}

	private Double calculateIntegralComponent(Double errorValue) {
		integratedError += errorValue;
		Double integralComponent = this.integralGain * integratedError;
		return integralComponent;
	}
	
	private Double calculateFinalActionValue(Double proportionalComponent, Double derivativeComponent,
			Double integralComponent) {
		Double actionValue = -1 * (proportionalComponent + derivativeComponent + integralComponent);
		return actionValue;
	}
	
	private List<Action> createActionList(Double actionValue) {
		List<Action> action = new ArrayList<Action>();
		action.add(this.actionBuilder.withValue(actionValue).build());
		return action;
	}

	@Override
	public ControlStrategyState getState() {
		return new PIDControlStrategyState(integratedError);
	}
}
