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
      if(res.authSetting['scope.userInfo']){
        console.log(2);
        // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
        wx.getUserInfo({
          success: res => {
            console.log(res.userInfo);
            
          }
        })
      }
    }
  })
}