package cafe.neso.jvm

import cafe.neso.core.Neso
import cafe.neso.core.NesoCore
import cafe.neso.core.logging.NesoLogger
import cafe.neso.core.settings.CoreSettings
import cafe.neso.jvm.logging.NesoJvmLogger
import java.util.logging.Logger

/**
 * Created by moltendorf on 2017-05-16.
 */

abstract class NesoJvm(settings: CoreSettings, logger: Logger): NesoCore(settings, NesoJvmLogger(logger))
