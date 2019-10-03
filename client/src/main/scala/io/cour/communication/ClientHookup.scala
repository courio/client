package io.cour.communication

import com.outr.hookup.{Hookup, HookupSupport}

import scribe.Execution.global

trait ClientHookup extends Hookup {
  val user: UserCommunication with HookupSupport = auto[UserCommunication]
  val message: MessageCommunication with HookupSupport = auto[MessageCommunication]
}