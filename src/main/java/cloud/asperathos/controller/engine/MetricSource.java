package cloud.asperathos.controller.engine;

import cloud.asperathos.controller.model.MetricError;

public interface MetricSource {

	MetricError getMostRecentMetric();
}
