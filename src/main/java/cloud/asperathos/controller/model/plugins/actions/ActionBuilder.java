package cloud.asperathos.controller.model.plugins.actions;

import cloud.asperathos.controller.model.Action;
import cloud.asperathos.controller.model.ActionTarget;
import cloud.asperathos.controller.model.plugins.actions.stub.StubAction;

public class ActionBuilder {

	private ActionTarget target;
	private Double value;
	
	public ActionBuilder(ActionTarget target) {
		this.target = target;
	}
	
	public ActionBuilder withValue(Double value) {
		this.value = value;
		return this;
	}
	
	public Action build() { 
		return new StubAction(this.target, this.value);
	}
}
