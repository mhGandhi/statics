package statics.json;

import java.util.*;

//todo comment

/**
 * java representation of a json object, so the object can be accessed within java
 *
 * @author Adrian Akipi
 */
public class JsonObject implements IJsonObject{
    /**
     * list of all types json fields support in this implementation
     */
    public static final List<Class<?>> SUPPORTED_VALUE_TYPES = List.of(
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

    @Override
    public IJsonObject add(IJsonObject pJsonObj) {
        return null;//todo
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

    static List<String> separateEntries(String pEntries) {
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
    static String indent(int pWidth){
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
}