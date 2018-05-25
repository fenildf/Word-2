package com.example.fragmentbestpractice;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class Pullwords implements wordparser {

    @Override
    public List<word> parse(InputStream is) throws Exception {
        List<word> words =null;
        word  word =null;
        XmlPullParser parser = Xml.newPullParser();//xml解析
        parser.setInput(is,"UTF-8");
        int eventType =parser.getEventType();
        while(eventType!= XmlPullParser.END_DOCUMENT){
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                    words=new ArrayList<word>();
                    break;
                case XmlPullParser.START_TAG:
                    if(parser.getName().equals("item")){
                        word =new word();
                    }else if(parser.getName().equals("word")){
                        eventType = parser.next();
                        word.setWord(parser.getText());
                    }else if(parser.getName().equals("trans")){
                        eventType =parser.next();
                        word.setTrans(parser.getText());
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if(parser.getName().equals("item")){
                        words.add(word);
                        word=null;
                    }
                    break;
            }
            eventType =parser.next();
        }
        return words;
    }

    //序列化
    @Override
    public String seriallize(List<word> words) throws Exception {
        XmlSerializer serializer = Xml.newSerializer();
        StringWriter writer =new StringWriter();
        serializer.setOutput(writer);
        serializer.startDocument("UTF-8",true);
        serializer.startTag("","wordbook");
        for(word word :words){
            serializer.startTag("","item");
            serializer.startTag("","word");
            serializer.text(word.getWord());
            serializer.endTag("","word");

            serializer.startTag("","trans");
            serializer.text(word.getTrans());
            serializer.endTag("","trans");
        }
        serializer.endTag("","wordbook");
        serializer.endDocument();
        return writer.toString();
    }
}
