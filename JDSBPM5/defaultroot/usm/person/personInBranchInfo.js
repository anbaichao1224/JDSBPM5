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
    bd.createChild({tag: 'h2', html: '部门信息'});



    var tabs = new Ext.FormPanel({
        labelWidth: 75,
        border:false,
        width: 350,

        items: {
            xtype:'tabpanel',
            activeTab: 0,
            defaults:{autoHeight:true, bodyStyle:'padding:10px'}, 
            items:[{
                title:'所选部门信息',
                layout:'form',
                defaults: {width: 230},
                defaultType: 'textfield',

                items: [{
                    fieldLabel: '部门名称',
                    name: 'first',
                    allowBlank:true,
                    value: 'Jack'
                },{

                    fieldLabel: '部门级别',
                    name: 'last',
                    value: 'Slocum'
                },{
                    fieldLabel: '部门描述',
                    name: 'company',
                    value: 'Ext JS'
               
                }]
            },{
                title:'所选部门权限',
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
            },{
                title:'所选部门人员列表',
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


