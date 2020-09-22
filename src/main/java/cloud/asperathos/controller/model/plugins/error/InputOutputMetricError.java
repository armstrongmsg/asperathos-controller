package cloud.asperathos.controller.model.plugins.error;

import cloud.asperathos.controller.model.MetricError;
import cloud.asperathos.controller.model.MetricType;

public class InputOutputMetricError implements MetricError {

	private double errorValue;
	
	public InputOutputMetricError(double errorValue) {
		this.errorValue = errorValue;
	}
	
	@Override
	public MetricType getType() {
		return MetricType.PERFORMANCE_INPUT_OUTPUT;
	}

	@Override
	public Double getValue() {
		return errorValue;
	}

}
