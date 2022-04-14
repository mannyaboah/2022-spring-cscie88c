/** main entry point for application
  */

package org.cscie88c

import org.cscie88c.config.{ ConfigUtils, CookieSettings, SimpleApp2Config }

import pureconfig.generic.auto._
import com.typesafe.scalalogging.{ LazyLogging }

object MainApp extends LazyLogging {

  val COOKIE_CONFIG_PATH = "org.cscie88c.cookie"
  val SIMPLE_APP_CONF_PATH = "org.cscie88c.simpleApp2"

  def hello(
      name: String
    )(implicit
      conf: CookieSettings,
      simpleAppConf: SimpleApp2Config
    ): String = {
    logger.info(
      s"running application version with ttl: ${conf.ttl}. \nApp properties: " +
        s"fileName: ${simpleAppConf.filename}"
    )
    s"Hello ${name}"
  }

  def main(args: Array[String]): Unit = {

    implicit val cookie =
      ConfigUtils.loadAppConfig[CookieSettings](COOKIE_CONFIG_PATH)

    implicit val simpleApp2 =
      ConfigUtils.loadAppConfig[SimpleApp2Config](SIMPLE_APP_CONF_PATH)

    val name = args.length match {
      case 0 => "Anonymous"
      case _ => args(0)
    }
    val message = hello(name)
    println(message)
  }
}

