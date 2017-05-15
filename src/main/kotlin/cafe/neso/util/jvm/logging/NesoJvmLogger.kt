package cafe.neso.util.jvm.logging

import cafe.neso.util.core.extension.Bool
import cafe.neso.util.core.logging.NesoLogger
import java.util.logging.Level.*
import java.util.logging.Logger

/**
 * Created by moltendorf on 2017-05-09.
 */

class NesoJvmLogger(val logger: Logger, override val testEnabled: Bool): NesoLogger {
  override val fineEnabled = logger.isLoggable(FINE)
  override val infoEnabled = logger.isLoggable(INFO)
  override val warningEnabled = logger.isLoggable(WARNING)

  override fun fine(vararg any: Any?) = logger.fine("$any")
  override fun info(vararg any: Any?) = logger.info("$any")
  override fun warning(vararg any: Any?) = logger.warning("$any")
  override fun severe(vararg any: Any?) = logger.severe("$any")
  override fun trace(thrown: Throwable, vararg any: Any?) = logger.log(SEVERE, "$any", thrown)
  override fun test(vararg any: Any?) = logger.fine("$any")
}
