package com.bext.behavioral.state.stephen;

import java.io.IOException;

abstract class State {
    public String info = "abstract State";
    abstract public void edit();
    abstract public void save() throws IOException;
}

