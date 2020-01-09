package com.bext.structural.adapter.approachwiki;

import java.util.HashMap;
import java.util.Map;

/*      K1           K2           value
        From         To           Adapter
        ClassA       ClassB       ClassAtoClassBAdapter
        ClassA       ClassAJson   ClassAtoClassAAdapter
        ClassAJson   ClassA       ClassAJsontoClassA

 */

public class AdapterFactory {
    private AdapterFactory instance = new AdapterFactory();

    Map toMap = new HashMap();
    Map fromMap = new HashMap();

    public AdapterFactory getInstance() {
        return instance;
    }
    public void registerAdapter(){};
    public void getAdapterFromTo(){};
}
