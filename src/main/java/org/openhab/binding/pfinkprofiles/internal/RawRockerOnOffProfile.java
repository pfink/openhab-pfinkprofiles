/**
 * Copyright (c) 2010-2020 Contributors to the openHAB project
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
import org.openhab.core.library.types.OnOffType;
import org.openhab.core.thing.CommonTriggerEvents;
import org.openhab.core.thing.profiles.ProfileCallback;
import org.openhab.core.thing.profiles.ProfileTypeUID;
import org.openhab.core.thing.profiles.TriggerProfile;
import org.openhab.core.types.State;

/**
 * The {@link RawRockerOnOffProfile} transforms rocker switch channel events into ON and OFF commands.
 *
 * @author Patrick Fink - Initial contribution
 */
@NonNullByDefault
public class RawRockerOnOffProfile implements TriggerProfile {

    private final ProfileCallback callback;

    RawRockerOnOffProfile(ProfileCallback callback) {
        this.callback = callback;
    }

    @Override
    public ProfileTypeUID getProfileTypeUID() {
        return PfinkProfilesBindingConstants.UID_ROCKER_TO_ON_OFF;
    }

    @Override
    public void onStateUpdateFromItem(State state) {
    }

    @Override
    public void onTriggerFromHandler(String event) {
        if (CommonTriggerEvents.DIR1_PRESSED.equals(event) || CommonTriggerEvents.DIR1_RELEASED.equals(event)) {
            callback.sendCommand(OnOffType.ON);
        } else if (CommonTriggerEvents.DIR2_PRESSED.equals(event) || CommonTriggerEvents.DIR2_RELEASED.equals(event)) {
            callback.sendCommand(OnOffType.OFF);
        }
    }
}
