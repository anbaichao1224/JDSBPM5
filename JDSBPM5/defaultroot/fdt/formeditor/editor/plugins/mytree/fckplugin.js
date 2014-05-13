var oItem = new FCKToolbarButton('mytree', '树形控件') ;
oItem.IconPath = FCKPlugins.Items['mytree'].Path + 'mytree.gif';
FCKToolbarItems.RegisterItem('mytree', oItem);

var FCKobj = new Object() ;
FCKobj.name='mytree';

FCKobj.Execute = function()
{
    var oDiv=FCK.InsertElement('div');
    oDiv.className='mytree';
        oDiv.style.display='inline';
    oDiv.setAttribute('cusType','ztree');
    oDiv.style.width='187';
    oDiv.style.height='185';

//oDiv.innerHTML='<img id="life" src="/formeditor/editor/images/mytree.gif"/>' ;
     var oImg=FCK.EditorDocument.createElement("img");
    oImg.src='/formeditor/editor/images/mytree.gif';
    oImg.style.width = '100%';
    oImg.style.height = '100%';
    oDiv.appendChild(oImg) ;
    
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

FCKCommands.RegisterCommand('mytree', FCKobj);

