var oItem = new FCKToolbarButton('columnchart', '柱形图') ;
oItem.IconPath = FCKPlugins.Items['columnchart'].Path + 'columnchart.gif';
FCKToolbarItems.RegisterItem('columnchart', oItem);

var FCKobj = new Object() ;
FCKobj.name = 'columnchart';

FCKobj.Execute = function()
{
    var oDiv = FCK.InsertElement('div');
    oDiv.className = 'document';
    oDiv.setAttribute('cusType', 'zcolumnchart');
    oDiv.style.display = 'inline';
    //    oDiv.innerHTML='<img id="life" src="/formeditor/editor/images/mydocument.jpg"/>' ;
    var oImg = FCK.EditorDocument.createElement("img");
    oImg.src = '/fdt/formeditor/editor/plugins/columnchart/columnchart.gif';
   // oImg.style.width = '100%';
   // oImg.style.height = '100%';
   oImg.contentEditable=false;
    oDiv.appendChild(oImg);
    oDiv.style.width = '250';
    oDiv.style.height = '180';
    oDiv.contentEditable = true;

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

FCKCommands.RegisterCommand('columnchart', FCKobj);

