//app.js
var config = require("./config.js");
var login = require("./utils/login.js");

App({
  host : config.host,
  onLaunch: function () {
    // 获取token
    var token = wx.getStorageSync('token');
    //判断token是否为空，若token为空，进行登录
    if(token == ''){
      login.login;
    }

    // 获取用户信息
    var userInfo = wx.getStorageSync('userInfo');

    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
    })
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo

              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })
  },
  globalData: {
    userInfo: null
  }
})