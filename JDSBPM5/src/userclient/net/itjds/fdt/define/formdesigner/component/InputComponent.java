package net.itjds.fdt.define.formdesigner.component;

import net.itjds.fdt.define.designer.core.AbstractDefine;
import net.itjds.fdt.define.designer.core.ComponentAbstractDefine;
import net.itjds.fdt.define.designer.metadata.attribute.InputAttribute;
import org.htmlparser.Parser;

/**
 * @author arnold
 * @version $Id: InputComponent.java,v 1.1 2011/06/09 14:43:16 administrator Exp $
 * @desc
 * @since 2009-2-22
 */
public class InputComponent implements Component
{
    private InputAttribute inputAttribute = new InputAttribute();

    public void build(Object obj, Parser parser)
    {
        inputAttribute.build(obj,parser);
    }

    public ComponentAbstractDefine getComponentAttribute()
    {
        return inputAttribute;
    }

    public String getComponentAttributePrefix()
    {
        return "inputAttribute";
    }

    public InputAttribute getInputAttribute() {
        return inputAttribute;
    }

    public void setInputAttribute(InputAttribute inputAttribute) {
        this.inputAttribute = inputAttribute;
    }
}
