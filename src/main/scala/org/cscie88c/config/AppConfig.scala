/** Configuration utils
  */

package org.cscie88c.config

import pureconfig.ConfigReader
import pureconfig.ConfigSource

import scala.reflect.ClassTag

// config classes
case class SignatureSettings(pkfile: String, keyPairId: String)
case class SimpleApp2Config(filename: String, month: String)
case class CookieSettings(
    domain: String,
    path: String,
    ttl: Int
  )
case class AppSettings(
    cookie: CookieSettings,
    signature: SignatureSettings,
    simpleApp2: SimpleApp2Config
  )

object ConfigUtils {

  /** loads a configuration case class
    */
  def loadAppConfig[A: ConfigReader: ClassTag](path: String): A =
    ConfigSource.default.at(path).loadOrThrow[A]
}
