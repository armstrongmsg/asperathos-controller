package cloud.asperathos.controller.engine.plugins.actuator;

import cloud.asperathos.controller.engine.Actuator;
import cloud.asperathos.controller.engine.plugins.actuator.stub.StubActuator;

public class ActuatorBuilder {

	public static Actuator getActuator(String actuatorType) throws Exception {
		switch (actuatorType) {
			case "stub": return new StubActuator();
			default: throw new Exception("Unknown Actuator type");
		}
	}

}
