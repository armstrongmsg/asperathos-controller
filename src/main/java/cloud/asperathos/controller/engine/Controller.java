package cloud.asperathos.controller.engine;

public interface Controller {

	String startControl();
	void changeControlConfiguration(ControlConfiguration appConfiguration);
	ControlState getControlState();
	void stopControl();
}
