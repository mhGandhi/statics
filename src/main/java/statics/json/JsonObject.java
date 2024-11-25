package statics.json;

import java.util.*;

//todo comment

/**
 * java representation of a json object, so the object can be accessed within java
 *
 * @author Adrian Akipi
 */
public class JsonObject {
    /**
     * list of all types json fields support in this implementation
     */
    private static final List<Class<?>> SUPPORTED_VALUE_TYPES = List.of(
            Double.class,
            String.class,
            Boolean.class,
            JsonObject.class,
            JsonValueList.class
    );

    /**
     * all fields of this json object
     */
    Map<String,JsonValue<?>> fields;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return set of all keys of the key-value pairs making up the fields if this field
     */
    public Set<String> getFieldKeys(){
        return this.fields.keySet();
    }

    /**
     * @param pKey key of the field of the value to get
     * @return desired value
     */
    public JsonValue<?> getField(String pKey){
        return this.fields.get(pKey);
    }

    /**
     * creates new field or modifies existing one
     * @param pKey key of the new field
     * @param pVal value of the new field
     */
    public void setField(String pKey, JsonValue<?> pVal){
        this.fields.put(pKey, pVal);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * default constructor instantiates fields
     */
    public JsonObject(){
        this.fields = new TreeMap<>();
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * converts a string into its respective JsonObject
     * @param pJson json in string form (whitespaces ignored)
     * @return json in JsonObject form
     * @throws InvalidSyntaxException thrown if @pJson can not be parsed
     */
    public static JsonObject fromString(String pJson)throws InvalidSyntaxException{
        return null;//todo // s&p https://www.json.org/json-de.html
    }

    /**
     * generates unformatted string representation of a JsonObject
     * @return string representation
     */
    public String toString(){
        return null;//todo
    }

    /**
     * formats a given Json with newlines and whitespaces
     * @param pUnformattedString string to format
     * @return formatted json
     */
    public static String formatJsonString(String pUnformattedString)/*throws InvalidSyntaxException???*/{
        return null;//todo
    }

////
    /**
     * represents a list of values for a value in a key-value pair
     */
    private class JsonValueList{
        /**
         * values of the list
         */
        private List<JsonValue<?>> values;

        /**
         * type of the values in the list
         */
        private Class<?> type;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /**
         * @return array containing the values
         */
        public JsonValue<?>[] getAll(){
            JsonValue<?>[] tmp = new JsonValue<?>[this.values.size()];
            for (int i = 0; i<tmp.length;i++){
                tmp[i] = this.values.get(i);
            }
            return tmp;
        }

        /**
         * add a new value to the list of values
         * @param pVal value to add
         */
        public void add(JsonValue<?> pVal){
            if(pVal == null){
                throw new NullPointerException();
            }

            if (pVal.get() instanceof JsonValueList) {
                throw new UnexpectedValueTypeException("Nesting lists directly within lists is not allowed");
            }

            if(this.values.isEmpty()){
                this.type = pVal.get().getClass();
                this.values.add(pVal);
                return;
            }

            if (!type.equals(pVal.get().getClass())) {
                throw new UnexpectedValueTypeException(
                        this+ " only supports type " + type.getName()
                );
            }
            this.values.add(pVal);
        }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        public JsonValueList(){
            this.values = new ArrayList<>();
        }
    }
////
    public class JsonValue<T>{
        private T value;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        public T get(){
            return this.value;
        }

        public void set(T pValue){
            if(!JsonObject.SUPPORTED_VALUE_TYPES.contains(pValue.getClass()))
                throw new UnsupportedValueTypeException("type "+pValue.getClass()+" not supported for json");
            this.value = pValue;
        }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        public JsonValue(T pInitValue){
            if(!JsonObject.SUPPORTED_VALUE_TYPES.contains(pInitValue.getClass()))
                throw new UnsupportedValueTypeException("type "+pInitValue.getClass()+" not supported for json");
            this.value = pInitValue;
        }
    }

}
