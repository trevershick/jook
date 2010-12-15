package com.railinc.jook.domain;

import java.io.Serializable;
import java.util.Date;


public class Incident extends DomainObject implements Serializable {
	public Long getRapidCaseId() {
		return rapidCaseId;
	}
	public void setRapidCaseId(Long rapidCaseId) {
		this.rapidCaseId = rapidCaseId;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -5002135557910891560L;
	private String operatingSystem;
	private String browser;
	private String ipAddress;
	private String product;
	private String moduleId;
	private Boolean cookiesEnabled;
	private Long rapidCaseId;
	private Integer screenWidth;
	private Integer screenHeight;
	private Integer browserWidth;
	private Integer browserHeight;
	private String flashVersion;
	private String ssoUserId;
	private String ssoRoles;
	private String name;
	private String company;
	private String phone;
	private Date incidentTime = new Date();
	private String userEmail;
	private Boolean sendDetails;
	private String stackTrace;
	private String requestDetails;
	public String getOperatingSystem() {
		return operatingSystem;
	}
	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public Boolean getCookiesEnabled() {
		return cookiesEnabled;
	}
	public void setCookiesEnabled(Boolean cookiesEnabled) {
		this.cookiesEnabled = cookiesEnabled;
	}
	public Integer getScreenWidth() {
		return screenWidth;
	}
	public void setScreenWidth(Integer screenWidth) {
		this.screenWidth = screenWidth;
	}
	public Integer getScreenHeight() {
		return screenHeight;
	}
	public void setScreenHeight(Integer screenHeight) {
		this.screenHeight = screenHeight;
	}
	public Integer getBrowserWidth() {
		return browserWidth;
	}
	public void setBrowserWidth(Integer browserWidth) {
		this.browserWidth = browserWidth;
	}
	public Integer getBrowserHeight() {
		return browserHeight;
	}
	public void setBrowserHeight(Integer browserHeight) {
		this.browserHeight = browserHeight;
	}
	public String getFlashVersion() {
		return flashVersion;
	}
	public void setFlashVersion(String flashVersion) {
		this.flashVersion = flashVersion;
	}
	public String getSsoUserId() {
		return ssoUserId;
	}
	public void setSsoUserId(String ssoUserId) {
		this.ssoUserId = ssoUserId;
	}
	public String getSsoRoles() {
		return ssoRoles;
	}
	public void setSsoRoles(String ssoRoles) {
		this.ssoRoles = ssoRoles;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getIncidentTime() {
		return incidentTime;
	}
	public void setIncidentTime(Date incidentTime) {
		this.incidentTime = incidentTime;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Boolean getSendDetails() {
		return sendDetails;
	}
	public void setSendDetails(Boolean sendDetails) {
		this.sendDetails = sendDetails;
	}
	public String getStackTrace() {
		return stackTrace;
	}
	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}
	public String getRequestDetails() {
		return requestDetails;
	}
	public void setRequestDetails(String requestDetails) {
		this.requestDetails = requestDetails;
	}
}
