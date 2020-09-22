package cloud.asperathos.controller.api.rest;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cloud.asperathos.controller.api.ControllerFacade;
import cloud.asperathos.controller.api.rest.response.StateToMap;
import cloud.asperathos.controller.api.rest.response.StateToMap.GetStateResponse;
import cloud.asperathos.controller.engine.ControlState;

@RestController
public class GetState {

	@GetMapping("state/{appId}")
	public GetStateResponse getState(@PathVariable String appId) {
		Map<String, ControlState> state = ControllerFacade.getInstance().getControlState(appId);
		return StateToMap.getStringMap(state);
	}
}
