function loadAjaxSearch(key,reload)
{
    var s = window.location.host;

    //var url = 'http://'+ s + $F('serverAddr') + '/epresssionSearch.action';
    var namespace = "tools"
    var url = "http://" + s + context + namespace + "/expressionSearch.action";

    if (key==null || key==''){
    key = $F("expressionTxt");
    }
    
    if(key==''){
        return ;
    }

    var param={expressionTxt:key};
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
            var data=Ext.decode(rs.responseText);
            showSearchRs(data,"ssdebug");
        },
        failure: function(){
			alert(rs.responseText);
            alert("查询出错");
        }
    });
}

//显示公式查询结果
function showSearchRs(data,divId){
    if((data instanceof Array)&&(data[0] instanceof Array)){
      var cols=data[0];
      var colM=[];
      var fields=[];
      for(var i=0;i<cols.length;i++){
          var curDate=new Date().getTime();
        colM.push({header:cols[i],dataIndex:cols[i]+curDate,sortable: true});
        
        fields.push({name:cols[i]+curDate});
      }
      var reader=new Ext.data.ArrayReader({},colM);
      data.shift();
      var store = new Ext.data.SimpleStore({
        fields: fields
      });
      store.loadData(data);
      
      var g=Ext.getCmp("ssdebug"+"GridPanel");
      if(g){
         resetObjDiv(g,"ssdebug");
      }
      
      var grid = new Ext.grid.GridPanel({
            //render:divId,
            id:divId+"GridPanel",
            store: store,    
            columns:colM,
            width:580,
            height:500,
            enableColumnHide:false
            //frame:true
          });
      grid.render(divId);
    }else{
      alert("数据错误");
    }
  }
