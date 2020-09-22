package cloud.asperathos.controller.engine.plugins.metricsource.stub;

import java.util.List;

import cloud.asperathos.controller.engine.MetricSource;
import cloud.asperathos.controller.model.Metric;
import cloud.asperathos.controller.model.MetricError;
import cloud.asperathos.controller.model.MetricType;
import cloud.asperathos.controller.model.plugins.error.InputOutputMetricError;
import cloud.asperathos.controller.model.plugins.error.MetricErrorBuilder;

public class StubMetricSource implements MetricSource {

	private List<Double> values;
	private Integer pointer;
	
	public StubMetricSource(List<Double> values) {
		//TODO validate
		pointer = 0;
		this.values = values;
	}
	
	@Override
	public MetricError getMostRecentMetric() {
		Double currentValue = values.get(pointer);
		
		if (pointer < values.size() - 1) {
			pointer++;			
		}
		
		return new InputOutputMetricError(currentValue);
	}

}
