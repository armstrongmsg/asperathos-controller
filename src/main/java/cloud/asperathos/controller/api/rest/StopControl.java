package cloud.asperathos.controller.api.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import cloud.asperathos.controller.api.ControllerFacade;

@RestController
public class StopControl {

	@PutMapping("/stop/{appId}")
	public void stop(@PathVariable String appId) {
		ControllerFacade.getInstance().stopControl(appId);
	}
}
