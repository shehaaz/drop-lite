package models

import com.fasterxml.jackson.annotation.JsonProperty

case class UpdateUserPoints(@JsonProperty("userId") userId: Int, @JsonProperty("points") points: Int)
