package io.cour.communication
import io.cour.model.MessagePreview

import scala.concurrent.Future

trait ClientMessageCommunication extends MessageCommunication {
  override def receive(message: MessagePreview): Future[Unit] = ???

  override def notify(message: MessagePreview): Future[Unit] = ???

  override def refresh(): Future[Unit] = ???
}
