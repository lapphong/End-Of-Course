package com.dlp.lapphong.bt3;

public class History {
    private int id;
    private String codeFROM,codeTO;
    private String input,result,date;

    public History(int id, String codeFROM, String codeTO, String input, String result, String date) {
        this.id = id;
        this.codeFROM = codeFROM;
        this.codeTO = codeTO;
        this.input = input;
        this.result = result;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCodeFROM() {
        return codeFROM;
    }

    public void setCodeFROM(String codeFROM) {
        this.codeFROM = codeFROM;
    }

    public String getCodeTO() {
        return codeTO;
    }

    public void setCodeTO(String codeTO) {
        this.codeTO = codeTO;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
