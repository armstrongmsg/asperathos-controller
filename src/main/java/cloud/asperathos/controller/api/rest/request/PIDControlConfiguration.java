package cloud.asperathos.controller.api.rest.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cloud.asperathos.controller.engine.ControlConfiguration;

public class PIDControlConfiguration implements ControlConfiguration {

	private String controlPlugin;
	private String scheduleStrategy;
	private String actuator;
	private String metricSource;
	private String metricQueue;
	private Integer checkInterval;
	private Double triggerDown;
	private Double triggerUp;
	private Integer minRep;
	private Integer maxRep;
	private Map<String, Double> heuristicOptions;

	public PIDControlConfiguration(String controlPlugin, String scheduleStrategy, 
			Map<String, Object> info) {
		// FIXME hardcoded
		this.controlPlugin = controlPlugin;
		this.scheduleStrategy = scheduleStrategy;
		this.actuator = (String) info.get("actuator");
		this.metricSource = (String) info.get("metric_source");
		this.metricQueue = (String) info.get("metric_queue");
		this.checkInterval = (Integer) info.get("check_interval");
		this.triggerDown = (Double) info.get("trigger_down");
		this.triggerUp = (Double) info.get("trigger_up");
		this.minRep = (Integer) info.get("min_rep");
		this.maxRep = (Integer) info.get("max_rep");
		this.heuristicOptions = (Map<String, Double>) info.get("heuristic_options");
	}

	@Override
	public String toString() {
		return "PIDControlConfiguration [controlPlugin=" + controlPlugin + ", scheduleStrategy=" + scheduleStrategy
				+ ", actuator=" + actuator + ", metricSource=" + metricSource + ", metricQueue=" + metricQueue
				+ ", checkInterval=" + checkInterval + ", triggerDown=" + triggerDown + ", triggerUp=" + triggerUp
				+ ", minRep=" + minRep + ", maxRep=" + maxRep + ", heuristicOptions=" + heuristicOptions + "]";
	}

	public String getActuator() {
		return actuator;
	}

	public String getMetricSource() {
		return metricSource;
	}

	public String getMetricQueue() {
		return metricQueue;
	}

	@Override
	public Integer getCheckInterval() {
		return checkInterval;
	}

	public Double getTriggerDown() {
		return triggerDown;
	}

	public Double getTriggerUp() {
		return triggerUp;
	}

	public Integer getMinRep() {
		return minRep;
	}

	public Integer getMaxRep() {
		return maxRep;
	}

	public Map<String, Double> getHeuristicOptions() {
		return heuristicOptions;
	}

	@Override
	public String getControlPlugin() {
		return controlPlugin;
	}

	@Override
	public String getControlStrategy() {
		return scheduleStrategy;
	}

	@Override
	public String getMetricSourceType() {
		return metricSource;
	}

	@Override
	public List<String> getMetricSourcesNames() {
		ArrayList<String> metricSourceNames = new ArrayList<String>();
		metricSourceNames.add(metricQueue);
		return metricSourceNames;
	}

	@Override
	public String getActuatorType() {
		return actuator;
	}
}
