
//function dhtmlXGridFromTable(obj, noIndex, noRowHead) {
function dhtmlXGridFromTable(obj,isMin) {
    var charArray="0ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
    //charArray.unshift("0");
	if (typeof (obj) != "object") {
		obj = document.getElementById(obj);
	}
		var newDIV = document.createElement("DIV");
		obj.parentNode.insertBefore(newDIV, obj);
	var formObj = obj.getAttribute("name") || ("name_" + (new Date()).valueOf());
	var windowf = new dhtmlXGridObject(newDIV);
	
	windowf.headEndIndex=(obj.getAttribute("headEndIndex")?parseInt(obj.getAttribute("headEndIndex")):null);
	windowf.footBeginIndex=(obj.getAttribute("footBeginIndex")?parseInt(obj.getAttribute("footBeginIndex")):null);
	windowf.rowEndIndex=(obj.getAttribute("rowEndIndex")?parseInt(obj.getAttribute("rowEndIndex")):null);
	
	windowf.colEndIndex=(obj.getAttribute("colEndIndex")?obj.getAttribute("colEndIndex"):null);
	if(windowf.colEndIndex){
	    var tmpNum=parseInt(windowf.colEndIndex);
    	if(!isNaN(tmpNum)){
    	    windowf.colEndIndex=String.fromCharCode(64+tmpNum);
    	}
	}
	
	if(windowf.headEndIndex||windowf.footBeginIndex||windowf.rowEndIndex||windowf.colEndIndex){
	    windowf.layoutSaved=true;
	}else{
	    windowf.layoutSaved=false;
	}
	
	
	var fristRow = obj.rows[0];

    windowf.enableContextMenu(cellMenu);
    
    var sumcol=0;
    var lastCell=fristRow.cells[fristRow.cells.length-1];
    var lastPid=lastCell.pid;
    windowf.tableColEnd=lastPid.substring(0,1);
    sumcol=lastPid.charCodeAt(0)-64;
    
    var	rowIndex=0;
    var rowMaxIndex=36;
    var maxColNum=27;
    if(isMin){
        maxColNum=sumcol+1;
    }

	var headerKey = new Array();
	headerKey[0] = "0";
	for (var cc = 1; cc <maxColNum; cc++) {
		var c = String.fromCharCode(64 + cc);
		headerKey[cc] = c;
	}
	var nullHeader = genArrayString("", maxColNum);
	windowf.setHeader(nullHeader);

	//if (noRowHead == null || noRowHead == "false" ) {
		windowf.attachHeader(headerKey.join());
	//}


    //add rowIndex cell
    for (j = rowIndex; j <rowMaxIndex; j++) {
    
      if( j < obj.rows.length){
        var rrow = obj.rows[j];
        var indexCell = document.createElement("TD");
        indexCell.innerHTML = j+ 1;
        
        indexCell.style.backgroundColor="#F3F3F3";

		indexCell._cellType="INDEX";
        
        //if (noIndex == null || noIndex == "false") {
        rrow.insertBefore(indexCell, rrow.cells[0]);
        if(!isMin){
          try{
            for(var f=sumcol;f<26;f++){
              var indexCell1 = document.createElement("TD");
              indexCell1.pid=charArray[f+1]+(j+1);
              indexCell1.type="ed";
              indexCell1.className="ED";
              indexCell.grid=windowf;
              rrow.appendChild(indexCell1);
            }
          }catch (e){ }
        }
        //}
      }else{
          break;
//        var tr=  document.createElement("TR");
//        for(var f=0;f<27;f++){
//          var indexCell2 = document.createElement("TD");
//          indexCell2.pid=charArray[f+1]+(j+1);
//          indexCell2.type="ed";
//          indexCell2.grid=windowf;
//          tr.appendChild(indexCell2);
//          if (f==0){
//            indexCell2.innerHTML = j ;
//          }
//        }
      }
    }
	
	var sumWidth = 0;
	var arr= genArrayString("50", sumcol).split(",")
	arr[0]=25;



    for(var f=arr.length ;f<maxColNum;f++){
      arr[f] = 50 ;
    }
    avrwidth=arr.join();
    for (i = 0; i < arr.length; i++) {
 	  sumWidth = arr[i] + sumWidth;
    }


	windowf.setImagePath($F('serverAddr')+"../images/designer/report/");
	windowf.setInitWidths(avrwidth);
	var colAlign = genArrayString("center", 27);
	windowf.setColAlign(colAlign);
	windowf.enableAutoHeigth(true);
	windowf.init();

	var n_l = obj.rows.length;
 

	for (var j = rowIndex; j < n_l; j++) {
		var r = obj.rows[rowIndex];
		//if (!r.id) {
			r.id ="r"+(j+1);
		//}
		
		windowf.rowsCol[j] = r;
		windowf.rowsAr[r.id] = r;
		r.idd = r.id;
		r.grid = windowf;
       // alert(r.innerHTML);
        windowf.obj.rows[0].parentNode.appendChild(r);
        for (var x = 0; x < r.cells.length; x++) {
    	    var cell = r.cells[x];
    		var cIndex=0;
    	    if(x==0){
    	        cell.id="@["+(j+1)+"]";
    	    }else if(cell.pid){
        		cell.id=cell.pid.replace(/(\d+)/,"[$1]");
    	        cIndex=cell.pid.charCodeAt(0)-64;
    	        
    	    }else{
//    	        cell.id=charArray[x]+"["+(j+1)+"]";
//    	        cIndex=x;
    	    }
    		var value = cell.innerHTML;

    		cell._cellIndex = cIndex;
    		cell.idd = cell.id;
    		//cell.cnId = cell.id;
    		cell.align = windowf.cellAlign[cIndex];
    		//cell.tit = cell.getAttribute("description");
    		cell.style.verticalAlign = windowf.cellVAlign[cIndex];

    		//	windowf.headEndIndex
//	windowf.footBeginIndex
//	windowf.rowEndIndex
//	windowf.colEndIndex
            if(cell.id.substring(0,1)<=windowf.tableColEnd){
                if(windowf.headEndIndex&&j<windowf.headEndIndex){
                    cell.className="HEAD";
                }else if(windowf.rowEndIndex&&j<windowf.rowEndIndex){
                    cell.className="HEAD";
                }
                if(windowf.footBeginIndex&&j>=(windowf.footBeginIndex-1)){
                    cell.className="FOOT";
                }
            }
            if(windowf.colEndIndex){
                if(j>=windowf.rowEndIndex&&j<=windowf.footBeginIndex){
                    var idC=cell.id.substring(0,1);
                    if(idC>="A"&&idC<=windowf.colEndIndex){
                        cell.className="HEAD";
                    }
                }
            }
            

//应用到旧htm时加的处理
if (cell.getAttribute("colType") == "float"
			||cell.getAttribute("colType") == "int"
			||cell.getAttribute("colType") == "text"
			||cell.getAttribute("colType") == "percent"
			||cell.getAttribute("colType") == "rowHeader"
			||cell.getAttribute("colType") == "colHeader"
			||cell.getAttribute("colType") == "label"
			||cell.getAttribute("colType") == "date"
			||cell.getAttribute("colType") == "number"
			||cell.getAttribute("colType") == "cnmoney"){
				if (cell.getAttribute("readonlyTd") == "true"){
					cell._cellType = 'rotext';	
				}else{
					cell._cellType = "text";	
				}
			}

            cell = windowf.cells4(cell);
            if(x!=0){
			  cell.setValue(value);
            }
		}
      //  alert(r.innerHTML);
    }
    
	obj.parentNode.removeChild(obj);
	var colw=0;
	if (avrwidth!=null){
		var arr = avrwidth.split(',');
		for (i = 0; i < arr.length; i++) {
			colw = parseInt(arr[i]) +colw;
		}
 	}	


	
    if(colw>0){
	  windowf.entBox.style.width = colw+50;
	 // windowf.entBox.width = colw+2;
    }

	newDIV.style.width=(colw+10)+"px";

	window[formObj] = windowf;
    windowf.obj.frame="box";

    windowf.tableRowEnd=windowf.obj.rows.length-1;
    if(!isMin){
     for(var j=windowf.tableRowEnd+1;j<36;j++){
         var r=windowf.addRow("r"+j, [],j);
         var indexCell= r.cells[0];
            indexCell.bgColor="#F3F3F3";
			indexCell._cellType="INDEX";
			indexCell.className='INDEX';
			indexCell.grid=windowf;
            indexCell.id = "@["+j+"]";
            indexCell.innerHTML=j;
         for(var i=1;i<r.cells.length;i++){
			 var indexCell2= r.cells[i];
             indexCell2.id = String.fromCharCode(64 + i) + "[" + j + "]";
             indexCell2.type="ed";
             indexCell2.grid=windowf;
             indexCell2.className="ED";
	     }

         //windowf.setRowHidden("r"+j,'none');
     }
    }

      
    return windowf;
}

function dhx_init_grids() {
	var z = document.getElementsByTagName("table");
	for (var a = 0; a < z.length; a++) {
		if (z[a].className == "dhtmlxGrid") {
			dhtmlXGridFromTable(z[a], z[a].noIndex, z[a].noRowHead);
		}
	}
}
if (window.addEventListener) {
	window.addEventListener("load", dhx_init_grids, false);
} else {
	if (window.attachEvent) {
		window.attachEvent("onload", dhx_init_grids);
	}
}
function insterRowSpan(headString, i) {
	var headArry = headString.split(",");
	var ruturnStr = "";
	if (i == 0) {
		ruturnStr = "#rspan," + headArry.join();
	} else {
		var hArry = headArry.slice(0, i);
		var eArry = headArry.slice(i, headArry.length);
		ruturnStr = hArry.join() + ",#rspan," + eArry.join();
	}
	return ruturnStr;
}
function avrWidth(k, l) {
	var widthArry = new Array();
	for (j = 0; j < l; j++) {
		widthArry[j] = (widthArry[j] - 2) * (k / l);
		widthArry[j] = k / l;
	}
	return widthArry.join();
}
function defaultWidth(widthString, k, l) {
	var widthArry = widthString.split(",");
	for (j = 0; j < widthArry.length; j++) {
		widthArry[j] = (widthArry[j]) * (k / l);
	 
	//	widthArry[j] = l / widthArry.length;
	}
	//alert('widthString='+widthString+'k='+k+'l='+l);
	return widthArry.join();
}
function genArrayString(str, sumcol) {
	var cch = new Array();
	for (var ch = 0; ch < sumcol; ch++) {
		cch[ch] = str;
	}
	return cch.join();
}
