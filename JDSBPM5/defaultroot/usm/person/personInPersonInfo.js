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
    bd.createChild({tag: 'h2', html: '��Ա��Ϣ'});



    var tabs = new Ext.FormPanel({
        labelWidth: 75,
        border:false,
        width: 350,

        items: {
            xtype:'tabpanel',
            activeTab: 0,
            defaults:{autoHeight:true, bodyStyle:'padding:10px'}, 
            items:[{
                title:'��ѡ��Ա��Ϣ',
                layout:'form',
                defaults: {width: 230},
                defaultType: 'textfield',

                items: [{
                        fieldLabel: '����(��)',
                        name: 'chinese',
                        width:190
                    }, {
                        fieldLabel: '����(Ӣ)',
                        name: 'English',
                        width:190
                    }, {
                        fieldLabel: '����',
                        name: 'password',
                        width:190
                    }, {
                        fieldLabel: '����',
                        name: 'department',
                        width:190
                    }, {
                        fieldLabel: 'ְ��',
                        name: 'duty',
                        width:190
                    }, {
                        fieldLabel: 'Email',
                        name: 'email',
                        vtype:'email',
                        width:190
                    }]
            },{
                title:'��ѡ��Ա����Ȩ��',
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


