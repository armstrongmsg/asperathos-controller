package cloud.asperathos.controller.engine;

import cloud.asperathos.controller.model.Action;
import cloud.asperathos.controller.model.ActionResult;

public interface Actuator {

	ActionResult performAction(Action action);
}
