Ext.onReady(function(){

    Ext.QuickTips.init();

    // turn on validation errors beside the field globally
    Ext.form.Field.prototype.msgTarget = 'side';

    var bd = Ext.getBody();


    /*
     * ================  Simple form  =======================
     */
/*
     * ================  Form 1  =======================
     */
    bd.createChild({tag: 'h2', html: '人员信息'});



    var tabs = new Ext.FormPanel({
        labelWidth: 75,
        border:false,
        width: 350,

        items: {
            xtype:'tabpanel',
            activeTab: 0,
            defaults:{autoHeight:true, bodyStyle:'padding:10px'}, 
            items:[{
                title:'所选人员信息',
                layout:'form',
                defaults: {width: 230},
                defaultType: 'textfield',

                items: [{
                        fieldLabel: '姓名(中)',
                        name: 'chinese',
                        width:190
                    }, {
                        fieldLabel: '姓名(英)',
                        name: 'English',
                        width:190
                    }, {
                        fieldLabel: '密码',
                        name: 'password',
                        width:190
                    }, {
                        fieldLabel: '部门',
                        name: 'department',
                        width:190
                    }, {
                        fieldLabel: '职务',
                        name: 'duty',
                        width:190
                    }, {
                        fieldLabel: 'Email',
                        name: 'email',
                        vtype:'email',
                        width:190
                    }]
            },{
                title:'所选人员基本权限',
                layout:'form',
                defaults: {width: 230},
                defaultType: 'textfield',

                items: [{

                    fieldLabel: 'Home',
                    name: 'home',
                    value: '(888) 555-1212'
                },{
                    fieldLabel: 'Business',
                    name: 'business'
                },{
                    fieldLabel: 'Mobile',
                    name: 'mobile'
                },{
                    fieldLabel: 'Fax',
                    name: 'fax'
                }]
            
            }]
        },

        buttons: [{
            text: '增加'
        },{
            text: '删除'
        },{
            text: '修改'
        }]
    });

    tabs.render(document.body);

});


