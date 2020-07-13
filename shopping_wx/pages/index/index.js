//index.js
//获取应用实例
const app = getApp()
var login = require("../../utils/login.js");

Page({
  data: {
    show: false,
    value:"",
    swiperCon: {
      imgUrls: ['demo-text-1', 'demo-text-2', 'demo-text-3'],
      indicatorDots: true,//是否显示面板指示点
      indicatorColor: "rgba(0, 0, 0, .3)",//指示点颜色
      indicatorActiveColor: "#007aff",//当前选中的指示点颜色
      autoplay: true,//是否自动切换
      interval: 5000,//自动切换时间间隔
      duration: 1000,//滑动动画时长
      circular: true,//是否采用衔接滑动
    }
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
