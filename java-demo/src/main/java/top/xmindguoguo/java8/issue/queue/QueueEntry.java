package top.xmindguoguo.java8.issue.queue;

public class QueueEntry<T> {
    private String key;
    private T model;

    public QueueEntry() {
        super();
    }

    public QueueEntry(String key, T model) {
        super();
        this.key = key;
        this.model = model;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }
}