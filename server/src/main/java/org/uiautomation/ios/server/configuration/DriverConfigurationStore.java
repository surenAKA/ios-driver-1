/*
 * Copyright 2012-2013 eBay Software Foundation and ios-driver committers
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.uiautomation.ios.server.configuration;

import org.uiautomation.ios.UIAModels.configuration.CommandConfiguration;
import org.uiautomation.ios.UIAModels.configuration.DriverConfiguration;
import org.uiautomation.ios.communication.WebDriverLikeCommand;

import java.util.HashMap;
import java.util.Map;

public class DriverConfigurationStore implements DriverConfiguration {

  private final
  Map<WebDriverLikeCommand, CommandConfiguration>
      configurations =
      new HashMap<WebDriverLikeCommand, CommandConfiguration>();

  public DriverConfigurationStore() {
    loadDefaults();
  }

  @Override
  public CommandConfiguration configure(WebDriverLikeCommand command) {
    CommandConfiguration config = configurations.get(command);
    if (config == null) {
      config = new CommandConfigurationStore();
      configurations.put(command, config);
    }
    return config;
  }


  private void loadDefaults() {
    configure(WebDriverLikeCommand.CLICK).set("nativeEvents", true);
    configure(WebDriverLikeCommand.SEND_KEYS).set("nativeEvents", true);
    configure(WebDriverLikeCommand.SET_VALUE).set("nativeEvents", true);
  }

}
