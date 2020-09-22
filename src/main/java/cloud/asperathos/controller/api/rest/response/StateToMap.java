package cloud.asperathos.controller.api.rest.response;

import java.util.HashMap;
import java.util.Map;

import cloud.asperathos.controller.engine.ControlState;

public class StateToMap {

	public static GetStateResponse getStringMap(Map<String, ControlState> state) {
		
		Map<String, Map<String, String>> response = new HashMap<String, Map<String, String>>();
		
		for (String confId : state.keySet()) {
			response.put(confId, state.get(confId).getStateParams());
		}
		
		return new GetStateResponse(response);
	}
	
	public static class GetStateResponse {
		private Map<String, Map<String, String>> state;

		public GetStateResponse(Map<String, Map<String, String>> state) {
			super();
			this.state = state;
		}

		public Map<String, Map<String, String>> getState() {
			return state;
		}

		public void setState(Map<String, Map<String, String>> state) {
			this.state = state;
		}
	}
}
