//app.js
var config = require("./config.js");

App({
  host : config.host,
  onLaunch: function () {

  },
  globalData: {
    userInfo: null
  },
})