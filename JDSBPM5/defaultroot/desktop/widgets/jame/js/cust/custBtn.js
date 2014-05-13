Ext.namespace("Jame.custBtn");

//表情路径 对所有客户端来说应该是一样的
Jame.custBtn.imgsPath=context+"desktop/widgets/jame/images/faces/";

//表情的提示文字
Jame.custBtn.imgTips=[
  "惊讶","困惑","色","发呆","墨镜","流泪","害羞","保密","睡觉","大哭","尴尬","怒","调皮","大笑","微笑","悲",
  "酷","口罩","折磨","吐","偷笑","可爱","白眼","撇嘴","饿","困","冷汗","汗","憨笑","抽烟","发奋","骂","疑问",
  "嘘","晕","发狂","衰","骷髅","敲打","再见","闪","发抖","亲亲","跳","喊","mm","猪头","猫脸","狗脸","抱抱"
];
//表情id及对应的实际图片文件
Jame.custBtn.imgs=[];
for(var i=0;i<50;i++){
	var tmp={};
	tmp.id="img"+i;
	tmp.url=Jame.custBtn.imgsPath+i+".gif";
	tmp.tip=Jame.custBtn.imgTips[i]||"";
	Jame.custBtn.imgs.push(tmp);
}


Jame.custBtn.faceCfg = {
                    itemId:'backcolor2',
                    cls:'x-btn-icon',
					icon:Jame.custBtn.imgsPath+"28.gif",
  clickEvent:'mousedown',
  tooltip: "选择表情",
  disabled:true,
  tabIndex:-1
};

Jame.custBtn.getFaceCfg=function(editor){
  var Im;
  if(extVer>=3){
    Im=Jame.menu.ImgMenu;
  }else{
    Im=Jame.menu.ImgMenu2x;
  }
  var cfg={
    focus: Ext.emptyFn,
    plain:true,
    imgs:Jame.custBtn.imgs
  };
  if (extVer >= 3) {
    cfg.listeners = {
      scope: this,
      select:function(cp, img) {
        var html = "<img width=20 height=20 src='" + img.url + "' title='" + img.tip + "'>"
        m.getEditor().insertAtCursor(html);
      }
    }
  } else{
    cfg.selectHandler = function(cp, img) {
      var html = "<img width=20 height=20 src='" + img.url + "' title='" + img.tip + "'>"
      m.editor.insertAtCursor(html);
    }
  }

  var m=new Im(cfg);
  if (extVer >= 3) {
    m.setEditor(editor);
  }else{
    m.editor=editor;
  }
  var cfg=Ext.apply({},Jame.custBtn.faceCfg);
  cfg.itemId=editor.id?editor.id+"_face":Ext.id()+"_face";
  cfg.menu = m;
  return cfg;
}