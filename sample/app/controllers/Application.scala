package controllers

import controllers.stack._
import models._
import views._
import play.api.mvc._

class Application (components: ControllerComponents)(implicit assetsFinder: AssetsFinder) extends AbstractController(components) with DBSessionElement with LoggingElement {
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def messages = StackAction { implicit req =>
    val messages = Message.findAll
    Ok(views.html.messages(messages))
  }

  def editMessage(id: MessageId) = StackAction { implicit req =>
    val messages = Message.findAll
    Ok(views.html.messages(messages))
  }

}
