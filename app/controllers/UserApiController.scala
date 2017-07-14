package controllers

import api._
import io.swagger.annotations._
import io.swagger.util.Json
import models.{Offer, RedeemableReward, Reward, UpdateUserPoints}
import play.api.mvc._

@Api(value = "/user", description = "Operations about user")
class UserApiController extends BaseApiController {
  var userData = new UserData

  @ApiOperation(nickname = "getProfile",
    value = "Get user by user id", response = classOf[models.User], httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Invalid userId supplied"),
    new ApiResponse(code = 404, message = "User not found")))
  def getProfile(@ApiParam(value = "The user that needs to be fetched.", required = true) userId: Int) = Action { implicit request =>
    userData.findUserById(userId) match {
      case Some(user) => JsonResponse(user)
      case None => JsonResponse(new value.ApiResponse(400, "Invalid input"))
    }
  }

  @ApiOperation(nickname = "getOffers",
    value = "Get user offers by userId", response = classOf[List[Offer]], httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Invalid username supplied"),
    new ApiResponse(code = 404, message = "User not found")))
  def getOffers(@ApiParam(value = "", required = true) userId: Int) = Action { implicit request =>
    userData.getUserOffers(userId) match {
      case Some(offerList) => JsonResponse(offerList)
      case None => JsonResponse(new value.ApiResponse(400, "Invalid input"))
    }
  }

  @ApiOperation(nickname = "addOffer",
    value = "", response = classOf[List[Offer]], httpMethod = "POST")
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "", value = "", required = true, dataType = "models.Offer", paramType = "body")))
  def addOffer(@ApiParam(value = "", required = true) userId: Int) = Action { implicit request =>
    request.body.asJson match {
      case Some(e) => {
        val offer = Json.mapper.readValue(e.toString, classOf[Offer])
        userData.addUserOffers(userId, offer) match {
          case Some(offerList) => JsonResponse(offerList)
          case None => JsonResponse(new value.ApiResponse(400, "Invalid input"))
        }
      }
      case None => JsonResponse(new value.ApiResponse(400, "Invalid input"))
    }
  }

  @ApiOperation(nickname = "getRewards",
    value = "Get user rewards", response = classOf[List[models.Reward]], httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Invalid username supplied"),
    new ApiResponse(code = 404, message = "User not found")))
  def getRewards(@ApiParam(value = "", required = true) userId: Int) = Action { implicit request =>
    userData.getUserRewards(userId) match {
      case Some(rewardList) => JsonResponse(rewardList)
      case None => JsonResponse(new value.ApiResponse(400, "Invalid input"))
    }
  }

  @ApiOperation(nickname = "addReward",
    value = "", response = classOf[List[Offer]], httpMethod = "POST")
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "", value = "", required = true, dataType = "models.Reward", paramType = "body")))
  def addReward(@ApiParam(value = "", required = true) userId: Int) = Action { implicit request =>
    request.body.asJson match {
      case Some(e) => {
        val reward = Json.mapper.readValue(e.toString, classOf[Reward])
        userData.addUserReward(userId, reward) match {
          case Some(offerList) => JsonResponse(offerList)
          case None => JsonResponse(new value.ApiResponse(400, "Invalid input"))
        }
      }
      case None => JsonResponse(new value.ApiResponse(400, "Invalid input"))
    }
  }

  @ApiOperation(nickname = "redeemReward",
    value = "", response = classOf[models.User], httpMethod = "POST")
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "", value = "", required = true, dataType = "models.RedeemableReward", paramType = "body")))
  def redeemReward = Action { implicit request =>
    request.body.asJson match {
      case Some(e) => {
        val redeemableReward = Json.mapper.readValue(e.toString, classOf[RedeemableReward])
        userData.redeemOffer(redeemableReward) match {
          case Some(user) => JsonResponse(user)
          case None => JsonResponse(new value.ApiResponse(400, "Invalid input"))
        }
      }
      case None => JsonResponse(new value.ApiResponse(400, "Invalid input"))
    }
  }

  @ApiOperation(nickname = "updatePoints",
    value = "", response = classOf[models.User], httpMethod = "POST")
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "", value = "", required = true, dataType = "models.UpdateUserPoints", paramType = "body")))
  def updatePoints = Action { implicit request =>
    request.body.asJson match {
      case Some(e) => {
        val updateUserPoints = Json.mapper.readValue(e.toString, classOf[UpdateUserPoints])
        userData.updateUserPoints(updateUserPoints) match {
          case Some(user) => JsonResponse(user)
          case None => JsonResponse(new value.ApiResponse(400, "Invalid input"))
        }
      }
      case None => JsonResponse(new value.ApiResponse(400, "Invalid input"))
    }
  }
}

object UserApiController {}
