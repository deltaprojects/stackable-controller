package controllers.stack

import play.api.mvc.{AbstractController, Result}
import jp.t2v.lab.play2.stackc.{RequestWithAttributes, StackableController}
import play.api.Logger

trait LoggingElement extends StackableController {
    self: AbstractController =>

  override def cleanupOnSucceeded[A](req: RequestWithAttributes[A], res: Option[Result]): Unit = {
      res.map { result =>
        Logger.debug(Array(result.header.status, req.toString(), req.body).mkString("\t"))
      }
      super.cleanupOnSucceeded(req, res)
  }

}

