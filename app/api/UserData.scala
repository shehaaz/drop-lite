package api

import models.{Offer, Reward, _}

import collection.mutable.ListBuffer
import scala.collection.mutable

class UserData {
  val users: ListBuffer[User] = new ListBuffer[User]()

  {
    users += User(id = 1, points = 1)
    users += User(id = 2, points = 1)
  }

  val offers: ListBuffer[Offer] = new ListBuffer[Offer]()

  {
    offers += Offer(1, "Lululemon", 3000, "spend at least 1$", true)
    offers += Offer(2, "Amazon", 2000, "spend at least 1$", true)
    offers += Offer(3, "Walmart", 1000, "spend at least 1$", true)
  }

  val offerMap = new mutable.HashMap[Int, List[Offer]]()
  offerMap.put(1, offers.toList)

  val rewards: ListBuffer[Reward] = new ListBuffer[Reward]()

  {
    rewards += Reward("Starbucks", 5000, "5$ reward", 1)
    rewards += Reward("H&M", 10000, "10$ reward", 2)
    rewards += Reward("Walmart", 5000, "5$ reward", 3)
  }

  val rewardsMap = new mutable.HashMap[Int, List[Reward]]()
  rewardsMap.put(1, rewards.toList)


  def findUserById(id: Int): Option[User] = {
    users.find(user => user.id == id)
  }

  def getUserOffers(id: Int): Option[List[Offer]] = {
    offerMap.get(id)
  }

  def addUserOffers(id: Int, newOffer: Offer): Option[List[Offer]] = {
    offerMap.put(id, offerMap.getOrElseUpdate(id, List(newOffer)).filterNot(o => o.id == newOffer.id) ++ List(newOffer))
    offerMap.get(id)
  }

  def getUserRewards(id: Int): Option[List[Reward]] = {
    rewardsMap.get(id)
  }

  def addUserReward(id: Int, newReward: Reward): Option[List[Reward]] = {
    rewardsMap.put(id, rewardsMap.getOrElseUpdate(id, List(newReward)).filterNot(o => o.id == newReward.id) ++ List(newReward))
    rewardsMap.get(id)
  }

  def redeemOffer(redeemableReward: RedeemableReward): Option[User] = {
    getUserRewards(redeemableReward.userId) match {
      case Some(rewardList) => {
        val reward = rewardList.find(r => r.id == redeemableReward.rewardId).get
        val user = findUserById(redeemableReward.userId).get
        updateUserPoints(UpdateUserPoints(redeemableReward.userId, user.points - reward.pointsNeeded))
        rewardsMap.put(redeemableReward.userId, rewardList.filterNot(r => r.id == redeemableReward.rewardId))
        findUserById(user.id)
      }
      case None => None
    }
  }

  def updateUserPoints(updateUserPoints: UpdateUserPoints): Option[User] = {
    val user = User(id = updateUserPoints.userId, points = updateUserPoints.points)
    addUser(user)
    Some(user)
  }

  def addUser(user: User): Unit = {
    users --= users.filter(u => u.id == user.id)
    users += user
  }

  def removeUser(user: User): Unit = {
    for (u <- users) {
      if (u.id == user) {
        users -= user
      }
    }
  }
}
