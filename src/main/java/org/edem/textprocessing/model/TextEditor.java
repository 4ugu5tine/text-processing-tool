package org.edem.textprocessing.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TextEditor {
    private List<String> content;

    public TextEditor() {
        content = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "TextEditor{" +
                "content=" + content +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextEditor that = (TextEditor) o;
        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(content);
    }
}
