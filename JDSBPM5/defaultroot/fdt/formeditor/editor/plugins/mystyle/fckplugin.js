FCKCommands.RegisterCommand('mystyle', new FCKDialogCommand('mystyle', "设置表单样式", FCKPlugins.Items['mystyle'].Path + 'fck_mystyle.html', 600, 400));

var oItem = new FCKToolbarButton('mystyle', '清除样式') ;
oItem.IconPath = FCKPlugins.Items['mystyle'].Path + 'mystyle.gif';
FCKToolbarItems.RegisterItem('mystyle', oItem);

var FCKobj = new Object() ;
FCKobj.name = 'mystyle';

FCKobj.Add = function(inputStyleSel, fontNameSel, fontSizeSel, lineHeightSel)
{
    var bodystyle = 'body { font-family: ' + fontNameSel + ';  font-size: ' + fontSizeSel + ';line-height:' + lineHeightSel + '}';
    removeChildNode(FCK.EditorDocument.getElementsByTagName("head")[0]);
    switch (inputStyleSel) {
        case "1":
            _appendCss(bodystyle + 'input{border-top:none;border-left:none;border-bottom:1px solid #333333 ;border-right:none;background-color:#f9f9f9;font-family:宋体;font-size:11pt;}'
                    + 'textarea{border-top:none;border-left:none;border-bottom:1px solid #333333 ;border-right:none;background-color:#f9f9f9;overflow:auto;font-family:宋体;font-size:11pt;}');
            return true;
        case "2":
            _appendCss(bodystyle + 'TABLE,TD      { FONT-SIZE:12px; LINE-HEIGHT:14px; letter-spacing:0px; }' +
                       ' INPUT         { FONT-SIZE:12px; LINE-HEIGHT:14px; letter-spacing:0px; border:1px solid #555555; }' +
                       ' textarea         { FONT-SIZE:12px; LINE-HEIGHT:14px; letter-spacing:0px; border:1px solid #555555; }' +
                       ' SELECT        { FONT-SIZE:12px; LINE-HEIGHT:14px; letter-spacing:0px; border:1px solid #555555; }' +
                       ' IMAGE         { border:0px; }');
            return true;
        case "0":
            _appendCss(bodystyle);
//            removeChildNode(FCK.EditorDocument.getElementsByTagName("head")[0]);
            return true;
    }

}

function _appendCss(cssStr) {
    var ss = FCK.EditorDocument.styleSheets;
    var head = FCK.EditorDocument.getElementsByTagName("head")[0];
    var rules = FCK.EditorDocument.createElement("style");
    rules.setAttribute("type", "text/css");
    rules.setAttribute('myid', 'mystyle');
    head.appendChild(rules);
    ss = rules.styleSheet;
    ss.cssText = cssStr;
}

//删除有myid属性的子节点  （也就是要清除formeditor自己加的样式）
function removeChildNode(n) {
    if (n.hasChildNodes()) {
        for (var i = 0; i < n.childNodes.length; i++) {
            if (n.childNodes[i] && n.childNodes[i].myid) {
                n.removeChild(n.childNodes[i]);
            }
        }
    }
}

FCKobj.GetState = function()
{
    return FCK_TRISTATE_OFF;
}


