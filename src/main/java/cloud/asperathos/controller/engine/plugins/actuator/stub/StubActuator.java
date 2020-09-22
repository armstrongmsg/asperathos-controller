package cloud.asperathos.controller.engine.plugins.actuator.stub;

import cloud.asperathos.controller.engine.Actuator;
import cloud.asperathos.controller.model.Action;
import cloud.asperathos.controller.model.ActionResult;
import cloud.asperathos.controller.model.plugins.actions.stub.StubActionResult;

public class StubActuator implements Actuator {

	@Override
	public ActionResult performAction(Action action) {
		System.out.println(String.format("Performing action: %f", action.getValue()));
		return new StubActionResult();
	}
}
