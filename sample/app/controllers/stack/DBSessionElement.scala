package controllers.stack

import play.api.mvc.{AbstractController, Result}
import scalikejdbc._
import jp.t2v.lab.play2.stackc.{RequestAttributeKey, RequestWithAttributes, StackableController}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait DBSessionElement extends StackableController {
    self: AbstractController =>

  case object DBSessionKey extends RequestAttributeKey[DBSession]

  override def proceed[A](req: RequestWithAttributes[A])(f: RequestWithAttributes[A] => Future[Result]): Future[Result] = {
    import TxBoundary.Future._
    DB.localTx { session =>
      super.proceed(req.set(DBSessionKey, session))(f)
    }
  }

  implicit def dbSession[A](implicit req: RequestWithAttributes[A]): DBSession = req.get(DBSessionKey).get // throw

}
