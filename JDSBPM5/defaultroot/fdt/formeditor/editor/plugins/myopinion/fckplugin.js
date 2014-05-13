var oItem = new FCKToolbarButton('myopinion', '意见标签') ;
oItem.IconPath = FCKPlugins.Items['myopinion'].Path + 'myopinion.gif';
FCKToolbarItems.RegisterItem('myopinion', oItem);

var FCKobj = new Object() ;
FCKobj.name='myopinion';

FCKobj.Execute = function()
{
    var oDiv=FCK.InsertElement('div');
    oDiv.className='myopinion';
        oDiv.style.display='inline';
    oDiv.setAttribute('cusType','zopinion');
    oDiv.style.width='300';
    oDiv.innerHTML=('   <table  width="100%" height="100%"  border="0" cellpadding="3" cellspacing="0" align="center" style="font-size:11pt" >'
     +' <tr  > <td nowrap style="font-family: 楷体_gb2312 ;font-size:14pt">同意签发此件。</td> </tr> <tr>'
         +' <td align="right">张主任</td></tr><tr><td align="right">2009-2-13</td></tr></table>') ;
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

FCKCommands.RegisterCommand('myopinion', FCKobj);

