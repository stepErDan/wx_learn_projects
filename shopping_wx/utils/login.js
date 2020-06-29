const config = require("../config.js")
var host = config.host;

module.exports = {
  login : login()
}

/**
 * 登录方法
 */
function login(){
    // 登录
    wx.login({
      success: res => {
        console.log(res);
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        wx.request({
          url: host + '/rest/login/getTokenByCode',
          method : "POST",
          data : { "str" : res.code },
          success : res2 => {
            console.log(res2);
            var openId = res2.data.msg;
            var userInfo = res2.data.data;
            //若用户信息为空，先行注册。
            if(userInfo == null){
              console.log(1);
              registe();
            }else{
              wx.setStorage('userInfo', userInfo);
            }
          }
        })
      }
    })
}
/**
 * 根据openid获取token
 * @param {*} openId 
 */
function getTokenByOpenid(openId){

}
/**
 * 注册用户
 * @param {*} openId 
 */
function registe(){
  // 获取用户信息
  wx.getSetting({
    success: res => {
      console.log(res);
      if (!res.authSetting['scope.userInfo']) {
          //弹框提示
      }
      // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
      wx.getUserInfo({
        success: res => {
          // 可以将 res 发送给后台解码出 unionId
          this.globalData.userInfo = res.userInfo
          console.log(res);
          // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
          // 所以此处加入 callback 以防止这种情况
          if (this.userInfoReadyCallback) {
            this.userInfoReadyCallback(res)
            console.log(res);
          }
        }
      })
    }
  })
}