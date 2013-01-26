package http

import org.vertx.scala.deploy.Verticle
import org.vertx.scala.Vertx
import org.vertx.scala.http.HttpServer
import org.vertx.scala.http.HttpServerRequest
import org.vertx.java.core.Handler
import org.vertx.scala.handlers.FunctionHandler1
import org.vertx.scala.handlers.FunctionHandler0
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


class SendFileHttpServer extends Verticle {

  var http: HttpServer = null

  @throws(classOf[Exception])
  override def start(): Unit = {
    http = vertx.createHttpServer.requestHandler({ req: HttpServerRequest =>
      println("path: " + req.path)
      req.response.sendFile("helloscala.txt")
    }).listen(8080)
  }

  @throws(classOf[Exception])
  override def stop(): Unit = {
    def latch = new CountDownLatch(1)
    def func = () => {
      latch.countDown()
    }
    http.close(func)
    latch.await(5000L, TimeUnit.MILLISECONDS)
  }


}