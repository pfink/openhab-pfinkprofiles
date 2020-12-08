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
import org.openhab.core.thing.profiles.ProfileTypeUID;

/**
 * The {@link PfinkProfilesBindingConstants} class defines common constants, which are
 * used across the whole binding.
 *
 * @author Patrick Fink - Initial contribution
 */
@NonNullByDefault
public class PfinkProfilesBindingConstants {

    public static final String BINDING_ID = "pfinkprofiles";

    // List of all Profile Type UIDs
    public static final ProfileTypeUID UID_ROCKER_TO_ON_OFF = new ProfileTypeUID(
            PfinkProfilesBindingConstants.BINDING_ID, "rocker-to-on-off");
}
