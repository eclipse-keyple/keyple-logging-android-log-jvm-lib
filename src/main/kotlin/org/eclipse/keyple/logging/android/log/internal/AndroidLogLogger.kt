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

import android.util.Log
import org.eclipse.keyple.core.util.logging.Logger

/**
 * An implementation of the [org.eclipse.keyple.core.util.logging.Logger] interface that delegates
 * all logging operations to Android Log.
 *
 * @since 1.0.0
 */
internal class AndroidLogLogger(className: String) : Logger {

  private val safeTag = generateTag(className)

  private companion object {

    private const val MAX_TAG_LENGTH = 23

    /**
     * Generates a tag for logging that complies with Android's maximum length constraint.
     *
     * <p>Android log tags have a maximum length of 23 characters. This function shortens the
     * provided `fullClassName` if it exceeds this limit.
     *
     * <p>The shortening strategy is as follows:
     * <ul>
     * <li>If the class name is already short enough, it is returned as is.
     * <li>If it's too long and contains a package name, the package name is compressed by taking
     *   only the first letter of each part (e.g., `org.eclipse.keyple` becomes `oek`).
     * <li>The final tag is formed by concatenating the compressed package and the simple class
     *   name, then truncating it to the maximum allowed length if necessary.
     * <li>If there's no package name, the `fullClassName` is simply truncated to the maximum
     *   length.
     * </ul>
     *
     * @param fullClassName The fully qualified class name to be used as a base for the tag.
     * @return A string suitable for use as an Android log tag, no longer than 23 characters.
     */
    private fun generateTag(fullClassName: String): String {
      if (fullClassName.length <= MAX_TAG_LENGTH) {
        return fullClassName
      }
      val lastDotIndex = fullClassName.lastIndexOf('.')
      if (lastDotIndex == -1) {
        return fullClassName.take(MAX_TAG_LENGTH)
      }
      val className = fullClassName.substring(lastDotIndex + 1)
      val packageName = fullClassName.take(lastDotIndex)
      val compressedPackage = packageName.split(".").joinToString("") { it.first().toString() }
      return "$compressedPackage.$className".take(MAX_TAG_LENGTH)
    }

    /**
     * Formats a log message by replacing placeholders with the provided arguments.
     *
     * This function takes a template string with placeholders in the form of `{}` and an array of
     * arguments. It iterates through the arguments and sequentially replaces each `{}` in the
     * template with the string representation of the corresponding argument. If an argument is
     * `null`, it is replaced with the string "null".
     *
     * This mimics the behavior of popular logging frameworks like SLF4J for message formatting.
     *
     * @param template The message template containing `{}` placeholders.
     * @param args The arguments to be inserted into the template.
     * @return The formatted message string.
     */
    private fun formatMessage(template: String, args: Array<out Any?>): String {
      var result = template
      args.forEach { result = result.replaceFirst("{}", it?.toString() ?: "null") }
      return result
    }
  }

  override fun isTraceEnabled() = Log.isLoggable(safeTag, Log.VERBOSE)

  override fun isDebugEnabled() = Log.isLoggable(safeTag, Log.DEBUG)

  override fun isInfoEnabled() = Log.isLoggable(safeTag, Log.INFO)

  override fun isWarnEnabled() = Log.isLoggable(safeTag, Log.WARN)

  override fun isErrorEnabled() = Log.isLoggable(safeTag, Log.ERROR)

  override fun trace(message: String, vararg args: Any?) {
    if (isTraceEnabled()) {
      Log.v(safeTag, formatMessage(message, args))
    }
  }

  override fun debug(message: String, vararg args: Any?) {
    if (isDebugEnabled()) {
      Log.d(safeTag, formatMessage(message, args))
    }
  }

  override fun info(message: String, vararg args: Any?) {
    if (isInfoEnabled()) {
      Log.i(safeTag, formatMessage(message, args))
    }
  }

  override fun warn(message: String, vararg args: Any?) {
    if (isWarnEnabled()) {
      Log.w(safeTag, formatMessage(message, args))
    }
  }

  override fun error(message: String, vararg args: Any?) {
    if (isErrorEnabled()) {
      Log.e(safeTag, formatMessage(message, args))
    }
  }

  override fun warn(message: String, throwable: Throwable?, vararg args: Any?) {
    if (isWarnEnabled()) {
      Log.w(safeTag, formatMessage(message, args), throwable)
    }
  }

  override fun error(message: String, throwable: Throwable?, vararg args: Any?) {
    if (isErrorEnabled()) {
      Log.e(safeTag, formatMessage(message, args), throwable)
    }
  }
}
