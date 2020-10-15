package com.turing.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CstCustomer implements Serializable {
    private String custNo;

    private String custName;

    private String custRegion;

    private Integer custManagerId;

    private String custManagerName;

    private Integer custLevel;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date custCreateDate;

    private Integer custSatisfy;

    private Integer custCredit;

    private String custAddr;

    private String custZip;

    private String custTel;

    private String custFax;

    private String custWebsite;

    private String custLicenceNo;

    private String custChieftain;

    private Integer custBankroll;

    private Integer custTurnover;

    private String custBank;

    private String custBankAccount;

    private String custLocalTaxNo;

    private String custNationalTaxNo;

    private String custStatus;
    
    private BaseDict baseDict;

	public BaseDict getBaseDict() {
		return baseDict;
	}

	public void setBaseDict(BaseDict baseDict) {
		this.baseDict = baseDict;
	}

	private static final long serialVersionUID = 1L;

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo == null ? null : custNo.trim();
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName == null ? null : custName.trim();
    }

    public String getCustRegion() {
        return custRegion;
    }

    public void setCustRegion(String custRegion) {
        this.custRegion = custRegion == null ? null : custRegion.trim();
    }

    public Integer getCustManagerId() {
        return custManagerId;
    }

    public void setCustManagerId(Integer custManagerId) {
        this.custManagerId = custManagerId;
    }

    public String getCustManagerName() {
        return custManagerName;
    }

    public void setCustManagerName(String custManagerName) {
        this.custManagerName = custManagerName == null ? null : custManagerName.trim();
    }

    public Integer getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(Integer custLevel) {
        this.custLevel = custLevel;
    }

    public Date getCustCreateDate() {
        return custCreateDate;
    }

    public void setCustCreateDate(Date custCreateDate) {
        this.custCreateDate = custCreateDate;
    }

    public Integer getCustSatisfy() {
        return custSatisfy;
    }

    public void setCustSatisfy(Integer custSatisfy) {
        this.custSatisfy = custSatisfy;
    }

    public Integer getCustCredit() {
        return custCredit;
    }

    public void setCustCredit(Integer custCredit) {
        this.custCredit = custCredit;
    }

    public String getCustAddr() {
        return custAddr;
    }

    public void setCustAddr(String custAddr) {
        this.custAddr = custAddr == null ? null : custAddr.trim();
    }

    public String getCustZip() {
        return custZip;
    }

    public void setCustZip(String custZip) {
        this.custZip = custZip == null ? null : custZip.trim();
    }

    public String getCustTel() {
        return custTel;
    }

    public void setCustTel(String custTel) {
        this.custTel = custTel == null ? null : custTel.trim();
    }

    public String getCustFax() {
        return custFax;
    }

    public void setCustFax(String custFax) {
        this.custFax = custFax == null ? null : custFax.trim();
    }

    public String getCustWebsite() {
        return custWebsite;
    }

    public void setCustWebsite(String custWebsite) {
        this.custWebsite = custWebsite == null ? null : custWebsite.trim();
    }

    public String getCustLicenceNo() {
        return custLicenceNo;
    }

    public void setCustLicenceNo(String custLicenceNo) {
        this.custLicenceNo = custLicenceNo == null ? null : custLicenceNo.trim();
    }

    public String getCustChieftain() {
        return custChieftain;
    }

    public void setCustChieftain(String custChieftain) {
        this.custChieftain = custChieftain == null ? null : custChieftain.trim();
    }

    public Integer getCustBankroll() {
        return custBankroll;
    }

    public void setCustBankroll(Integer custBankroll) {
        this.custBankroll = custBankroll;
    }

    public Integer getCustTurnover() {
        return custTurnover;
    }

    public void setCustTurnover(Integer custTurnover) {
        this.custTurnover = custTurnover;
    }

    public String getCustBank() {
        return custBank;
    }

    public void setCustBank(String custBank) {
        this.custBank = custBank == null ? null : custBank.trim();
    }

    public String getCustBankAccount() {
        return custBankAccount;
    }

    public void setCustBankAccount(String custBankAccount) {
        this.custBankAccount = custBankAccount == null ? null : custBankAccount.trim();
    }

    public String getCustLocalTaxNo() {
        return custLocalTaxNo;
    }

    public void setCustLocalTaxNo(String custLocalTaxNo) {
        this.custLocalTaxNo = custLocalTaxNo == null ? null : custLocalTaxNo.trim();
    }

    public String getCustNationalTaxNo() {
        return custNationalTaxNo;
    }

    public void setCustNationalTaxNo(String custNationalTaxNo) {
        this.custNationalTaxNo = custNationalTaxNo == null ? null : custNationalTaxNo.trim();
    }

    public String getCustStatus() {
        return custStatus;
    }

    public void setCustStatus(String custStatus) {
        this.custStatus = custStatus == null ? null : custStatus.trim();
    }

	@Override
	public String toString() {
		return "CstCustomer [custNo=" + custNo + ", custName=" + custName + ", custRegion=" + custRegion
				+ ", custManagerId=" + custManagerId + ", custManagerName=" + custManagerName + ", custLevel="
				+ custLevel + ", custCreateDate=" + custCreateDate + ", custSatisfy=" + custSatisfy + ", custCredit="
				+ custCredit + ", custAddr=" + custAddr + ", custZip=" + custZip + ", custTel=" + custTel + ", custFax="
				+ custFax + ", custWebsite=" + custWebsite + ", custLicenceNo=" + custLicenceNo + ", custChieftain="
				+ custChieftain + ", custBankroll=" + custBankroll + ", custTurnover=" + custTurnover + ", custBank="
				+ custBank + ", custBankAccount=" + custBankAccount + ", custLocalTaxNo=" + custLocalTaxNo
				+ ", custNationalTaxNo=" + custNationalTaxNo + ", custStatus=" + custStatus + "]";
	}
}