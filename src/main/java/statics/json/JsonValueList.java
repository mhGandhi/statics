package statics.json;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * represents a list of values for a value in a key-value pair
 *
 * @author Adrian Akipi
 */
public class JsonValueList {
    /**
     * values of the list
     */
    private List<JsonValue<?>> values;

    /**
     * type of the values in the list
     */
    private Class<?> type;

    public static JsonValueList fromString(String pListSt) throws InvalidSyntaxException {
        if (!pListSt.startsWith("[") || !pListSt.endsWith("]")) {
            throw new InvalidSyntaxException(pListSt + " not a valid list");
        }
        List<String> fields = JsonObject.separateEntries(pListSt.substring(1, pListSt.length() - 1));

        List<JsonValue<?>> values = new LinkedList<>();
        for (String field : fields) {
            try {
                values.add(JsonValue.fromString(field));
            } catch (UnexpectedValueTypeException e) {
                System.err.println(field + " not a valid value");
            }
        }
        return new JsonValueList(values);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @return array containing the values
     */
    public JsonValue<?>[] getAll() {
        JsonValue<?>[] tmp = new JsonValue<?>[this.values.size()];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = this.values.get(i);
        }
        return tmp;
    }

    /**
     * add a new value to the list of values
     *
     * @param pVal value to add
     */
    public void add(JsonValue<?> pVal) {
        if (pVal == null) {
            throw new NullPointerException();
        }

        //prevent saving lists directly within other lists
        if (pVal.get() instanceof JsonValueList) {
            throw new UnexpectedValueTypeException("Nesting lists directly within lists is not allowed");
        }

        //logic for adding first element
        if (this.values.isEmpty()) {
            this.type = pVal.get().getClass();//set type for the list
            this.values.add(pVal);
            return;
        }

        //check whether value is of the right type
        if (!type.equals(pVal.get().getClass())) {
            throw new UnexpectedValueTypeException(
                    this + " only supports type " + type.getName()
            );
        }
        this.values.add(pVal);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * default constructor initializes value list
     */
    public JsonValueList() {
        this.values = new ArrayList<>();
    }

    public JsonValueList(List<JsonValue<?>> pFields) throws UnexpectedValueTypeException {
        this();
        for (JsonValue<?> val : pFields) {
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
    public String toString(int pIndent) {
        StringBuilder str = new StringBuilder();
        str.append(JsonObject.indent(pIndent)).append("[\n");
        for (int i = 0; i < this.values.size(); i++) {
            str.append(JsonObject.indent(pIndent + 1)).append(this.values.get(i).toString(pIndent + 1));
            if (i < this.values.size() - 1) {
                str.append(",\n");
            }
        }
        str.append('\n').append(JsonObject.indent(pIndent)).append(']');

        return str.toString();
    }
}
