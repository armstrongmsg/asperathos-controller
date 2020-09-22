package cloud.asperathos.controller.engine.plugins.metricsource;

import java.util.ArrayList;
import java.util.List;

import cloud.asperathos.controller.engine.ControlConfiguration;
import cloud.asperathos.controller.engine.MetricSource;
import cloud.asperathos.controller.engine.plugins.metricsource.stub.StubMetricSource;

public class MetricSourceBuilder {

	public static MetricSource getMetricSource(String metricSourceType, String metricSourceName, 
			ControlConfiguration configuration) throws Exception {
		
		switch (metricSourceType) {
			case "stub": {
				List<Double> values = new ArrayList<Double>();
				values.add(-0.1);
				values.add(-0.5);
				values.add(-0.1);
				values.add(-0.5);
				values.add(0.0);
				values.add(0.1);
				values.add(0.5);
				values.add(0.1);
				values.add(0.5);
				values.add(0.0);
				
				return new StubMetricSource(values);
			
			}
			default: throw new Exception("Unknown Metric Source type");
		}
	}
}
