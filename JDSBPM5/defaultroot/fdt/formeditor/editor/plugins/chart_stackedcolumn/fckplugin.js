var oItem = new FCKToolbarButton('chart_stackedcolumn', '柱形堆叠图') ;
oItem.IconPath = FCKPlugins.Items['chart_stackedcolumn'].Path + 'chart_stackedcolumn1.jpg';
FCKToolbarItems.RegisterItem('chart_stackedcolumn', oItem);

var FCKobj = new Object() ;
FCKobj.name = 'chart_stackedcolumn';

FCKobj.Execute = function()
{
    var oDiv = FCK.InsertElement('div');
    oDiv.className = 'document';
    oDiv.setAttribute('cusType', 'stackedcolumnchart');
    oDiv.style.display = 'inline';
    //    oDiv.innerHTML='<img id="life" src="/formeditor/editor/images/mydocument.jpg"/>' ;
    var oImg = FCK.EditorDocument.createElement("img");
    oImg.src = '/fdt/formeditor/editor/plugins/chart_stackedcolumn/chart_stackedcolumn.jpg';
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

FCKCommands.RegisterCommand('chart_stackedcolumn', FCKobj);

