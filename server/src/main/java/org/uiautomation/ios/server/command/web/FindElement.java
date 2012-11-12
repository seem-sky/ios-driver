package org.uiautomation.ios.server.command.web;

import org.json.JSONObject;
import org.uiautomation.ios.communication.WebDriverLikeRequest;
import org.uiautomation.ios.communication.WebDriverLikeResponse;
import org.uiautomation.ios.exceptions.IOSAutomationException;
import org.uiautomation.ios.server.IOSDriver;
import org.uiautomation.ios.server.command.BaseCommandHandler;
import org.uiautomation.ios.webInspector.DOM.RemoteWebElement;

public class FindElement extends BaseCommandHandler {

  public FindElement(IOSDriver driver, WebDriverLikeRequest request) {
    super(driver, request);
  }

  @Override
  public WebDriverLikeResponse handle() throws Exception {
    JSONObject payload = getRequest().getPayload();

    String type = payload.getString("using");
    String value = payload.getString("value");

    JSONObject element = new JSONObject();

    if ("css selector".equals(type)) {
      RemoteWebElement rmo = getSession().getWebInspector().findElementByCSSSelector(null, value);
      element.put("ELEMENT", rmo.getId());
    } else {
      throw new IOSAutomationException("selector not implemented");
    }

    return new WebDriverLikeResponse(getRequest().getSession(), 0, element);
  }

}
