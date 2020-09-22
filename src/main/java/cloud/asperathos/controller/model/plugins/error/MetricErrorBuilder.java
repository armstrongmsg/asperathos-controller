package cloud.asperathos.controller.model.plugins.error;

import cloud.asperathos.controller.model.MetricError;
import cloud.asperathos.controller.model.MetricType;

public class MetricErrorBuilder {

	public static MetricError getMetricError(MetricType type) {
		switch (type) {
			case PERFORMANCE_INPUT_OUTPUT: return new InputOutputMetricError(0);
			default: ;
		}
		return null;
	}
}
