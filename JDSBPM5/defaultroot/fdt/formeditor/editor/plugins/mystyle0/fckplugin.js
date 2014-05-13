var oItem = new FCKToolbarButton('mystyle0', '清除样式') ;
oItem.IconPath = FCKPlugins.Items['mystyle0'].Path + 'mystyle0.gif';
FCKToolbarItems.RegisterItem('mystyle0', oItem);

var FCKobj = new Object() ;
FCKobj.name='mystyle0';

FCKobj.Execute = function()
{

    _appendCss('body { }'
   +'input{}'
    +'textarea{}');
 removeChildNode(FCK.EditorDocument.getElementsByTagName("head")[0]);
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

//删除有myid属性的子节点  （也就是要清除formeditor自己加的样式）
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

FCKCommands.RegisterCommand('mystyle0', FCKobj);

