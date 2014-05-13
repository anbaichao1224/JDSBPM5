var oItem = new FCKToolbarButton('multipleselect', '多选框') ;
oItem.IconPath = FCKPlugins.Items['multipleselect'].Path + 'multipleselect.gif';
FCKToolbarItems.RegisterItem('multipleselect', oItem);

var FCKobj = new Object() ;
FCKobj.name='multipleselect';

FCKobj.Execute = function()
{
    var oDiv=FCK.InsertElement('div');
    oDiv.setAttribute('cusType','zmultipleselect');
        oDiv.style.display='inline';
        oDiv.contentEditable = false ;
       oDiv.style.width='246';
       oDiv.style.height='147';
    oDiv.innerHTML=('<table border="0" width="100%" height="100%"><tr><td>' +
                    '<select multiple size="8" name="list1"   >' +
                    '<option value="11"> ultiple 1.1.1 </option>' +
                    '<option value="12"> ultiple 1.2.1 </option>' +
                    '<option value="13"> ultiple 1.3.1 </option>' +
                    '</select></td><td>' +
                    '<input type="button" value=">>"  name="B1"><br>' +
                    '<input type="button" value="<<"  name="B2">' +
                    '</td>' +
                    '<td><select multiple size="8" name="list2"  >' +
                    '<option value="21"> ultiple 2.1.1 </option>' +
                    '<option value="22"> ultiple 2.2.1 </option>' +
                    '<option value="23"> ultiple 2.3.1 </option>' +
                    '</select></td></tr></table>') ;


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

FCKCommands.RegisterCommand('multipleselect', FCKobj);

