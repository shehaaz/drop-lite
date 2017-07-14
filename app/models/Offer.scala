package models

import com.fasterxml.jackson.annotation.JsonProperty

case class Offer(
            @JsonProperty("id") id:Int,
            @JsonProperty("merchant") merchant:String,
            @JsonProperty("points") points:Int,
            @JsonProperty("offer") offer:String,
            @JsonProperty("isActive") isActive: Boolean = false)
