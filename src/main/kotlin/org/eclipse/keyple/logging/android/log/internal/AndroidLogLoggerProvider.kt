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

import org.eclipse.keyple.core.util.logging.Logger
import org.eclipse.keyple.core.util.logging.spi.LoggerProvider

/**
 * Provides an implementation of the [org.eclipse.keyple.core.util.logging.spi.LoggerProvider]
 * interface that creates loggers leveraging the Android native logging framework.
 *
 * <p>This implementation is intended to standardize the creation of logger instances while
 * utilizing Android Log for actual logging operations, ensuring compatibility with Android-based
 * logging systems.
 *
 * @since 1.0.0
 */
class AndroidLogLoggerProvider : LoggerProvider {

  override fun getLogger(className: String): Logger {
    return AndroidLogLogger(className)
  }
}
