package com.jf_eam_project.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;

/**
 * Created by think on 2015/12/25.
 * 货币
 *
 */
@JsonType
public class Currency extends Entity {
    private static final String TAG = "Currency";
    private static final long serialVersionUID = 2015050105L;
    @JsonField(fieldName = "currencycode")
    public String currencycode;//货币
    @JsonField(fieldName = "description")
    public String description;//描述

    public String getCurrencycode() {
        return currencycode;
    }

    public void setCurrencycode(String currencycode) {
        this.currencycode = currencycode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
