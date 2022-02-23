package model;

import java.util.List;

public class Email {
    private List<String> language;
    private List<Integer> pageSize;
    private boolean spamFilter;
    private String signature;

    public Email() {
    }

    public Email(List<String> language, List<Integer> pageSize, boolean spamFilter, String signature) {
        this.language = language;
        this.pageSize = pageSize;
        this.spamFilter = spamFilter;
        this.signature = signature;
    }

    public List<String> getLanguage() {
        return language;
    }

    public void setLanguage(List<String> language) {
        this.language = language;
    }

    public List<Integer> getPageSize() {
        return pageSize;
    }

    public void setPageSize(List<Integer> pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isSpamFilter() {
        return spamFilter;
    }

    public void setSpamFilter(boolean spamFilter) {
        this.spamFilter = spamFilter;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
