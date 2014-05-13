var oItem = new FCKToolbarButton('mystyle3', '样式三') ;
oItem.IconPath = FCKPlugins.Items['mystyle3'].Path + 'mystyle3.gif';
FCKToolbarItems.RegisterItem('mystyle3', oItem);

var FCKobj = new Object() ;
FCKobj.name='mystyle3';

FCKobj.Execute = function()
{
        removeChildNode(FCK.EditorDocument.getElementsByTagName("head")[0]);
    _appendCss('body { font-family: "宋体";  font-size: 12pt;}font {FONT-SIZE: 12pt; COLOR: #5353A6; FONT-FAMILY: 宋体}'
   +'input{border-top:none;border-left:none;border-bottom:1px solid #333333;} ;border-right:none;background-color:#f9f9f9;font-family:宋体;font-size:11pt;}'
    +'textarea{border-top:none;border-left:none;border-bottom:1px solid #333333;} ;border-right:none;background-color:#f9f9f9;overflow:auto;font-family:宋体;font-size:11pt;}');

}

function _appendCss(cssStr){
  var ss=FCK.EditorDocument.styleSheets;
    var head = FCK.EditorDocument.getElementsByTagName("head")[0];
    var rules =FCK.EditorDocument.createElement("style");
    rules.setAttribute("type", "text/css");
    rules.setAttribute('myid','mystyle');
    head.appendChild(rules);
    ss = rules.styleSheet;
    ss.cssText = cssStr;
}

//删除有myid属性的子节点  （也就是我自己加的）
function removeChildNode(n) {
    if (n.hasChildNodes()) {
        for (var i = 0; i < n.childNodes.length; i++) {
            if(n.childNodes[i] && n.childNodes[i].myid){
            n.removeChild(n.childNodes[i]);
            }
        }
    }
}

FCKobj.GetState = function()
{
	return FCK_TRISTATE_OFF ;
}

FCKCommands.RegisterCommand('mystyle3', FCKobj);

