package cloud.asperathos.controller.engine.plugins.control.def;

import java.util.ArrayList;
import java.util.List;

import cloud.asperathos.controller.engine.Actuator;
import cloud.asperathos.controller.engine.ControlConfiguration;
import cloud.asperathos.controller.engine.ControlState;
import cloud.asperathos.controller.engine.Controller;
import cloud.asperathos.controller.engine.MetricSource;
import cloud.asperathos.controller.engine.plugins.actuator.ActuatorBuilder;
import cloud.asperathos.controller.engine.plugins.metricsource.MetricSourceBuilder;
import cloud.asperathos.controller.model.Action;
import cloud.asperathos.controller.model.MetricError;
import cloud.asperathos.controller.model.ControlStrategy;
import cloud.asperathos.controller.model.plugins.strategy.ControlStrategyBuilder;

public class DefaultController implements Controller {

	private ControlRunner runner;
	private Thread runnerThread;
	
	public DefaultController(ControlConfiguration appConfiguration) {
		ControlStrategy strategy = null;
		
		try {
			strategy = ControlStrategyBuilder.getControlStrategy(appConfiguration.getControlStrategy(), appConfiguration);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		List<MetricSource> metricSources = new ArrayList<MetricSource>();
		
		String metricSourceType = appConfiguration.getMetricSourceType();
		List<String> metricSourceNames = appConfiguration.getMetricSourcesNames();
		
		for (String metricSourceName : metricSourceNames){
			MetricSource metricSource;
			try {
				metricSource = MetricSourceBuilder.getMetricSource(metricSourceType, metricSourceName, 
						appConfiguration);
				metricSources.add(metricSource);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Actuator actuator = null;
		
		try {
			actuator = ActuatorBuilder.getActuator(appConfiguration.getActuatorType());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Integer waitTime = appConfiguration.getCheckInterval();
		
		runner = new ControlRunner(strategy, metricSources, actuator, waitTime);
	}
	
	@Override
	public String startControl() {
		runnerThread = new Thread(runner);
		runnerThread.start();
		return "";
	}

	@Override
	public void changeControlConfiguration(ControlConfiguration appConfiguration) {
		// TODO Auto-generated method stub

	}

	@Override
	public ControlState getControlState() {
		return new DefaultControlState(this.runner.getControlStrategy().getState());
	}

	@Override
	public void stopControl() {
		runner.stop();
	}
	
	private class ControlRunner implements Runnable {
		
		private ControlStrategy strategy;
		private List<MetricSource> metricSources;
		private Actuator actuator;
		private Integer waitTime;
		private Boolean stop;
		
		ControlRunner(ControlStrategy strategy, List<MetricSource> metricSources, 
				Actuator actuator, Integer waitTime) {
			this.strategy = strategy;
			this.metricSources = metricSources;
			this.waitTime = waitTime;
			this.actuator = actuator;
			this.stop = false;
		}
		
		public ControlStrategy getControlStrategy() { 
			return strategy;
		}
		
		@Override
		public void run() { 
			try {
				while (!stop) {
					Thread.sleep(waitTime*1000);
					System.out.println("1");
					List<MetricError> metricErrors = new ArrayList<MetricError>();
					
					for (MetricSource source : metricSources) {
						metricErrors.add(source.getMostRecentMetric());
					}
					System.out.println("2");
					
					List<Action> actions = this.strategy.getResponse(metricErrors);
					
					System.out.println(actions);
					
					// TODO should check the action type before 
					// calling the actuator
					for (Action action : actions) {
						System.out.println(actuator);
						System.out.println(action.getValue());
						this.actuator.performAction(action);
					}
					
					System.out.println("4");
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public synchronized void stop() {
			this.stop = true;
		}
	}
}
