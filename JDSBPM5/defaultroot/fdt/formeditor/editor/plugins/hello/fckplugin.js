//注册

 FCKCommands.RegisterCommand('hello',new FCKDialogCommand('hello',FCKLang.HelloB,FCKPlugins.Items['hello'].Path+"hello.html",200,200));

 //定义工具栏

 var NHello=new FCKToolbarButton('hello',FCKLang.Hello);

 NHello.IconPath=FCKPlugins.Items['hello'].Path+'documentprops.gif';

 //注册

 FCKToolbarItems.RegisterItem('hello',NHello);

