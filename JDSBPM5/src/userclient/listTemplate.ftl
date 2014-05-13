package ${package};

import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.worklist.list.support.annotation.ColumnDefine;
import net.itjds.worklist.list.support.annotation.FrameDefine;
import net.itjds.worklist.list.support.annotation.enums.ElementAlign;
import net.itjds.worklist.list.support.annotation.enums.ElementButton;
import net.itjds.worklist.list.support.annotation.enums.ElementTbar;


@EsbBeanAnnotation(id="${frame.id}",
		name="${frame.name}",
		expressionArr="${frame.id}()",
		desc="${frame.desc}",
		dataType="action")
@FrameDefine(
		title = "${frame.title}",
		height = ${frame.height},
		width = ${frame.width},<#if frame.tbar??>
		tbar = ElementTbar.${frame.tbar},</#if><#if frame.buttons??>
		buttons = {<#list frame.buttons as button>ElementButton.${button}<#if button_has_next>,</#if></#list>},</#if>
		hasRowNumber = ${frame.hasRowNumber},
		hasCheckBox = ${frame.hasCheckBox},
		buttonAlign = ElementAlign.${frame.buttonAlign},
		url = "${frame.url}",
		root = "${frame.root}",
		pageSize = ${frame.pageSize}
)
public class ${frame.id}{
	private ActivityInst activityInst;
	<#list columns as col>
	
	@ColumnDefine(
		header = "${col.header}",
		width = ${col.width},
		sortable = ${col.sortable},
		hidden = ${col.hidden},
		tooltip = "${col.tooltip}",
		align = ElementAlign.${col.align},
		mapping = "${col.colName}"
	)
	private String ${col.colName};
	</#list> 
	
	public ActivityInst getActivityInst() {
		return activityInst;
	}

	public void setActivityInst(ActivityInst activityInst) {
		this.activityInst = activityInst;
	}
	
	<#list columns as col>
	public void set${col.colName?cap_first}(String ${col.colName}){
		this.${col.colName} = ${col.colName};
	}
	
	public String get${col.colName?cap_first}(){
		return this.${col.colName};
	}
	
	</#list> 
}