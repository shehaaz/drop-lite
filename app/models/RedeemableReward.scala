package models

import com.fasterxml.jackson.annotation.JsonProperty

//Assume that client already verified that this offer is redeemable
case class RedeemableReward(@JsonProperty("userId") userId: Int, @JsonProperty("rewardId") rewardId: Int)
