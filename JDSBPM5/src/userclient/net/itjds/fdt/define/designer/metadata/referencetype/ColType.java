package net.itjds.fdt.define.designer.metadata.referencetype;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author arnold
 * @version $Id: ColType.java,v 1.1 2011/06/09 14:42:02 administrator Exp $
 * @since 2008-1-3
 */
public enum ColType implements Type
{
	TextType("文本","net.itjds.fdt.define.designer.metadata.datatype.TextType"),
   // NormalType("常规","net.itjds.fdt.define.designer.metadata.datatype.TextType"),
    DateType("日期","net.itjds.fdt.define.designer.metadata.datatype.DateType"),
    TimeType("时间","net.itjds.fdt.define.designer.metadata.datatype.TimeType"),
    NumberType("数值","net.itjds.fdt.define.designer.metadata.datatype.NumberType"),
    ZiHao("字号","net.itjds.fdt.define.designer.metadata.datatype.ZiHao");
   // PercentType("百分比","net.itjds.fdt.define.designer.metadata.datatype.PercentType"),
   // MoneyType("金额大写","net.itjds.fdt.define.designer.metadata.datatype.MoneyType");
   		
	

    private String caption;
    private String path;
    private static Map mapping;

    ColType(String caption,String path)
    {
        this.caption = caption;
        this.path = path;
    }

    public String getCaption()
    {
        return this.caption;
    }
    public String getPath()
    {
        return this.path;
    }

    public List<String> getCaptions()
    {
        List<String> options = new ArrayList<String>();
        for(ColType colType:ColType.values())
        {
            options.add(colType.getCaption());
        }
        return options;
    }

    public  List<String> getValues()
    {
        List<String> values = new ArrayList<String>();
        for(ColType colType:ColType.values())
        {
            values.add(colType.name());
        }
        return values;
    }

    public boolean hasSubType()
    {
        return true;
    }

    public boolean hasAssociateClass()
    {
        return true;
    }

    public Map<String,Class> getSubTypeMapping()
    {
        if(null == mapping)
        {
            mapping = new HashMap();
            try
            {
                for(ColType colType:ColType.values())
                {
                    mapping.put(colType.name(),Class.forName(colType.getPath()));
                }
            } catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        return mapping;
    }

    public Class getAssociateClass()
    {
        if(null == mapping)
        {
            mapping = new HashMap();
            try
            {
                for(ColType colType:ColType.values())
                {
                    mapping.put(colType.name(),Class.forName(colType.getCaption()));
                }
            } catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        return (Class)mapping.get(this.name());
    }
}
