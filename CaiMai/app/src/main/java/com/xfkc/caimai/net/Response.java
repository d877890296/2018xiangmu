package com.xfkc.caimai.net;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by LK on 2017/5/9 13:51.
 */

public class Response implements Serializable {
  //public static final String SUCCESS = "000000";
//  @SerializedName("RSPCOD")
//  private String rspCod;
//  @SerializedName("RSPMSG")
//  private String rspMsg;

  @SerializedName("retCode")
  private String rspCod;
  @SerializedName("message")
  private String rspMsg;


  public String getRspCod() {
    return rspCod;
  }

  public void setRspCod(String rspCod) {
    this.rspCod = rspCod;
  }

  public String getRspMsg() {
    return rspMsg;
  }

  public void setRspMsg(String rspMsg) {
    this.rspMsg = rspMsg;
  }


}
