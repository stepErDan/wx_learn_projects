const config = require("../config.js")
var host = config.host;

module.exports.login = login;

/**
 * 登录方法
 */
function login(){
    // 登录
    wx.login({
      success: res => {
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
              registe(openId);
            }else{
              wx.setStorageSync('userInfo', userInfo);
            }
            //再调用获得token
            getTokenByOpenid(openId);
          }
        })
      }
    })
}
/**
 * 根据openid获取token
 * @param {*} openId 
 */
function getTokenByOpenid(){
  wx.request({
    url: host + '/rest/login/getTokenByUser',
    method : "POST",
    data : wx.getStorageSync('userInfo'),
    success : res2 => {
      wx.setStorageSync('token', res2.data.data);
    }
  })
}
/**
 * 注册用户
 * @param {*} openId 
 */
function registe(openId){
  // 获取用户信息
  // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
  wx.getUserInfo({
    success: res => {
      wx.request({
        url: host + '/rest/login/registe',
        method : "POST",
        data : res.userInfo,
        header: {
          'wx-openid' : openId
        },
        success : res2 => {
          if(res2.data.code != 200){
            console.log("registe:err");
          }
        }
      })
    }
  });
}