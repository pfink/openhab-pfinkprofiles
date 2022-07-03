/**
 * Copyright (c) 2010-2022 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.pfinkprofiles.internal;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.core.library.types.OnOffType;
import org.openhab.core.thing.CommonTriggerEvents;
import org.openhab.core.thing.DefaultSystemChannelTypeProvider;
import org.openhab.core.thing.profiles.ProfileCallback;
import org.openhab.core.thing.profiles.ProfileContext;
import org.openhab.core.thing.profiles.ProfileTypeUID;
import org.openhab.core.thing.profiles.TriggerProfile;
import org.openhab.core.types.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This profile allows a channel of the "system:rawbutton" type to be bound to an item.
 *
 * It reads the triggered events and uses the item's current state and toggles it once it detects that the
 * button was pressed.
 *
 * @author Simon Kaufmann - Initial contribution
 */
@NonNullByDefault
public class ButtonToggleSwitchProfile implements TriggerProfile {

    private final Logger logger = LoggerFactory.getLogger(ButtonToggleSwitchProfile.class);

    private final ProfileCallback callback;
    private static final String EVENT_PARAM = "event";

    private final String onEvent;

    private @Nullable State previousState;

    public ButtonToggleSwitchProfile(ProfileCallback callback, ProfileContext context) {
        this.callback = callback;

        String triggerEvent = (String) context.getConfiguration().get(EVENT_PARAM);
        if (isValidEvent(triggerEvent)) {
            onEvent = triggerEvent;
        } else {
            if (triggerEvent != null) {
                logger.warn(
                        "'{}' is not a valid trigger event for Profile button-toggle-switch. Default trigger event SHORT_PRESSED is used instead.",
                        triggerEvent);
            }
            onEvent = CommonTriggerEvents.SHORT_PRESSED;
        }
    }

    public boolean isValidEvent(@Nullable String triggerEvent) {
        return DefaultSystemChannelTypeProvider.SYSTEM_BUTTON.getEvent().getOptions().stream()
                .anyMatch(e -> e.getValue().equals(triggerEvent));
    }

    @Override
    public ProfileTypeUID getProfileTypeUID() {
        return PfinkProfilesBindingConstants.UID_BUTTON_TOGGLE_SWITCH;
    }

    @Override
    public void onTriggerFromHandler(String event) {
        if (onEvent.equals(event)) {
            OnOffType newState = OnOffType.ON.equals(previousState) ? OnOffType.OFF : OnOffType.ON;
            callback.sendCommand(newState);
            previousState = newState;
        }
    }

    @Override
    public void onStateUpdateFromItem(State state) {
        previousState = state.as(OnOffType.class);
    }
}
