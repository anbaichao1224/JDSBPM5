var oItem = new FCKToolbarButton('checkboxlist', '复选集合') ;
oItem.IconPath = FCKPlugins.Items['checkboxlist'].Path + 'checkboxlist.gif';
FCKToolbarItems.RegisterItem('checkboxlist', oItem);
var FCKobj = new Object() ;
FCKobj.name='checkboxlist';

FCKobj.Execute = function()
{
    var oDiv=FCK.InsertElement('div');
    oDiv.setAttribute('cusType','checkboxlist');
        oDiv.style.display='inline';
        oDiv.contentEditable = false ;
       oDiv.style.width='200';
       oDiv.style.height='70';
	oDiv.innerHTML=('<table border="0" cellspacing="0" width="100%" height="100%"><tr><td>' +
                    '<input type="checkbox" name="checkboxlist" value="1" >选项1</input></td><td>' +
                    '<input type="checkbox" name="checkboxlist" value="2" >选项2</input></td></tr><tr><td>' +
                    '<input type="checkbox" name="checkboxlist" value="3" >选项3</input></td><td>' +
                    '<input type="checkbox" name="checkboxlist" value="4" >选项4</input></td></tr>' +
                    '</table>');

	oDiv.onresizestart = function()
	{
		FCK.EditorWindow.event.returnValue = true ;
		return true ;
	}
}

FCKobj.GetState = function()
{
	return FCK_TRISTATE_OFF ;
}

FCKobj._SetupClickListener = function()
{
    FCKobj._ClickListener = function(e)
    {
        if (e.target.tagName == 'div' && e.target._fckplaceholder)
            FCKSelection.SelectNode(e.target);
    }

    FCK.EditorDocument.addEventListener('click', FCKobj._ClickListener, true);
}

FCKCommands.RegisterCommand('checkboxlist', FCKobj);

