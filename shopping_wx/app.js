//app.js
var config = require("./config.js");
var login = require("./utils/login.js");

App({
  host : config.host,
  onLaunch: function () {
    //先进行授权
    
    // 获取token
    var token = wx.getStorage('token');
    //判断token是否为空，若token为空，进行登录
    if(token == ''){
      login.login;
    }

    // 获取用户信息
    var userInfo = wx.getStorageSync('userInfo');

  },
  globalData: {
    userInfo: null
  }
})