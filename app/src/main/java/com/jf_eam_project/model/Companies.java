package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;

/**
 * Created by think on 2015/12/25.
 * 供应商
 *
 */
@JsonType
public class Companies extends Entity {
    private static final String TAG = "Companies";
    private static final long serialVersionUID = 2015050105L;
    @JsonField(fieldName = "bankaccount")
    public String bankaccount;
    @JsonField(fieldName = "companiescode")
    public String companiescode;
    @JsonField(fieldName = "company")
    public String company;//公司
    @JsonField(fieldName = "currencycode")
    public String currencycode;//
    @JsonField(fieldName = "name")
    public String name;//描述
    @JsonField(fieldName = "type")
    public String type;//公司类型
    @JsonField(fieldName = "udkhh")
    public String udkhh;

    public String getBankaccount() {
        return bankaccount;
    }

    public void setBankaccount(String bankaccount) {
        this.bankaccount = bankaccount;
    }

    public String getCompaniescode() {
        return companiescode;
    }

    public void setCompaniescode(String companiescode) {
        this.companiescode = companiescode;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCurrencycode() {
        return currencycode;
    }

    public void setCurrencycode(String currencycode) {
        this.currencycode = currencycode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUdkhh() {
        return udkhh;
    }

    public void setUdkhh(String udkhh) {
        this.udkhh = udkhh;
    }
}
