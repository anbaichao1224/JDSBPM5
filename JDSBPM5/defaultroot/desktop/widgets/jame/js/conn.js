//连接等


Ext.namespace("Jame.backend");

 Jame.backend.callbackFn=function(str,okFn,errorFn){
    var msg=Ext.decode(str);
    if(msg){
      if(msg.status =="ok"){
        if(okFn){
          okFn(msg);
        }
      }else{
        if(errorFn){
          errorFn(msg);
        }else{
          alert(msg.error);
        }
      }
    }
  }

Jame.backend.Connection = {

  /**
	 * @method getNotifications
	 * @public
	 * @description creates the periodically updater and evals its response
	 */
	getNotifications : function(){
		var container = null;
		if(!$('update-container')){
			container = document.createElement("DIV");
			container.id = "update-container";
			document.body.appendChild(container);
		}
		var update = function(el,ret){
			Jame.backend.Xml.parseNotifications(ret.responseXML.firstChild);
		}
		Jame.mgr = new Ext.Updater("update-container");
		Jame.mgr.startAutoRefresh(2, Jame.backend.url.getnotifications);
		Jame.mgr.on("update", update);
	},




	/**
	 * @method login
	 * @public
	 * @description evals the response from the Login Form
	 */
	login : function(f,a){
		f.ownerCt.form.submit({
			reset:false,
			scope: this,
			waitMsg:"正在发送。。。",
			success:function(r){
				Ext.MessageBox.hide();
				Jame.backend.Connection.LoadJsonFromUrl(Jame.backend.url.isconnected,Jame.factory.loginORclient);
			}
		});
	},

	/**
	 * @method sendSubscription
	 * @public
	 * @description sends a subscription type to a specific buddy
	 */
	sendSubscription : function(_buddy,_type){
		var url = Jame.backend.url.subscribe;
		var parseDom = function(XmlEl){
			// TODO
		}
		Jame.backend.Connection.LoadJsonFromUrl(url,parseDom,{name:_buddy,type:_type});
	},

	/**
	 * @method isConnected
	 * @public
	 * @description checks wether the user is already connected or not, if true shows the client widget,
	 * 		if false shows the login widget
	 */
	isConnected : function(){
		Jame.backend.Connection.LoadJsonFromUrl(Jame.backend.url.isconnected,Jame.factory.loginORclient);
	},

	/**
	 * @method logout
	 * @public
	 * @description sends logout to the backend after confirmation
	 */
	logout : function(){
		var exit = function(btn){
			Ext.MessageBox.hide();
			if(btn == "yes"){
				var parseDom = function(e){
					if(e.getAttribute("type") == "success"){
						Ext.WindowMgr.each(function(win){win.close()});
						Ext.ComponentMgr.all.each(function(comp){Ext.ComponentMgr.unregister(comp)})
						Jame.connected = false;
						Jame.mgr.stopAutoRefresh();
			    		Jame.timer.stop();
					}else{
						// TODO
						window.location.reload();
					}
				}
				Jame.backend.Connection.LoadJsonFromUrl(Jame.backend.url.logout, parseDom);
			}
		}
		Ext.Msg.show({
	 		title:'确认退出?',
	 		msg: '是否确认退出？',
	 		buttons: Ext.Msg.YESNO,
			fn: exit,
			icon:Ext.MessageBox.QUESTION
		});
	},



	addBuddyByTree : function(e,a){
		  messageHandBoxProgress('正在读取用户信息','正在读取用户信息','正在读取用户信息');

		   var url=Jame.backend.url.getOrgTree;

		   var evalJs = function(o){
			 Ext.namespace("EVAL");
               eval(o);
               EVAL.openperformwin(Jame.backend.url.getOrgTreeData);
               doOk();
			};
		   Jame.backend.Connection.LoadJsonFromUrl(url,evalJs,({personId:personid,groupId:e.node.id}));

	},


	/**
	 * @method addBuddy
	 * @public
	 * @description adds a buddy to the Jame.ui.RosterTree.rosterBuddys, and destroys the addbuddy widget
	 */
	addBuddy : function(personIds,groupId){
			var url = Jame.backend.url.addbuddy;
		Jame.backend.Connection.LoadJsonFromUrl(url, Jame.backend.callbackFn,{personIds:personIds,groupId:groupId,personId:personid});
	},





	/**
	 * @method addGroup
	 * @public
	 * @description adds a group to Jame.ui.RosterTree.rosterGroups, and destroys the addgroup widget
	 */
	addGroup : function(node,name){
		var url = Jame.backend.url.addgroup;
		
		var evalJs = function(o){
        var msg=Ext.decode(o);
        if(msg&&msg.status=="ok"){
          if(node.setId){//3.0可以直接改id, 2.x不支持,直接改属性会引起ui等多处问题
            node.setId(msg.groupId);
          node.attributes.groupId=msg.groupId;
          }else{
            var t = node.getOwnerTree();
//            if (t) {
//              t.unregisterNode(node);
//              node.id = msg.groupId;
//              t.registerNode(node);
//            }

            var group = new Ext.tree.TreeNode({
              text:node.text,
              iconCls:"display:none!important;",
              expanded:false,
              //    type:"custGroup",
              id:msg.groupId,
              expandable:true,
              allowDrag:false,
              leaf:false
            });
            node.remove();
            t.root.appendChild(group);
            group.attributes.groupId=msg.groupId;
          }
        }else{
          alert(msg.error);
        }

      }
	
			Jame.backend.Connection.LoadJsonFromUrl(url,evalJs,{name:name,personId:personid});
	},

		/**
	 * @method updatePassword
	 * @public
	 * @description adds a group to Jame.ui.RosterTree.rosterGroups, and destroys the addgroup widget
	 */
	updatePassword : function(f,a){
		var url = Jame.backend.url.updatePassword;

		var form=f.ownerCt.form;
		if (form.findField('password').getValue()==null||form.findField('password').getValue()==''){
			 warningMsg('密码不能为空！');
			  return ;
		}
		if (form.findField('password').getValue()!=form.findField('password2').getValue()){
			 warningMsg('两次密码输入不一致请重新输入');
			 return ;
		}


		var evalJs = function(o){
			   Ext.namespace("EVAL");
               eval(o);
			   alertMsg('修改成功');
			   Ext.WindowMgr.get("updatePasswordLayout").hide();
			}

		Jame.backend.Connection.LoadJsonFromUrl(url,evalJs,{passWord:form.findField('password').getValue(),personId:personid});
	},




	/**
	 * @method removeGroup
	 * @public
	 * @description removes a Group from Jame.ui.RosterTree.rosterGroups after Confirmation
	 */
	removeGroup : function(node) {
    if (node.attributes.type == "SYSTEM") {
      Ext.MessageBox.show({title:"提示",msg:"不能删除默认组",buttons:Ext.MessageBox.OK});
      return;
    }
    var deleteem = function(btn){
			if(btn == "yes"){
				var url = Jame.backend.url.deletegroup;
				var evalJs = function(o){
//						Jame.roster.removeGroup(e.node.text);
          node.remove();
        }
				Jame.backend.Connection.LoadJsonFromUrl(url,evalJs,{name:node.text,groupId:node.id,personId:personid});
			}
		}
		Ext.MessageBox.show({
	 		title:'确认删除',
	 		msg: '删除组会同时删除其下的用户,<br>您确认删除组['+node.text+"]?",
	 		buttons: Ext.MessageBox.YESNOCANCEL,
			fn: deleteem,
			icon:Ext.MessageBox.QUESTION,
			group:node.text
		});
	},

	/**
	 * @method removeBuddy
	 * @public
	 * @description removes a buddy from Jame.ui.RosterTree.rosterBuddys after confirmation
	 */
	removeBuddy : function(node){
    var deleteem = function(btn){
			if(btn == "yes"){
				var url = Jame.backend.url.removeBuddy;

				var evalJs = function(o){
				var msg=Ext.decode(o);
          if(msg.status=="ok"){
            node.remove();
          }else{
            alert(msg.error);
          }
        }
        Jame.backend.Connection.LoadJsonFromUrl(url,evalJs,{personId:personid,personIds:node.id,name:node.text,groupId:node.attributes.groupId});
			}
		}
		Ext.MessageBox.show({
	 		title:'确认删除',
	 		msg: '您确认删除 '+node.text,
	 		buttons: Ext.MessageBox.YESNOCANCEL,
			fn: deleteem,
			icon:Ext.MessageBox.QUESTION
		});
	},

	/**
	 * @method renameBuddy
	 * @public
	 * @description renames a buddy from Jame.ui.RosterTree.rosterBuddys
	 */
//	renameBuddy : function(_te,_new,_old){
//		var jid = _te.editNode.attributes["personId"];
//		var url = Jame.backend.url.renamebuddy;
//		var parseDom = function(XmlEl){
//			// TODO
//		}
//		Jame.backend.Connection.LoadJsonFromUrl(url,parseDom,({name:jid,newname:_new}));
//	},

	/**
	 * @method renameGroup
	 * @public
	 * @description renames a group from Jame.ui.RosterTree.rosterGroups
	 */
	renameGroup : function(node,_new){
		var url = Jame.backend.url.renamegroup;
		var evalJs = function(o){
			}
		Jame.backend.Connection.LoadJsonFromUrl(url,evalJs,({groupId:node.id,newname:_new,personId:personid}));
	},

	/**
	 * @method switchUserGroup
	 * @public
	 * @description switches a buddy from Jame.ui.RosterTree.rosterBuddys into another group
	 */
	switchUserGroup : function(_jid,_new,_old){

		var url = Jame.backend.url.switchusergroup;

		var parseDom = function(XmlEl){
			// TODO
		}
    Jame.backend.Connection.LoadJsonFromUrl(url,parseDom,({newGroupId:_new,oldGroupId:_old,ipersonId:_jid,personId:personid}));
	},

	/**
	 * @method LoadJsonFromUrl
	 * @public
	 * @description sends a Request with the specified parameters to the specified url, and calls the specified
	 * 		callback function on success
	 */
	LoadJsonFromUrl : function(_url,callback,args){
		if(!args)
			args = {};

			//alert(args.toQueryString());
		var cfg={url:_url,
      method:"post",
      param : args,
      okFn: function(o) {
        if (o && o.responseText) {
          callback(o.responseText);
        }
      },
      errorFn : function(transport) {
        Ext.Msg.show({
          title:'服务器错误',
          msg: '与服务器通讯失败!',
          buttons: Ext.MessageBox.OK,
          fn:callback,
          icon:Ext.MessageBox.ERROR
        });
      }
    };
    requestUrl(cfg);
  }
}

Ext.namespace("Jame.backend");
Jame.backend.url = {
	baseurl : "/jame/",
	setpresence : context+"jame/updateStatus.action?personId="+personid,//更改在线状态
 	updatePassword:context+"jame/updatePassWord.action",


  allTree:context+"jame/getAllTreeData.action?personId="+personid,
  sendmessage : context+"jame/addMsg.action",//发送消息
  sendGroupMessage : context+"jame/addGroupMsg.action",//发送 组 消息
  addbuddy : context+"jame/addPerson.action",//添加人
	addgroup : context+"jame/addGroup.action",//添加组
	removeBuddy : context+"jame/deletePerson.action",//删除人
	deletegroup : context+"jame/deleteGroup.action",//删除组
	
	renamegroup :context+"jame/renameGroup.action",//修改组名
	switchusergroup : context+"jame/switchUserGroup.action",//分组拖动
	hisMsg : context+"jame/hisMsg.action"

  ,chatFileBeforeSend:context+"jame/chatFileBeforeSend.action"
  ,chatFileBeforeRec:context+"jame/chatFileBeforeRec.action"
  ,chatFileUpdatePro:context+"jame/chatFileUpdatePro.action"
}