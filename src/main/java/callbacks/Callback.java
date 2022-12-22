package callbacks;

public interface Callback<T> {
    void onSuccess(T value);
    void onFailure(T value);
}