//index.js
//获取应用实例
const app = getApp()
var login = require("../../utils/login.js");

Page({
  data: {
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    show: false,
  },
  onLoad: function () {
    wx.getSetting({
      success: res => {
        if(!res.authSetting['scope.userInfo']){
          this.setData({ show: true });
        }
      }
    })

    // 获取token
    var token = wx.getStorageSync('token');
    //判断token是否为空，若token为空，进行登录
    if(token == ''){
      
    }

    // 获取用户信息
    var userInfo = wx.getStorageSync('userInfo');
  }
})
