package com.duo.medical.ui.consultation;

import java.io.Serializable;

public class ChatItemModel implements Serializable {
    public static final int CHAT_A=1001;
    public static final int CHAT_B=1002;
    public int type;
    public Object object;

    public ChatItemModel(int type,Object object){
        this.type=type;
        this.object=object;
    }
}
