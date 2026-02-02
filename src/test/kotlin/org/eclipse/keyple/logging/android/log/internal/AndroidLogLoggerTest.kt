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
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class AndroidLogLoggerTest {

  private val logger: Logger = AndroidLogLogger("TestTag")

  @Test
  fun shouldNotCrashOnTraceLog() {
    logger.trace("Trace message")
  }

  @Test
  fun shouldNotCrashOnTraceLogWithParameters() {
    logger.trace("Trace message [p1={}, p2={}]", "Hello World", 42)
  }

  @Test
  fun shouldNotCrashOnDebugLog() {
    logger.debug("Debug message")
  }

  @Test
  fun shouldNotCrashOnDebugLogWithParameters() {
    logger.debug("Debug message [p1={}, p2={}]", "Hello World", 42)
  }

  @Test
  fun shouldNotCrashOnInfoLog() {
    logger.info("Info message")
  }

  @Test
  fun shouldNotCrashOnInfoLogWithParameters() {
    logger.info("Info message [p1={}, p2={}]", "Hello World", 42)
  }

  @Test
  fun shouldNotCrashOnWarnLog() {
    logger.warn("Warn message")
  }

  @Test
  fun shouldNotCrashOnWarnLogWithParameters() {
    logger.warn("Warn message [p1={}, p2={}]", "Hello World", 42)
  }

  @Test
  fun shouldNotCrashOnWarnLogWithThrowable() {
    logger.warn("Warn message [reason={}]", RuntimeException("Boom"), "details")
  }

  @Test
  fun shouldNotCrashOnErrorLog() {
    logger.error("Error message")
  }

  @Test
  fun shouldNotCrashOnErrorLogWithParameters() {
    logger.error("Error message [p1={}, p2={}]", "Hello World", 42)
  }

  @Test
  fun shouldNotCrashOnErrorLogWithThrowable() {
    logger.error("Error message [reason={}]", RuntimeException("Boom"), "details")
  }
}
