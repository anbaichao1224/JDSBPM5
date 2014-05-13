var oItem = new FCKToolbarButton('mylabel', 'label') ;
oItem.IconPath = FCKPlugins.Items['mylabel'].Path + 'mylabel.gif';
FCKToolbarItems.RegisterItem('mylabel', oItem);

var FCKobj = new Object() ;
FCKobj.name='mylabel';

FCKobj.Execute = function()
{


     var htmltext=FCKSelection.GetSelection().createRange().htmlText;
    //替换掉内部的<div><label></label><div>
     htmltext = htmltext.replace(/<div[^>]*><label[^>]*>((?:.|\s)*?)<\/label><\/div>/gi, '$1') ;

    
    var oDiv=FCK.InsertElement('div');
    oDiv.setAttribute('cusType','zlabel');
    oDiv.style.display='inline';
    oDiv.innerHTML=('<LABEL>【'+htmltext+'】</LABEL>') ;

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
        if (e.target.tagName == 'div')
            FCKSelection.SelectNode(e.target);
    }

    FCK.EditorDocument.addEventListener('click', FCKobj._ClickListener, true);
}

FCKCommands.RegisterCommand('mylabel', FCKobj);

