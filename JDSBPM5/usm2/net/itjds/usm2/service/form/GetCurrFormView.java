package net.itjds.usm2.service.form;

import java.util.Map;

import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;

import net.itjds.common.expression.function.AbstractFunction;
import net.itjds.common.logging.Log;
import net.itjds.common.logging.LogFactory;
import net.itjds.common.util.ClassUtility;

import net.itjds.usm2.UsmProxy;
import net.itjds.usm2.db.UsmService;
import net.itjds.usm2.db.util.ClassUtil;
import net.itjds.usm2.db.util.EsbUtil;
import net.itjds.usm2.define.mapping.PanelBean;
@EsbBeanAnnotation(id="currFormView",
		name = "获取当前行的数据集", 
		expressionArr = "GetCurrFormView($currFormUsmService(),$currFormPanelBean(),$currFormData())", 
		desc="获取当前行的数据集",
		dataType="action"
		)
	
public class GetCurrFormView extends AbstractFunction{
	protected transient static final Log log = LogFactory.getLog(
			"currFormView", GetCurrFormView.class);
	public Object  perform( UsmService service,PanelBean editViewBean,UsmProxy proxy) {
		Object obj=null;
		Map esbContextMap = EsbUtil.getContextMap();
		if (esbContextMap.containsKey(editViewBean.getPath())){;
		  obj= esbContextMap.get(editViewBean.getPath());
		}else{
			try {
				obj=ClassUtil.getUsmViewInstance(editViewBean.getClazz(), proxy);
			} catch (Exception e) {
				e.printStackTrace();
			}
			esbContextMap.put(editViewBean.getPath(), obj);
		}
		
		return obj;
	}
	
	

}
