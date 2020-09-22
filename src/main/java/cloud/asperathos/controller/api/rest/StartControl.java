package cloud.asperathos.controller.api.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cloud.asperathos.controller.api.ControllerFacade;
import cloud.asperathos.controller.api.rest.request.StartControlInfo;

@RestController
public class StartControl {

	@PostMapping("/start/{appId}")
	public String start(@PathVariable String appId, 
						@RequestBody StartControlInfo controlInfo) {
		
		String controlId = ControllerFacade.getInstance().startControl(appId, controlInfo.getControlConfiguration());
		return controlId;
	}
}
