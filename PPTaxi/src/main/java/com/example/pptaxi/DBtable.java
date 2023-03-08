package com.example.pptaxi;

public class DBtable {
    private Integer CodeID;
    private String TbFIO;
    private String TbVoz;
    private String TbSt;
    private String TbNS;
    private String TbMN;
    private String TbGosN;
    private String TbNum;

    public DBtable(Integer CodeID, String TbFIO, String TbVoz, String TbSt, String TbNS, String TbMN, String TbGosN, String TbNum) {
        this.CodeID = CodeID;
        this.TbFIO = TbFIO;
        this.TbVoz = TbVoz;
        this.TbSt = TbSt;
        this.TbNS = TbNS;
        this.TbMN = TbMN;
        this.TbGosN = TbGosN;
        this.TbNum = TbNum;
    }

    public DBtable() {

    }
    public Integer getIDCode() {
        return CodeID;
    }

    public Integer getCodeID() {
        return CodeID;
    }

    public void setCodeID(Integer CodeID) {
        this.CodeID = CodeID;
    }

    public String getTbFIO() {
        return TbFIO;
    }

    public void setTbFIO(String tbFIO) {
        this.TbFIO = tbFIO;
    }

    public String getTbVoz() {
        return TbVoz;
    }

    public void setTbVoz(String TbVoz) {
        this.TbVoz = TbVoz;
    }

    public String getTbSt() {
        return TbSt;
    }

    public void setTbSt(String TbSt) {
        this.TbSt = TbSt;
    }

    public String getTbNS() {
        return TbNS;
    }

    public void setTbNS(String TbNS) {
        this.TbNS = TbNS;
    }

    public String getTbMN() {
        return TbMN;
    }

    public void setTbMN(String TbMN) {
        this.TbMN = TbMN;
    }

    public String getTbGosN() {
        return TbGosN;
    }

    public void setTbGosN(String TbGosN) {
        this.TbGosN = TbGosN;
    }

    public String getTbNum() {
        return TbNum;
    }

    public void setTbNum(String TbNum) {
        this.TbNum = TbNum;
    }
}
