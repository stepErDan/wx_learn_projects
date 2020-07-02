//index.js
//获取应用实例
const app = getApp()
var login = require("../../utils/login.js");

Page({
  data: {
    show: false,
    value:"",
  },
  onLoad: function () {
    wx.getSetting({
      success: res => {
        if(res.authSetting['scope.userInfo']){
          //已授权进行登录验证
          loginAuth();
        }else{
          //未授权开启授权
          this.setData({ show : true });
        }
      }
    })
  },
  login:function(){
    this.setData({ show : false });
    loginAuth();
  },
  onSearch:function(){

  },
  onCancel:function(){},
  onShareAppMessage() {
    return {
      title: 'swiper',
      path: 'page/component/pages/swiper/swiper'
    }
  },
})
function loginAuth(){
  // 获取token
  var token = wx.getStorageSync('token');
  //判断token是否为空，若token为空，进行登录
  if(token == ''){
    login.login;
  }
}
