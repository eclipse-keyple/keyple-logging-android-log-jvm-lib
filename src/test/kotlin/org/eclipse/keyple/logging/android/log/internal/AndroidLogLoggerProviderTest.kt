/* **************************************************************************************
 * Copyright (c) 2026 Calypso Networks Association https://calypsonet.org/
 *
 * See the NOTICE file(s) distributed with this work for additional information
 * regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License 2.0 which is available at http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 ************************************************************************************** */
package org.eclipse.keyple.logging.android.log.internal

import java.util.ServiceLoader
import org.eclipse.keyple.core.util.logging.spi.LoggerProvider
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class AndroidLogLoggerProviderTest {

  @Test
  fun shouldCreateNonNullLogger() {
    val provider = AndroidLogLoggerProvider()

    val logger = provider.getLogger("TestTag")

    assertNotNull(logger)
    assertTrue(logger is AndroidLogLogger)
  }

  @Test
  fun shouldSupportAnyLoggerName() {
    val provider = AndroidLogLoggerProvider()

    assertNotNull(provider.getLogger("A"))
    assertNotNull(provider.getLogger("VeryLongLoggerName"))
  }

  @Test
  fun shouldBeDiscoverableViaServiceLoader() {
    val providers = ServiceLoader.load(LoggerProvider::class.java).toList()

    assertTrue(providers.any { it is AndroidLogLoggerProvider })
  }
}
