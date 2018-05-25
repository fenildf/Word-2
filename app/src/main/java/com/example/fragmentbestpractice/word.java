package com.example.fragmentbestpractice;

import org.litepal.crud.DataSupport;

public class word extends DataSupport{
    private String word;
    private String trans;
    public String getWord(){
        return word;
    }
    public String getTrans(){
        return trans;
    }
    public void setWord(String word){
        this.word=word;
    }
    public void setTrans(String trans){
        this.trans=trans;
    }
}
