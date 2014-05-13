function ReportShowBean(head,body,foot)
{
    this.body = new dhtmlXGridFromTable(body);
    //this.head = new dhtmlXGridFromTable(head);
    //this.foot = new dhtmlXGridFromTable(foot);
    var ffoot = new dhtmlXGridFromTable(foot);
    var hhead = new dhtmlXGridFromTable(head);
    ffoot.hdr.onmousemove = null;
    ffoot.hdr.onmousedown = null;
    hhead.hdr.onmousemove = null;
    hhead.hdr.onmousedown = null;

//    ffoot.entBox.grid.doColResize = function(ev,el,startW,x,tabW)
//    {
//        el.style.cursor = "E-resize";
//        this.resized = el;
//        var fcolW = startW + (ev.clientX-x);
//        var wtabW = tabW + (ev.clientX-x)
//        if ((this.onRSI)&&(!this.onRSI(el._cellIndex,fcolW,this))) return;
//        if (el.colSpan>1)
//        {
//            var a_sizes=new Array();
//            for (var i=0; i<el.colSpan; i++)
//            a_sizes[i]=Math.round(fcolW*this.hdr.rows[0].childNodes[el._cellIndexS+i].offsetWidth/el.offsetWidth);
//            for (var i=0; i<el.colSpan; i++)
//            this._setColumnSizeR(el._cellIndexS+i*1,a_sizes[i]);
//        }
//        else
//            this._setColumnSizeR(el._cellIndex,fcolW);
//        this.doOnScroll(0,1);
//        if (_isOpera) this.setSizes();
//            this.objBuf.childNodes[0].style.width = "";
//    }
    this.body.startColResize = function(ev)
    {
        this.resized = null;
        var el = ev.target||ev.srcElement;
        if(el.tagName!="TD")
        {
            el = this.getFirstParentOfType(el,"TD");
        }
        var x = ev.clientX;
        var tabW = this.hdr.offsetWidth;
        var startW = parseInt(el.offsetWidth);
        if(el.tagName=="TD" && el.style.cursor!="default")
        {
            if ((this._drsclmn)&&(!this._drsclmn[el._cellIndex]))
            {
                return;
            }
            var rowIndex = el.parentNode.rowIndex;
            var cellIndex = el.cellIndex;
            var tempcolspan = 0;
            var tarCellIndex = 0;
            for(var i = 0;i <= cellIndex;i++)
            {
                tempcolspan = this.hdr.rows[rowIndex].cells[i].getAttribute("colspan");
                if(null != tempcolspan && '' != tempcolspan)
                {
                    tarCellIndex = tarCellIndex + tempcolspan;
                }
                else
                {
                    tarCellIndex++;
                }
            }
            tarCellIndex--;            
            var cellFoot = ffoot.hdr.rows[ffoot.hdr.rows.length - 1].cells[tarCellIndex];
            var cellHead = hhead.hdr.rows[hhead.hdr.rows.length - 1].cells[tarCellIndex];
            this.entBox.onmousemove = function(e)
            {
                this.grid.doColResize(e||window.event,el,startW,x,tabW);
                ffoot.entBox.grid.doColResize(e||window.event,cellFoot,startW,x,tabW);
                hhead.entBox.grid.doColResize(e||window.event,cellHead,startW,x,tabW);
            }
            document.body.onmouseup = new Function("","document.getElementById('"+this.entBox.id+"').grid.stopColResize()");
        }
    };
    return this;
}
