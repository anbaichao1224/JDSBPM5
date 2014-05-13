var oItem = new FCKToolbarButton('zihao', '字号') ;
oItem.IconPath = FCKPlugins.Items['zihao'].Path + 'myattach.gif';
FCKToolbarItems.RegisterItem('zihao', oItem);

var FCKobj = new Object() ;
FCKobj.name='zihao';

FCKobj.Execute = function()
{
    var oDiv=FCK.InsertElement('div');
    oDiv.className='zihao';
    oDiv.setAttribute('cusType','zihao');
      
        oDiv.style.display='inline';
      // oDiv.innerText = ('<ww:select <ww:select onchange="mainselect()" 	name="$Fdtnmfwbl.fdtOaNmfwblDAO.mj" list="#{'yb':'一般','mm':'秘密','jm':'机密','juem':'绝密'}"  	listKey="key"	listValue="value"/><input type="text"/><ww:action name="tozihao" executeResult="true"></ww:action>') ;
      //oDiv.innerHTML=(' <input type="text"/><ww:action name="tozihao" executeResult="true"><ww:param name="actid"><ww:property value="activityInstId"/></ww:param></ww:action>') ;
    oDiv.innerHTML=('<iframe id="iframeupss"  width="470" scrolling="no" frameborder=0 height="44" src="/tozihao.do?actid=<ww:property value="activityInstId"/>"></iframe>');
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

FCKCommands.RegisterCommand('zihao', FCKobj);

