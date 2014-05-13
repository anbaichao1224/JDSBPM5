var oItem = new FCKToolbarButton('myattach', '附件') ;
oItem.IconPath = FCKPlugins.Items['myattach'].Path + 'myattach.gif';
FCKToolbarItems.RegisterItem('myattach', oItem);

var FCKobj = new Object() ;
FCKobj.name='myattach';

FCKobj.Execute = function()
{
    var oDiv=FCK.InsertElement('div');
    oDiv.className='myattach';
    oDiv.setAttribute('cusType','zattach');
        oDiv.style.display='inline';
    oDiv.innerHTML=('  <table id="attach" width="100%" bgcolor="#cccccc" border="1" cellpadding="3" cellspacing="0" align="center" style="font-size:11pt" >'
     +' <tr  ><td nowrap>附件名称</td><td nowrap>上传人</td><td nowrap>上传时间</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></table>') ;
    
    oDiv.contentEditable = false ;

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

FCKCommands.RegisterCommand('myattach', FCKobj);

