var bTextMode;
function setMode(newMode,obj)
{
    bTextMode = newMode;
    var cont;
    if (bTextMode)
    {
        cleanHtml(obj);
        cleanHtml(obj);

        cont = obj.document.body.innerHTML;
        obj.document.body.innerText = cont;

    }
    else
    {
        cont = obj.document.body.innerText;
        obj.document.body.innerHTML = cont;
    }

    obj.focus();
}
function cleanHtml(obj)
{
    var fonts = obj.document.body.all.tags("FONT");
    var curr;
    for (var i = fonts.length - 1; i >= 0; i--)
    {
        curr = fonts[i];
        if (curr.style.backgroundColor == "#ffffff") curr.outerHTML = curr.innerHTML;
    }
}