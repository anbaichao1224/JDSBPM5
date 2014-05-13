
    var cellMenu;

    var blockMenu;

   var rowMenu;

    var colMenu;

   var contextMenu;
  // var xml_prefix = "../scripts/designer/config/";
    var xml_prefix=context+'report/config/';

	var gfxpath=context+'imgs/formDef/';
    function initRightMenu(){
		
    cellMenu=new dhtmlXContextMenuObject('120',0);
      cellMenu.menu.setGfxPath(gfxpath);
      cellMenu.menu.setMenuCSS("contextmenuTable") ;
      cellMenu.menu.secTableCSS="contextsecondMenuTable";
	  
      cellMenu.menu.loadXML(xml_prefix + "context_menuutf8.xml");
      cellMenu.setContextMenuHandler(cellButtonClick);

    blockMenu=new dhtmlXContextMenuObject('120',0);
      blockMenu.menu.setGfxPath(gfxpath);
      blockMenu.menu.setMenuCSS("contextmenuTable") ;
      blockMenu.menu.secTableCSS="contextsecondMenuTable";
      blockMenu.menu.loadXML(xml_prefix + "context_BlockMenuutf8.xml");
      blockMenu.setContextMenuHandler(blockButtonClick);

    rowMenu=new dhtmlXContextMenuObject('120',0);
      rowMenu.menu.setGfxPath(gfxpath);
      rowMenu.menu.setMenuCSS("contextmenuTable") ;
      rowMenu.menu.secTableCSS="contextsecondMenuTable";
      rowMenu.menu.loadXML(xml_prefix + "context_IndexMenuutf8.xml");
      rowMenu.setContextMenuHandler(rowButtonClick);

    colMenu=new dhtmlXContextMenuObject('120',0);
      colMenu.menu.setGfxPath(gfxpath);
      colMenu.menu.setMenuCSS("contextmenuTable") ;
      colMenu.menu.secTableCSS="contextsecondMenuTable";
      colMenu.menu.loadXML(xml_prefix + "context_ColMenuutf8.xml");
      colMenu.setContextMenuHandler(colButtonClick);
    }

    



    function menuhebing(){

         _heBinCell();
      return true;
    }


    //列右键菜单单击处理
    function colButtonClick(itemId,itemValue){
		var idx=itemValue.substring(itemValue.lastIndexOf('_')+1);
		
        if(!(idx>="1"&&idx<=windowf.tableColEnd.charCodeAt(0)-64)){
            alert("不能对表外列操作");
            return;
        }
		try{
            eval("colCxtMenu_"+itemId+"(idx,itemId,itemValue)");
		}catch(e){
		    //没有事件处理
		}
        return true;
    }
    
    //行右键菜单单击处理
    function rowButtonClick(itemId,itemValue){
//        var idx=windowf.getRowIndex(windowf.getSelectedId());
//        if(!idx||idx<0){
//            alert("请先选中行");
//            return true;
//        }
//        if(idx>=windowf.tableRowEnd){
//            alert("不能对表外行操作");
//            return;
//        }
//        idx=idx+1;
        
        var rowNum=itemValue.replace(/^r(\d+).*$/,"$1");
        var idx=parseInt(rowNum);
        if(idx>windowf.tableRowEnd){
            alert("不能对表外行操作");
            return;
        }
        try{
            eval("rowCxtMenu_"+itemId+"(idx,itemId,itemValue)");
        }catch(e){
		    //没有事件处理
		}
        return true;
    }
    function cellButtonClick(itemId,itemValue){
		var pat=/^r(\d+)_(\d+)$/;
		var cellStr="";
		if(pat.test(itemValue)){
		    cellStr=String.fromCharCode(parseInt(RegExp.$2)+64)+RegExp.$1;
		}
		try{
		  eval("cellCxtMenu_"+itemId+"(cellStr,itemId,itemValue)");
		}catch(e){
		    //没有事件处理
		}
        return true;
    }
    function blockButtonClick(itemId,itemValue){    
	//左上列
	pos.LeftTopCol 
	//左上行
	pos.LeftTopRow
	//右上行
	pos.RightBottomCol 
	//右上行
	pos.RightBottomRow 
	//所有被选中的CELL
	blockSelectCells
         alert('  pos.LeftTopCol ='+  pos.LeftTopCol +'pos.LeftTopRow='+ pos.LeftTopRow+'pos.RightBottomCol ='+	pos.RightBottomCol +'pos.RightBottomRow ='+	pos.RightBottomRow );
		 eval("menu"+itemId+"(pos,blockSelectCells)");
            return false;  
     }




    function caifen(){
    }

     function menuaddRow(){
         var idx=windowf.getRowIndex(windowf.getSelectedId());
            var j=idx+1;
        
         var row=windowf.addRow("r"+j,[],j);

       windowf.rowsCol[j] = row;
		windowf.rowsAr["r"+j] = row;
         var indexCell= row.cells[0];
            indexCell.bgColor="#F3F3F3";
			indexCell._cellType="INDEX";
			indexCell.className='INDEX';
			indexCell.grid=windowf;
            indexCell.id = "@["+j+"]";
            indexCell.innerHTML=j;
         for(var i=1;i<row.cells.length;i++){
			 var indexCell2= row.cells[i];
             indexCell2.id = String.fromCharCode(64 + i) + "[" + j + "]";
             indexCell2.type="ed";
             indexCell2.className="ED";
	     }
       handleAddRow(windowf,idx);
       updateCellAll();
    }
    function menudelRow(){
          var idx=windowf.getRowIndex(windowf.getSelectedId());
        if(confirm('您真的要删除第['+(idx+1)+']行吗?')){
          windowf.deleteSelectedItem();
          handleDelRow(windowf,idx);
          updateCellAll();
                }
    }

    function onButtonClick1(itemId,itemValue){
        var idx=windowf.getRowIndex(windowf.getSelectedId());
       if(itemId=='_heBinCell'){
        _heBinCell();

           return;
        }
      initHeadAndFootIdx(windowf);

      if(idx<=0){
        alert("请先选中一行");
        return;
      }

      if(idx<=_headMaxIdx||idx>=_footMinIdx){
        alert("错啦...\n不能增加删除本行");
        return;
      }

      if(itemId=='addNew'){
        menuaddRow();
      }else if(itemId=='delete'){
         menudelRow();
      }
    }



    /////////////////////////////////////////////////////////////////////////////////////////
    var _headMaxIdx=-1;
    var _footMinIdx=-1;
    var _startRowNum=0;
    var _endRowNum=0;

    function initHeadAndFootIdx(windowf){
      _headMaxIdx=getHeadMaxIdx(windowf);
      _footMinIdx=getFootMinIdx(windowf);

      _startRowNum=_headMaxIdx+1;
      _endRowNum=_footMinIdx-1;
    }

    function handleAddRow(windowf,idx){
      //var d=new Date().getTime();

      _footMinIdx++;
      _endRowNum++;
      var tab=windowf.obj;

      var row=tab.rows[idx];
      var cells=row.cells;

      var curRow=tab.rows[idx+1];
      var curCells=curRow.cells;

      //设置正确的rowspan
      var spanCell;
      var tmpRow=0;
      for(var i=_startRowNum;i<=idx;i++){
        if(tab.rows[i].cells[1].rowSpan>1){
          spanCell=tab.rows[i].cells[1];
          tmpRow=i;
        }
      }
      if(spanCell){
        if((tmpRow+spanCell.rowSpan-1)>=idx){
          spanCell.rowSpan+=1;
        }
      }

      //设置正确的列数
      var colSpan=curCells.length-cells.length;//考虑上一行的colspan
      var start=0;
      for(var i=0;i<cells.length;i++){//考虑上一行的rowspan
        if(cells[i].rowSpan>1){
          start++;
        }
      }
      colSpan+=start;
      for(var i=0;i<colSpan;i++){
        curCells[curCells.length-1].parentNode.removeChild(curCells[curCells.length-1]);
      }

      //设置正确的colspan
      for(var i=0;i<curCells.length;i++){
        curCells[i]["colSpan"]=cells[i]["colSpan"];
      }

      //复制列值等
      copyCells(cells,curCells,start);

      //重新设置cell的正确属性
      //增加时,索引为新行
      resetCell(windowf,idx+1,"add");

      //alert("222=="+(new Date().getTime()-d)/1000);
    }

    function handleDelRow(windowf,idx){
      //var d=new Date().getTime();

      _footMinIdx--;
      _endRowNum--;
      var tab=windowf.obj;

      var count=0;
      var rowSpan=0;
      //设置正确的rowspan,目前不考虑删除rowspan>1的行的情况
      for(var i=idx-1;i>0;i--){
        count++;
        rowSpan=tab.rows[i].cells[1].rowSpan;
        if(rowSpan>1){
          if(rowSpan>count){
            tab.rows[i].cells[1].rowSpan-=1;
            break;
          }
        }
      }

      //重新设置cell的正确属性
      //删除时,索引为当前行,即删除前的下一行
      resetCell(windowf,idx,"del");

      //alert("11====="+(new Date().getTime()-d)/1000);
    }

    //计算表头所在的数据行
    function getHeadMaxIdx(windowf){
      var tab=windowf.obj;
      for(var i=1;i<tab.rows.length;i++){
        if(tab.rows[i].cells[0].id.indexOf("HEAD")!=0){
          return i-1;
        }
      }
      return -1;
    }

    //取表尾的最小行
    function getFootMinIdx(windowf){
      var tab=windowf.obj;
      for(var i=tab.rows.length-1;i>=0;i--){
        if(tab.rows[i].cells[0].id.indexOf("FOOT")!=0){
          return i+1;
        }
      }
      return tab.rows.length;
    }

    //从源列表的指定位置开始复制其内容到目的列表
    function copyCells(src,des,start){
      for(var i=start;i<src.length;i++){
        var s=src[i];
        var d=des[i-start];
        if(s.referenceType&&s.referenceType=="0"){//如果是原始数据才复制显示值
          des[i-start].innerHTML=src[i].innerHTML;
        }
        copyCellAttribute(s,d);
      }
    }

    //复制单元格的属性
    function copyCellAttribute(s,d){
      var keys=getCellAttributeNames(s);

      for(var i=0;i<keys.length;i++){
        var k=keys[i];
        if(k!="colSpan"&&k!="rowSpan"){
          if(s[k]){
            try{
              d[k]=s[k];
            }catch(e){
              //alert(e+"==="+k);
            }
          }
        }
      }
    }

    //取得单元格的属性名
    function getCellAttributeNames(cell){
      var str=cell.outerHTML;
      var keys=[];

      /*
      for(var att in cell){
        if(str.indexOf(att+"=")>-1){
          keys[keys.length]=att+"";
        }
      }
      */
      var pat=/\w+=/g;
      var m=str.match(pat);

      if(m){
        for(var i=0;i<m.length;i++){
          var k=m[i];
          k=k.substring(0,k.length-1);
          if(cell[k]){
            keys[keys.length]=k;
          }
        }
      }
      return keys;
    }

    //设置行头第一单元格的正确显示行数,及其它单元格的正确属性
    function resetCell(windowf,rowIdx,opt){
      var tab=windowf.obj;
      var pCells=tab.rows[rowIdx-1].cells;
      var pRowNum=0;
      var id=pCells[0].id;
      if(/^[A-Z]\[(\d+)\]/.test(id)){
        pRowNum=parseInt(RegExp.$1);
      }

      var num=0;
      if(opt=="add"){
        num=1;
      }else if(opt=="del"){
        num=-1;
        pRowNum++;
      }

      var rows=tab.rows;
      for(var i=rowIdx;i<rows.length;i++){
        var cs=rows[i].cells;
        for(var j=0;j<cs.length;j++){
          var c=cs[j];
          if(j==0){
            c.innerText=i;
          }

          //页脚的行,只改变行数显示,其它不动
          if(i>_endRowNum){
            break;
          }
          resetCellAttribute(pRowNum,c,num);
          setExpression(tab,c);
        }
      }
    }

    //修改单元格的属性为正确的值
    function resetCellAttribute(pRowNum,cell,num){
      var keys=getCellAttributeNames(cell);

      for(var i=0;i<keys.length;i++){
        if(cell[keys[i]]){
          if(keys[i]=="id"){
            var id=cell["id"];
            var p=/([A-Z]+\[)(\d+)\]/g;
            if(p.test(id)){
              id=id.replace(p,RegExp.$1+(parseInt(RegExp.$2)+num)+"]");
              cell.id=id;
            }
          }else{
            var str=cell[keys[i]];
            if(typeof str=="string"){
              if(/(sum|SUM)\([A-Z]\d+\:[A-Z]\d+\)/.test(str)){
                cell[keys[i]]=replaceSumNum(str,pRowNum,num);
              }else{
                cell[keys[i]]=replaceNum(str,pRowNum,num);
              }
            }
          }
        }
      }
    }

    //处理表内sum公式
    function replaceSumNum(str,curRowNum,num){
      var pat=/(SUM|sum)\(([A-Z])(\d+)\:([A-Z])(\d+)\)/g;
      var rtn=str;
      if(pat.test(str)){
        var n1=parseInt(RegExp.$3);
        var n2=parseInt(RegExp.$5);
        if(n1==curRowNum){
          n2=n2+num;
        }else if(n1>curRowNum){
            n1=n1+num;
            n2=n2+num;
        }else{
          if(n2>=curRowNum){
            n2=n2+num;
          }
        }
        rtn=RegExp.$1+"("+RegExp.$2+n1+":"+RegExp.$4+n2+")";
      }
      return rtn;
    }

    //修改单元格属性中的行列标识为正确值
    //str 包函标识的字符串
    //curRowNum 基准行数,对于增加来说就是增加行上一行的行索引数,删除时就是被删除的行索引数
    //即增加或删除后的新行的比较基准值
    function replaceNum(str,curRowNum,num){
      var pat=/([A-Z])(\d+)/g;
      var tmp;
      var newStr="";
      var startIdx=0;
      while( tmp=pat.exec(str)){
        newStr+=str.substring(startIdx,tmp.index);
        if(newStr.substring(newStr.length-1)=="."){
          newStr+=tmp[0];
        }else{
          newStr+=tmp[1];
          if(parseInt(tmp[2])>=curRowNum){
            newStr+=parseInt(tmp[2])+num;
          }else{
            newStr+=tmp[2];
          }
        }
        startIdx=tmp.lastIndex;
      }
      newStr+=str.substring(startIdx);
      return newStr;
    }

    //如果对应的表头有可变的theoremExpression,则设置其值
    function setExpression(tab,cell){
      var id=cell.id;
      if(!id){
        return;
      }
      var colName;
      var rowNum=0;
      if(/([A-Z]+)\[(\d+)\]/.test(id)){
        colName=RegExp.$1;
        rowNum=RegExp.$2;
      }
      if(colName){
        for(var i=_headMaxIdx;i>=0;i--){
          var c=tab.rows[i].cells["HEAD"+colName+"["+i+"]"];
          if(c){
            var exp=c["theoremExpression"];
            if(exp){
              if(/.+\{\*\}.*/.test(exp)){
                exp=exp.replace(/\{\*\}/g,(parseInt(rowNum)-1));
                cell["theoremExpression"]=exp;
                break;
              }
            }
          }
        }
      }
    }
