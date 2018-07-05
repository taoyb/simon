package com.activiti.utils;
/**
 * @Author TYB
 * @Date 2018-01-15 下午 2:26
 * @Desc 流程定义KEY
 */
public enum ProcessEnum {
	//Variable 开始
	outcome("outcome"),//工作流点击按钮结果
	model("model"),
	//Variable 结束
	LEAVE("LEAVE");//请假审批
	private ProcessEnum(String value) {
		this.value = value;
	}
	
	private String value;
	
	public String getValue() {
		return value;
	}
	
}