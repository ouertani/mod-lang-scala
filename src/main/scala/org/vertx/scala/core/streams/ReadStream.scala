/*
 * Copyright 2011-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.vertx.scala.core.streams

import org.vertx.java.core.buffer.Buffer
import org.vertx.java.core.streams.{ ReadStream => JReadStream }
import org.vertx.scala.core.Delegator

/**
 * @author swilliams
 * @author Ranie Jade Ramiso
 * @author Edgar Chan
 */
trait ReadStream[I <: JReadStream[Any]] extends ExceptionSupport[I] { self: Delegator[I] =>

  import org.vertx.scala.core.FunctionConverters._

  def dataHandler(handler: Buffer => Unit): ReadStream.this.type = {
    internal.dataHandler(handler)
    this
  }

  def endHandler(handler: () => Unit): ReadStream.this.type = {
    internal.endHandler(handler)
    this
  }

  def pause(): ReadStream.this.type = {
    internal.pause
    this
  }

  def resume(): ReadStream.this.type = {
    internal.resume
    this
  }

  def unwrap(): I = this.internal
}