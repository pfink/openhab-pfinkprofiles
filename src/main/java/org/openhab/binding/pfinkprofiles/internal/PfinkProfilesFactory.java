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

import static org.openhab.binding.pfinkprofiles.internal.PfinkProfilesBindingConstants.*;

import java.util.Collection;
import java.util.Locale;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.core.library.CoreItemFactory;
import org.openhab.core.thing.DefaultSystemChannelTypeProvider;
import org.openhab.core.thing.profiles.*;
import org.osgi.service.component.annotations.Component;

/**
 * The {@link PfinkProfilesFactory} is responsible for creating things and thing
 * handlers.
 *
 * @author Patrick Fink - Initial contribution
 */
@NonNullByDefault
@Component(service = { ProfileFactory.class, ProfileTypeProvider.class, PfinkProfilesFactory.class })
public class PfinkProfilesFactory implements ProfileFactory, ProfileTypeProvider {

    private static final TriggerProfileType RAWROCKER_ON_OFF_TYPE = ProfileTypeBuilder
            .newTrigger(UID_ROCKER_TO_ON_OFF, "Raw Rocker To On Off")
            .withSupportedItemTypes(CoreItemFactory.SWITCH, CoreItemFactory.DIMMER, CoreItemFactory.COLOR)
            .withSupportedChannelTypeUIDs(DefaultSystemChannelTypeProvider.SYSTEM_RAWROCKER.getUID()).build();

    private static final TriggerProfileType BUTTON_TOGGLE_SWITCH_TYPE = ProfileTypeBuilder
            .newTrigger(UID_BUTTON_TOGGLE_SWITCH, "Button Toggle Switch")
            .withSupportedItemTypes(CoreItemFactory.SWITCH, CoreItemFactory.DIMMER, CoreItemFactory.COLOR)
            .withSupportedChannelTypeUIDs(DefaultSystemChannelTypeProvider.SYSTEM_BUTTON.getUID()).build();

    @Override
    public @Nullable Profile createProfile(ProfileTypeUID profileTypeUID, ProfileCallback callback,
            ProfileContext profileContext) {
        if (profileTypeUID.equals(UID_ROCKER_TO_ON_OFF)) {
            return new RawRockerOnOffProfile(callback);
        } else if (profileTypeUID.equals(UID_BUTTON_TOGGLE_SWITCH)) {
            return new ButtonToggleSwitchProfile(callback, profileContext);
        }
        return null;
    }

    @Override
    public Collection<ProfileTypeUID> getSupportedProfileTypeUIDs() {
        return Set.of(UID_ROCKER_TO_ON_OFF, UID_BUTTON_TOGGLE_SWITCH);
    }

    @Override
    public Collection<ProfileType> getProfileTypes(@Nullable Locale locale) {
        return Set.of(RAWROCKER_ON_OFF_TYPE, BUTTON_TOGGLE_SWITCH_TYPE);
    }
}
