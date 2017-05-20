package cafe.neso.jvm.logging

import cafe.neso.core.logging.NesoLogger
import java.util.logging.Level.SEVERE
import java.util.logging.Logger

/**
 * Created by moltendorf on 2017-05-09.
 */

class NesoJvmLogger(val logger: Logger): NesoLogger {
  override fun fine(vararg any: Any?) = logger.fine("$any")
  override fun info(vararg any: Any?) = logger.info("$any")
  override fun warning(vararg any: Any?) = logger.warning("$any")
  override fun severe(vararg any: Any?) = logger.severe("$any")
  override fun trace(thrown: Throwable, vararg any: Any?) = logger.log(SEVERE, "$any", thrown)
  override fun test(vararg any: Any?) = logger.fine("$any")
}
