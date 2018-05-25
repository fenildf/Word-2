package com.example.fragmentbestpractice;

import java.io.InputStream;
import java.util.List;

public interface wordparser {
    public List<word> parse(InputStream is)throws Exception;
    public String seriallize(List<word> words)throws Exception;
}
