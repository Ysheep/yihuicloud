package com.qzdsoft.vo;

import java.util.HashMap;

public class TemplateItem extends HashMap<String,Item>{
	private static final long serialVersionUID = -3728490424738325020L;
	
	public TemplateItem() {}

    public TemplateItem(String key, Item item) {
        this.put(key, item);
    }
}
