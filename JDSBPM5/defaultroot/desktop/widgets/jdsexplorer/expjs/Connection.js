

Ext.namespace("JDS.ajax.exp", "JDS.ajax.exp.url");

//所有的exp相关的后台调用

JDS.ajax.exp.Connection = {
    //
    //    singleFolder: function(path){
    //        var url = ExtJame.backend.url.getOrgTree;
    //        var evalJs = function(o){
    //            Ext.namespace("EVAL");
    //            eval(o);
    //        };
    //        JDS.ajax.Connection.LoadJsonFromUrl(url, evalJs, $H({
    //            personId: personid,
    //            path: path
    //        }));
    //
    //    },
    //    getKeyPath: function(path, personid){
    //        var tree;
    //        var paths;
    //		var pos ;
    //        var param = {
    //            dirName: path,
    //            personId: personid
    //        };
    //        var callback = function(o){
    //            var msg = o.trim();
    //            paths = msg.split(',');
    //            var explorer = new AppModule();
    //            explorer.createWindow();
    //            tree = App.westTree;
    //            setTimeout('action()', 1000);
    //        };
    //        action = function(){
    //            pos = paths.length - 1;
    //            var path = paths[pos];
    //            var node = tree.getNodeById(path);
    //            node.expand(false,false,back);
    //
    //        }
    //        back = function(n){
    //			var p = --pos;
    //			n.eachChild(function(item){
    //				if(item.id ==paths[p]){
    //					item.expand(false,false,back);
    //					if(p == 0){
    //
    //						Explorer.Manager.currentPath = item.id;
    //						Explorer.Manager.render(Explorer.Manager.currentPath);
    //					}
    //
    //				}
    //
    //			})
    //        }
    //        var url = JDS.ajax.explorer.url.baseurl + JDS.ajax.explorer.url.getKeyPath;
    //        JDS.ajax.Connection.LoadJsonFromUrl(url, callback, $H(param));
    //    },
    Folder4tree: function(dropIDs, parent, target, path){
        var params = {
            nodeId: dropIDs,
            target: target.id
        }
        var callback = function(o){
            var msg = o.trim();
            if (msg == "success") {
                //todo
                //if the parent or the target is null that dropNode is file
                parent.reload();//refresh the parent of dropNode
                target.reload();//refresh the target of dropNode
                //                folderEx.refresh(folderEx.getCurrentPath());//refresh the folder panel in currentPath
                JDS.exp.refreshAllWin(path);
            }
            else {
                //todo
                alert(msg);
            }
        };
        
        var url = JDS.ajax.exp.url.baseurl + JDS.ajax.exp.url.Folder4tree;
        JDS.ajax.Connection.LoadJsonFromUrl(url, callback, $H(params));
    },
    folder4folder: function(dropIDs, parent, target, path){
        var params = {
            nodeId: dropIDs,
            target: target.id
        }
        var callback = function(o){
            var msg = o.trim();
            if (msg == "success") {
                //todo
                //if the parent or the target is null that dropNode is file
                parent.reload();//refresh the parent of dropNode
                target.reload();//refresh the target of dropNode
                //                folderEx.refresh(folderEx.getCurrentPath());//refresh the folder panel in currentPath
                JDS.exp.refreshAllWin(path);
            }
            else {
                //todo
                alert(msg);
            }
        };
        var url = JDS.ajax.exp.url.baseurl + JDS.ajax.exp.url.folder4folder;
        JDS.ajax.Connection.LoadJsonFromUrl(url, callback, $H(params));
    },
    tree4folder: function(dropNode, parent, target, path){
    
        var params = {
            nodeId: dropNode.id,
            target: target.id
        }
        var callback = function(o){
            var msg = o.trim();
            if (msg == "success") {
                target.reload();
                parent.reload();
                //                folderEx.render(folderEx.getCurrentPath());
                JDS.exp.refreshAllWin(path);
            }
            else {
                alert(msg);
            }
        };
        
        var url = JDS.ajax.exp.url.baseurl + JDS.ajax.exp.url.tree4folder;
        JDS.ajax.Connection.LoadJsonFromUrl(url, callback, $H(params));
    },
    createFolder: function(path){
        //传入所在树节点和右侧面板
        //alert(folderEx.getCurrentPath());
        var params = {
            nodeId: path
        }
        var callback = function(o){
            var msg = o.trim();
            if (msg == "success") {
                //todo
                //alert("操作成功");
                JDS.exp.refreshAllWin(path);
            }
            else {
                //todo
                alert(msg);
            }
        };
        var url = JDS.ajax.exp.url.baseurl + JDS.ajax.exp.url.createFolder;
        JDS.ajax.Connection.LoadJsonFromUrl(url, callback, $H(params));
    },
    //
    deleteFile: function(path, pPath){
        //alert("this is delete().");
        if (!confirm("真的要删除所选项?")) 
            return;
        if (path instanceof Array) {
            path = path.join(",");
        }
        var param = {
            nodeId: path
        }
        var callback = function(o){
            var msg = o.trim();
            if (msg == "success") {
                JDS.exp.refreshAllWin(pPath);
            }
            else {
                //todo
                alert("操作失败");
                alert(msg);
            }
        };
        var url = JDS.ajax.exp.url.baseurl + JDS.ajax.exp.url.deleteFile;
        JDS.ajax.Connection.LoadJsonFromUrl(url, callback, $H(param));
    },
    
    
    //粘贴时的后台调用
    copyto: function(source, target, fn){
        if (source instanceof Array) {
            source = source.join(",");
        }
        var params = {
            nodeId: source,
            target: target
        }
        
        
        var cfg = {
            title: "复制",
            text: "正在复制",
            msg: "已复制"            //下面是实时取 进度 数据的参数
            ,
            url: "",
            params: "",
            fn: ""
        }
        var proBox = JDS.exp.ProgressBox.showNew(cfg);
        
        
        //操作成功的回调处理，目标目录的刷新
        var callback = function(o){
        proBox.close();//测试时,返回即取消进度框
            var msg = o.trim();
            if (msg == "success") {
                //alert("success");
            }
            else {
            
                proBox.close();
                //todo
                // alert(msg);
            }
        };
        
        var url = JDS.ajax.exp.url.baseurl + JDS.ajax.exp.url.copyto;
        JDS.ajax.Connection.LoadJsonFromUrl(url, callback, $H(params));
    },
    //      remove: function(path){
    //        //alert("this is delete().");
    //        var param = {
    //            nodeId:path
    //        }
    //        var callback = function(o){
    //            var msg = o.trim();
    //            if (msg == "success") {
    //              //
    //            }
    //            else {
    //                alert("操作失败");
    //                alert(msg);
    //            }
    //        };
    //        var url = JDS.ajax.exp.url.baseurl + JDS.ajax.exp.url.deleteFile;
    //        JDS.ajax.Connection.LoadJsonFromUrl(url, callback, $H(param));
    //    }
    //   ,
    
    moveto: function(dropNode, parent, target, path){
        var params;
        if (typeof(dropNode) == 'string') {
            params = {
                nodeId: dropNode,
                target: target.id
            }
        }
        else {
            params = {
                nodeId: dropNode.id,
                target: target.id
            }
        }
        
        
        var callback = function(o){
            var msg = o.trim();
            if (msg == "success") {
                target.reload();
                parent.reload();
                JDS.exp.refreshAllWin(path);
            }
            else {
                alert(msg);
            }
        };
        var url = JDS.ajax.exp.url.baseurl + JDS.ajax.exp.url.moveto;
        JDS.ajax.Connection.LoadJsonFromUrl(url, callback, $H(params));
    },
    //
    //    getAttrib: function(param){
    //        var callback = function(o){
    //            var msg = o.trim();
    //            if (msg == "success") {
    //
    //            }
    //            else {
    //                alert(msg);
    //            }
    //        };
    //        var url = JDS.ajax.explorer.url.baseurl + JDS.ajax.explorer.url.getAttrib;
    //        JDS.ajax.Connection.LoadJsonFromUrl(url, callback, $H(param));
    //    },
    //
    //    loadThumb: function(){
    //
    //    },
    
    getFileName: function(name, opt){
        var param = {
            newName: name
        };
        var callback = function(o){
            var msg = o.trim();
            var url = '/fdt/formeditor/edit.action?fileName=' + msg;
            var bindURL = '/fdt/designer/fdtDesigner.action?fileName=' + msg
            if (opt == 'binding') 
                window.open(bindURL);
            else 
                window.open(url);
        };
        var url = JDS.ajax.exp.url.baseurl + JDS.ajax.exp.url.getFileName;
        JDS.ajax.Connection.LoadJsonFromUrl(url, callback, $H(param));
    },
    /**
     * 下载
     * @param {Object} path
     */
    downloadFile: function(path){
        if (path instanceof Array) {
            path = path.join(",");
        }
        var param = {
            newName: path
        };
        var callback = function(o){
            var msg = o.trim();
            document.location = '/explorer/downloadFile.action?nodeId=' + msg;
        };
        var url = JDS.ajax.exp.url.baseurl + JDS.ajax.exp.url.getFileName;
        JDS.ajax.Connection.LoadJsonFromUrl(url, callback, $H(param));
    },
    
    setMainForm: function(data, name){
    	
    	
        var param = {
            nodeId: data.path
        };
        var callback = function(o){
            //alert(name + ' 成功设为主表单');
            JDS.exp.refreshAllWin(data.pid);
        };
        var url = JDS.ajax.exp.url.baseurl + JDS.ajax.exp.url.setMainForm;
        JDS.ajax.Connection.LoadJsonFromUrl(url, callback, $H(param));
    },
    updateProcess: function(syn,id){
    	//alert(syn);
		var param = {
            synName: syn
        };
        var callback = function(o){
            var msg = o.trim();
            if (msg == "success") 
                JDS.exp.refreshAllWin(id);
                //alert('工作流同步成功');
        };
        var url = JDS.ajax.exp.url.baseurl + JDS.ajax.exp.url.updateProcess;
		//alert(syn);
        JDS.ajax.Connection.LoadJsonFromUrl(url, callback, $H(param));
    },
    uploadFile: function(destPath){
        /**
         *文件上传
         */
        var refresh = destPath;
        var dia = new Ext.ux.UploadDialog.Dialog({
            upload_autostart: true,
            //closeAction: alert("this closeAction"),//'hide',
            reset_on_hide: true,
            allow_close_on_upload: false,
            post_var_name: 'file',
            method: 'post',
            enctype: 'multipart/form-data',
            inputType: 'file',
            url: '/explorer/doUpload.action',
            base_params: {
                'savePath': destPath,
                'fileCaption': destPath
            },
            onCloseButtonClick: function(destPath){//覆盖关闭方法执行刷新
                //setTimeout("refGridById(getActiveFormId())",500);
                this[this.closeAction].call(this);
                //                var refh = App.westTree.getNodeById(refresh);
                //                refh.reload();
                //                Explorer.Manager.render(refresh);
                JDS.exp.refreshAllWin(refresh);
            }
        });
        dia.show();
    },
    renameFile: function(data,name){
    /**
     *重命名
     **/
    	var param = {
            nodeId: data.path,
            newName: name
        };
        var callback = function(o){
             JDS.exp.refreshAllWin(data.pid);
             //alert("重命名成功");
        };
        var url = JDS.ajax.exp.url.baseurl + JDS.ajax.exp.url.rename;
        JDS.ajax.Connection.LoadJsonFromUrl(url, callback, $H(param));
    }
}


/**
 * @JDS.ajax.exp.url
 * @description stores the backend urls
 */
JDS.ajax.exp.url = {
    baseurl: "/explorer/",
    getKeyPath: "getKeyPath.action",
    Folder4tree: "Folder4tree.action",
    folder4folder: "folder4folder.action",
    tree4folder: "tree4folder.action",
    createFolder: 'createFolder.action',
    deleteFile: "deleteFile.action",
    copyto: "copyto.action",
    copy: "copy.action",
    setMainForm: "setMainForm.action",//设置主表单，新增功能
    updateProcess: "updateProcess.action",//同步流程，新增功能
    paste: "paste.action",
    moveto: "moveto.action",
    getAttrib: "getAttrib.action",
    loadThumb: "loadThumb.action",
    rename: "rename.action",
    create: "create.action",
    find: "find.action",
    getFileName: "getFileName.action",
    uploadFile: "uploadFile.action",
    downloadFile: "downloadFile.action"
}

