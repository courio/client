package spec

import io.cour.client.CourioClient
import org.scalatest.Matchers
import org.scalatest.wordspec.AsyncWordSpec
import io.youi.net._

class UserClientSpec extends AsyncWordSpec with Matchers {
  "User client" should {
    lazy val client = new CourioClient("userClientSpec", url"http://localhost:8888/communication")
    "connect the client" in {
      client.connect().map { _ =>
        succeed
      }
    }
    "register a user" in {
      client.comm.user.updateProfile("test-user", "Test User").map {
        case Left(e) => fail(e.message)
        case Right(p) => p.username should be("test-user")
      }
    }
    "set a password" in {
      client.comm.user.changePassword(None, "password").map {
        case Left(e) => fail(e.message)
        case Right(_) => succeed
      }
    }
    "authenticate" in {
      client.comm.user.logIn("test-user", "password").map {
        case Left(e) => fail(e.message)
        case Right(p) => p.username should be("test-user")
      }
    }
    "get profile" in {
      client.comm.user.profile().map { p =>
        p.username should be("test-user")
      }
    }
    "disconnect the client" in {
      client.dispose()
      succeed
    }
  }
}
