package com.gsnotes.services;

import java.util.List;

import com.gsnotes.bo.Element;

public interface IElementService {
public  List<Element> getElementByModule(long IdModule);
}
