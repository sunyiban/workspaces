package com.bpms.action;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.CarInfo;
import com.bpms.service.CarInfoService;
import com.bpms.util.WordGeneratorUtil;
import com.bpms.view.model.DataModel;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 车辆信息 控制器
 */
@Namespace("/carInfo")
@Action(value = "carInfoAction")
public class CarInfoAction  extends BaseAction implements ModelDriven<CarInfo>{

	private static final long serialVersionUID = 1L;

	private CarInfo ci = new CarInfo();
	
	@Autowired
	private CarInfoService carInfoService;	
	
	@Override
	public CarInfo getModel() {
		return ci;
	}
	
	public void saveCarInfo(){
		boolean b = false;
		String message = "保存失败";
		b = carInfoService.persistenceCarInfo(ci);
		if(b){
			message = "保存成功";
		}
		OutputJson(new DataModel("提示",message,b,ci));
	}
	
	public void findCarInfoByOrderId(){
		CarInfo carinfo = carInfoService.findCarInfoByOid(ci.getLoanOrderId());
		OutputJson(carinfo);
	}
	
	/**
	 * 导出合同信息
	 */
	public void downloadCarInfo() {
		try {
			CarInfo carInfo = carInfoService.findCarInfoByOid(ci.getLoanOrderId());
			if (carInfo != null) {
				File file = WordGeneratorUtil.createDoc(carInfo,
						File.separator + "temp" + File.separator + "loan"
								+ File.separator + "车辆买卖协议书.ftl");
				
				HttpServletResponse response = ServletActionContext.getResponse();
				// 设置头文件
				response.setContentType("application/octet-stream");
				response.setHeader(
						"Content-disposition",
						"attachment;filename="
								+ URLEncoder.encode(carInfo.getLoaner()+"-车辆买卖协议书"
										+ ".doc", "utf-8"));
				// 写入流中
				IOUtils.write(FileUtils.readFileToByteArray(file),
						response.getOutputStream());
				// 删除文件
				if (file.exists())
					file.delete();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
