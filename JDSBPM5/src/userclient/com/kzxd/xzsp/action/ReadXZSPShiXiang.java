package com.kzxd.xzsp.action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.itjds.common.database.DBBeanBase;
import net.itjds.j2ee.util.UUID;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.kzxd.ftp.FTP;
import com.kzxd.xzsp.util.Acceptnode;
import com.kzxd.xzsp.util.Application1;
import com.kzxd.xzsp.util.Applynode;
import com.kzxd.xzsp.util.Handlenode;
import com.kzxd.xzsp.util.Material;
import com.kzxd.xzsp.util.Node;
import com.kzxd.xzsp.util.Notifynode;
import com.kzxd.xzsp.util.Permission;

public class ReadXZSPShiXiang  {

	//����ҵ����ˮ��bsnum�ж��Ƿ��Ѿ�����
	public Permission sfczBsnum(String bsnum){
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = dbbase.getConn();
		String sqlStr = "select * from xzspjk_permission t where t.bsnum = '"+bsnum+"'";
		Statement st = null;
		ResultSet rs = null;
		
		Permission per = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sqlStr);
			if(rs != null && rs.next()){
				per = new Permission();
				per.setUuid(rs.getString("uuid"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null){
					rs.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(st != null){
					st.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(conn != null){
					conn.close();
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {				
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}			
		return per;
	}
	
	public Integer saveOrUpdateBusinessCollect(String businessxml, String authenticatexml) {
		Application1 application = null;
		Permission permission = null;
		Material material = null;
		Node node = null;
		Notifynode notifynode = null;
		Acceptnode acceptnode = null;
		Applynode applynode = null;
		Handlenode handlenode = null;

		List<Permission> permissions = null;
		List<Material> materials = null;
		List<Node> nodes = null;
		List<Notifynode> notifynodes = null;
		List<Acceptnode> acceptnodes = null;
		List<Applynode> applynodes = null;
		List<Handlenode> handlenodes = null;
		List<Application1> applications = null;
		
		List<Element> materialEles = null;
		List<Element> nodeEles = null;
		List<Element> notifynodeEles = null;
		List<Element> acceptnodeEles = null;
		List<Element> applynodeEles = null;
		List<Element> handlenodeEles = null;
		List<Element> applicationEles = null;
		List<Element> dds = null;
		
		SAXReader reader = new SAXReader();
		
		applications = new ArrayList<Application1>();
		permissions = new ArrayList<Permission>();
		materials = new ArrayList<Material>();
		nodes = new ArrayList<Node>();
		notifynodes = new ArrayList<Notifynode>();
		acceptnodes = new ArrayList<Acceptnode>();
		applynodes = new ArrayList<Applynode>();
		handlenodes = new ArrayList<Handlenode>();

		application = new Application1();
		
		String bsnum = null;
		
		try {
			InputStream is = new ByteArrayInputStream(businessxml.getBytes("utf-8"));
			Document document = reader.read(is);
			InputStream iis = new ByteArrayInputStream(authenticatexml.getBytes("utf-8"));
			Document dd = reader.read(iis);
			dds = dd.selectNodes("authenticate");
			Element authenticate = dds.get(0);
			String authenticateid = authenticate.elementText("authenticateid");
			//Application  ��������Ϣ
			applicationEles = document.selectNodes("cooperation/application");
			if(applicationEles != null && applicationEles.size() > 0){
				for(Element applicationEle : applicationEles){
					UUID a = new UUID();
					String appUuid = a.toString();
					application.setUuid(appUuid);
					application.setAppname(applicationEle.elementText("appname"));
					application.setApporg(applicationEle.elementText("apporg"));
					application.setCardid(applicationEle.elementText("cardid"));
					application.setAppdate(applicationEle.elementText("appdate"));
					application.setMobilephone(applicationEle.elementText("mobilephone"));
					application.setPhone(applicationEle.elementText("phone"));
					application.setEmail(applicationEle.elementText("email"));
					application.setAddress(applicationEle.elementText("address"));
					application.setAuthenticateid(authenticateid);
					applications.add(application);
				}	
			}
				
			//Permission  ����������Ϣ
			List<Element> permissionEles = document.selectNodes("cooperation/permissions/permission");
			if(permissionEles != null && permissionEles.size() > 0){
				for (Element permissionEle : permissionEles) {
					permission = new Permission();
					UUID p = new UUID();
					String peruuid = (p.toString());
					permission.setUuid(peruuid);
					permission.setId(permissionEle.elementText("id"));
					permission.setName(permissionEle.elementText("name"));
					bsnum = permissionEle.elementText("bsnum");
					permission.setBsnum(bsnum);
					permission.setXmmc(permissionEle.elementText("xmmc"));
					permission.setDepartment(permissionEle.elementText("department"));
					permission.setStatus(permissionEle.elementText("status"));
					permission.setApplication1(application);
					permission.setFlag("0");
					permissions.add(permission);
					
					//��õ�ǰ�ڵ���ӽڵ�   Document�൱��Element
					//Material  ��������
					materialEles = permissionEle.selectNodes("materials/material");
					if(materialEles != null && materialEles.size() > 0){
						for (Element materialEle : materialEles) {
							material = new Material();
							UUID m = new UUID();
							material.setUuid(m.toString());
							material.setId(materialEle.elementText("id"));
							material.setMlid(materialEle.elementText("mlid"));
							material.setMlname(materialEle.elementText("mlname"));
							material.setSelecttype(materialEle.elementText("selecttype"));
							material.setFid(materialEle.elementText("fid"));
							material.setFname(materialEle.elementText("fname"));
							material.setStatus(materialEle.elementText("status"));
							material.setOrinum(materialEle.elementText("orinum"));
							material.setCopynum(materialEle.elementText("copynum"));
							material.setIsneed(materialEle.elementText("isneed"));
							material.setBaseinfo(materialEle.elementText("baseinfo"));
							material.setAdjustment(materialEle.elementText("adjustment"));
							material.setPermission(permission);
							FTP ftp = new FTP();
							String fpath="";
							String fpath1 = materialEle.elementText("fpath");
							if(fpath1 != null && fpath1.length()>0){
								fpath = ftp.testDownload(fpath1, materialEle.elementText("fname"), bsnum);
							}
							material.setFpath(fpath);
							materials.add(material);
						}				
					}
					
					//Node  ��������
					nodeEles = permissionEle.selectNodes("nodes/node");
					if(nodeEles != null && nodeEles.size() > 0){
						for (Element nodeEle : nodeEles) {
							node = new Node();
							UUID n = new UUID();
							node.setUuid(n.toString());
							node.setNodeid(nodeEle.elementText("nodeid"));
							node.setNodename(nodeEle.elementText("nodename"));
							node.setNodeactor(nodeEle.elementText("nodeactor"));
							node.setNodeactorgh(nodeEle.elementText("nodeactorgh"));
							node.setNodeactorzwmc(nodeEle.elementText("nodeactorzwmc"));
							node.setNodeactorzwdm(nodeEle.elementText("nodeactorzwdm"));
							node.setDepartment(nodeEle.elementText("department"));
							node.setHandlerdate(nodeEle.elementText("handlerdate"));
							node.setHandleridea(nodeEle.elementText("handleridea"));
							node.setPermission(permission);
							nodes.add(node);
						}				
					}
					//Notifynode  ������֪����
					notifynodeEles = permissionEle.selectNodes("nodes/complementnode/notifynode");
					if(notifynodeEles != null && notifynodeEles.size() > 0){
						for (Element element : notifynodeEles) {
							notifynode = new Notifynode();
							UUID no = new UUID();
							notifynode.setUuid(no.toString());
							notifynode.setNodeactor(element.elementText("nodeactor"));
							notifynode.setComplementdate(element.elementText("complementdate"));
							notifynode.setComplementidea(element.elementText("complementidea"));
							notifynode.setComplementlist(element.elementText("complementlist"));
							notifynode.setDepartment(element.elementText("department"));
							notifynode.setPermission(permission);
							notifynodes.add(notifynode);
						}
					}
					//Acceptnodes   ���������� 
					acceptnodeEles = permissionEle.selectNodes("nodes/complementnode/acceptnode");
					if(acceptnodeEles != null && acceptnodeEles.size() > 0){
						for (Element element : acceptnodeEles) {
							acceptnode = new Acceptnode();
							UUID aa = new UUID();
							acceptnode.setUuid(aa.toString());
							acceptnode.setHandler(element.elementText("handler"));
							acceptnode.setHandlerdate(element.elementText("handlerdate"));
							acceptnode.setHandleraddr(element.elementText("handleraddr"));
							acceptnode.setHandlerlist(element.elementText("handlerlist"));
							acceptnode.setDepartment(element.elementText("department"));
							acceptnode.setPermission(permission);
							acceptnodes.add(acceptnode);
						}
					}
					//Applynode    �ر�������뻷��
					applynodeEles = permissionEle.selectNodes("nodes/specialnode/applynode");
					if(applynodeEles != null && applynodeEles.size() > 0){
						for (Element element : applynodeEles) {
							applynode = new Applynode();
							UUID ap = new UUID();
							applynode.setUuid(ap.toString());
							applynode.setSpecialtype(element.elementText("specialtype"));
							applynode.setSpecialname(element.elementText("specialname"));
							applynode.setSpecialstartdate(element.elementText("specialstartdate"));
							applynode.setSpecialuser(element.elementText("specialuser"));
							applynode.setSpecialusertel(element.elementText("specialusertel"));
							applynode.setSpecialuserphone(element.elementText("specialuserphone"));
							applynode.setSpecialidea(element.elementText("specialidea"));
							applynode.setSpecialcontent(element.elementText("specialcontent"));
							applynode.setSpeciallimit(element.elementText("speciallimit"));
							applynode.setSpecialunit(element.elementText("specialunit"));
							applynode.setDepartment(element.elementText("department"));
							applynode.setPermission(permission);
							applynodes.add(applynode);
						}
					}
					//Handlenode   �ر��������
					handlenodeEles = permissionEle.selectNodes("nodes/specialnode/handlenode");
					if(handlenodeEles != null && handlenodeEles.size() > 0){
						for (Element element : handlenodeEles) {
							handlenode = new Handlenode();
							UUID h = new UUID();
							handlenode.setUuid(h.toString());
							handlenode.setSpecialresult(element.elementText("specialresult"));
							handlenode.setSpecialresultdate(element.elementText("specialresultdate"));
							handlenode.setSpecialenddate(element.elementText("specialenddate"));
							handlenode.setSpecialpay(element.elementText("specialpay"));
							handlenode.setDepartment(element.elementText("department"));
							handlenode.setPermission(permission);
							handlenodes.add(handlenode);
						}
					}
				}
			}
			
			Statement stmt = null;
			StringBuffer sql = null;
			
			DBBeanBase dbbase = new DBBeanBase("org");			
		    Connection conn = dbbase.getConn();
		    
			try {
				stmt=conn.createStatement();	
				Permission perm = this.sfczBsnum(bsnum);
				
				//����bsnum ������޸Ĳ���------------------------------------------------------------
				if(perm != null){ 
					
					//����Acceptnodes  ����  ��������--------
					if(acceptnodes != null && acceptnodes.size() > 0){
						for(Acceptnode ac : acceptnodes){
							sql = new StringBuffer();
							sql.append("insert into xzspjk_acceptnode(uuid,handler,handlerdate,handleraddr,handlerlist,department,permissionid ) ");
							sql.append("values ('");
							sql.append(ac.getUuid());
							sql.append("','");
							sql.append(ac.getHandler());
							sql.append("','");
							sql.append(ac.getHandlerdate());
							sql.append("','");
							sql.append(ac.getHandleraddr());
							sql.append("','");
							sql.append(ac.getHandlerlist());
							sql.append("','");
							sql.append(ac.getDepartment());
							sql.append("','");
							sql.append(ac.getPermission().getUuid());
							sql.append("')");
							stmt.executeUpdate(sql.toString());
						}
					}
					
					//����Handlenode����  �ر���������------------
					if(handlenodes != null && handlenodes.size() > 0){
						for (Handlenode han : handlenodes) {
							sql = new StringBuffer();
							sql.append("insert into xzspjk_handlenode (uuid,specialresult,specialresultdate,specialenddate,specialpay,department,permissionid ) ");
							sql.append("values ('");
							sql.append(han.getUuid());
							sql.append("','");
							sql.append(han.getSpecialresult());
							sql.append("','");
							sql.append(han.getSpecialresultdate());
							sql.append("','");
							sql.append(han.getSpecialenddate());
							sql.append("','");
							sql.append(han.getSpecialpay());
							sql.append("','");
							sql.append(han.getDepartment());
							sql.append("','");
							sql.append(han.getPermission().getUuid());
							sql.append("')");
							stmt.executeUpdate(sql.toString());
						}
					}
					//����Material�����id�ж��Ƿ��Ѿ�����  ������ֱ���޸� ��������ֱ�ӱ���
					if(materials != null && materials.size() > 0){
						for(Material ma : materials){
							XZSPAction xzspAction = new XZSPAction();
							boolean flag = xzspAction.findMaterialById(material.getId());
							
							if(!flag){//������ �򱣴�
								sql = new StringBuffer();
								sql.append("insert into xzspjk_material (uuid,id,mlid,mlname,selecttype,fid,fpath,fname,status,orinum,copynum,isneed,baseinfo,adjustment,permissionid ) ");
								sql.append("values ('");
								sql.append(ma.getUuid() );
								sql.append("','");
								sql.append(ma.getId());
								sql.append("','");
								sql.append(ma.getMlid());
								sql.append("','");
								sql.append(ma.getMlname());
								sql.append("','");
								sql.append(ma.getSelecttype());
								sql.append("','");
								sql.append(ma.getFid());
								sql.append("','");
								sql.append(ma.getFpath());
								sql.append("','");
								sql.append(ma.getFname());
								sql.append("','");
								sql.append(ma.getStatus());
								sql.append("','");
								sql.append(ma.getOrinum());
								sql.append("','");
								sql.append(ma.getCopynum());
								sql.append("','");
								sql.append(ma.getIsneed());
								sql.append("','");
								sql.append(ma.getBaseinfo());
								sql.append("','");
								sql.append(ma.getAdjustment());
								sql.append("','");
								sql.append(ma.getPermission().getUuid());
								sql.append("')");
								stmt.executeUpdate(sql.toString());
							}else{//����   ���޸�
								sql = new StringBuffer();
								sql.append("update xzspjk_material t set t.id='");
								sql.append(ma.getId());
								sql.append("',t.mlid='");
								sql.append(ma.getMlid());
								sql.append("',t.mlname='");
								sql.append(ma.getMlname());
								sql.append("',t.selecttype='");
								sql.append(ma.getSelecttype());
								sql.append("',t.fid='");
								sql.append(ma.getFid());
								sql.append("',t.fpath='");
								sql.append(ma.getFpath());
								sql.append("',t.fname='");
								sql.append(ma.getFname());
								sql.append("',t.status='");
								sql.append(ma.getStatus());
								sql.append("',t.orinum='");
								sql.append(ma.getOrinum());
								sql.append("',t.copynum='");
								sql.append(ma.getCopynum());
								sql.append("',t.isneed='");
								sql.append(ma.getIsneed());
								sql.append("',t.baseinfo='");
								sql.append(ma.getBaseinfo());
								sql.append("',t.adjustment='");
								sql.append(ma.getAdjustment());
								sql.append("')");
							}
						}
					}
					
				}else{
					
					//����Application1
					if(applications != null && applications.size() > 0){
						for (Application1 aa : applications) {
							sql = new StringBuffer();
							sql.append("insert into xzspjk_application (uuid,appname,apporg,cardid,appdate,mobilephone,phone,email,address,authenticateid) ");
							sql.append("values ('");
							sql.append(aa.getUuid());
							sql.append("','");
							sql.append(aa.getAppname());
							sql.append("','");
							sql.append(aa.getApporg());
							sql.append("','");
							sql.append(aa.getCardid());
							sql.append("','");
							sql.append(aa.getAppdate());
							sql.append("','");
							sql.append(aa.getMobilephone());
							sql.append("','");
							sql.append(aa.getPhone());
							sql.append("','");
							sql.append(aa.getEmail());
							sql.append("','");
							sql.append(aa.getAddress());
							sql.append("','");
							sql.append(aa.getAuthenticateid());
							sql.append("')");
							stmt.executeUpdate(sql.toString());
						}
					}
					
					//����Permission����
					if(permissions != null && permissions.size() > 0){
						for (Permission per : permissions) {
							 sql = new StringBuffer();
							 sql.append("insert into xzspjk_permission (uuid,id,name,xmmc,department,status,applicationid,bsnum,flag) ");
							 sql.append(" values ('");
							 sql.append(per.getUuid());
							 sql.append("','");
							 sql.append(per.getId());
							 sql.append("','");
							 sql.append(per.getName());
							 sql.append("','");
							 sql.append(per.getXmmc());
							 sql.append("','");
							 sql.append(per.getDepartment());
							 sql.append("','");
							 sql.append(per.getStatus());
							 sql.append("','");
							 sql.append(per.getApplication1().getUuid());
							 sql.append("','");
							 sql.append(per.getBsnum());
							 sql.append("','");
							 sql.append(per.getFlag());
							 sql.append("')");
							 stmt.executeUpdate(sql.toString());
						}
					}
					
					//����Material����
					if(materials != null && materials.size() > 0){
						for(Material ma : materials){
							sql = new StringBuffer();
							sql.append("insert into xzspjk_material (uuid,id,mlid,mlname,selecttype,fid,fpath,fname,status,orinum,copynum,isneed,baseinfo,adjustment,permissionid ) ");
							sql.append("values ('");
							sql.append(ma.getUuid() );
							sql.append("','");
							sql.append(ma.getId());
							sql.append("','");
							sql.append(ma.getMlid());
							sql.append("','");
							sql.append(ma.getMlname());
							sql.append("','");
							sql.append(ma.getSelecttype());
							sql.append("','");
							sql.append(ma.getFid());
							sql.append("','");
							sql.append(ma.getFpath());
							sql.append("','");
							sql.append(ma.getFname());
							sql.append("','");
							sql.append(ma.getStatus());
							sql.append("','");
							sql.append(ma.getOrinum());
							sql.append("','");
							sql.append(ma.getCopynum());
							sql.append("','");
							sql.append(ma.getIsneed());
							sql.append("','");
							sql.append(ma.getBaseinfo());
							sql.append("','");
							sql.append(ma.getAdjustment());
							sql.append("','");
							sql.append(ma.getPermission().getUuid());
							sql.append("')");
							stmt.executeUpdate(sql.toString());
						}
					}
					
					//����Node����
					if(nodes != null && nodes.size() > 0){
						for(Node nn : nodes){
							sql = new StringBuffer();
							sql.append("insert into xzspjk_node (uuid,nodeid,nodename,nodeactor,nodeactorgh,nodeactorzwmc,nodeactorzwdm,department,handlerdate,handleridea,permissionid) ");
							sql.append("values ('");
							sql.append(nn.getUuid());
							sql.append("','");
							sql.append(nn.getNodeid());
							sql.append("','");
							sql.append(nn.getNodename());
							sql.append("','");
							sql.append(nn.getNodeactor());
							sql.append("','");
							sql.append(nn.getNodeactorgh());
							sql.append("','");
							sql.append(nn.getNodeactorzwmc());
							sql.append("','");
							sql.append(nn.getNodeactorzwdm());
							sql.append("','");
							sql.append(nn.getDepartment());
							sql.append("','");
							sql.append(nn.getHandlerdate());
							sql.append("','");
							sql.append(nn.getHandleridea());
							sql.append("','");
							sql.append(nn.getPermission().getUuid());
							sql.append("')");
							stmt.executeUpdate(sql.toString());
						}
					}
				}
					
			} catch (SQLException e) {
				try {
					conn.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
					return 0;
				}
				e.printStackTrace();
			}finally{
				try {
					stmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				try {
					dbbase.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			 
			return 1;
			
			
		}catch (DocumentException e) {
			e.printStackTrace();
			return 0;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return 0;
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
			
	}
	
//	public static void main(String[] args) {
//		ReadXZSPShiXiang rx = new ReadXZSPShiXiang();
//		rx.saveOrUpdateBusinessCollect("src/userclient/com/kzxd/xzsp/xml/lianxi.xml", "");
//	}
	

}




