Ext.namespace("JDS.exp");

JDS.exp.History = function(win) {
  this.list = [];
  this.current = -1;
  this.win=win;
  this.max = 100;
}

JDS.exp.History.prototype.getBackPath = function(num) {
  num = num || -1;
  if (this.current >= 0&&this.current<this.list.length) {
    return this.list[this.current];
  } else {
    return null;
  }
}
JDS.exp.History.prototype.go=function(num){
  if(num==0)return;
  num=num||-1;
  this.current = this.current + num;
  if (this.current < 0)this.current = 0;
  if (this.current >= this.list.length)this.current = this.list.length - 1;
  var path=this.getBackPath(this.current);
  JDS.exp.actions.exec("changeCurrentPath", [this.win,path,null,true]);
  if(this.hasHistory()){
    JDS.exp.enableToolbarBtn(this.win.URIbar,"btnGoBack");
  }else{
    JDS.exp.disableToolbarBtn(this.win.URIbar,"btnGoBack");
  }
  if(this.hasForward()){
    JDS.exp.enableToolbarBtn(this.win.URIbar,"btnGoforward");
  }else{
    JDS.exp.disableToolbarBtn(this.win.URIbar,"btnGoforward");
  }
}
JDS.exp.History.prototype.add=function(path){
  if(this.current>=this.max){
    this.list.shift();
  }else{
    this.current+=1;
  }
  this.list.length=this.current+1;
  this.list[this.current]=path;
  //if(this.hasHistory()){
    JDS.exp.enableToolbarBtn(this.win.URIbar,"btnGoBack");
  //}
  JDS.exp.disableToolbarBtn(this.win.URIbar,"btnGoforward");
}
JDS.exp.History.prototype.hasHistory=function(){
  return this.current>0;
}
JDS.exp.History.prototype.hasForward=function(){
  return this.current<(this.list.length-1);
}
JDS.exp.History.prototype.getList=function(){
  var l=this.list.clone();
  l.length=this.current+1;
  l.reverse();
  return l;
}

JDS.exp.History.prototype.clean=function(){
  this.list = [];
  this.current = -1;
}