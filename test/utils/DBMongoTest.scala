package utils

import org.specs2.mutable._
import play.api.test.MockApplication._
import utils.DBMongo._
import com.mongodb.casbah.{MongoCollection, MongoDB}

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 17/12/11
 * Time: 21:56
 */
object DBMongoTest extends Specification {

  "DBMongo getConnection" should {
    "return a connection" in {
      withApplication(Nil, Map.empty) {
        getConnection() should anInstanceOf[MongoDB]
      }
    }
    "return only one connection" in {
      withApplication(Nil, Map.empty) {
        getConnection() should be equalTo getConnection()
      }
    }
  }

  "DBMongo withConnection" should {
    "call a block with a collection" in {
      withApplication(Nil, Map.empty) {
        withConnection("screws") { connection =>
          connection should anInstanceOf[MongoCollection]
        }
      }
    }
  }
}