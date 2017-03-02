package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;

/**
 * Created by think on 2015/12/25.
 * 联系人
 *
 */
@JsonType
public class Compcontact extends Entity {
    private static final String TAG = "Compcontact";
    private static final long serialVersionUID = 2015050105L;
    @JsonField(fieldName = "company")
    public String company;//公司
    @JsonField(fieldName = "contact")
    public String contact; //联系人
    @JsonField(fieldName = "voicephone")
    public String voicephone;//电话

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getVoicephone() {
        return voicephone;
    }

    public void setVoicephone(String voicephone) {
        this.voicephone = voicephone;
    }
}
