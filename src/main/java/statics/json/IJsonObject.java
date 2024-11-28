package statics.json;

import java.util.Set;

//todo comment
public interface IJsonObject {
    public Set<String> getFieldKeys();
    public JsonValue<?> getField(String pKey);
    public void setField(String pKey, JsonValue<?> pVal);
}
