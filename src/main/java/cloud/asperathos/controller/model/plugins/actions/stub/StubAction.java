package cloud.asperathos.controller.model.plugins.actions.stub;

import cloud.asperathos.controller.model.Action;
import cloud.asperathos.controller.model.ActionTarget;

public class StubAction implements Action {

	private ActionTarget target;
	private Double value;
	
	public StubAction(ActionTarget target, Double value) {
		this.target = target;
		this.value = value;
	}
	
	@Override
	public ActionTarget getTarget() {
		return target;
	}

	@Override
	public Double getValue() {
		return value;
	}
}
