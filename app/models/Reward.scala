package models

import com.fasterxml.jackson.annotation.JsonProperty

case class Reward(@JsonProperty("merchant") merchant: String,
             @JsonProperty("pointsNeeded") pointsNeeded: Int,
             @JsonProperty("reward") reward: String,
             @JsonProperty("id") id: Int)
