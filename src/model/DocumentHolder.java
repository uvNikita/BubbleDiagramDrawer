package model;

import java.util.ArrayList;

public interface DocumentHolder {
    ArrayList<Bubble> getCurrentDocument();

    void setCurrentDocument(ArrayList<Bubble> document);
}
