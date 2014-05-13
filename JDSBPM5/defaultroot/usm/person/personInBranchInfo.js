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
    bd.createChild({tag: 'h2', html: '������Ϣ'});



    var tabs = new Ext.FormPanel({
        labelWidth: 75,
        border:false,
        width: 350,

        items: {
            xtype:'tabpanel',
            activeTab: 0,
            defaults:{autoHeight:true, bodyStyle:'padding:10px'}, 
            items:[{
                title:'��ѡ������Ϣ',
                layout:'form',
                defaults: {width: 230},
                defaultType: 'textfield',

                items: [{
                    fieldLabel: '��������',
                    name: 'first',
                    allowBlank:true,
                    value: 'Jack'
                },{

                    fieldLabel: '���ż���',
                    name: 'last',
                    value: 'Slocum'
                },{
                    fieldLabel: '��������',
                    name: 'company',
                    value: 'Ext JS'
               
                }]
            },{
                title:'��ѡ����Ȩ��',
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
                title:'��ѡ������Ա�б�',
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
            text: '����'
        },{
            text: 'ɾ��'
        },{
            text: '�޸�'
        }]
    });

    tabs.render(document.body);

});


