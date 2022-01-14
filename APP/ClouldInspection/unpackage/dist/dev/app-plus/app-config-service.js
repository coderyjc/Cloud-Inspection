
var isReady=false;var onReadyCallbacks=[];
var isServiceReady=false;var onServiceReadyCallbacks=[];
var __uniConfig = {"pages":["pages/index/index","pages/home/home","pages/home/login","pages/home/settings","pages/home/history","pages/damage/postDamage","pages/task/process","pages/damage/damageDetail","pages/task/result/completeRepair","pages/task/operation/submitTask","pages/task/operation/cancelTask","pages/task/result/cancelRepair","pages/task/operation/delayTask","pages/task/task","pages/task/record","pages/damage/alert","pages/task/today/outOfTime","pages/task/today/ongoing","pages/task/today/complete","uview-ui/components/u-avatar-cropper/u-avatar-cropper","pages/damage/inspect","pages/task/today/checking"],"window":{"navigationBarTitleText":"uni-app","navigationBarBackgroundColor":"#3DB0FC","navigationBarTextStyle":"white","backgroundColor":"#F8F8F8"},"tabBar":{"color":"#7A7E83","selectedColor":"#5098ff","borderStyle":"white","backgroundColor":"#F8F8F8","list":[{"pagePath":"pages/index/index","iconPath":"static/icon/home.png","selectedIconPath":"static/icon/home-active.png","text":"首页"},{"pagePath":"pages/task/task","iconPath":"static/icon/list.png","selectedIconPath":"static/icon/list-active.png","text":"任务"},{"pagePath":"pages/home/home","iconPath":"static/icon/member.png","selectedIconPath":"static/icon/member-active.png","text":"个人中心"}]},"nvueCompiler":"uni-app","nvueStyleCompiler":"weex","renderer":"auto","splashscreen":{"alwaysShowBeforeRender":true,"autoclose":false},"appname":"云巡检-demo","compilerVersion":"3.3.5","entryPagePath":"pages/index/index","networkTimeout":{"request":60000,"connectSocket":60000,"uploadFile":60000,"downloadFile":60000}};
var __uniRoutes = [{"path":"/pages/index/index","meta":{"isQuit":true,"isTabBar":true},"window":{"navigationBarTitleText":"云巡检"}},{"path":"/pages/home/home","meta":{"isQuit":true,"isTabBar":true},"window":{"navigationBarTitleText":"个人中心"}},{"path":"/pages/home/login","meta":{},"window":{"navigationBarTitleText":"登录"}},{"path":"/pages/home/settings","meta":{},"window":{"navigationBarTitleText":"设置"}},{"path":"/pages/home/history","meta":{},"window":{"navigationBarTitleText":"历史任务"}},{"path":"/pages/damage/postDamage","meta":{},"window":{"navigationBarTitleText":"报告损伤","enablePullDownRefresh":false}},{"path":"/pages/task/process","meta":{},"window":{"navigationBarTitleText":"工作进度","enablePullDownRefresh":false}},{"path":"/pages/damage/damageDetail","meta":{},"window":{"navigationBarTitleText":"损伤详情","enablePullDownRefresh":false}},{"path":"/pages/task/result/completeRepair","meta":{},"window":{"navigationBarTitleText":"任务已完成","enablePullDownRefresh":false}},{"path":"/pages/task/operation/submitTask","meta":{},"window":{"navigationBarTitleText":"提交任务","enablePullDownRefresh":false}},{"path":"/pages/task/operation/cancelTask","meta":{},"window":{"navigationBarTitleText":"取消任务","enablePullDownRefresh":false}},{"path":"/pages/task/result/cancelRepair","meta":{},"window":{"navigationBarTitleText":"放弃任务","enablePullDownRefresh":false}},{"path":"/pages/task/operation/delayTask","meta":{},"window":{"navigationBarTitleText":"申请延时","enablePullDownRefresh":false}},{"path":"/pages/task/task","meta":{"isQuit":true,"isTabBar":true},"window":{"navigationBarTitleText":"任务列表","enablePullDownRefresh":false}},{"path":"/pages/task/record","meta":{},"window":{"navigationBarTitleText":"工单汇总"}},{"path":"/pages/damage/alert","meta":{},"window":{"navigationBarTitleText":"损伤报警"}},{"path":"/pages/task/today/outOfTime","meta":{},"window":{"navigationBarTitleText":"超时工单"}},{"path":"/pages/task/today/ongoing","meta":{},"window":{"navigationBarTitleText":"待提交"}},{"path":"/pages/task/today/complete","meta":{},"window":{"navigationBarTitleText":"已完成"}},{"path":"/uview-ui/components/u-avatar-cropper/u-avatar-cropper","meta":{},"window":{"navigationBarTitleText":"头像裁剪","navigationBarBackgroundColor":"#000000"}},{"path":"/pages/damage/inspect","meta":{},"window":{"navigationBarTitleText":"巡检","enablePullDownRefresh":false}},{"path":"/pages/task/today/checking","meta":{},"window":{"navigationBarTitleText":"正在审核","enablePullDownRefresh":false}}];
__uniConfig.onReady=function(callback){if(__uniConfig.ready){callback()}else{onReadyCallbacks.push(callback)}};Object.defineProperty(__uniConfig,"ready",{get:function(){return isReady},set:function(val){isReady=val;if(!isReady){return}const callbacks=onReadyCallbacks.slice(0);onReadyCallbacks.length=0;callbacks.forEach(function(callback){callback()})}});
__uniConfig.onServiceReady=function(callback){if(__uniConfig.serviceReady){callback()}else{onServiceReadyCallbacks.push(callback)}};Object.defineProperty(__uniConfig,"serviceReady",{get:function(){return isServiceReady},set:function(val){isServiceReady=val;if(!isServiceReady){return}const callbacks=onServiceReadyCallbacks.slice(0);onServiceReadyCallbacks.length=0;callbacks.forEach(function(callback){callback()})}});
service.register("uni-app-config",{create(a,b,c){if(!__uniConfig.viewport){var d=b.weex.config.env.scale,e=b.weex.config.env.deviceWidth,f=Math.ceil(e/d);Object.assign(__uniConfig,{viewport:f,defaultFontSize:Math.round(f/20)})}return{instance:{__uniConfig:__uniConfig,__uniRoutes:__uniRoutes,global:void 0,window:void 0,document:void 0,frames:void 0,self:void 0,location:void 0,navigator:void 0,localStorage:void 0,history:void 0,Caches:void 0,screen:void 0,alert:void 0,confirm:void 0,prompt:void 0,fetch:void 0,XMLHttpRequest:void 0,WebSocket:void 0,webkit:void 0,print:void 0}}}});
