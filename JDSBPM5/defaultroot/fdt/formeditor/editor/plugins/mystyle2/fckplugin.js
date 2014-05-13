var oItem = new FCKToolbarButton('mystyle2', '样式二') ;
oItem.IconPath = FCKPlugins.Items['mystyle2'].Path + 'mystyle2.gif';
FCKToolbarItems.RegisterItem('mystyle2', oItem);

var FCKobj = new Object() ;
FCKobj.name='mystyle2';

FCKobj.Execute = function()
{
        removeChildNode(FCK.EditorDocument.getElementsByTagName("head")[0]);
    _appendCss('BODY          {  background-color:#ffffff; font-size:12px; line-height:18px; }'+
                'TABLE,TD      { FONT-SIZE:12px; LINE-HEIGHT:14px; letter-spacing:0px; }'+
               ' INPUT         { FONT-SIZE:12px; LINE-HEIGHT:14px; letter-spacing:0px; border:1px solid #555555; }' +
               ' SELECT        { FONT-SIZE:12px; LINE-HEIGHT:14px; letter-spacing:0px; border:1px solid #555555; }' +
               ' IMAGE         { border:0px; }');

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

FCKCommands.RegisterCommand('mystyle2', FCKobj);

