package statics.json;

import java.util.*;

//todo comment
public class JsonObject {
    private static final List<Class<?>> SUPPORTED_VALUE_TYPES = List.of(
            Double.class,
            String.class,
            Boolean.class,
            JsonObject.class,
            JsonValueList.class
    );
    Map<String,JsonValue<?>> fields;

    public Set<String> getFieldKeys(){
        return this.fields.keySet();
    }
    public JsonValue<?> getField(String pKey){
        return this.fields.get(pKey);
    }
    public void setField(String pKey, JsonValue<?> pVal){
        this.fields.put(pKey, pVal);
    }

    public JsonObject(){
        this.fields = new TreeMap<>();
    }

    public static JsonObject fromString(String pJson){
        return null;
    }

    public String toString(){
        return null;
    }

    public String formatJsonString(String pUnformattedString){
        return null;
    }

    private class JsonValueList{
        private List<JsonValue<?>> values;
        private Class<?> type;

        public JsonValue<?>[] getAll(){
            JsonValue<?>[] tmp = new JsonValue<?>[this.values.size()];
            for (int i = 0; i<tmp.length;i++){
                tmp[i] = this.values.get(i);
            }
            return tmp;
        }
        public void add(JsonValue<?> pVal){
            if(pVal == null){
                throw new NullPointerException();
            }

            if (pVal.get() instanceof JsonValueList) {
                throw new WrongValueTypeException("Nesting lists directly within lists is not allowed");
            }

            if(this.values.isEmpty()){
                this.type = pVal.get().getClass();
                this.values.add(pVal);
                return;
            }

            if (!type.equals(pVal.get().getClass())) {
                throw new WrongValueTypeException(
                        this+ " only supports type " + type.getName()
                );
            }
            this.values.add(pVal);
        }

        public JsonValueList(){
            this.values = new ArrayList<>();
        }
    }

    public class JsonValue<T>{
        private T value;

        public T get(){
            return this.value;
        }
        public void set(T pValue){
            if(!JsonObject.SUPPORTED_VALUE_TYPES.contains(pValue.getClass()))
                throw new UnsupportedValueTypeException("type "+pValue.getClass()+" not supported for json");
            this.value = pValue;
        }

        public JsonValue(T pInitValue){
            if(!JsonObject.SUPPORTED_VALUE_TYPES.contains(pInitValue.getClass()))
                throw new UnsupportedValueTypeException("type "+pInitValue.getClass()+" not supported for json");
            this.value = pInitValue;
        }
    }

}
