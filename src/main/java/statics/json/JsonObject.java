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

    public JsonObject(Map<String,JsonValue<?>> pFields){
        this();
        for(String key:pFields.keySet()){
            this.setField(key,pFields.get(key));
        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * converts a string into its respective JsonObject
     * @param pJson json in string form (whitespaces ignored)
     * @return json in JsonObject form
     * @throws InvalidSyntaxException thrown if @pJson can not be parsed
     */
    public static JsonObject fromString(String pJson)throws InvalidSyntaxException{
        pJson = stripWhitespace(pJson);
        if(pJson.startsWith("{") && pJson.endsWith("}")){
            List<String> fields = separateEntries(pJson.substring(1,pJson.length()-1));
            return new JsonObject(fieldsFromStrings(fields));
        }else{
            throw new InvalidSyntaxException(pJson+" not a valid object");
        }
    }

    private static List<String> separateEntries(String pEntries) {
        List<String> entries = new LinkedList<String>();

        int lastFieldEnd = 0;

        int inOtherObject = 0;
        int inList = 0;
        boolean inString = false;
        for(int i = 0; i<pEntries.length();i++) {
            if (pEntries.charAt(i) == '"' && (i==0 || pEntries.charAt(i - 1) != '\\')) {
                inString = !inString;
            }
            if (!inString) {
                if (pEntries.charAt(i) == '{') {
                    inOtherObject++;
                }
                if (pEntries.charAt(i) == '}') {
                    inOtherObject--;
                }

                if (pEntries.charAt(i) == '[') {
                    inList++;
                }
                if (pEntries.charAt(i) == ']') {
                    inList--;
                }
            }


            if (pEntries.charAt(i) == ',' && inOtherObject == 0 && inList == 0 && !inString) {
                entries.add(pEntries.substring(lastFieldEnd, i));
                lastFieldEnd = i + 1;
            }
            if (i == pEntries.length() - 1) {
                entries.add(pEntries.substring(lastFieldEnd));
            }
        }
        return entries;
    }

    private static Map<String,JsonValue<?>> fieldsFromStrings(List<String> fields){
        Map<String,JsonValue<?>> ret = new TreeMap<>();
        for(String field : fields){
            String key = null;


            if(!field.startsWith("\""))throw new InvalidSyntaxException(field+" does not start with a key");
            int i;
            for (i = 1; i < field.length(); i++) {
                if(field.charAt(i)=='"'){
                    key=field.substring(1,i);
                    break;
                }
            }


            if(field.charAt(i+1)!=':')throw new InvalidSyntaxException(field+" not separated by =");
            String valueSt = field.substring(i+2);
            //finally parsing the value
            JsonValue<?> value;
            try{
                value = JsonValue.fromString(valueSt);
            }catch(InvalidSyntaxException e){
                value = null;
            }


            if(value != null && key != null){
                ret.put(key,value);
            }
        }
        return ret;
    }

    /**
     * {@inheritDoc}
     * generates formatted string representation of a JsonObject
     * @return string representation
     */
    public String toString(){
        return this.toString(0);
    }

    /**
     * {@inheritDoc}
     * generates formatted string representation of a JsonObject
     * @param pIndent current indentation
     * @return string representation
     */
    public String toString(int pIndent){
        StringBuilder ret = new StringBuilder();
        ret.append(indent(pIndent)).append("{\n");
        int i = 0;
        for (String key : this.fields.keySet()){
            ret.append(indent(pIndent+1));
            ret.append('"').append(key).append('"').append(":");
            ret.append(getField(key).toString(pIndent+1));
            if(i<this.fields.keySet().size()-1)
                ret.append(",\n");
            i++;
        }
        ret.append('\n').append(indent(pIndent)).append('}');

        return ret.toString();
    }

    /**
     * generates indent of specified width
     * @param pWidth width of the indent
     * @return resulting indent
     */
    private static String indent(int pWidth){
        return new String(new char[pWidth]).replace("\0", "\t");
    }

    private static String stripWhitespace(String pToStrip){
        StringBuilder str = new StringBuilder(pToStrip);
        boolean inString = false;
        int removals = 0;
        for (int i = 0; i < pToStrip.length(); i++) {
            if(pToStrip.charAt(i)== '"'&&(i==0||pToStrip.charAt(i-1)!='\\')){
                inString = !inString;
            }
            if(!inString && Character.isWhitespace(pToStrip.charAt(i))){
                str.deleteCharAt(i-removals);
                removals++;
            }
        }

        return str.toString();
    }

////
    /**
     * represents a list of values for a value in a key-value pair
     */
    public static class JsonValueList{
        /**
         * values of the list
         */
        private List<JsonValue<?>> values;

        /**
         * type of the values in the list
         */
        private Class<?> type;

        public static JsonValueList fromString(String pListSt)throws InvalidSyntaxException {
            if(!pListSt.startsWith("[")||!pListSt.endsWith("]")){
                throw new InvalidSyntaxException(pListSt+ " not a valid list");
            }
            List<String> fields = separateEntries(pListSt.substring(1,pListSt.length()-1));

            List<JsonValue<?>> values = new LinkedList<>();
            for (String field:fields){
                try{
                    values.add(JsonValue.fromString(field));
                }catch(UnexpectedValueTypeException e) {
                    System.err.println(field + " not a valid value");
                }
            }
            return new JsonValueList(values);
        }

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

            //prevent saving lists directly within other lists
            if (pVal.get() instanceof JsonValueList) {
                throw new UnexpectedValueTypeException("Nesting lists directly within lists is not allowed");
            }

            //logic for adding first element
            if(this.values.isEmpty()){
                this.type = pVal.get().getClass();//set type for the list
                this.values.add(pVal);
                return;
            }

            //check whether value is of the right type
            if (!type.equals(pVal.get().getClass())) {
                throw new UnexpectedValueTypeException(
                        this+ " only supports type " + type.getName()
                );
            }
            this.values.add(pVal);
        }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         * default constructor initializes value list
         */
        public JsonValueList(){
            this.values = new ArrayList<>();
        }

        public JsonValueList(List<JsonValue<?>> pFields) throws UnexpectedValueTypeException{
            this();
            for (JsonValue<?> val : pFields){
                this.add(val);
            }
        }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return toString(0);
        }

        /**
         * @param pIndent indent to add to the list
         * @return string
         */
        public String toString(int pIndent){
            StringBuilder str = new StringBuilder();
            str.append(indent(pIndent)).append("[\n");
            for (int i = 0; i < this.values.size(); i++) {
                str.append(indent(pIndent + 1)).append(this.values.get(i).toString(pIndent + 1));
                if(i<this.values.size()-1){
                    str.append(",\n");
                }
            }
            str.append('\n').append(indent(pIndent)).append(']');

            return str.toString();
        }
    }
////

    /**
     * shell for values of json fields
     * @param <T> type of the value
     */
    public static class JsonValue<T>{
        /**
         * value held by the associated field
         */
        private T value;

        public static JsonValue<?> fromString(String valueSt) {
            if(valueSt.startsWith("[")&&valueSt.endsWith("]")){
                return new JsonValue<>(JsonValueList.fromString(valueSt));
            }
            if(valueSt.startsWith("{")&&valueSt.endsWith("}")){
                return new JsonValue<>(JsonObject.fromString(valueSt));
            }
            if(valueSt.startsWith("\"")&&valueSt.endsWith("\"")&&valueSt.length()>1){
                return new JsonValue<>(valueSt.substring(1,valueSt.length()-1));
            }
            if((valueSt.equals("true")||valueSt.equals("false"))){
                boolean res;
                res = valueSt.equals("true");
                return new JsonValue<>(res);
            }

            try {
                return new JsonValue<>(Double.parseDouble(valueSt));
            } catch (NumberFormatException e) {
                throw new InvalidSyntaxException(valueSt+" not a valid value");
            }
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /**
         * @return value
         */
        public T get(){
            return this.value;
        }

        /**
         * @param pValue new value
         * @throws UnsupportedValueTypeException thrown, if the WOMP //todo when instantiated no longer ambiguous?
         */
        public void set(T pValue)throws UnsupportedValueTypeException{
            if(!JsonObject.SUPPORTED_VALUE_TYPES.contains(pValue.getClass()))
                throw new UnsupportedValueTypeException("type "+pValue.getClass()+" not supported for json");
            this.value = pValue;
        }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         * constructor ensures the initial value is of a supported type defined in JsonObject
         * @param pInitValue initial value; also defines type
         */
        public JsonValue(T pInitValue){
            if(!JsonObject.SUPPORTED_VALUE_TYPES.contains(pInitValue.getClass()))
                throw new UnsupportedValueTypeException("type "+pInitValue.getClass()+" not supported for json");
            this.value = pInitValue;
        }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return this.toString(0);
        }

        /**
         * @param pIndent indent to add to the string if it represents an object
         * @return string
         */
        public String toString(int pIndent) {
            String ret = null;

            if(this.get().getClass().equals(String.class)){
                ret = '"'+this.get().toString()+'"';
            }
            if(this.get() instanceof JsonObject tmp){
                ret ='\n'+tmp.toString(pIndent+1);
            }
            if(this.get() instanceof JsonValueList tmp){
                ret ='\n'+tmp.toString(pIndent+1);
            }
            if(ret == null){
                ret = this.get().toString();
            }

            return ret;
        }
    }

}
