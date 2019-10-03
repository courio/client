package io.cour.client

import com.outr.hookup.{Hookup, HookupSupport}
import io.cour.communication.{ClientHookup, MessageCommunication, UserCommunication}
import io.youi.net._
import io.youi.client.WebSocketClient

import scala.concurrent.Future

class CourioClient(sessionId: String, url: URL = url"https://app.courio.com/communication") {
  private val connection: WebSocketClient = new WebSocketClient(url.withParam("sessionId", sessionId))
  private val hookup: ClientHookup = Hookup.client[ClientHookup]

  def connect(): Future[Unit] = connection.connect()
  def disconnect(): Unit = connection.disconnect()
  def dispose(): Unit = connection.dispose()

  object comm {
    def user: UserCommunication with HookupSupport = hookup.user
    def message: MessageCommunication with HookupSupport = hookup.message
  }
}