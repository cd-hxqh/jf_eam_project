package com.jf_eam_project.model;


import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;


/**
 * Created by think on 2015/11/26.
 * �ɹ�����
 */
@JsonType
public class PoLine extends Entity {

    private static final String TAG = "PoLine";
    private static final long serialVersionUID = 2015050105L;

    @JsonField(fieldName = "category")
    public String category;//���
    @JsonField(fieldName = "conversion")
    public String conversion;//����ϵ��
    @JsonField(fieldName = "description")
    public String description;//����
    @JsonField(fieldName = "enterby")
    public String enterby;//������
    @JsonField(fieldName = "enterdate")
    public String enterdate;//��������
    @JsonField(fieldName = "itemnum")
    public String itemnum;//��Ŀ
    @JsonField(fieldName = "linecost")
    public String linecost;//����˰�ܼ�
    @JsonField(fieldName = "polinenum")
    public String polinenum;//��
    @JsonField(fieldName = "ponum")
    public String ponum;//�ɹ������
    @JsonField(fieldName = "tax1")
    public String tax1;//˰
    @JsonField(fieldName = "tositeid")
    public String tositeid;//�ص�
    @JsonField(fieldName = "orderunit")
    public String orderunit;//������λ
    @JsonField(fieldName = "orderqty")
    public String orderqty;//����
    @JsonField(fieldName = "unitcosttax")
    public String unitcosttax;//��˰����
    @JsonField(fieldName = "unitcost")
    public String unitcost;//����˰����
    @JsonField(fieldName = "linetype")
    public String linetype;//������
    @JsonField(fieldName = "shiptoattn")
    public String shiptoattn;//������
    @JsonField(fieldName = "requestedby")
    public String requestedby;//������
    @JsonField(fieldName = "storeloc")
    public String storeloc;//�ⷿ

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getConversion() {
        return conversion;
    }

    public void setConversion(String conversion) {
        this.conversion = conversion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEnterby() {
        return enterby;
    }

    public void setEnterby(String enterby) {
        this.enterby = enterby;
    }

    public String getEnterdate() {
        return enterdate;
    }

    public void setEnterdate(String enterdate) {
        this.enterdate = enterdate;
    }

    public String getItemnum() {
        return itemnum;
    }

    public void setItemnum(String itemnum) {
        this.itemnum = itemnum;
    }

    public String getLinecost() {
        return linecost;
    }

    public void setLinecost(String linecost) {
        this.linecost = linecost;
    }

    public String getPolinenum() {
        return polinenum;
    }

    public void setPolinenum(String polinenum) {
        this.polinenum = polinenum;
    }

    public String getPonum() {
        return ponum;
    }

    public void setPonum(String ponum) {
        this.ponum = ponum;
    }

    public String getTax1() {
        return tax1;
    }

    public void setTax1(String tax1) {
        this.tax1 = tax1;
    }

    public String getTositeid() {
        return tositeid;
    }

    public void setTositeid(String tositeid) {
        this.tositeid = tositeid;
    }

    public String getOrderunit() {
        return orderunit;
    }

    public void setOrderunit(String orderunit) {
        this.orderunit = orderunit;
    }

    public String getOrderqty() {
        return orderqty;
    }

    public void setOrderqty(String orderqty) {
        this.orderqty = orderqty;
    }

    public String getUnitcosttax() {
        return unitcosttax;
    }

    public void setUnitcosttax(String unitcosttax) {
        this.unitcosttax = unitcosttax;
    }

    public String getUnitcost() {
        return unitcost;
    }

    public void setUnitcost(String unitcost) {
        this.unitcost = unitcost;
    }

    public String getLinetype() {
        return linetype;
    }

    public void setLinetype(String linetype) {
        this.linetype = linetype;
    }

    public String getShiptoattn() {
        return shiptoattn;
    }

    public void setShiptoattn(String shiptoattn) {
        this.shiptoattn = shiptoattn;
    }

    public String getRequestedby() {
        return requestedby;
    }

    public void setRequestedby(String requestedby) {
        this.requestedby = requestedby;
    }

    public String getStoreloc() {
        return storeloc;
    }

    public void setStoreloc(String storeloc) {
        this.storeloc = storeloc;
    }
}
