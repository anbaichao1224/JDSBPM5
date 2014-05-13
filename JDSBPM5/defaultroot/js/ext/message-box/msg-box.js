/*
 * Ext JS Library 2.0
 * Copyright(c) 2006-2007, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */

Ext.onReady(function(){
   
});
function   messageBoxConfirm (txt){
        Ext.MessageBox.confirm('Confirm', txt, showResult);
    };

     function   messageBoxPrompt (txt){
        Ext.MessageBox.prompt('Name', txt, showResultText);
    };

    function   messageBoxMultilineInput (ttitle,txt,el){
        Ext.MessageBox.show({
           title: ttitle,
           msg: txt,
           width:300,
           buttons: Ext.MessageBox.OKCANCEL,
           multiline: true,
           fn: showResultText,
           animEl: el
       });
    };

    function   messageBoxYESNOCANCEL (ttitle,txt,el){
        Ext.MessageBox.show({
           title:ttitle,
           msg:txt,
           buttons: Ext.MessageBox.YESNOCANCEL,
           fn: showResult,
           animEl:el,
           icon: Ext.MessageBox.QUESTION
       });
    };


  function messageBoxprogress(ttitle,progressText,txt,k,el,functionName){
     if (!k)
    {
	k=6;
    }
   Ext.MessageBox.show({
           title: ttitle,
           msg: txt,
           progressText: progressText,
           width:300,
           progress:true,
           closable:false,
           animEl:el
       });

       // this hideous block creates the bogus progress
       var f = function(v){
            return function(){
                if(v == k-1){
                    Ext.MessageBox.hide();
                    if (functionName){
                    eval(functionName);
                    }                   
                }else{
                    var i = v/(k-2);
                    Ext.MessageBox.updateProgress(i, Math.round(100*i)+'% completed');
                }
           };
       };
       for(var i = 1; i < k; i++){
           setTimeout(f(i), i*500);
       }
}

 function doOk(title,txt){
   Ext.MessageBox.hide();
   //Ext.example.msg(title,txt);
 }


  function messageHandBoxProgress(ttitle,progressText,txt,el){
        Ext.MessageBox.show({
           msg: txt,
           progressText: progressText,
           width:300,
           wait:true,
		   title:ttitle,
          // waitConfig: {interval:200},
           icon:'ext-mb-download', //custom class in msg-box.html
           animEl:el
       });
//        setTimeout(function(){
//            Ext.MessageBox.hide();
//            Ext.example.msg('Done', 'Your fake data was saved!');
//        }, 8000);
    };
   
   function MessageAlert(txt,title){
        Ext.MessageBox.alert(title, txt, showResult);
    };



    function showResult(btn){
        Ext.example.msg('Button Click', 'You clicked the {0} button', btn);
    };

    function showResultText(btn, text){
        Ext.example.msg('Button Click', 'You clicked the {0} button and entered the text "{1}".', btn, text);
    }