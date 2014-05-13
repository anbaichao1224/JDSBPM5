
function loadAjaxSearch(key,reload)
{
	
	
    var s = window.location.host;

   
    var url = "http://" + s + context  + "expressionSearch.action";

    if (key==null || key==''){
    key = $F("expressionTxt");
    }
    
    if(key==''){
        return ;
    }
    var param={expressionTxt:key,activityInstId:$("activityInstId").value};
  if (reload!=null && reload!='false'){
        param.reload="true";
    }else{
	   param.reload="false";
	}


    Ext.Ajax.request(
    {
        url: url,
        params:param,
        success: function(rs){
            var obj=Ext.decode(rs.responseText);
			
			var totalCount= obj.totalCount;
		
            showSearchRs(obj.data,"ssdebug",totalCount,url,param);
        },
        failure: function(rs){
			alert(rs.responseText);
            alert("查询出错");
        }
    });
}





//显示公式查询结果
function showSearchRs(data,divId,totalProperty,url,param){
	param.reload=false;

    if((data instanceof Array)&&(data[0] instanceof Array)){
      var cols=data[0];
      var colM=[];
      var fields=[];
      for(var i=0;i<cols.length;i++){
          var curDate=new Date().getTime();
        colM.push({header:cols[i],dataIndex:cols[i],sortable: true});
        
        fields.push({name:cols[i]});
      }
      var reader=new Ext.data.ArrayReader({},colM);
      data.shift();
     var store ;
	
	 if (data.length>0){
	 	store = new Ext.data.SimpleStore({
        fields: fields
        });
      store.loadData(data);
	 }else{
		store = new Ext.data.Store({
		url:url+"?"+Ext.urlEncode(param),
		remoteSort:true,
		autoLoad:true,
        reader: new Ext.data.JsonReader({
            root: 'data',
            totalProperty: 'totalCount',
            id: 'processid',
            fields: fields
		})
      });
		
	 
	 }
	 	
		
		
	
		
     // store.loadData(data);
      
      var g=Ext.getCmp("ssdebug"+"GridPanel");
      if(g){
         resetObjDiv(g,"ssdebug");
      }

      var grid = new Ext.grid.GridPanel({
            id:divId+"GridPanel",
            store: store,    
            columns:colM,
            //height:500,
		  bbar: new Ext.PagingToolbar({
	            pageSize: 20,
	            store: store,
	            displayInfo: true,
	            displayMsg: '当前显示{0} - {1}  条记录 /共 {2}条记录',
	            emptyMsg: "无显示数据"
	        }),
        //autoWidth:true,
      //  autoHeight:true,
		  defaults:{autoScroll: true},
            enableColumnHide:false,
            frame:true
          });
      grid.on("render",function(){
        var obj=Ext.getCmp("editAreaTabs");
        obj=obj.findParentBy(function(){return true;});
        grid.setSize(obj.getSize().width-3,obj.getSize().height-68);
      });

      grid.render(divId);

    }else{
      alert("数据错误");
    }
  }

   