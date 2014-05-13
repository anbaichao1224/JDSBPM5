var oItem = new FCKToolbarButton('mydocument', '模板正文') ;
oItem.IconPath = FCKPlugins.Items['mydocument'].Path + 'mydocument.gif';
FCKToolbarItems.RegisterItem('mydocument', oItem);

var FCKobj = new Object() ;
FCKobj.name = 'mydocument';

FCKobj.Execute = function()
{
    var oDiv = FCK.InsertElement('div');
    oDiv.className = 'document';
    oDiv.setAttribute('cusType', 'zdocument');
    oDiv.style.display = 'inline';
    //    oDiv.innerHTML='<img id="life" src="/formeditor/editor/images/mydocument.jpg"/>' ;
    var oImg = FCK.EditorDocument.createElement("img");
    oImg.src = '/fdt/formeditor/editor/plugins/mydocument/mydocument.gif';
    oImg.style.width = '100%';
    oImg.style.height = '100%';
    oDiv.appendChild(oImg);
    oDiv.style.width = '40';
    oDiv.style.height = '40';
    oDiv.contentEditable = false;

    oDiv.onresizestart = function()
    {
        FCK.EditorWindow.event.returnValue = true;
        return true;
    }
}

FCKobj.GetState = function()
{
    return FCK_TRISTATE_OFF;
}

FCKobj._SetupClickListener = function()
{
    FCKobj._ClickListener = function(e)
    {
        if (e.target.tagName == 'div')
            FCKSelection.SelectNode(e.target);
    }
    FCK.EditorDocument.addEventListener('click', FCKobj._ClickListener, true);
}

FCKCommands.RegisterCommand('mydocument', FCKobj);

