function extInit() {
  Ext.BLANK_IMAGE_URL = context + 'js/ext/resources/images/default/s.gif';
  Ext.form.Field.prototype.msgTarget = 'side';
  Ext.QuickTips.init();
}
function requestUrl(cfg) {
  var url = cfg.url;
  var param = cfg.param || "";
  var method = cfg.method || "POST";
  var okFn = cfg.okFn || function(rs) {
    alert("��ц��������");
  };
  var errorFn = cfg.errorFn || function() {
    alert("杩���ユ����″�ㄥ�洪��");
  }
  Ext.Ajax.request(
  {
    url: url,
    params:param,
    method:method,
    success:okFn ,
    failure:errorFn
  });
}

function openWin(url,status,params){
  var w=window.open(url,"",status);
  Ext.apply(w,params);
  return w;
}