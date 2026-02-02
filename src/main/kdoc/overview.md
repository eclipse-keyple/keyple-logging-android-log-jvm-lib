<h2>Overview</h2>
<p>
    This library provides an Android Log-based implementation of the Keyple logging API.
    It allows Keyple components to integrate seamlessly with the Android logging system (android.util.Log),
    enabling applications to leverage Android's native logging infrastructure including Logcat for debugging
    and monitoring.
</p>

<h2>Purpose</h2>
<p>
    The Keyple Logging Android Log library serves as a bridge between the Keyple logging framework and Android's
    built-in logging system. By using this implementation, developers can leverage the standard Android logging
    tools and practices to configure and manage logging across their Keyple-based Android applications,
    with full compatibility with Logcat, Android Studio's log viewer, and third-party log analysis tools.
</p>

<h2>Key Features</h2>
<ul>
    <li>Native integration with Android's logging system (android.util.Log)</li>
    <li>Automatic tag generation with 23-character limit compliance</li>
    <li>Optimized tag compression preserving class identification</li>
    <li>Full support for Android log levels (VERBOSE, DEBUG, INFO, WARN, ERROR)</li>
    <li>Seamless Logcat integration for development and debugging</li>
    <li>Consistent logging behavior across Keyple components on Android</li>
</ul>

<h2>Usage</h2>
<p>
    To use this library, simply include it as a dependency in your Android project. The Keyple logging
    framework will automatically detect and use this Android implementation. Logs will be visible in
    Logcat using the standard Android debugging tools.
</p>

<h2>Tag Management</h2>
<p>
    This implementation automatically handles Android's 23-character tag limit by intelligently compressing
    fully qualified class names. For example, <code>com.mycompany.android.data.repository.UserRepository</code>
    is transformed to <code>cmadr.UserRepository</code>, preserving both package context and class identification.
</p>

<h2>Package Structure</h2>
<p>
    This library contains a single internal package:
</p>
<ul>
    <li><code>org.eclipse.keyple.logging.android.log.internal</code> - Internal implementation classes for Android Log integration</li>
</ul>

<h2>Important Notes</h2>
<p>
    <strong>Internal API:</strong> The classes in this library are internal implementation details and should
    not be used directly by client applications. They are subject to change without notice. Applications should
    interact with the Keyple logging framework through the public API provided by the core Keyple logging module.
</p>
<p>
    <strong>ProGuard/R8:</strong> Ensure that logging-related classes are properly configured in your ProGuard/R8
    rules to maintain tag generation accuracy in release builds. Add the following rules to your <code>proguard-rules.pro</code> file:
</p>

```bash
# ============================================================================
# Keyple Logging Android - ProGuard/R8 Configuration
# ============================================================================

# Service Provider Interface (SPI) - REQUIRED
-keep interface org.eclipse.keyple.core.util.logging.spi.LoggerProvider
-keep class * implements org.eclipse.keyple.core.util.logging.spi.LoggerProvider {
public <init>();
}

# Preserve class names for correct tag generation - CRITICAL
-keepnames class org.eclipse.keyple.**

# If you use logging in your own classes, preserve their names too
# -keepnames class com.yourpackage.** {
# Replace with your actual package name
# }

# Required attributes
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes InnerClasses

# ============================================================================
# Important notes:
# - Do not use -keep instead of -keepnames as it prevents optimization
# - -keepnames only preserves names, not dead code
# - Test in release mode to verify tags are readable
# ============================================================================
```