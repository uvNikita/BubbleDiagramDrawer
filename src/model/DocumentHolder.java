package model;

import java.util.List;

public interface DocumentHolder {
    List<Bubble> getCurrentDocument();

    void setCurrentDocument(List<Bubble> doc);
}
