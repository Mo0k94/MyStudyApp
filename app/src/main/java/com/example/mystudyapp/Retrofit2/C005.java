package com.example.mystudyapp.Retrofit2;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class C005 {

    @Override
    public String toString() {
        return "C005{" +
                "result=" + RESULT +
                ", totalCount='" + total_count + '\'' +
                ", row=" + row +
                '}';
    }

    @SerializedName("RESULT")
    @Expose
    private RESULT RESULT;
    @SerializedName("total_count")
    @Expose
    private String total_count;
    @SerializedName("row")
    @Expose
    private List<Row> row = null;

    public RESULT getRESULT() {
        return RESULT;
    }

    public void setRESULT(RESULT RESULT) {
        this.RESULT = RESULT;
    }

    public String getTotalCount() {
        return total_count;
    }

    public void setTotalCount(String total_count) {
        this.total_count = total_count;
    }

    public List<Row> getRow() {
        return row;
    }

    public void setRow(List<Row> row) {
        this.row = row;
    }



    public class RESULT {

        @SerializedName("MSG")
        @Expose
        private String MSG;
        @SerializedName("CODE")
        @Expose
        private String CODE;

        public String getMSG() {
            return MSG;
        }

        public void setMSG(String MSG) {
            this.MSG = MSG;
        }

        public String getCODE() {
            return CODE;
        }

        public void setCODE(String CODE) {
            this.CODE = CODE;
        }

        @Override
        public String toString() {
            return "RESULT{" +
                    "mSG='" + MSG + '\'' +
                    ", cODE='" + CODE + '\'' +
                    '}';
        }
    }



    public class Row {

        @SerializedName("BAR_CD")
        @Expose
        private String bARCD;
        @SerializedName("PRDLST_NM")
        @Expose
        private String pRDLSTNM;
        @SerializedName("PRDLST_DCNM")
        @Expose
        private String pRDLSTDCNM;
        @SerializedName("PRMS_DT")
        @Expose
        private String pRMSDT;
        @SerializedName("BSSH_NM")
        @Expose
        private String bSSHNM;
        @SerializedName("CLSBIZ_DT")
        @Expose
        private String cLSBIZDT;
        @SerializedName("INDUTY_NM")
        @Expose
        private String iNDUTYNM;
        @SerializedName("SITE_ADDR")
        @Expose
        private String sITEADDR;
        @SerializedName("POG_DAYCNT")
        @Expose
        private String pOGDAYCNT;
        @SerializedName("END_DT")
        @Expose
        private String eNDDT;
        @SerializedName("PRDLST_REPORT_NO")
        @Expose
        private String pRDLSTREPORTNO;

        public String getBARCD() {
            return bARCD;
        }

        public void setBARCD(String bARCD) {
            this.bARCD = bARCD;
        }

        public String getPRDLSTNM() {
            return pRDLSTNM;
        }

        public void setPRDLSTNM(String pRDLSTNM) {
            this.pRDLSTNM = pRDLSTNM;
        }

        public String getPRDLSTDCNM() {
            return pRDLSTDCNM;
        }

        public void setPRDLSTDCNM(String pRDLSTDCNM) {
            this.pRDLSTDCNM = pRDLSTDCNM;
        }

        public String getPRMSDT() {
            return pRMSDT;
        }

        public void setPRMSDT(String pRMSDT) {
            this.pRMSDT = pRMSDT;
        }

        public String getBSSHNM() {
            return bSSHNM;
        }

        public void setBSSHNM(String bSSHNM) {
            this.bSSHNM = bSSHNM;
        }

        public String getCLSBIZDT() {
            return cLSBIZDT;
        }

        public void setCLSBIZDT(String cLSBIZDT) {
            this.cLSBIZDT = cLSBIZDT;
        }

        public String getINDUTYNM() {
            return iNDUTYNM;
        }

        public void setINDUTYNM(String iNDUTYNM) {
            this.iNDUTYNM = iNDUTYNM;
        }

        public String getSITEADDR() {
            return sITEADDR;
        }

        public void setSITEADDR(String sITEADDR) {
            this.sITEADDR = sITEADDR;
        }

        public String getPOGDAYCNT() {
            return pOGDAYCNT;
        }

        public void setPOGDAYCNT(String pOGDAYCNT) {
            this.pOGDAYCNT = pOGDAYCNT;
        }

        public String getENDDT() {
            return eNDDT;
        }

        public void setENDDT(String eNDDT) {
            this.eNDDT = eNDDT;
        }

        public String getPRDLSTREPORTNO() {
            return pRDLSTREPORTNO;
        }

        public void setPRDLSTREPORTNO(String pRDLSTREPORTNO) {
            this.pRDLSTREPORTNO = pRDLSTREPORTNO;
        }

        @Override
        public String toString() {
            return "Row{" +
                    "bARCD='" + bARCD + '\'' +
                    ", pRDLSTNM='" + pRDLSTNM + '\'' +
                    ", pRDLSTDCNM='" + pRDLSTDCNM + '\'' +
                    ", pRMSDT='" + pRMSDT + '\'' +
                    ", bSSHNM='" + bSSHNM + '\'' +
                    ", cLSBIZDT='" + cLSBIZDT + '\'' +
                    ", iNDUTYNM='" + iNDUTYNM + '\'' +
                    ", sITEADDR='" + sITEADDR + '\'' +
                    ", pOGDAYCNT='" + pOGDAYCNT + '\'' +
                    ", eNDDT='" + eNDDT + '\'' +
                    ", pRDLSTREPORTNO='" + pRDLSTREPORTNO + '\'' +
                    '}';
        }
    }

}



