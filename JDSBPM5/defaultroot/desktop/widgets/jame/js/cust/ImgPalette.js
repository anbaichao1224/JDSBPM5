

Ext.namespace("Jame");
Jame.ImgPalette = function(config){
    Jame.ImgPalette.superclass.constructor.call(this, config);
    this.addEvents(
        'select'
    );

    if(this.handler){
        this.on("select", this.handler, this.scope, true);
    }
};
Ext.extend(Jame.ImgPalette, Ext.Component, {
    itemCls : "x-color-palette",
    value : null,
    clickEvent:'click',
    // private
    ctype: "Jame.ImgPalette",

    allowReselect : false,

    imgs :[], 

    // private
    onRender : function(container, position){
        var t = this.tpl || new Ext.XTemplate(
            '<tpl for="."><a href="#" class="" hidefocus="on"><em>',
            
            '<img src="{url}" imgidx="{#}" imgid="{id}" title="{tip}" width=20 height=20>',
            '</em></a></tpl>'
        );
        var el = document.createElement("div");
        el.id = this.getId();
        el.className = this.itemCls;
        t.overwrite(el, this.imgs);
        container.dom.insertBefore(el, position);
        this.el = Ext.get(el);

        if (extVer >= 3) {
          this.mon(this.el, this.clickEvent, this.handleClick, this, {delegate: 'a'});
          if (this.clickEvent != 'click') {
            this.mon(this.el, 'click', Ext.emptyFn, this, {delegate: 'a', preventDefault: true});
          }
        } else {

          this.el.on(this.clickEvent, this.handleClick, this, {delegate: "a"});
          if (this.clickEvent != 'click') {
            this.el.on('click', Ext.emptyFn, this, {delegate: "a", preventDefault:true});
          }
        }
    },

    // private
    afterRender : function(){
        Jame.ImgPalette.superclass.afterRender.call(this);
        if(this.value){
            var s = this.value;
            this.value = null;
            this.select(s);
        }
    },

    // private
    handleClick : function(e, t){
        e.preventDefault();
        if(!this.disabled){
            var c = t.firstChild.firstChild.imgidx;
            c=parseInt(c);
            if(c){
              c=c-1;
              this.select(this.imgs[c]);
            }
        }
    },

    /**
     * Selects the specified color in the palette (fires the {@link #select} event)
     * @param {String} color A valid 6-digit color hex code (# will be stripped if included)
     */
    select : function(img){
        this.value=img;
        this.fireEvent("select",this,img);
    }

});