/*
 * Copyright 2013 the original author or authors.
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
package org.vertx.scala.core.file


import org.vertx.java.core.file.{AsyncFile => JAsyncFile}
import org.vertx.java.core.{file, AsyncResult, Handler}
import org.vertx.java.core.buffer.Buffer
import org.vertx.scala.core.FunctionConverters._
import org.vertx.scala.core.streams.{WriteStream, ReadStream}

/**
 * @author Edgar Chan
 * @author swilliams
 */
object AsyncFile {
  def apply(internal:JAsyncFile) = new AsyncFile(internal)
  implicit def toScala(internal:JAsyncFile) = new AsyncFile(internal)
}

class AsyncFile(internal:JAsyncFile) extends ReadStream with WriteStream {

  def dataHandler(handler: (Buffer) => Unit):AsyncFile.this.type = {
    internal.dataHandler(handler)
    this
  }

  def dataHandler(handler: Handler[Buffer]):AsyncFile.this.type = {
    internal.dataHandler(handler)
    this
  }

  def pause():AsyncFile.this.type = {
    internal.pause()
    this
  }

  def resume():AsyncFile.this.type = {
    internal.resume()
    this
  }

  def endHandler(handler: () => Unit ):AsyncFile.this.type = {
    internal.endHandler(handler)
    this
  }

  def endHandler(handler: Handler[Void]):AsyncFile.this.type = {
    internal.endHandler(handler)
    this
  }

  def write(data: Buffer):AsyncFile.this.type = {
    internal.write(data)
    this
  }

  def setWriteQueueMaxSize(max: Int):AsyncFile.this.type = {
    internal.setWriteQueueMaxSize(max)
    this
  }

  def writeQueueFull: Boolean = internal.writeQueueFull()

  def drainHandler(handler: () => Unit):AsyncFile.this.type = {
    internal.drainHandler(handler)
    this
  }

  def drainHandler(handler: Handler[Void]):AsyncFile.this.type = {
    internal.drainHandler(handler)
    this
  }

  def exceptionHandler(handler: (Throwable) => Unit ):AsyncFile.this.type = {
    internal.exceptionHandler(handler)
    this
  }

  def exceptionHandler(handler: Handler[Throwable]):AsyncFile.this.type = {
    internal.exceptionHandler(handler)
    this
  }

  def close():Unit = {
    internal.close()
  }

  def close(handler:AsyncResult[Unit] => Unit):Unit = {
    internal.close(voidAsyncHandler(handler))
  }

  def close(handler: Handler[AsyncResult[Void]]):Unit = {
    internal.close(handler)
  }


  def write(data:Buffer, p:Int, handler:AsyncResult[Unit] => Unit ):AsyncFile.this.type = {
    internal.write(data, p, voidAsyncHandler(handler))
    this
  }

  def write(buffer: Buffer, position: Int, handler: Handler[AsyncResult[Void]]):AsyncFile.this.type = {
    internal.write(buffer, position, handler)
    this
  }


  def read(buffer: Buffer, offset: Int, position: Int, length: Int, handler: AsyncResult[Buffer] => Unit):AsyncFile.this.type = {
    internal.read(buffer, offset, position, length, new Handler[AsyncResult[Buffer]] {
      def handle(event: AsyncResult[Buffer]) {
        handler(event)
      }
    })
    this
  }

  def read(buffer: Buffer, offset: Int, position: Int, length: Int, handler: Handler[AsyncResult[Buffer]]):AsyncFile.this.type = {
    internal.read(buffer, offset, position, length, handler)
    this
  }

  def flush():AsyncFile.this.type = {
    internal.flush()
    this
  }

  def flush(handler: AsyncResult[Unit] => Unit):AsyncFile.this.type = {
   flush(voidAsyncHandler(handler))
   this
  }

  def flush(handler: Handler[AsyncResult[Void]]):AsyncFile.this.type = {
    internal.flush(handler)
    this
  }
}
